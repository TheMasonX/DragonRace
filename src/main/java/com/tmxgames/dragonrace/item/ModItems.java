package com.tmxgames.dragonrace.item;

import com.tmxgames.dragonrace.DragonRace;
import com.tmxgames.dragonrace.block.ModBlocks;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DragonRace.MODID);

    public static final RegistryObject<Item> DRAGON_BLOCK_ITEM = ITEMS.register("dragon_block",
            () -> new BlockItem(ModBlocks.DRAGON_BLOCK.get(),
                    new Item.Properties().tab(DragonRace.MOD_TAB)
                            .stacksTo(64)
                            .fireResistant()
                            .rarity(Rarity.EPIC)
            ));

    public static final RegistryObject<Item> DRAGON_ORE = ITEMS.register("dragon_ore",
            () -> new Item(new Item.Properties().tab(DragonRace.MOD_TAB)
                            .stacksTo(64)
                            .fireResistant()
                            .rarity(Rarity.EPIC)
            ));

    public static final RegistryObject<Item> DRAGON_FRUIT = ITEMS.register("dragon_fruit",
            () -> new Item(new Item.Properties().tab(DragonRace.MOD_TAB)
                    .food(new FoodProperties.Builder().nutrition(4).saturationMod(2)
                            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 500, 2), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 500, 2), 1.0F).build())
                    .fireResistant()
                    .stacksTo(16)
            ));

    public static final RegistryObject<Item> DRAGON_FRUIT_SEEDS = ITEMS.register("dragon_fruit_seeds",
            () -> new ItemNameBlockItem(ModBlocks.DRAGON_FRUIT_CROP.get(), new Item.Properties().tab(DragonRace.MOD_TAB)));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
