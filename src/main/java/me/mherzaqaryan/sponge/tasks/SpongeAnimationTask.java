package me.mherzaqaryan.sponge.tasks;

import me.mherzaqaryan.sponge.Sponge;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SpongeAnimationTask extends BukkitRunnable {

    private final Block block;
    private final Location loc;

    private int radius = 1;
    private float pitch = 1.7F;

    public SpongeAnimationTask(Block block) {
        this.block = block;
        this.loc = block.getLocation();
    }

    @Override
    public void run() {
        if (radius > 4) {
            cancel();
            block.setType(Material.AIR);
            loc.getWorld().playSound(loc, Sound.valueOf(Sponge.getSplash()), 1, 1);
            return;
        }
        getParticles(loc, radius).forEach(loc -> Sponge.getParticleSupport().play(loc));
        loc.getWorld().playSound(loc, Sound.valueOf(Sponge.getWoodClick()), 1.0F, pitch);
        radius++;
        pitch++;
    }

    public List<Location> getParticles(Location loc, int radius) {
        List<Location> result = new ArrayList<>();
        Block start = loc.getWorld().getBlockAt(loc);
        int iterations = (radius * 2) + 1;
        List<Block> blocks = new ArrayList<>(iterations * iterations * iterations);
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    blocks.add(start.getRelative(x, y, z));
                }
            }
        }
        blocks.stream().filter(b -> b.getType().equals(Material.AIR)).forEach(b -> result.add(b.getLocation()));
        return result;
    }

}
