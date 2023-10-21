package com.tmxgames.dragonrace.entity;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import org.slf4j.Logger;

import javax.annotation.Nullable;

public class NonaDragonPhaseManager {
    private static final Logger LOGGER = LogUtils.getLogger();
    private final NonaDragon dragon;
    private final NonaDragonPhaseInstance[] phases = new NonaDragonPhaseInstance[NonaDragonPhase.getCount()];
    @Nullable
    private NonaDragonPhaseInstance currentPhase;

    public NonaDragonPhaseManager(NonaDragon dragon) {
        this.dragon = dragon;
        this.setPhase(NonaDragonPhase.HOVERING);
    }

    public void setPhase(NonaDragonPhase<?> p_31417_) {
        if (this.currentPhase == null || p_31417_ != this.currentPhase.getPhase()) {
            if (this.currentPhase != null) {
                this.currentPhase.end();
            }

            this.currentPhase = this.getPhase(p_31417_);
            if (!this.dragon.level.isClientSide) {
                this.dragon.getEntityData().set(EnderDragon.DATA_PHASE, p_31417_.getId());
            }

            LOGGER.debug("Dragon is now in phase {} on the {}", p_31417_, this.dragon.level.isClientSide ? "client" : "server");
            this.currentPhase.begin();
        }
    }

    public NonaDragonPhaseInstance getCurrentPhase() {
        return this.currentPhase;
    }

    public <T extends NonaDragonPhaseInstance> T getPhase(NonaDragonPhase<T> p_31419_) {
        int i = p_31419_.getId();
        if (this.phases[i] == null) {
            this.phases[i] = p_31419_.createInstance(this.dragon);
        }

        return (T) this.phases[i];
    }
}
