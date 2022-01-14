package me.spikey.redstoneremover;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Main extends JavaPlugin implements Listener {
    private boolean blockAll = true;

    private List<Material> blacklist = Lists.newArrayList();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        blockAll = getConfig().getBoolean("blockall");

        for (String s : getConfig().getStringList("blacklist")) {
            blacklist.add(Material.valueOf(s.toUpperCase()));
        }


        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void restone(BlockRedstoneEvent event) {
        if (blockAll) {
            event.setNewCurrent(0);
            return;
        }

        if (blacklist.contains(event.getBlock().getType())) event.setNewCurrent(0);
    }
}
