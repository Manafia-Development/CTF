package com.github.manafia.ctf.manager;

import com.github.manafia.ctf.CTF;
import com.github.manafia.ctf.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Winner {
    public static List<String> winnerNames = new ArrayList<>();

    public static void runCommands(Player p) {
        for (String cmd : Utils.config.getStringList("CTF.Winning-Commands")) {
            cmd = cmd.replace("{player}", p.getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
        }
    }

    public void setWinnersToFile() {
        CTF.instance.winnerFile.getConfiguration().set("winners", winnerNames);
        CTF.instance.winnerFile.save();
    }
}
