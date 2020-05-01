package me.refrac.sophos.handlers;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import me.refrac.sophos.Core;

public class Check implements Listener {
	
    private String Identifier;
    private String Name;
    private Core Core;
    private boolean Enabled = true;

    public Check(String Identifier, String Name, Core Sophos) {
        this.Name = Name;
        this.Core = Sophos;
        this.Identifier = Identifier;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public boolean isEnabled() {
        return this.Enabled;
    }

    public void setEnabled(boolean Enabled) {
        if (Core.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled") != Enabled
                && Core.getConfig().get("Checks." + this.getIdentifier() + ".enabled") != null) {
            this.Enabled = Core.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled");
            return;
        }
        if (Enabled) {
            if (!isEnabled()) {
                this.Core.registerListener(this);;
            }
        } else if (isEnabled()) {
            HandlerList.unregisterAll(this);
        }
        this.Enabled = Enabled;
    }

    public Core getSophos() {
        return this.Core;
    }

    public void checkValues() {
        if (Core.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled")) {
            this.setEnabled(true);
        } else {
            this.setEnabled(false);
        }
    }

    public String getName() {
        return this.Name;
    }

    public String getIdentifier() {
        return this.Identifier;
    }
}