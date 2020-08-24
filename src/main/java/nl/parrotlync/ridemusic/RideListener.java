package nl.parrotlync.ridemusic;

import com.bergerkiller.bukkit.tc.controller.MinecartMember;
import com.bergerkiller.bukkit.tc.controller.MinecartMemberStore;
import net.mcjukebox.plugin.bukkit.api.JukeboxAPI;
import nl.parrotlync.ridemusic.util.ChatUtil;
import nl.parrotlync.ridemusic.util.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class RideListener implements Listener {

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        MinecartMember<?> member = MinecartMemberStore.getFromEntity(event.getVehicle());
        if (member == null) { return; }
        if (event.getEntered() instanceof Player) {
            if (member.getGroup().getProperties().getTags().contains("RideMusic")) {
                Player player = (Player) event.getEntered();
                String identifier = member.getGroup().getProperties().getTrainName();
                JukeboxAPI.getShowManager().getShow(identifier).addMember(player, false);
            }
        }
    }

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        MinecartMember<?> member = MinecartMemberStore.getFromEntity(event.getVehicle());
        if (member == null) { return; }
        if (event.getExited() instanceof Player) {
            if (member.getGroup().getProperties().getTags().contains("RideMusic")) {
                Player player = (Player) event.getExited();
                String identifier = member.getGroup().getProperties().getTrainName();
                JukeboxAPI.getShowManager().getShow(identifier).removeMember(player);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("ridemusic.update")) {
            if (RideMusic.updateAvailable) {
                ChatUtil.sendMessage(player, "&7There is an update available! Get it at https://www.spigotmc.org/resources/ridemusic.80710/", true);
            }
        }
    }
}
