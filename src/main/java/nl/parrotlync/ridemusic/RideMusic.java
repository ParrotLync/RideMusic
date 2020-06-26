package nl.parrotlync.ridemusic;

import com.bergerkiller.bukkit.tc.signactions.SignAction;
import nl.parrotlync.ridemusic.commands.RideCommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class RideMusic extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new RideListener(), this);
        this.getCommand("ridemusic").setExecutor(new RideCommandExecutor());
        SignAction.register(new RideSignAction());
        getLogger().info("RideMusic is now enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RideMusic is now disabled!");
    }
}