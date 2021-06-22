package me.mherzaqaryan.sponge.particle.versions;

import me.mherzaqaryan.sponge.particle.ParticleSupport;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;

public class Newer implements ParticleSupport {

    @Override
    public void play(Location location) {
        Bukkit.getOnlinePlayers().forEach(p -> p.spawnParticle(Particle.CLOUD, location, 1, 0.0F, 0.0F, 0.0F, 0.1F));
    }

}
