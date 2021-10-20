package com.github.manafia.ctf.manager;

import com.github.manafia.ctf.CTF;
import com.github.manafia.ctf.util.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Set;

public class BlockManager {

    public static void setBlockLocation(Player p) {
        Block block = p.getTargetBlock((Set<Material>) null, 100);
        Location l = block.getLocation();
        double x = l.getX();
        double y = l.getY();
        double z = l.getZ();
        String world = l.getWorld().getName();
        Utils.config.set("CTF.Block-Location.x", x);
        Utils.config.set("CTF.Block-Location.y", y);
        Utils.config.set("CTF.Block-Location.z", z);
        Utils.config.set("CTF.Block-Location.world", world);
    }

    public static boolean getBlockLocation(Block block) {
        double x = Utils.config.getDouble("CTF.Block-Location.x");
        double y = Utils.config.getDouble("CTF.Block-Location.y");
        double z = Utils.config.getDouble("CTF.Block-Location.z");
        String world = Utils.config.getString("CTF.Block-Location.world");
        double xBlock = block.getLocation().getX();
        double yBlock = block.getLocation().getY();
        double zBlock = block.getLocation().getZ();
        String wWorld = block.getLocation().getWorld().getName();
        return x == xBlock && y == yBlock && z == zBlock && world.equals(wWorld);
    }
}
