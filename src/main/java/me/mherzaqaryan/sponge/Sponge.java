package me.mherzaqaryan.sponge;

import com.andrei1058.bedwars.api.BedWars;
import lombok.Getter;
import me.mherzaqaryan.sponge.listeners.SpongePlaceListener;
import me.mherzaqaryan.sponge.metrics.Metrics;
import me.mherzaqaryan.sponge.particle.ParticleSupport;
import me.mherzaqaryan.sponge.particle.versions.Newer;
import me.mherzaqaryan.sponge.particle.versions.Older;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Sponge extends JavaPlugin {

    @Getter private static Sponge instance;
    @Getter private static BedWars bedWars;
    @Getter private static ParticleSupport particleSupport;
    @Getter private static String splash, woodClick;

    @Getter private static final String version = Bukkit.getServer().getClass().getPackage().getName().substring(23);

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();
        instance = this;
        if (!setupBedwars()) return;
        if (!setupParticle()) return;
        bedWars = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
        new Metrics(this, 11788);
        Bukkit.getPluginManager().registerEvents(new SpongePlaceListener(), this);
        splash = bedWars.getForCurrentVersion("SPLASH", "ENTITY_PLAYER_SPLASH", "ENTITY_PLAYER_SPLASH");
        woodClick = bedWars.getForCurrentVersion("WOOD_CLICK", "BLOCK_WOOD_BUTTON_CLICK_ON", "BLOCK_WOODEN_BUTTON_CLICK_ON");
        getLogger().log(Level.INFO, "Successfully loaded in " + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }

    private boolean setupBedwars() {
        if (Bukkit.getPluginManager().isPluginEnabled("BedWars1058")) return true;
        getLogger().severe("BedWars1058 was not found. Disabling...");
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
