package nl.parrotlync.ridemusic;

import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import net.mcjukebox.plugin.bukkit.api.JukeboxAPI;
import net.mcjukebox.plugin.bukkit.api.ResourceType;
import net.mcjukebox.plugin.bukkit.api.models.Media;
import nl.parrotlync.ridemusic.util.ChatUtil;
import nl.parrotlync.ridemusic.util.DataUtil;

import java.util.HashMap;

public class RideSignAction extends SignAction {

    @Override
    public boolean match(SignActionEvent info) {
        return info.isType("ridemusic");
    }

    @Override
    public void execute(SignActionEvent info) {
        if (!info.isPowered()) { return; }
        String name = info.getGroup().getProperties().getTrainName();
        if (info.getLine(2).equalsIgnoreCase("music")) {
            String url = String.valueOf(RideMusic.shortCodes.get(info.getLine(3)));
            Media media = new Media(ResourceType.MUSIC, url);
            media.setLooping(false);
            JukeboxAPI.getShowManager().getShow(name).play(media);
            return;
        }
        if (info.getLine(2).equalsIgnoreCase("sound")) {
            String url = String.valueOf(RideMusic.shortCodes.get(info.getLine(3)));
            Media media = new Media(ResourceType.SOUND_EFFECT, url);
            JukeboxAPI.getShowManager().getShow(name).play(media);
            return;
        }
        if (info.getLine(2).equalsIgnoreCase("stop")) {
            JukeboxAPI.getShowManager().getShow(name).stopMusic();
        }
    }

    @Override
    public boolean build(SignChangeActionEvent event) {
        if (event.isCartSign()) {
            return false;
        } else if (event.isTrainSign()) {
            if (event.getPlayer().hasPermission("ridemusic.build")) {
                if (event.getLine(2).equalsIgnoreCase("music") || event.getLine(2).equalsIgnoreCase("sound")) {
                    String url = String.valueOf(RideMusic.shortCodes.get(event.getLine(3)));
                    if (url.equals("null")) {
                        ChatUtil.sendMessage(event.getPlayer(), "&cThat shortcode doesn't exist. Please add it first with &3/ridemusic add", true);
                        return false;
                    }
                    ChatUtil.sendMessage(event.getPlayer(), "&7You have &acreated &7a RideMusic sign!", true);
                    ChatUtil.sendMessage(event.getPlayer(), "&7URL: " + url, true);
                    return true;
                } else if(event.getLine(2).equalsIgnoreCase("stop")) {
                    ChatUtil.sendMessage(event.getPlayer(), "&7You have &acreated &7a RideMusic stop sign!", true);
                    return true;
                }
            } else {
                ChatUtil.sendMessage(event.getPlayer(), "&cYou do not have permission to do that!", true);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean canSupportRC() {
        return false;
    }
}
