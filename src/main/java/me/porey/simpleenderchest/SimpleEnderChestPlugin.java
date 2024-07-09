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
            openEnderChest(player, player);
            return true;
        }
        // Targeting another player
        if(!sender.hasPermission("simpleenderchest.use.others")) {
            openEnderChest(player, player);
            return true;
        }
        String targetName = args[0];
        handleTargetPlayer(player, targetName);
        return true;
    }

    private void handleTargetPlayer(Player sender, String targetName) {
        Player target = sender.getServer().getPlayerExact(targetName);
        if(target == null) {
            sender.sendMessage("Could not find player with name " + targetName + "!");
            return;
        }
        openEnderChest(sender, target);
    }

    private void openEnderChest(Player player, Player target) {
        player.openInventory(target.getEnderChest());
    }
}