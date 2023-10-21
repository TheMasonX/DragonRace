package com.tmxgames.dragonrace.block;

import com.tmxgames.dragonrace.DragonRace;
import com.tmxgames.dragonrace.item.ModItems;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DragonRace.MODID);

    public static final int DRAGON_BLOCK_LIGHT = 8;

    public static final RegistryObject<Block> DRAGON_BLOCK = BLOCKS.register("dragon_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .color(MaterialColor.NETHER).explosionResistance(1000).jumpFactor(10).speedFactor(2).destroyTime(3.1415f).friction(0.5f)
                    .strength(3).requiresCorrectToolForDrops()
                    .lightLevel(value -> DRAGON_BLOCK_LIGHT) .isRedstoneConductor((state, getter, pos) -> true)
            ));

    public static final RegistryObject<Block> DRAGON_FRUIT_CROP = BLOCKS.register("dragon_fruit_crop",
            () -> new DragonFruitCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static void register(IEventBus eventBus)
    {
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
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}
