package club.mher.sponge.tasks;

import club.mher.sponge.Sponge;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SpongeAnimationTask extends BukkitRunnable {

    private final Block block;
    private final Location location;

    private int radius = 1;
    private int pitch = 17;

    public SpongeAnimationTask(Block block) {
        this.block = block;
        this.location = block.getLocation();
    }

    @Override
    public void run() {
        if (radius > 4) {
            cancel();
            block.setType(Material.AIR);
            location.getWorld().playSound(location, Sound.valueOf(Sponge.getSplash()), 1, 1);
            return;
        }
        for (Location particle : getParticles(location, radius)) {
            Sponge.getParticleSupport().play(particle);
        }
        location.getWorld().playSound(location, Sound.valueOf(Sponge.getWoodClick()), 1.0F, pitch/10F);
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
