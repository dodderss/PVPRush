package net.williamd47.pvprush;

import net.williamd47.pvprush.commands.EndCommand;
import net.williamd47.pvprush.commands.StartCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        String prefix = ChatColor.GRAY + "[" + ChatColor.GREEN + "PVP Rush" + ChatColor.GRAY + "] ";
        getLogger().info(prefix + ChatColor.GREEN +  "Initialising /startrush and the permission pvprush.start");
        new StartCommand(this);
        getLogger().info(prefix + ChatColor.GREEN +  "Initialising /endrush and the permission pvprush.end");
        new EndCommand(this);

    }
}
