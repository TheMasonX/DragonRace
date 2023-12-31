package com.tmxgames.dragonrace.entity.phases;
import com.tmxgames.dragonrace.entity.NonaDragon;
import com.tmxgames.dragonrace.entity.NonaDragonPhase;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.AbstractDragonPhaseInstance;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

public class NonaDragonTakeoffPhase extends AbstractNonaDragonPhaseInstance {
    private boolean firstTick;
    @Nullable
    private Path currentPath;
    @Nullable
    private Vec3 targetLocation;

    public NonaDragonTakeoffPhase(NonaDragon p_31370_) {
        super(p_31370_);
    }

    public void doServerTick() {
        if (!this.firstTick && this.currentPath != null) {
            BlockPos blockpos = this.dragon.level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.END_PODIUM_LOCATION);
            if (!blockpos.closerToCenterThan(this.dragon.position(), 10.0D)) {
                this.dragon.getPhaseManager().setPhase(NonaDragonPhase.HOLDING_PATTERN);
            }
        } else {
            this.firstTick = false;
            this.findNewTarget();
        }

    }

    public void begin() {
        this.firstTick = true;
        this.currentPath = null;
        this.targetLocation = null;
    }

    private void findNewTarget() {
        int i = this.dragon.findClosestNode();
        Vec3 vec3 = this.dragon.getHeadLookVector(1.0F);
        int j = this.dragon.findClosestNode(-vec3.x * 40.0D, 105.0D, -vec3.z * 40.0D);
        j -= 12;
        j &= 7;
        j += 12;

        this.currentPath = this.dragon.findPath(i, j, (Node) null);
        this.navigateToNextPathNode();
    }

    private void navigateToNextPathNode() {
        if (this.currentPath != null) {
            this.currentPath.advance();
            if (!this.currentPath.isDone()) {
                Vec3i vec3i = this.currentPath.getNextNodePos();
                this.currentPath.advance();

                double d0;
                do {
                    d0 = (double)((float)vec3i.getY() + this.dragon.getRandom().nextFloat() * 20.0F);
                } while(d0 < (double)vec3i.getY());

                this.targetLocation = new Vec3((double)vec3i.getX(), d0, (double)vec3i.getZ());
            }
        }

    }

    @Nullable
    public Vec3 getFlyTargetLocation() {
        return this.targetLocation;
    }

    public NonaDragonPhase<NonaDragonTakeoffPhase> getPhase() {
        return NonaDragonPhase.TAKEOFF;
    }
}