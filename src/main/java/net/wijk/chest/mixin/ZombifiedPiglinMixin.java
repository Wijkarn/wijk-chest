package net.wijk.chest.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.wijk.chest.config.ChestConfigInitializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Original code from https://github.com/litetex-oss/mcm-zombified-piglin-restore-anger-loot
@Mixin(ZombifiedPiglinEntity.class)
@SuppressWarnings("javabugs:S6320")
public abstract class ZombifiedPiglinMixin {

    @Inject(method = "mobTick",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/ZombieEntity;mobTick(Lnet/minecraft/server/world/ServerWorld;)V"))
    protected void mobTick(final ServerWorld world, final CallbackInfo ci) {
        if (!ChestConfigInitializer.getZombifiedPiglinXP())
            return;

        final ZombifiedPiglinEntity current = (ZombifiedPiglinEntity) (Object) this;
        if (current.hasAngerTime()) {
            current.playerHitTimer = current.age;
        }
    }

    @Inject(method = "setTarget",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/ZombieEntity;setTarget(Lnet/minecraft/entity/LivingEntity;)V"))
    public void setTarget(final LivingEntity target, final CallbackInfo ci) {
        if (!ChestConfigInitializer.getZombifiedPiglinXP())
            return;

        if (target instanceof final PlayerEntity targetPlayer) {
            final ZombifiedPiglinEntity current = (ZombifiedPiglinEntity) (Object) this;
            current.setAttacking(targetPlayer, current.age);
        }
    }
}
