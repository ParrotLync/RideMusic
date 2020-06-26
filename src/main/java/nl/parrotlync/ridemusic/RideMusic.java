package nl.parrotlync.ridemusic;

import org.bukkit.plugin.java.JavaPlugin;

public class RideMusic extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new RideListener(), this);
        getLogger().info("RideMusic is now enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RideMusic is now disabled!");
    }
}