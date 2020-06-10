/*
 * Copyright (c) Refrac
 * If you have any questions please email refracplaysmc@gmail.com or reach me on Discord
 */

package me.refrac.sophos.handlers;

import org.bukkit.Bukkit;

// Created by Refrac/Zachstyles 5/10/20
public enum Logger {

    NONE('r'), SUCCESS('a'), ERROR('c'), WARNING('e'), INFO('b');

    private char color;

    Logger(char color) { this.color = color; }

    public void out(String message) {
        message = Utils.color(String.format("%s > &%c%s", new Object[] { "&cSophos", Character.valueOf(this.color), message }));
        Bukkit.getConsoleSender().sendMessage(message);
    }
}