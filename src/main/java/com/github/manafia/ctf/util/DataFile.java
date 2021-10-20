package com.github.manafia.ctf.util;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DataFile {
    private File file;
    private YamlConfiguration configuration;

    public DataFile(JavaPlugin plugin, String name) {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        if (!this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdir();
        }
        plugin.saveResource(name + ".yml", false);
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void load() {
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void save() {
        try {
            this.configuration.save(this.file);
        } catch (IOException var2) {
            var2.printStackTrace();
        }
    }

    public YamlConfiguration getConfiguration() {
        return this.configuration;
    }

    public double getDouble(String path) {
        return this.configuration.contains(path) ? this.configuration.getDouble(path) : 0.0;
    }

    public int getInt(String path) {
        return this.configuration.contains(path) ? this.configuration.getInt(path) : 0;
    }

    public Set getConfigurationSection(String path, boolean deep) {
        return this.configuration.contains(path) ? this.configuration.getConfigurationSection(path).getKeys(deep) : null;
    }

    public boolean getBoolean(String path) {
        return this.configuration.contains(path) && this.configuration.getBoolean(path);
    }

    public String getString(String path) {
        return this.configuration.contains(path) ? ChatColor.translateAlternateColorCodes('&', this.configuration.getString(path)) : "";
    }

    public List<String> getStringList(String path) {
        if (this.configuration.contains(path)) {
            ArrayList<String> strings = new ArrayList<>();
            this.configuration.getStringList(path).forEach(string -> strings.add(ChatColor.translateAlternateColorCodes('&', string)));
            return strings;
        }
        return Collections.singletonList("Invalid path.");
    }
}
