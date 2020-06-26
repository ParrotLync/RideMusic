package nl.parrotlync.ridemusic.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtil {

    public static void sendMessage(CommandSender sender, String msg, boolean withPrefix) {
        if (withPrefix) {
            msg = ChatColor.translateAlternateColorCodes('&', "&8[&cRideMusic&8] " + msg);
        } else {
            msg = ChatColor.translateAlternateColorCodes('&', msg);
        }

        sender.sendMessage(msg);
    }
}
