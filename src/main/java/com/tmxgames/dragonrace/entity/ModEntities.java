package com.tmxgames.dragonrace.entity;

import com.tmxgames.dragonrace.DragonRace;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DragonRace.MODID);

    public static final RegistryObject<EntityType<NonaDragon>> NONA_DRAGON = ENTITY_TYPES.register("nona_dragon",
            () -> EntityType.Builder.of(NonaDragon::new, MobCategory.CREATURE)
                    .sized(0.5f,0.75f)
                    .build(new ResourceLocation(DragonRace.MODID, "textures/entity/nona_dragon/dragon.png").toString()));

    public static void register(IEventBus eventBus)
    {
        ENTITY_TYPES.register(eventBus);
    }
}
