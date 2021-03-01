package net.williamd47.pvprush.commands;

import net.williamd47.pvprush.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;


import static org.bukkit.Bukkit.getLogger;
public class EndCommand implements CommandExecutor{

private Main plugin;
    String prefix = ChatColor.GRAY + "[" + ChatColor.GREEN + "PVP Rush" + ChatColor.GRAY + "] ";
    public EndCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("endrush").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            getLogger().info(prefix + ChatColor.RED +  "You cannot do this inside of console");
        } else {
            Player p = (Player) sender;
            if (p.hasPermission("pvprush.end")) {
                p.sendMessage(prefix + ChatColor.RED + "" + ChatColor.BOLD + "Stopped PVP Rush");
                p.getServer().getScheduler().cancelTasks(this.plugin);
            } else {
                p.sendMessage(prefix + ChatColor.RED + "" + ChatColor.BOLD + "You do not have permission to do this!");

            }
        }

        return false;

    }
}
