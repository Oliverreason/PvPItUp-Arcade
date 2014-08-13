package com.pvpitup.capturethedog.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pvpitup.arcade.SettingsManager;

public class CTC_SetBlue implements CommandExecutor {
	
	SettingsManager sm = SettingsManager.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String cl, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only Players Can Do This!");
		}
		Player p = (Player) sender;
		if (cl.equalsIgnoreCase("ctc_setblue")) {
				if (p.hasPermission("ctc.admin")) {
					if (!(args.length == 1)) {
						p.sendMessage("Usage: /ctc_setblue <ArenaName>");
					}else{
						String name = args[0];
						if (sm.getCtcConfig().contains("CTC.Arena." + name + ".Enabled")) {
							sm.getCtcConfig().set("CTC.Arena." + name + ".BlueSpawnSet", "true");
							sm.getCtcConfig().set("CTC.Arena." + name + ".BlueSpawn_World", p.getLocation().getWorld());
							sm.getCtcConfig().set("CTC.Arena." + name + ".BlueSpawn_X", p.getLocation().getX());
							sm.getCtcConfig().set("CTC.Arena." + name + ".BlueSpawn_Y", p.getLocation().getY());
							sm.getCtcConfig().set("CTC.Arena." + name + ".BlueSpawn_Z", p.getLocation().getZ());
							sm.getCtcConfig().set("CTC.Arena." + name + ".BlueSpawn_Yaw", p.getLocation().getYaw());
							sm.getCtcConfig().set("CTC.Arena." + name + ".BlueSpawn_Pitch", p.getLocation().getPitch());
							sm.saveCtcConfig();
							p.sendMessage(ChatColor.GOLD + "Blue Spawn Set For " + ChatColor.AQUA + ChatColor.BOLD + name + ChatColor.GOLD + " Arena");
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
