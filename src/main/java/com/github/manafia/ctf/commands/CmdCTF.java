package com.github.manafia.ctf.commands;

import com.github.manafia.ctf.CTF;
import com.github.manafia.ctf.manager.BlockManager;
import com.github.manafia.ctf.manager.Winner;
import com.github.manafia.ctf.util.Message;
import com.github.manafia.ctf.util.Utils;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CmdCTF implements CommandExecutor, TabCompleter {
    public static boolean isRunning = false;
    public Winner winners = new Winner();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        FileConfiguration config = CTF.instance.getConfig();
        if (cmd.getName().equalsIgnoreCase("ctf")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length == 0) {
                    p.sendMessage(Message.CTF_COMMAND_HELP.getMessage());
                    return true;
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    if (!p.hasPermission(Utils.config.getString("CTF.Admin-Permission"))) {
                        p.sendMessage(Message.NO_PERMISSION.getMessage());
                        return true;
                    }
                    p.sendMessage(Message.CTF_RELOADED.getMessage());
                    CTF.instance.reloadConfig();
                    return true;
                }
                if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("setblock")) {
                    if (!p.hasPermission(Utils.config.getString("CTF.Admin-Permission"))) {
                        p.sendMessage(Message.NO_PERMISSION.getMessage());
                        return true;
                    }
                    p.sendMessage(Message.CTF_BLOCK_SET.getMessage());
                    BlockManager.setBlockLocation(p);
                    CTF.instance.saveConfig();
                    return true;
                } else if (args[0].equalsIgnoreCase("claim") || args[0].equalsIgnoreCase("redeem")) {
                    if (!Winner.winnerNames.contains(p.getName())) {
                        p.sendMessage(Message.CTF_NO_WINS.getMessage());
                        winners.setWinnersToFile();
                        return true;
                    }
                    p.sendMessage(Message.CTF_REDEEMING_PRIZES.getMessage());
                    Winner.runCommands(p);
                    Winner.winnerNames.remove(p.getName());
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("start")) {
                if (!sender.hasPermission(Utils.config.getString("CTF.Admin-Permission"))) {
                    sender.sendMessage(Message.NO_PERMISSION.getMessage());
                    return true;
                }
                Bukkit.broadcastMessage(Message.CTF_STARTED_BROADCAST.getMessage().replace("{x}", String.valueOf(config.getDouble("CTF.Block-Location.x")))
                        .replace("{y}", String.valueOf(config.getDouble("CTF.Block-Location.y")))
                        .replace("{z}", String.valueOf(config.getDouble("CTF.Block-Location.z")))
                        .replace("{world}", String.valueOf(config.getString("CTF.Block-Location.world"))));
                return CmdCTF.isRunning = true;
            } else if (args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("location")) {
                sender.sendMessage(Message.CTF_LOCATION_INFO.getMessage().replace("{x}", String.valueOf(config.getDouble("CTF.Block-Location.x")))
                        .replace("{y}", String.valueOf(config.getDouble("CTF.Block-Location.y")))
                        .replace("{z}", String.valueOf(config.getDouble("CTF.Block-Location.z")))
                        .replace("{world}", String.valueOf(config.getString("CTF.Block-Location.world"))));
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> arguments = Arrays.asList("reload", "info", "start", "claim", "redeem", "set", "setblock");
        List<String> Flist = Lists.newArrayList();
        if (args.length == 1) {
            for (String s : arguments) {
                if (s.toLowerCase().startsWith(args[0].toLowerCase())) Flist.add(s);
            }
            return Flist;
        }
        return null;
    }
}
