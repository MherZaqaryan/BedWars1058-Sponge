package me.mherzaqaryan.sponge.listeners;

import me.mherzaqaryan.sponge.Sponge;
import me.mherzaqaryan.sponge.tasks.SpongeAnimationTask;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class SpongePlaceListener implements Listener {

    @EventHandler
    public void onSpongePlace(BlockPlaceEvent e) {
        if (e.isCancelled()) return;
        if (!Sponge.getBedWars().getArenaUtil().isPlaying(e.getPlayer())) return;
        if (!e.getBlock().getType().equals(Material.SPONGE)) return;
        new SpongeAnimationTask(e.getBlock()).runTaskTimer(Sponge.getInstance(), 0L, 8L);
    }

}
