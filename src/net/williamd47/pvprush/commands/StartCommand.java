package net.williamd47.pvprush.commands;

import net.williamd47.pvprush.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import static org.bukkit.Bukkit.getServer;

public class StartCommand implements CommandExecutor {

    private Main plugin;
    public int task1;
    public int task2;

    public StartCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("prush").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p1 = (Player) sender;
        BukkitScheduler sched = p1.getServer().getScheduler();
        p1.getServer().broadcastMessage(ChatColor.YELLOW +"Game Starting!!");
        task1 = sched.scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            int num = 10;
            @Override
            public void run() {
                if (num == 0) {
                    p1.getServer().broadcastMessage(ChatColor.GREEN + "PVP Rush has started!");
                    Bukkit.getScheduler().cancelTask(task1);
                }else {
                    p1.getServer().broadcastMessage(ChatColor.GOLD+Integer.toString(num--));
                }


            }
        }, 20L, 20L);

        sched.scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                task2 = sched.scheduleSyncRepeatingTask(plugin, new Runnable() {
                    int num = 20;
                    @Override
                    public void run() {
                        if (num == 0) {
                            for (Player p : getServer().getOnlinePlayers())
                                p.teleport(new Location(Bukkit.getWorld("world"), 0, 69, 0));
                            p1.getServer().broadcastMessage(ChatColor.GREEN + "All players have been teleported!");
                            Bukkit.getScheduler().cancelTask(task2);
                        }else {
                            p1.getServer().broadcastMessage(ChatColor.GOLD+ Integer.toString(num--));
                        }


                    }
                }, 0L, 20L);



            }
        }, 2460L, 2400L);

        return true;
    }
}
