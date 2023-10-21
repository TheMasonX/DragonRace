package com.tmxgames.dragonrace.entity;

import com.tmxgames.dragonrace.DragonRace;
import net.minecraft.client.renderer.entity.EnderDragonRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import org.jetbrains.annotations.NotNull;

public class NonaDragonRenderer extends EnderDragonRenderer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(DragonRace.MODID, "textures/entity/nona_dragon/dragon.png");

    public NonaDragonRenderer(EntityRendererProvider.Context context) {
        super(context);
        DragonRace.LOGGER.debug("NonaDragonRenderer Constructor!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @Override
    public ResourceLocation getTextureLocation(@NotNull EnderDragon nonaDragon) {
        DragonRace.LOGGER.debug("NonaDragonRenderer getTextureLocation!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return TEXTURE;
    }
}
