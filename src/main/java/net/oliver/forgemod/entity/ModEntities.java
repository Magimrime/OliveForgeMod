package net.oliver.forgemod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.oliver.forgemod.ForgeMod;
import net.oliver.forgemod.entity.custom.SandSnailEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ForgeMod.MOD_ID);

    public static final RegistryObject<EntityType<SandSnailEntity>> SANDSNAIL =
            ENTITY_TYPES.register("sand_snail", () -> EntityType.Builder.of(SandSnailEntity::new, MobCategory.CREATURE)
                    .sized(0.45f, 0.45f).build("sand_snail"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
