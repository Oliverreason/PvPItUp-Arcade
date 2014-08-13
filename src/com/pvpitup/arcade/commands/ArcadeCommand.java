package com.pvpitup.arcade.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pvpitup.arcade.Arcade;

public class ArcadeCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only Players Can Do This!");
			return false;
		}
		Player p = (Player) sender;
		if (command.equalsIgnoreCase("arcade")) {
			if (p.hasPermission("arcade.commands.arcade")) {
				p.sendMessage(Arcade.prefix + ChatColor.GOLD + "" + ChatColor.BOLD + "Arcade Commands:");
				p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "/arcade ");
				p.sendMessage("");
				p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Game Commands:");
				p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "/ctc ");
				p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "/lms ");
			} else {
				p.sendMessage(Arcade.prefix + ChatColor.RED + "" + ChatColor.BOLD + "No Permission");
				return false;
			}
		}else{
			return false;
		}
		return false;
	}
}
