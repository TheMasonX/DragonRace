package com.tmxgames.dragonrace.entity.phases;
import com.tmxgames.dragonrace.entity.NonaDragon;
import com.tmxgames.dragonrace.entity.NonaDragonPhase;

import javax.annotation.Nullable;
import net.minecraft.world.phys.Vec3;

public class NonaDragonHoverPhase extends AbstractNonaDragonPhaseInstance {
    @Nullable
    private Vec3 targetLocation;

    public NonaDragonHoverPhase(NonaDragon dragon) {
        super(dragon);
    }

    public void doServerTick() {
        if (this.targetLocation == null) {
            this.targetLocation = this.dragon.position();
        }

    }

    public boolean isSitting() {
        return true;
    }

    public void begin() {
        this.targetLocation = null;
    }

    public float getFlySpeed() {
        return 1.0F;
    }

    @Nullable
    public Vec3 getFlyTargetLocation() {
        return this.targetLocation;
    }

    public NonaDragonPhase<NonaDragonHoverPhase> getPhase() {
        return NonaDragonPhase.HOVERING;
    }
}
