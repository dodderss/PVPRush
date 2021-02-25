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
    String prefix = ChatColor.GRAY + "[" + ChatColor.GREEN + "PVP Rush" + ChatColor.GRAY + "] ";
    private Main plugin;
    public int task1;                                                                                                                                               
    public int task2;

    public StartCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("startrush").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p1 = (Player) sender;
        if(p1.hasPermission("pvprush.start")) {
            if (args.length == 0) {
                p1.getServer().broadcastMessage(prefix + ChatColor.RED + "" + ChatColor.BOLD + "You need to specify a world");



            } else if (args.length == 1) {
                BukkitScheduler sched = p1.getServer().getScheduler();
                int playercount = 0;
                for (Player p : getServer().getOnlinePlayers())
                    playercount += 1;

                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.getWorld().getName().equals(args[0])) {
                        p1.getServer().broadcastMessage(prefix + ChatColor.YELLOW + "Game Starting with " + Integer.toString(playercount) + " players and in the world " + args[0]);
                    }
                }

                task1 = sched.scheduleSyncRepeatingTask(this.plugin, new Runnable() {
                    int num = 10;

                    @Override
                    public void run() {
                        if (num == 0) {
                            for(Player p : Bukkit.getOnlinePlayers()){
                                if(p.getWorld().getName().equals(args[0])) {
                                    p.sendMessage(prefix + ChatColor.GREEN + "PVP Rush has started!");
                                }
                            }
                            Bukkit.getScheduler().cancelTask(task1);
                        } else {
                            for(Player p : Bukkit.getOnlinePlayers()){
                                if(p.getWorld().getName().equals(args[0])){
                                    p.sendMessage(prefix + ChatColor.GOLD + "Starting in " +  Integer.toString(num--));
                                }
                            }

                        }


                    }
                }, 20L, 20L);

                sched.scheduleSyncRepeatingTask(this.plugin, new Runnable() {
                    @Override
                    public void run() {
                        task2 = sched.scheduleSyncRepeatingTask(plugin, new Runnable() {
                            int num = 20;
                            Location loc = Bukkit.getWorld(args[0]).getSpawnLocation();
                            @Override
                            public void run() {
                                if (num == 0) {
                                    for (Player p : getServer().getOnlinePlayers())
                                        try {
                                            if(p.getWorld().getName().equals(args[0]) ) {
                                                p.teleport(p1.getWorld().getSpawnLocation());
                                            }
                                        } catch (IllegalArgumentException e) {
                                            p1.getServer().broadcastMessage(prefix + ChatColor.YELLOW + "" + ChatColor.BOLD + "The world you specified was invalid");
                                        }

                                    p1.getServer().broadcastMessage(prefix + ChatColor.GREEN + "All players have been teleported!");
                                    Bukkit.getScheduler().cancelTask(task2);
                                } else {
                                    p1.getServer().broadcastMessage(prefix + ChatColor.GOLD + "Teleporting in "+  Integer.toString(num--));
                                }


                            }
                        }, 0L, 20L);


                    }
                }, 2460L, 2400L);
            }
        } else {
            p1.sendMessage(prefix + ChatColor.RED + "" + ChatColor.BOLD + "You do not have permission to do this!");

        }



        return true;
    }
}
