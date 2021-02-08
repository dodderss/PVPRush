package net.williamd47.pvprush.commands;

import net.williamd47.pvprush.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EndCommand implements CommandExecutor{

private Main plugin;
    public EndCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("erush").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        p.getServer().broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "Stopping PVP Rush");
        p.getServer().getScheduler().cancelTasks(this.plugin);
        return false;
    }
}
