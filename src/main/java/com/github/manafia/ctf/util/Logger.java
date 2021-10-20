package com.github.manafia.ctf.util;

import com.github.manafia.ctf.CTF;
import org.bukkit.ChatColor;

public class Logger {

    public static void print(String message, PrefixType type) {
        CTF.instance.getServer().getConsoleSender().sendMessage(type.getPrefix() + message);
    }

    public enum PrefixType {

        WARNING(ChatColor.RED + "WARNING: "), NONE(""), DEFAULT(ChatColor.GOLD + "[CTF] "), FAILED(ChatColor.RED + "FAILED: ");

        private final String prefix;

        PrefixType(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return this.prefix;
        }

    }

}
