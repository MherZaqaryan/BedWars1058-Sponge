package club.mher.sponge.particle.versions;

import club.mher.sponge.particle.ParticleSupport;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

public class Older implements ParticleSupport {

    @Override
    public void play(Location location) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.CLOUD, true, (float) location.getX(), (float) location.getY(), (float) location.getZ(), 0.0F, 0.0F, 0.0F, 0.1F, 1);
        location.getWorld().getPlayers().forEach(p -> ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet));
    }

}
