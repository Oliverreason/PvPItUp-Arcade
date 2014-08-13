package com.pvpitup.capturethedog.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pvpitup.arcade.SettingsManager;

public class CTC_Create implements CommandExecutor {
	
	SettingsManager sm = SettingsManager.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String cl, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only Players Can Do This!");
		}
		
		Player p = (Player) sender;
		if (cl.equalsIgnoreCase("ctc_create")) {
				if (p.hasPermission("ctc.admin")) {
					if (args.length == 1) {
						String name = args[0];
						sm.getCtcConfig().set("CTC.Arena." + name + ".Enabled", "true");
						sm.getCtcConfig().set("CTC.Arena." + name + ".BlueSpawnSet", "false");
						sm.getCtcConfig().set("CTC.Arena." + name + ".RedSpawnSet", "false");
						sm.saveCtcConfig();
						p.sendMessage("You Created " + name);
					}else{
						p.sendMessage("Please Specifiy A Name!");
					}
				}
			}else{
				return false;
			}
		return false;
	}

}
