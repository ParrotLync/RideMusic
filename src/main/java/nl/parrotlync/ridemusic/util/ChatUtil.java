package nl.parrotlync.ridemusic.util;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
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

    public static void sendHoverMessage(CommandSender sender, String msg, String hmsg, String url, boolean withPrefix) {
        if (withPrefix) {
            msg = ChatColor.translateAlternateColorCodes('&', "&8[&cRideMusic&8] " + msg);
            hmsg = ChatColor.translateAlternateColorCodes('&', " " + hmsg);
            TextComponent main = new TextComponent(msg);
            TextComponent hover = new TextComponent(hmsg);
            hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(url).create()));
            hover.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
            main.addExtra(hover);
            sender.spigot().sendMessage(main);
        }
    }
}
