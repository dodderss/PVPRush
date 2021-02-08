package net.williamd47.pvprush;

import net.williamd47.pvprush.commands.EndCommand;
import net.williamd47.pvprush.commands.StartCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        new StartCommand(this);
        new EndCommand(this);
    }
}
