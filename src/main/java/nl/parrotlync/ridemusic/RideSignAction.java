package nl.parrotlync.ridemusic;

import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import de.leonhard.storage.Json;
import net.mcjukebox.plugin.bukkit.api.JukeboxAPI;
import net.mcjukebox.plugin.bukkit.api.ResourceType;
import net.mcjukebox.plugin.bukkit.api.models.Media;
import nl.parrotlync.ridemusic.util.ChatUtil;
import org.apache.commons.lang.StringUtils;

public class RideSignAction extends SignAction {
    private Json json = new Json("shortcodes", "plugins/RideMusic");

    @Override
    public boolean match(SignActionEvent info) {
        return info.isType("ridemusic");
    }

    @Override
    public void execute(SignActionEvent info) {
        String identifier = info.getGroup().getProperties().getTrainName();
        if (info.getLine(2).equals("music")) {
            String url = String.valueOf(json.get(info.getLine(3)));
            Media media = new Media(ResourceType.MUSIC, url);
            media.setLooping(false);
            JukeboxAPI.getShowManager().getShow(identifier).play(media);
            return;
        }
        if (info.getLine(2).equals("sound")) {
            String url = String.valueOf(json.get(info.getLine(3)));
            Media media = new Media(ResourceType.SOUND_EFFECT, url);
            JukeboxAPI.getShowManager().getShow(identifier).play(media);
            return;
        }
        if (info.getLine(2).equals("stop")) {
            JukeboxAPI.getShowManager().getShow(identifier).stopMusic();
        }
    }

    @Override
    public boolean build(SignChangeActionEvent event) {
        if (event.isCartSign()) {
            return false;
        } else if (event.isTrainSign()) {
            if (event.getPlayer().hasPermission("ridemusic.build")) {
                if (event.getLine(2).equals("music") || event.getLine(2).equals("sound")) {
                    String url = String.valueOf(json.get(event.getLine(3)));
                    if (url.equals("null")) {
                        ChatUtil.sendMessage(event.getPlayer(), "&cThat shortcode doesn't exist. Please add it first with &3/ridemusic add", true);
                        return false;
                    }
                    ChatUtil.sendMessage(event.getPlayer(), "&7You have &acreated &7a RideMusic sign!", true);
                    ChatUtil.sendMessage(event.getPlayer(), "&7URL: " + url, true);
                    return true;
                } else if(event.getLine(2).equals("stop")) {
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
