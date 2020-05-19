package me.refrac.sophos;

import me.refrac.sophos.handlers.Check;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class SophosAPI {

    private static Sophos sophos;
    private Plugin plugin;

    public SophosAPI(Plugin plugin) {
        this.plugin = plugin;
        sophos = (Sophos) plugin;
    }

    public static List<Check> getChecks() {
        return sophos.getChecks();
    }
}