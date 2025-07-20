package net.oliver.forgemod.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.oliver.forgemod.entity.ModEntities;
import net.oliver.forgemod.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class SandSnailEntity extends Animal {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState curlAnimationState = new AnimationState(); // New animation state for curling
    private int idleAnimationTimeout = 0;
    private int panicTicks = 0;
    private static final EntityDataAccessor<Boolean> IS_PANICKED = SynchedEntityData.defineId(SandSnailEntity.class, EntityDataSerializers.BOOLEAN);

    public SandSnailEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_PANICKED, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this,2.0));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.is(ModItems.NIGHT_BERRIES.get()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.05)
                .add(Attributes.FOLLOW_RANGE, 10);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(ModItems.NIGHT_BERRIES.get());
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        // Set panic when hurt
        this.panicTicks = 120; // Panic for 6 seconds when hurt
        return super.hurt(pSource, pAmount);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.SANDSNAIL.get().create(pLevel);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 60;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    public boolean isPanicked() {
        return this.entityData.get(IS_PANICKED);
    }

    private void setPanicked(boolean panicked) {
        this.entityData.set(IS_PANICKED, panicked);
        if (panicked) {
            this.idleAnimationState.stop();
            this.curlAnimationState.start(this.tickCount);
        } else {
            this.curlAnimationState.stop();
            this.idleAnimationState.start(this.tickCount);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }

        // Check for nearby players and set panic
        if (!this.level().isClientSide()) {
            Player nearestPlayer = this.level().getNearestPlayer(this, 8.0);
            if (nearestPlayer != null && nearestPlayer.distanceToSqr(this) < 64.0) {
                this.panicTicks = 60; // Panic for 3 seconds (60 ticks)
            }
        }

        // Manage panic ticks
        if (this.panicTicks > 0) {
            this.panicTicks--;
            if (!this.isPanicked()) {
                this.setPanicked(true);
            }
        } else if (this.isPanicked()) {
            this.setPanicked(false);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("PanicTicks", this.panicTicks);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.panicTicks = tag.getInt("PanicTicks");
    }
}