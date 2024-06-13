package me.porey.simpleenderchest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleEnderChestPlugin extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("I'm sorry, but only players are allowed to perform this command.");
            return false;
        }
        Player player = (Player) sender;
        if(args.length == 0) {
            // Opening their own ender chest
            player.openInventory(player.getEnderChest());
            return true;
        }
        // Targeting another player
        String targetName = args[0];
        handleTargetPlayer(player, targetName);
        return true;
    }

    private void handleTargetPlayer(Player sender, String targetName) {
        if(!sender.hasPermission("simpleenderchest.use.others")) {
            sender.openInventory(sender.getEnderChest());
            return;
        }
        Player target = sender.getServer().getPlayerExact(targetName);
        if(target == null) {
            sender.sendMessage("Could not find player with name " + targetName + "!");
            return;
        }
        sender.openInventory(target.getEnderChest());
    }
}