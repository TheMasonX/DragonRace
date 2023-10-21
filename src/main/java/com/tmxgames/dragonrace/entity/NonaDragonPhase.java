package com.tmxgames.dragonrace.entity;

import java.lang.reflect.Constructor;
import java.util.Arrays;

import com.tmxgames.dragonrace.entity.phases.NonaDragonChargePlayerPhase;
import com.tmxgames.dragonrace.entity.phases.*;
import net.minecraft.world.entity.boss.enderdragon.phases.*;

public class NonaDragonPhase<T extends NonaDragonPhaseInstance> {
    private static NonaDragonPhase<?>[] phases = new NonaDragonPhase[0];
    public static final NonaDragonPhase<NonaDragonHoldingPatternPhase> HOLDING_PATTERN = create(NonaDragonHoldingPatternPhase.class, "HoldingPattern");
    public static final NonaDragonPhase<NonaDragonStrafePlayerPhase> STRAFE_PLAYER = create(NonaDragonStrafePlayerPhase.class, "StrafePlayer");
    public static final NonaDragonPhase<NonaDragonLandingApproachPhase> LANDING_APPROACH = create(NonaDragonLandingApproachPhase.class, "LandingApproach");
    public static final NonaDragonPhase<NonaDragonLandingPhase> LANDING = create(NonaDragonLandingPhase.class, "Landing");
    public static final NonaDragonPhase<NonaDragonTakeoffPhase> TAKEOFF = create(NonaDragonTakeoffPhase.class, "Takeoff");
    public static final NonaDragonPhase<NonaDragonSittingFlamingPhase> SITTING_FLAMING = create(NonaDragonSittingFlamingPhase.class, "SittingFlaming");
    public static final NonaDragonPhase<NonaDragonSittingScanningPhase> SITTING_SCANNING = create(NonaDragonSittingScanningPhase.class, "SittingScanning");
    public static final NonaDragonPhase<NonaDragonSittingAttackingPhase> SITTING_ATTACKING = create(NonaDragonSittingAttackingPhase.class, "SittingAttacking");
    public static final NonaDragonPhase<NonaDragonChargePlayerPhase> CHARGING_PLAYER = create(NonaDragonChargePlayerPhase.class, "ChargingPlayer");
    public static final NonaDragonPhase<NonaDragonDeathPhase> DYING = create(NonaDragonDeathPhase.class, "Dying");
    public static final NonaDragonPhase<NonaDragonHoverPhase> HOVERING = create(NonaDragonHoverPhase.class, "Hover");
    private final Class<? extends NonaDragonPhaseInstance> instanceClass;
    private final int id;
    private final String name;

    private NonaDragonPhase(int p_31394_, Class<? extends NonaDragonPhaseInstance> p_31395_, String p_31396_) {
        this.id = p_31394_;
        this.instanceClass = p_31395_;
        this.name = p_31396_;
    }

    public NonaDragonPhaseInstance createInstance(NonaDragon p_31401_) {
        try {
            Constructor<? extends NonaDragonPhaseInstance> constructor = this.getConstructor();
            return constructor.newInstance(p_31401_);
        } catch (Exception exception) {
            throw new Error(exception);
        }
    }

    protected Constructor<? extends NonaDragonPhaseInstance> getConstructor() throws NoSuchMethodException {
        return this.instanceClass.getConstructor(NonaDragon.class);
    }

    public int getId() {
        return this.id;
    }

    public String toString() {
        return this.name + " (#" + this.id + ")";
    }

    public static NonaDragonPhase<?> getById(int p_31399_) {
        return p_31399_ >= 0 && p_31399_ < phases.length ? phases[p_31399_] : HOLDING_PATTERN;
    }

    public static int getCount() {
        return phases.length;
    }

    private static <T extends NonaDragonPhaseInstance> NonaDragonPhase<T> create(Class<T> p_31403_, String p_31404_) {
        NonaDragonPhase<T> nonaDragonPhase = new NonaDragonPhase<>(phases.length, p_31403_, p_31404_);
        phases = Arrays.copyOf(phases, phases.length + 1);
        phases[nonaDragonPhase.getId()] = nonaDragonPhase;
        return nonaDragonPhase;
    }
}