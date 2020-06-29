package nl.parrotlync.ridemusic;

import de.leonhard.storage.Json;
import nl.parrotlync.ridemusic.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RideCommandExecutor implements CommandExecutor {
    private Json json = new Json("shortcodes", "plugins/RideMusic");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return help(sender);
        }

        if (args[0].equalsIgnoreCase("help")) {
            return help(sender);
        }

        if (args[0].equalsIgnoreCase("add")) {
            if (args.length == 3) {
                json.set(args[1], args[2]);
                Object url = json.get(args[1]);
                ChatUtil.sendHoverMessage(sender, "&7You added a shortcode!", "&7[&a" + args[1] + "&7]", String.valueOf(url), true);
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("remove")) {
            if (args.length == 2) {
                Object url = json.get(args[1]);
                json.remove(args[1]);
                ChatUtil.sendHoverMessage(sender, "&7You removed a shortcode!", "&7[&a" + args[1] + "&7]", String.valueOf(url), true);
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("list")) {
            Map<String, Object> shortcodes = json.getData();
            List<String> keys = new ArrayList<String>(shortcodes.keySet());
            int numberOfPages = (int) Math.ceil((shortcodes.size() / 5) + 1);

            int page = 1;
            if (args.length == 2) {
                try {
                    page = Integer.parseInt(args[1]);
                    if (page > numberOfPages) { return false; }
                } catch (NumberFormatException e) {
                    return false;
                }
            }

            ChatUtil.sendMessage(sender, "&f+---+ &aRideMusic &bshortcode list &7(page " + page + ") &f+---+", false);
            for (int i = (page - 1) * 5 ; i < page * 5 && i < shortcodes.size(); i++) {
                ChatUtil.sendMessage(sender, "&6" + keys.get(i) + "&7: " + shortcodes.get(keys.get(i)), false);
            }
            if (page < numberOfPages) {
                ChatUtil.sendMessage(sender, "&3Type '/ridemusic list " + (page + 1) + "' to see more.", false);
            }
            return true;
        }
        return help(sender);
    }

    private boolean help(CommandSender sender) {
        if (sender.hasPermission("ridemusic.use")) {
            ChatUtil.sendMessage(sender, "&f+---+ &aRideMusic &f+---+", false);
            ChatUtil.sendMessage(sender, "&3/ridemusic add <shortcode> <url> &7Add a new shortcode set", false);
            ChatUtil.sendMessage(sender, "&3/ridemusic remove <shortcode> &7Remove a shortcode set", false);
            ChatUtil.sendMessage(sender, "&3/ridemusic list &7View all shortcodes", false);
        } else {
            ChatUtil.sendMessage(sender, "&cYou do not have permission to do that.", true);
        }
        return true;
    }

}
