package me.GamingMaster0211.VeinMinerUltimate;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VeinMinerUltimateToggleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Players only.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("veinminer.use")) {
            player.sendMessage("§cNo permission.");
            return true;
        }

        boolean enabled = VeinMinerUltimateState.toggle(player.getUniqueId());
        player.sendMessage("§aVeinMinerUltimate " + (enabled ? "enabled" : "disabled") + "!");
        return true;
    }
}

