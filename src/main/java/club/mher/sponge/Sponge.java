package club.mher.sponge;

import club.mher.sponge.support.BW1058;
import club.mher.sponge.support.BW2023;
import club.mher.sponge.support.IBedWars;
import club.mher.sponge.particle.ParticleSupport;
import lombok.Getter;
import club.mher.sponge.listeners.SpongePlaceListener;
import club.mher.sponge.particle.versions.Newer;
import club.mher.sponge.particle.versions.Older;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Sponge extends JavaPlugin {

    @Getter private static Sponge instance;
    @Getter private static IBedWars bedWars;
    @Getter private static ParticleSupport particleSupport;
    @Getter private static String splash, woodClick;

    @Getter private static final String version = Bukkit.getServer().getClass().getPackage().getName().substring(23);

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();
        instance = this;
        if (!setupBedwars()) {
            return;
        }
        if (!setupParticle()) {
            return;
        }
        if (Bukkit.getPluginManager().isPluginEnabled("BedWars1058")){
            bedWars = new BW1058();
        } else if (Bukkit.getPluginManager().isPluginEnabled("BedWars2023")){
            bedWars = new BW2023();
        }
        new Metrics(this, 11788);
        Bukkit.getPluginManager().registerEvents(new SpongePlaceListener(), this);
        splash = bedWars.getForCurrentVersion("SPLASH", "ENTITY_PLAYER_SPLASH", "ENTITY_PLAYER_SPLASH");
        woodClick = bedWars.getForCurrentVersion("WOOD_CLICK", "BLOCK_WOOD_BUTTON_CLICK_ON", "BLOCK_WOODEN_BUTTON_CLICK_ON");
        getLogger().info("Successfully loaded in " + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }

    private boolean setupBedwars() {
        if (Bukkit.getPluginManager().isPluginEnabled("BedWars1058")) {
            return true;
        }
        if (Bukkit.getPluginManager().isPluginEnabled("BedWars2023")) {
            return true;
        }
        getLogger().severe("BedWars1058 or BedWars2023 was not found. Disabling...");
        setEnabled(false);
        return false;
    }

    private boolean setupParticle() {
        boolean supported = true;
        switch (version) {
            case "v1_8_R2":
            case "v1_8_R1":
                supported = false;
                break;
            case "v1_8_R3":
                particleSupport = new Older();
                break;
            default:
                particleSupport = new Newer();
                break;
        }
        if (!supported){
            getLogger().severe("Your server version is not supported!");
            setEnabled(false);
        }
        return supported;
    }

}
