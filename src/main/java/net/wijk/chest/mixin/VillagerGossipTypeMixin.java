package net.wijk.chest.mixin;

import net.minecraft.village.VillagerGossipType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.wijk.chest.config.ChestConfigInitializer.getVillagerMultipleCuresTrade;

// Original code from here https://github.com/No-Eul/rscd
@Mixin(VillagerGossipType.class)
public class VillagerGossipTypeMixin {
	@ModifyArgs(method = "<clinit>", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/village/VillagerGossipType;<init>(Ljava/lang/String;ILjava/lang/String;IIII)V"
	))

	private static void modifyArgs(Args args) {
        if(!getVillagerMultipleCuresTrade())
            return;

		switch (args.<String>get(2)) {
			case "minor_positive" -> args.set(4, 200);
			case "major_positive" -> {
				args.set(4, 100);
				args.set(6, 100);
			}
		}
	}
}
