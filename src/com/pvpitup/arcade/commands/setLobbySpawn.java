package com.pvpitup.arcade.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pvpitup.arcade.SettingsManager;

import com.pvpitup.arcade.Arcade;

public class setLobbySpawn {
	
SettingsManager sm = SettingsManager.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String cl, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only Players Can Do This!");
		}
		Player p = (Player) sender;
		if (cl.equalsIgnoreCase("lms_setspawn")) {
				if (p.hasPermission("lms.admin")) {
					if (!(args.length == 1)) {
						p.sendMessage("Usage: /lms_setspawn <ArenaName>");
					}else{
						String name = args[0];
						if (sm.getCtcConfig().contains("CTC.Arena." + name + ".Enabled")) {
							sm.getCtcConfig().set("LMS.Arena." + name + ".SpawnSet", "true");
							sm.getCtcConfig().set("LMS.Arena." + name + ".Spawn_World", p.getLocation().getWorld());
							sm.getCtcConfig().set("LMS.Arena." + name + ".Spawn_X", p.getLocation().getX());
							sm.getCtcConfig().set("LMS.Arena." + name + ".Spawn_Y", p.getLocation().getY());
							sm.getCtcConfig().set("LMS.Arena." + name + ".Spawn_Z", p.getLocation().getZ());
							sm.getCtcConfig().set("LMS.Arena." + name + ".Spawn_Yaw", p.getLocation().getYaw());
							sm.getCtcConfig().set("LMS.Arena." + name + ".Spawn_Pitch", p.getLocation().getPitch());
							sm.saveCtcConfig();
							p.sendMessage(Arcade.prefix + ChatColor.GOLD + "Spawn Set For " + ChatColor.RED + ChatColor.BOLD + name + ChatColor.GOLD + " Arena");
						}else{
							p.sendMessage("Thats An Invalid Arena!");
							return false;
						}
					}
				}else{
					p.sendMessage(Arcade.prefix + "" + ChatColor.RED + ChatColor.BOLD + "No Permission");
					return false;
				}
			}else{
				return false;
			}
		return false;
	}
}
