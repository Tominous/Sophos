/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos;

import me.refrac.sophos.handlers.Check;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class SophosAPI {

    private static Sophos sophos;

    public SophosAPI(Plugin plugin) {
        sophos = (Sophos)plugin;
    }

    /**
     * @return All of the current checks/chat filters within Sophos.
     * You can create/register your own as well.
     */
    public static List<Check> getChecks() {
        return sophos.getChecks();
    }
}