package nl.parrotlync.ridemusic.commands;

import de.leonhard.storage.Json;
import nl.parrotlync.ridemusic.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RideCommandExecutor implements CommandExecutor {
    private Json json = new Json("shortcodes", "plugins/RideMusic");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return help(sender);
        }

        if (args[0].equals("help")) {
            return help(sender);
        }

        if (args[0].equals("add")) {
            if (args.length == 3) {
                json.set(args[1], args[2]);
                ChatUtil.sendMessage(sender, "&7You added a shortcode! (&a" + args[1] + "&7)", true);
                return true;
            }
        }

        if (args[0].equals("remove")) {
            if (args.length == 2) {
                json.remove(args[1]);
                ChatUtil.sendMessage(sender, "&7You removed a shortcode! (&a" + args[1] + "&7)", true);
                return true;
            }
        }
        return help(sender);
    }

    private boolean help(CommandSender sender) {
        if (sender.hasPermission("ridemusic.use")) {
            ChatUtil.sendMessage(sender, "&f+---+ &aRide&bMusic &f+---+", false);
            ChatUtil.sendMessage(sender, "&3/ridemusic add <shortcode> <url> &7Add a new shortcode set", false);
            ChatUtil.sendMessage(sender, "&3/ridemusic remove <shortcode> &7Remove a shortcode set", false);
        } else {
            ChatUtil.sendMessage(sender, "&cYou do not have permission to do that.", true);
        }
        return true;
    }

}
