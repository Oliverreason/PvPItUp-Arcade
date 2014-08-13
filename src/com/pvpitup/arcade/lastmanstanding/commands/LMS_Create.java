package com.pvpitup.arcade.lastmanstanding.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
package com.pvpitup.arcade.lastmanstanding.commands;

import com.pvpitup.arcade.SettingsManager;

public class LMS_Create implements CommandExecutor {

	SettingsManager sm = SettingsManager.getInstance();

	public boolean onCommand(CommandSender sender, Command cmd, String cl,
			String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only Players Can Do This!");
		}

		Player p = (Player) sender;
		if (cl.equalsIgnoreCase("ctc_create")) {
			if (p.hasPermission("ctc.admin")) {
				if (args.length == 1) {
					String name = args[0];
					int arenaNumber = sm.getLmsConfig().getInt("LMS.NumberOfArenas") + 1;
					sm.getLmsConfig().set("LMS.NumberOfArenas", arenaNumber);
					sm.getLmsConfig().set("LMS.Arena." + arenaNumber + ".SpawnSet", "false");
					sm.saveCtcConfig();
					p.sendMessage("You Created " + name);
				} else {
					p.sendMessage("Please Specifiy A Name!");
				}
			}
		} else {
			return false;
		}
		return false;
	}

}
