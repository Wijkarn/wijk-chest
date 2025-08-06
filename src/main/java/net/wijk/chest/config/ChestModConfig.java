package net.wijk.chest.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.wijk.chest.WijkChest;

@Config(name = WijkChest.MOD_ID)
public class ChestModConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public boolean zombifiedPiglinXP = false;
    @ConfigEntry.Gui.Tooltip
    public boolean allEnchantments = false;
    @ConfigEntry.Gui.Tooltip(count = 2)
    public boolean villagerMultipleCuresTrade = false;

    /*
    @ConfigEntry.Gui.CollapsibleObject
    public InnerStuff stuff = new InnerStuff();

    @ConfigEntry.Gui.Excluded
    public InnerStuff invisibleStuff = new InnerStuff();

    public static class InnerStuff {
        int a = 0;
        int b = 1;
    }
    */
}