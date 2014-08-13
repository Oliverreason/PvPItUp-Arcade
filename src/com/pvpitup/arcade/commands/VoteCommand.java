package com.pvpitup.arcade.commands;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pvpitup.arcade.tempData;

public class VoteCommand implements CommandExecutor {
	
	public static HashMap<Player, String> vote = new HashMap<Player, String>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String cl, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only Players Can Do This!");
			return false;
		}
		Player p = (Player) sender;
		if (cl.equalsIgnoreCase("vote")) {
			if (!(args.length == 1)) {
				avalibleMiniGames(p);
			}else{
				
				String string = args[0];
				int id = Integer.parseInt(string);
				if (id == 1) {
					
					if (vote.get(p) == "ctc") {
						p.sendMessage(Arcade.prefix + ChatColor.RED + "You Have Already Voted For This Minigame!");
						return false;
					}else{
						if (vote.containsKey(p)) {
							tempData.removeVoteLMS();
							vote.replace(p, "ctc");
							tempData.addVoteCTD();
							p.sendMessage(Arcade.prefix + ChatColor.YELLOW + "CaptureTheDog Has " + tempData.getVoteCTD() + " Votes");
							return false;
						}
					vote.put(p, "ctc");
					tempData.addVoteCTD();
					p.sendMessage(Arcade.prefix + ChatColor.AQUA + "CaptureTheDog Has " + ChatColor.GOLD + ChatColor.BOLD + tempData.getVoteCTD() + ChatColor.AQUA + " Votes");
					return false;
					}
				}else if (id == 2) {

					if (vote.get(p) == "lms") {
						p.sendMessage(Arcade.prefix + "" + ChatColor.RED + "You Have Already Voted For This Minigame!");
						return false;
					}else{
						if (vote.containsKey(p)) {
							tempData.removeVoteCTD();
							vote.replace(p, "lms");
							tempData.addVoteLMS();
							p.sendMessage(Arcade.prefix + ChatColor.AQUA + "LastManStanding Has " + ChatColor.GOLD + ChatColor.BOLD + tempData.getVoteLMS() + ChatColor.AQUA + " Votes");
							return false;
						}
					vote.put(p, "lms");
					tempData.addVoteLMS();
					p.sendMessage("LastManStanding Has " + tempData.getVoteLMS() + " Votes");
					return false;
					}
				}else{
					avalibleMiniGames(p);
					return false;
				}
			}
		}else{
			return false;
		}
		return false;
	}
	
	public void avalibleMiniGames(Player p) {
		p.sendMessage(Arcade.prefix + "" + ChatColor.GOLD + ChatColor.BOLD + "===-- Vote For MiniGame --===");
		p.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + "1" + ChatColor.RED + " - " +  ChatColor.AQUA + "CaptureTheDog" + ChatColor.RED + "  -  " + ChatColor.AQUA + "With " + tempData.getVoteCTD() + ChatColor.AQUA + " Votes!");
		p.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + "2" + ChatColor.RED + " - " +  ChatColor.AQUA + "LastManStanding" + ChatColor.RED + "  -  " + ChatColor.AQUA + "With " + tempData.getVoteLMS() + ChatColor.AQUA + " Votes!");
	}
}
