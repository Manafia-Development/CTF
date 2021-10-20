package com.github.manafia.ctf.util;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class JsonComponentText {

    public static void sendCommandMsg(Player p, String msg, String tooltip, String command) {
        TextComponent message = new TextComponent(msg);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + command));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(tooltip).create()));
        p.spigot().sendMessage(message);
    }

    public static void sendHoverText(Player p, String msg, String tooltip) {
        TextComponent message = new TextComponent(msg);
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(tooltip).create()));
        p.spigot().sendMessage(message);
    }

    public static void sendUrlMessage(Player p, String msg, String tooltip, String url) {
        TextComponent message = new TextComponent(msg);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(tooltip).create()));
        p.spigot().sendMessage(message);
    }
}
