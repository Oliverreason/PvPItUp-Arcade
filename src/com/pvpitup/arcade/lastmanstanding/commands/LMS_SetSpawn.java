package com.pvpitup.arcade.lastmanstanding.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pvpitup.arcade.SettingsManager;

public class LMS_SetSpawn implements CommandExecutor {
	
SettingsManager sm = SettingsManager.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String cl, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only Players Can Do This!");
		}
		Player p = (Player) sender;
		if (cl.equalsIgnoreCase("lms_setspawn")) {
				if (p.hasPermission("lms.admin")) {
					if (!(args.length == 1)) {
						p.sendMessage("Usage: /lms_setspawn <ArenaNumber>");
					}else{
						String nameString = args[0];
						int map = 0;
						try {
						map = Integer.parseInt(nameString);
						} catch(Exception e) {
							e.printStackTrace();
							p.sendMessage("Please Enter A Number For An Arena!");
						}
						if (sm.getLmsConfig().contains("CTC.Arena." + map + ".Enabled")) {
							sm.getLmsConfig().set("LMS.Arena." + map + ".SpawnSet", "true");
							sm.getLmsConfig().set("LMS.Arena." + map + ".Spawn_World", p.getLocation().getWorld());
							sm.getLmsConfig().set("LMS.Arena." + map + ".Spawn_X", p.getLocation().getX());
							sm.getLmsConfig().set("LMS.Arena." + map + ".Spawn_Y", p.getLocation().getY());
							sm.getLmsConfig().set("LMS.Arena." + map + ".Spawn_Z", p.getLocation().getZ());
							sm.getLmsConfig().set("LMS.Arena." + map + ".Spawn_Yaw", p.getLocation().getYaw());
							sm.getLmsConfig().set("LMS.Arena." + map + ".Spawn_Pitch", p.getLocation().getPitch());
							sm.saveLmsConfig();
							p.sendMessage(ChatColor.GOLD + "Spawn Set For Arena " + ChatColor.RED + ChatColor.BOLD + map);
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
