package com.kryeit.utils.forge;

import net.minecraft.world.item.CreativeModeTab;

public class ItemUtilsImpl {
    public static int nextTabId() {
        return CreativeModeTab.getGroupCountSafe();
    }
}
