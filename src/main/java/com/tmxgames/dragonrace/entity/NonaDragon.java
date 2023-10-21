package com.tmxgames.dragonrace.entity;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.entity.PartEntity;

import javax.annotation.Nullable;

public class NonaDragon extends EnderDragon {

    public NonaDragon(EntityType<? extends EnderDragon> entityType, Level level) {
        super(entityType, level);
        //for (PartEntity<?> part : this.getParts())
        //{
        //}
    }

    public static AttributeSupplier setAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH,     260)
                .add(Attributes.ATTACK_DAMAGE,  3f)
                .add(Attributes.ATTACK_SPEED,   1.5f)
                .add(Attributes.MOVEMENT_SPEED, 2.0f)
                .add(Attributes.FLYING_SPEED,   15.0f)
                .add(Attributes.LUCK,           2)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 1f)
                .build();
    }

    @Override
    public boolean canRide(Entity entity) {
        return true;
    }

    @Override
    public boolean canBeLeashed(Player entity) { return  true; }

    @Override
    public boolean canChangeDimensions() {
        return true;
    }
}
