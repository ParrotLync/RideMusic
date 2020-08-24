package nl.parrotlync.ridemusic;

import nl.parrotlync.ridemusic.util.ChatUtil;
import nl.parrotlync.ridemusic.util.DataUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class RideCommandExecutor implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission("ridemusic.use")) {
            if (args.length == 0) {
                return help(sender);
            }

            if (args[0].equalsIgnoreCase("help")) {
                return help(sender);
            }

            if (args[0].equalsIgnoreCase("add")) {
                if (args.length == 3) {
                    RideMusic.shortCodes.put(args[1], args[2]);
                    DataUtil.saveObjectToPath(RideMusic.shortCodes, RideMusic.path);
                    ChatUtil.sendMessage(sender, "&7You added a shortcode! &7[&a" + args[1] + "&7]", true);
                    return true;
                }
            }

            if (args[0].equalsIgnoreCase("remove")) {
                if (args.length == 2) {
                    String url = RideMusic.shortCodes.get(args[1]);
                    RideMusic.shortCodes.remove(args[1]);
                    DataUtil.saveObjectToPath(RideMusic.shortCodes, RideMusic.path);
                    ChatUtil.sendMessage(sender, "&7You removed a shortcode! &7[&c" + args[1] + "&7]", true);
                    return true;
                }
            }

            if (args[0].equalsIgnoreCase("list")) {
                List<String> keys = new ArrayList<>(RideMusic.shortCodes.keySet());
                int numberOfPages = (int) Math.ceil((RideMusic.shortCodes.size() / 5) + 1);

                int page = 1;
                if (args.length == 2) {
                    try {
                        page = Integer.parseInt(args[1]);
                        if (page > numberOfPages) { return false; }
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }

                ChatUtil.sendMessage(sender, "&f+---+ &aRideMusic &bShortcode List &7(page " + page + ") &f+---+", false);
                for (int i = (page - 1) * 5 ; i < page * 5 && i < RideMusic.shortCodes.size(); i++) {
                    ChatUtil.sendMessage(sender, "&6" + keys.get(i) + "&7: " + RideMusic.shortCodes.get(keys.get(i)), false);
                }
                if (page < numberOfPages) {
                    ChatUtil.sendMessage(sender, "&3Type '/ridemusic list " + (page + 1) + "' to see more.", false);
                }
                return true;
            }
            return help(sender);
        } else {
            ChatUtil.sendMessage(player, "&cYou don't have permission to do that!", true);
            return true;
        }
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

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String Label, String[] args) {
        List<String> suggestions = new ArrayList<>();
        if (args.length == 1) {
            suggestions.add("help");
            suggestions.add("add");
            suggestions.add("remove");
            suggestions.add("list");
            return StringUtil.copyPartialMatches(args[0], suggestions, new ArrayList<>());
        }

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("remove")) {
                List<String> keys = new ArrayList<>(RideMusic.shortCodes.keySet());
                suggestions.addAll(keys);
                return StringUtil.copyPartialMatches(args[args.length - 1], suggestions, new ArrayList<>());
            }
        }

        return suggestions;
    }
}
