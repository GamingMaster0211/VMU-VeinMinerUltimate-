package me.GamingMaster0211.VeinMinerUltimate;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class VeinMinerUltimateCommands implements CommandExecutor {

    private final JavaPlugin plugin;

    public VeinMinerUltimateCommands(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + plugin.getName());
            sender.sendMessage(ChatColor.GRAY + "Use /vmu usage for help.");
            return true;
        }

        if (args[0].equalsIgnoreCase("info")) {
            sender.sendMessage(ChatColor.GREEN + "VMU (" + plugin.getName() + ")");
            sender.sendMessage(ChatColor.GRAY + "Break entire veins at once!");
            sender.sendMessage(ChatColor.GRAY + "Author: " + plugin.getDescription().getAuthors().get(0));
            return true;
        }

        if (args[0].equalsIgnoreCase("version")) {
            sender.sendMessage(ChatColor.GREEN + plugin.getName()
                    + " version " + plugin.getDescription().getVersion());
            return true;
        }

        if (args[0].equalsIgnoreCase("usage")) {
            sender.sendMessage(ChatColor.YELLOW + plugin.getName() + " Commands:");
            sender.sendMessage(ChatColor.GRAY + "/vmu info - Plugin information");
            sender.sendMessage(ChatColor.GRAY + "/vmu version - Plugin version");
            sender.sendMessage(ChatColor.GRAY + "/vmu usage - Command list");
            sender.sendMessage(ChatColor.GRAY + "/vmu <on|off> - Enable or disable VeinMinerUltimate");
            sender.sendMessage(ChatColor.GRAY + "/vmu toggle - Toggle VeinMinerUltimate");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Players only.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("veinminer.use")) {
            player.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }

        UUID uuid = player.getUniqueId();

        if (args[0].equalsIgnoreCase("toggle")) {
            boolean enabled = VeinMinerUltimateState.toggle(uuid);
            player.sendMessage(ChatColor.GREEN + "VeinMinerUltimate " + (enabled ? "enabled" : "disabled") + "!");
            return true;
        }

        if (args[0].equalsIgnoreCase("on")) {
            VeinMinerUltimateState.setEnabled(uuid, true);
            player.sendMessage(ChatColor.GREEN + "VeinMinerUltimate enabled!");
            return true;
        }

        if (args[0].equalsIgnoreCase("off")) {
            VeinMinerUltimateState.setEnabled(uuid, false);
            player.sendMessage(ChatColor.RED + "VeinMinerUltimate disabled!");
            return true;
        }

        player.sendMessage(ChatColor.RED + "Unknown arguement. Use /vmu usage.");
        return true;
    }
}
