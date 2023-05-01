package club.mher.sponge.particle.versions;

import club.mher.sponge.particle.ParticleSupport;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class Newer implements ParticleSupport {

    @Override
    public void play(Location location) {
        for (Player player : location.getWorld().getPlayers()) {
            player.spawnParticle(Particle.CLOUD, location, 1, 0.0F, 0.0F, 0.0F, 0.1F);
        }
    }

}
