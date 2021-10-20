package com.github.manafia.ctf.util;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

import static com.github.manafia.ctf.CTF.instance;

public class Utils {


    public static FileConfiguration config = instance.getConfig();

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static int ctfBlockDurability = config.getInt("CTF.Block-Durability");

    public static Location faceEntity(Location location, Entity entity) {
        Vector direction = location.toVector().subtract(entity.getLocation().toVector());
        direction.multiply(-1);
        location.setDirection(direction);
        return location;
    }


    public static void spawnAnimation(Player p, Block block){
        ArmorStand armorStand = block.getLocation().getWorld().spawn(
                Utils.faceEntity((block.getLocation().add(0.5, 0.5, 0.5)), p), ArmorStand.class);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setBasePlate(false);

        armorStand.setHelmet(constructArmorSkull());
        new BukkitRunnable() {
            int frames = 0;

            public void run() {
                if (frames > 30) {
                    this.cancel();
                    armorStand.remove();
                    HandlerList.unregisterAll((Listener) instance);
                    return;
                }
                if (frames > 10) {
                    Location newLocation = armorStand.getLocation().add(0, 0.2, 0);
                    newLocation.setYaw(newLocation.getYaw() + 18f);
                    armorStand.teleport(newLocation);
                }
                p.spigot().playEffect(armorStand.getLocation().clone().add(0, 0.5, 0), Effect.getByName(Utils.config.getString("CTF.Particle-Name")),
                        0, 0, 0, 0, 0,
                        0, 3, 15);
                frames++;
            }
        }.runTaskTimer(instance, 0, 1L);
    }

    public static ItemStack constructArmorSkull() {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        skullMeta.setDisplayName(null);
        itemStack.setItemMeta(skullMeta);

        new SkullProfile(Utils.config.getString("CTF.Animation-Skull-Hash"))
                .applyTextures(itemStack);

        return itemStack;
    }
    
}
