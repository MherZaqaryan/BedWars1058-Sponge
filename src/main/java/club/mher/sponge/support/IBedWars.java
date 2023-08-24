package club.mher.sponge.support;

import org.bukkit.entity.Player;

public interface IBedWars {
    String getForCurrentVersion(String v18, String v12, String v13);

    boolean isPlaying(Player player);
}
