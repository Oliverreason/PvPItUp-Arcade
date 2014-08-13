package com.pvpitup.capturethedog.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pvpitup.arcade.SettingsManager;

public class CTC_SetRed implements CommandExecutor {
	
	SettingsManager sm = SettingsManager.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String cl, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only Players Can Do This!");
		}
		Player p = (Player) sender;
		if (cl.equalsIgnoreCase("ctc_setredchicken")) {
				if (p.hasPermission("ctc.admin")) {
					if (!(args.length == 1)) {
						p.sendMessage("Usage: /ctc_setredchicken <ArenaName>");
					}else{
						String name = args[0];
						if (sm.getCtcConfig().contains("CTC.Arena." + name + ".Enabled")) {
							sm.getCtcConfig().set("CTC.Arena." + name + ".RedChickenSpawnSet", "true");
							sm.getCtcConfig().set("CTC.Arena." + name + ".RedChickenSpawn_World", p.getLocation().getWorld());
							sm.getCtcConfig().set("CTC.Arena." + name + ".RedChickenSpawn_X", p.getLocation().getX());
							sm.getCtcConfig().set("CTC.Arena." + name + ".RedChickenSpawn_Y", p.getLocation().getY());
							sm.getCtcConfig().set("CTC.Arena." + name + ".RedChickenSpawn_Z", p.getLocation().getZ());
							sm.getCtcConfig().set("CTC.Arena." + name + ".RedChickenSpawn_Yaw", p.getLocation().getYaw());
							sm.getCtcConfig().set("CTC.Arena." + name + ".RedChickenSpawn_Pitch", p.getLocation().getPitch());
							sm.saveCtcConfig();
							p.sendMessage(ChatColor.GOLD + "Red Chicken Spawn Set For " + ChatColor.RED + ChatColor.BOLD + name + ChatColor.GOLD + " Arena");
						}else{
							p.sendMessage("Thats An Invalid Arena!");
							return false;
						}
					}
				}else{
					p.sendMessage("" + ChatColor.RED + ChatColor.BOLD + "No Permission");
					return false;
				}
			}else{
				return false;
			}
		return false;
	}
}
