package com.tmxgames.dragonrace.entity.phases;
import com.tmxgames.dragonrace.entity.NonaDragon;
import com.tmxgames.dragonrace.entity.NonaDragonPhase;

import javax.annotation.Nullable;

import com.tmxgames.dragonrace.entity.NonaDragon;
import com.tmxgames.dragonrace.entity.NonaDragonPhase;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.AbstractDragonPhaseInstance;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;
import net.minecraft.world.phys.Vec3;

public class NonaDragonLandingPhase extends AbstractNonaDragonPhaseInstance {
    @Nullable
    private Vec3 targetLocation;

    public NonaDragonLandingPhase(NonaDragon dragon) {
        super(dragon);
    }

    public void doClientTick() {
        Vec3 vec3 = this.dragon.getHeadLookVector(1.0F).normalize();
        vec3.yRot((-(float)Math.PI / 4F));
        double d0 = this.dragon.head.getX();
        double d1 = this.dragon.head.getY(0.5D);
        double d2 = this.dragon.head.getZ();

        for(int i = 0; i < 8; ++i) {
            RandomSource randomsource = this.dragon.getRandom();
            double d3 = d0 + randomsource.nextGaussian() / 2.0D;
            double d4 = d1 + randomsource.nextGaussian() / 2.0D;
            double d5 = d2 + randomsource.nextGaussian() / 2.0D;
            Vec3 vec31 = this.dragon.getDeltaMovement();
            this.dragon.level.addParticle(ParticleTypes.DRAGON_BREATH, d3, d4, d5, -vec3.x * (double)0.08F + vec31.x, -vec3.y * (double)0.3F + vec31.y, -vec3.z * (double)0.08F + vec31.z);
            vec3.yRot(0.19634955F);
        }

    }

    public void doServerTick() {
        if (this.targetLocation == null) {
            this.targetLocation = Vec3.atBottomCenterOf(this.dragon.level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.END_PODIUM_LOCATION));
        }

        if (this.targetLocation.distanceToSqr(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ()) < 1.0D) {
            this.dragon.getPhaseManager().getPhase(NonaDragonPhase.SITTING_FLAMING).resetFlameCount();
            this.dragon.getPhaseManager().setPhase(NonaDragonPhase.SITTING_SCANNING);
        }

    }

    public float getFlySpeed() {
        return 1.5F;
    }

    public float getTurnSpeed() {
        float f = (float)this.dragon.getDeltaMovement().horizontalDistance() + 1.0F;
        float f1 = Math.min(f, 40.0F);
        return f1 / f;
    }

    public void begin() {
        this.targetLocation = null;
    }

    @Nullable
    public Vec3 getFlyTargetLocation() {
        return this.targetLocation;
    }

    public NonaDragonPhase<NonaDragonLandingPhase> getPhase() {
        return NonaDragonPhase.LANDING;
    }
}