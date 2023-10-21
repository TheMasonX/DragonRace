package com.tmxgames.dragonrace.entity.phases;
import com.tmxgames.dragonrace.entity.NonaDragon;
import com.tmxgames.dragonrace.entity.NonaDragonPhase;

import net.minecraft.sounds.SoundEvents;

public class NonaDragonSittingAttackingPhase extends AbstractNonaDragonSittingPhase {
    private static final int ROAR_DURATION = 40;
    private int attackingTicks;

    public NonaDragonSittingAttackingPhase(NonaDragon dragon) {
        super(dragon);
    }

    public void doClientTick() {
        this.dragon.level.playLocalSound(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ(), SoundEvents.ENDER_DRAGON_GROWL, this.dragon.getSoundSource(), 2.5F, 0.8F + this.dragon.getRandom().nextFloat() * 0.3F, false);
    }

    public void doServerTick() {
        if (this.attackingTicks++ >= 40) {
            this.dragon.getPhaseManager().setPhase(NonaDragonPhase.SITTING_FLAMING);
        }

    }

    public void begin() {
        this.attackingTicks = 0;
    }

    public NonaDragonPhase<NonaDragonSittingAttackingPhase> getPhase() {
        return NonaDragonPhase.SITTING_ATTACKING;
    }
}
