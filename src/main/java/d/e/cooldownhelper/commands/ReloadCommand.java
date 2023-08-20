package d.e.cooldownhelper.commands;

import d.e.cooldownhelper.CooldownHelper;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    CooldownHelper ch;
    public ReloadCommand(CooldownHelper cooldownHelper) {
        this.ch = cooldownHelper;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission("cooldownhelper.reload"))) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        ch.reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Successfully reloaded CooldownHelper configs!");

        return false;
    }
}