package net.creative.tutorialmod.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoods {

    // Nutrition and Saturation
    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f).build();
    public static final FoodProperties HONEY_BERRIES = new FoodProperties.Builder().nutrition(2).saturationModifier(0.15f).build();

    // Eat/Drink, Duration of Eat/Drink, Effect, Probability
    //      * Consume Always Method???
    public static final Consumable STRAWBERRY_CONSUMABLE = Consumables.defaultFood()
            .consumeSeconds(1f).onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 200), 0.15f)).build();


}