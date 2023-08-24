package club.mher.sponge.support;

import club.mher.sponge.Sponge;
import com.tomkeuper.bedwars.api.BedWars;
import com.tomkeuper.bedwars.api.addon.Addon;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BW2023 extends Addon implements IBedWars {
    final BedWars instance;
    public BW2023() {
        this.instance = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
        instance.getAddonsUtil().registerAddon(this);
    }

    @Override
    public String getAuthor() {
        return Sponge.getInstance().getDescription().getAuthors().get(0);
    }

    @Override
    public Plugin getPlugin() {
        return Sponge.getInstance();
    }

    @Override
    public String getVersion() {
        return Sponge.getInstance().getDescription().getVersion();
    }

    @Override
    public String getDescription() {
        return Sponge.getInstance().getDescription().getDescription();
    }

    @Override
    public String getName() {
        return Sponge.getInstance().getDescription().getName();
    }

    @Override
    public void load() {
        Bukkit.getPluginManager().enablePlugin(Sponge.getInstance());
    }

    @Override
    public void unload() {
        Bukkit.getPluginManager().disablePlugin(Sponge.getInstance());
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
