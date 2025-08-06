package net.wijk.chest.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.wijk.chest.WijkChest;

import static net.wijk.chest.WijkChest.*;

public class ChestConfigInitializer {
    public static ChestModConfig modConfig = null;

    public static void initializeConfig() {
        AutoConfig.register(ChestModConfig.class, GsonConfigSerializer::new);
        ChestModConfig config = AutoConfig.getConfigHolder(ChestModConfig.class).getConfig();
        try {
            config.validatePostLoad();
            modConfig = config;
        } catch (ConfigData.ValidationException e) {
            WijkChest.LOGGER.info("Config for " + MOD_ID + " is not correct");
        }
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean getZombifiedPiglinXP() {
        return modConfig != null && modConfig.zombifiedPiglinXP;
    }

    public static boolean getAllEnchantments() {
        return modConfig != null && modConfig.allEnchantments;
    }

    public static boolean getVillagerMultipleCuresTrade() {
        return modConfig != null && modConfig.villagerMultipleCuresTrade;
    }
}
