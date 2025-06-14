package net.wijk.chest;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
//import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = WijkChest.MOD_ID)
public class ChestModConfig implements ConfigData {
    public boolean zombifiedPiglinXP = true;

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