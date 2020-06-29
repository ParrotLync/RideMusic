package nl.parrotlync.ridemusic;

import com.bergerkiller.bukkit.tc.signactions.SignAction;
import nl.parrotlync.ridemusic.util.Metrics;
import nl.parrotlync.ridemusic.util.UpdateChecker;
import org.bukkit.plugin.java.JavaPlugin;

public class RideMusic extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new RideListener(), this);
        this.getCommand("ridemusic").setExecutor(new RideCommandExecutor());
        SignAction.register(new RideSignAction());
        Metrics metrics = new Metrics(this, 8038);
        getLogger().info("RideMusic is now enabled!");
        new UpdateChecker(this, 80710).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                getLogger().info("You are on the newest version.");
            } else {
                getLogger().info("There is a new version available! https://www.spigotmc.org/resources/ridemusic.80710/");
            }
        });
    }

    @Override
    public void onDisable() {
        getLogger().info("RideMusic is now disabled!");
    }
}