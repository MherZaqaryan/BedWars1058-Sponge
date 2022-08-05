package me.mherzaqaryan.sponge.tasks;

import me.mherzaqaryan.sponge.Sponge;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SpongeAnimationTask extends BukkitRunnable {

    private final Location loc;

    private int radius = 1;
    private int pitch = 17;

    @SuppressWarnings("FieldCanBeLocal")
    private final double WATER_RADIUS = 2.5;

    public SpongeAnimationTask(Block block) {
        this.loc = block.getLocation();

        for (double x = ( WATER_RADIUS*-1); x < WATER_RADIUS; x++) {
            for (double y = ( WATER_RADIUS*-1); y < WATER_RADIUS; y++) {
                for (double z = ( WATER_RADIUS*-1); z < WATER_RADIUS; z++) {
                    Block water = block.getLocation().clone().add(x,y,z).getBlock();
                    if (water.getType() == Material.WATER) {
                        Bukkit.broadcastMessage("EEEEEE");
                        water.setType(Material.AIR);
                    }
                }
            }
        }

        block.setType(Material.AIR);
    }

    @Override
    public void run() {
        if (radius > 4) {
            cancel();
            loc.getWorld().playSound(loc, Sound.valueOf(Sponge.getSplash()), 1, 1);
            return;
        }
        getParticles(loc, radius).forEach(loc -> Sponge.getParticleSupport().play(loc));
        loc.getWorld().playSound(loc, Sound.valueOf(Sponge.getWoodClick()), 1.0F, pitch/10F);
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
