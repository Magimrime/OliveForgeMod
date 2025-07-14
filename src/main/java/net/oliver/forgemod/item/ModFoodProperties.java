package net.oliver.forgemod.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties KOHLRABI = new FoodProperties.Builder().nutrition(4).saturationModifier(1f)
            .effect(new MobEffectInstance(MobEffects.INVISIBILITY, 200), 0.2f).build();
}
