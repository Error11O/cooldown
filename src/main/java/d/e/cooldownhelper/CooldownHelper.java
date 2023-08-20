package d.e.cooldownhelper;

import d.e.cooldownhelper.commands.ReloadCommand;
import d.e.cooldownhelper.events.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class CooldownHelper extends JavaPlugin {

    @Override
    public void onEnable() {
        this.registerEvents();
        this.registerCommands();
        this.addDefaults();
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerItemConsumeListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new GoldenAppleCooldownListener(this), this);
        getServer().getPluginManager().registerEvents(new CobwebCooldownListener(this), this);
        getServer().getPluginManager().registerEvents(new ShieldCooldownListener(this), this);
    }

    public void registerCommands() {
        getCommand("chreload").setExecutor(new ReloadCommand(this));
    }

    public void addDefaults() {
        FileConfiguration config = this.getConfig();
        config.addDefault("pearl-cooldown", 200);
        config.addDefault("gapple-cooldown", 200);
        config.addDefault("cobweb-cooldown", 200);
        config.options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {

    }
}