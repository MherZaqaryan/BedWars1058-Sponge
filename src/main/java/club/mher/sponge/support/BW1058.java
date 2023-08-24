package club.mher.sponge.support;

import com.andrei1058.bedwars.api.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BW1058 implements IBedWars {
    final BedWars instance;

    public BW1058() {
        this.instance = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
    }

    @Override
    public String getForCurrentVersion(String v18, String v12, String v13){
        return instance.getForCurrentVersion(v18, v12, v13);
    }

    @Override
    public boolean isPlaying(Player player){
        return instance.getArenaUtil().isPlaying(player);
    }
}
