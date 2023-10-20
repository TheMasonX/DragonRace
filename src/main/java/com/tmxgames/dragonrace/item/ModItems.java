package com.tmxgames.dragonrace.item;

import com.tmxgames.dragonrace.DragonRace;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems
{
    public static final ModCreativeTab MOD_TAB = new ModCreativeTab(CreativeModeTab.TABS.length, DragonRace.MODID);
    public static  final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DragonRace.MODID);
    public static  final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DragonRace.MODID);

    public static final RegistryObject<Block> DRAGON_BLOCK = BLOCKS.register("dragon_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .color(MaterialColor.NETHER).explosionResistance(1000).jumpFactor(10).speedFactor(2).destroyTime(3.1415f)
            ));

    public static final RegistryObject<Item> DRAGON_BLOCK_ITEM = ITEMS.register("dragon_block",
            () -> new BlockItem(DRAGON_BLOCK.get(),
                    new Item.Properties().tab(MOD_TAB)
                            .stacksTo(64)
                            .fireResistant()
                            .rarity(Rarity.EPIC)
            ));

    public static final RegistryObject<Item> DRAGON_FRUIT = ITEMS.register("dragon_fruit",
            () -> new Item(new Item.Properties().tab(MOD_TAB)
                    .food(new FoodProperties.Builder().nutrition(4).saturationMod(2)
                            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 500, 2), 1.0F).build())
                    .fireResistant()
                    .stacksTo(16)
            ));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab)
    {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static class ModCreativeTab extends CreativeModeTab {
        public ModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(DRAGON_BLOCK.get());
        }
    }
}
