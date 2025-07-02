package net.wijk.chest.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.entry.RegistryEntry;
import net.wijk.chest.config.ChestConfigInitializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class AllEnchantmentsMixin {

    @Inject(at = @At("HEAD"), method = "canBeCombined", cancellable = true)
    private static void injectCanBeCombined(RegistryEntry<Enchantment> first, RegistryEntry<Enchantment> second, CallbackInfoReturnable<Boolean> cir) {
        if (!ChestConfigInitializer.getAllEnchantments())
            return;

        cir.setReturnValue(true);
    }
}