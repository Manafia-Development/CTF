package com.github.manafia.ctf.util;

import org.bukkit.ChatColor;

public enum Message {

    NO_PERMISSION("no-permission", "&c&l[!] &7You do not have permission."),
    NOT_PLAYER("must-be-player", "&c&l[!] &7You must be a player to use this command!"),
    PLAYER_NOT_FOUND("player-not-found", "&c&l[!] &b%player% &7is not online!"),
    CTF_RELOADED("ctf.reloaded", "&2&l[!] &7Reloaded Configs...."),
    CTF_COMMAND_HELP("ctf.command-help", "&c&l[!] &7Try using &b/ctf claim &7 or &b/ctf info&7."),
    CTF_BLOCK_SET("ctf.block-set", "&c&l[!] &2You have successfully set the ctf point!"),
    CTF_LOCATION_INFO("ctf.location-info", "&c&l[!] &7The location of the point is &b{x}, {y}, {z}, {world}"),
    CTF_STARTED_BROADCAST("ctf.started-broadcast", "&c&l[!] &b&lCTF » &7Started! Location: {x}, {y}, {z}, {world}"),
    CTF_REDEEMING_PRIZES("ctf.redeeming-prizes", "&2&l[!] &7Redeeming Event Prizes..."),
    CTF_NO_WINS("ctf.no-wins", "&c&l[!] &7You do not have any winnings to claim!"),
    CTF_WINNER_BROADCAST("ctf.winner-broadcast", "&c&l[!] &b{player} &7has won the CTF Event!"),
    CTF_MINE_NOTIFICATION("ctf.mine-notification", "&c&l[!] &7You must mine the point &b&l{count} &7more times!"),
    CTF_NOT_ACTIVE("ctf.not-active", "&c&l[!] &7There is currently no CTF event taking place!");

    String config, message;

    Message(String config, String message) {
        this.config = config;
        this.message = ChatColor.translateAlternateColorCodes('&', message);
    }

    public String getConfig() {
        return config;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
