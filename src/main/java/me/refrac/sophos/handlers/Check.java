package me.refrac.sophos.handlers;

import me.refrac.sophos.Sophos;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public class Check implements Listener {
	
    private String Identifier;
    private String Name;
    private Sophos Sophos;
    private boolean Enabled = true;

    public Check(String Identifier, String Name, Sophos Sophos) {
        this.Name = Name;
        this.Sophos = Sophos;
        this.Identifier = Identifier;
    }

    public boolean isEnabled() {
        return this.Enabled;
    }

    public void setEnabled(boolean Enabled) {
        if (Sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled") != Enabled
                && Sophos.getConfig().get("Checks." + this.getIdentifier() + ".enabled") != null) {
            this.Enabled = Sophos.getConfig().getBoolean("Checks." + this.getIdentifier() + ".enabled");
            return;
        }
        if (Enabled) {
            if (!isEnabled()) {
                this.Sophos.registerListener(this);;
            }
        } else if (isEnabled()) {
            HandlerList.unregisterAll(this);
        }
        this.Enabled = Enabled;
    }

    public Sophos getSophos() {
        return this.Sophos;
    }

    public String getName() {
        return this.Name;
    }

    public String getIdentifier() {
        return this.Identifier;
    }
}