package com.github.manafia.ctf;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.github.manafia.ctf.commands.CmdCTF;
import com.github.manafia.ctf.file.CustomFile;
import com.github.manafia.ctf.file.impl.MessageFile;
import com.github.manafia.ctf.listeners.BlockBreakListener;
import com.github.manafia.ctf.manager.Winner;
import com.github.manafia.ctf.util.DataFile;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public class CTF extends JavaPlugin implements Listener {
    public static CTF instance;
    public DataFile winnerFile;

    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        getDataFolder().mkdirs();
        Collections.singletonList(new MessageFile()).forEach(CustomFile::init);
        getWorldGuard();
        winnerFile = new DataFile(this, "winners");
        getCommand("ctf").setExecutor(new CmdCTF());
        getCommand("ctf").setTabCompleter(new CmdCTF());
        this.getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        Winner.winnerNames.addAll(winnerFile.getStringList("winners"));
    }

    public WorldGuardPlugin getWorldGuard() {
        Plugin w = this.getServer().getPluginManager().getPlugin("WorldGuard");
        if (w == null || !(w instanceof WorldGuardPlugin)) {
            return null;
        }
        return (WorldGuardPlugin) w;
    }
}
