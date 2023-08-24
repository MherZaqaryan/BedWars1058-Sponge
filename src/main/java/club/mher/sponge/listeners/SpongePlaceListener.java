package club.mher.sponge.listeners;

import club.mher.sponge.Sponge;
import club.mher.sponge.tasks.SpongeAnimationTask;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class SpongePlaceListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onSpongePlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (!Sponge.getBedWars().isPlaying(player)) {
            return;
        }
        Block block = e.getBlock();
        if (block.getType() != Material.SPONGE) {
            return;
        }
        new SpongeAnimationTask(block).runTaskTimer(Sponge.getInstance(), 0L, 8L);
    }

}
