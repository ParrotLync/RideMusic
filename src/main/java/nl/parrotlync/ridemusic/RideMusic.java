package nl.parrotlync.ridemusic;

import com.bergerkiller.bukkit.tc.signactions.SignAction;
import nl.parrotlync.ridemusic.util.DataUtil;
import nl.parrotlync.ridemusic.util.UpdateChecker;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class RideMusic extends JavaPlugin {
    static boolean updateAvailable;
    static String path = "plugins/RideMusic/short_codes.data";
    static HashMap<String, String> shortCodes;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new RideListener(), this);
        this.getCommand("ridemusic").setExecutor(new RideCommandExecutor());
        SignAction.register(new RideSignAction());
        shortCodes = DataUtil.loadObjectFromPath(RideMusic.path);
        if (shortCodes == null) { shortCodes = new HashMap<>(); }
        getLogger().info("RideMusic is now enabled!");
        new UpdateChecker(this, 80710).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                getLogger().info("You are on the newest version.");
                updateAvailable = false;
            } else {
                getLogger().info("There is a new version available! https://www.spigotmc.org/resources/ridemusic.80710/");
                updateAvailable = true;
            }
        });
    }

    @Override
    public void onDisable() {
        getLogger().info("RideMusic is now disabled!");
    }
}