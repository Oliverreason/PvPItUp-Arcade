package com.pvpitup.arcade;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class tempData {
	
	static boolean inGame = false;
	static int voteCaptureTheDog = 0;
	static int voteLastManStanding = 0;
	static String gameName;
	
	public static void setType(String type) {
		gameName = type;
	}
	
	public static String getType() {
		if (gameName == null) {
			return null;
		}else {
			return gameName;
		}
	}
	
	public static boolean isInGame() {
		return inGame;
	}
	
	public static void setInGame(boolean setGame) {
		inGame = setGame;
	}
	
	public static int getVoteCTD() {
		return voteCaptureTheDog;
	}
	
	public static int getVoteLMS() {
		return voteLastManStanding;
	}
	
	public static void addVoteCTD() {
		voteCaptureTheDog = voteCaptureTheDog+1;
	}
	
	public static void addVoteLMS() {
		voteLastManStanding = voteLastManStanding+1;
	}
	
	public static void removeVoteCTD() {
		voteCaptureTheDog = voteCaptureTheDog-1;
	}
	
	public static void removeVoteLMS() {
		voteLastManStanding = voteLastManStanding-1;
	}
	
	public void reset() {
		inGame = false;
		voteCaptureTheDog = 0;
		voteLastManStanding = 0;
	}
	
	static int num = 0;
	public static int voteRunnable() {
		
			return Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Arcade"), new Runnable() {
				public void run() {
					if (inGame == true) {
						Bukkit.getScheduler().cancelTask(voteRunnable());
					}
					if (num == 0) {
						Bukkit.broadcastMessage(Arcade.prefix + ChatColor.GREEN + "" + ChatColor.BOLD + "Avaliable Games");
						Bukkit.broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + "1" + ChatColor.RED + " - " +  ChatColor.AQUA + "CaptureTheDog" + ChatColor.RED + "  -  " + ChatColor.AQUA + "With " + ChatColor.GOLD + ChatColor.BOLD + tempData.getVoteCTD() + ChatColor.AQUA + " Votes!");
						Bukkit.broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + "2" + ChatColor.RED + " - " +  ChatColor.AQUA + "LastManStanding" + ChatColor.RED + "  -  " + ChatColor.AQUA + "With " + ChatColor.GOLD + ChatColor.BOLD + tempData.getVoteLMS() + ChatColor.AQUA + " Votes!");
						Bukkit.broadcastMessage("");
						num ++;
					} else if (num == 1) {
						Bukkit.broadcastMessage(Arcade.prefix + ChatColor.GREEN + "" + ChatColor.BOLD + "Avaliable Games");
						Bukkit.broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + "1" + ChatColor.RED + " - " +  ChatColor.AQUA + "CaptureTheDog" + ChatColor.RED + "  -  " + ChatColor.AQUA + "With " + ChatColor.GOLD + ChatColor.BOLD + tempData.getVoteCTD() + ChatColor.AQUA + " Votes!");
						Bukkit.broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + "2" + ChatColor.RED + " - " +  ChatColor.AQUA + "LastManStanding" + ChatColor.RED + "  -  " + ChatColor.AQUA + "With " + ChatColor.GOLD + ChatColor.BOLD + tempData.getVoteLMS() + ChatColor.AQUA + " Votes!");
						Bukkit.broadcastMessage("");
						num = 0;
					}
				}
			}, 0L, 700L);
		}
	
	static int time = 15;
	public static int startGame() {
		return Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Arcade"), new Runnable() {
			
			public void run() {
				if (!(time == 0)) {
					time--;
					Bukkit.broadcastMessage(Arcade.prefix + ChatColor.GREEN + "Game Starting In " + time + " Seconds!");
				}else{
					setInGame(true);
				}
				
			}
		}, 0L, 60L);
	}
	
	
	public static int doneVotingGame() {
		return Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Arcade"), new Runnable() {
			
			public void run() {
				
				Player p = (Player) Bukkit.getOnlinePlayers();
				
				Inventory selector = Bukkit.createInventory(p, 9, "Select A Team");
				
				int size = Bukkit.getOnlinePlayers().size();
				if (size >= 2) {
					Bukkit.getScheduler().cancelTask(doneVotingGame());
					int perTeam = size / 2;
					
					ItemStack tS = new ItemStack(Material.COMPASS);
					ItemMeta tSM = tS.getItemMeta();
					tSM.setDisplayName("Team Selector");
					tS.setItemMeta(tSM);
					p.getInventory().setItem(1, tS);
					
					ItemStack red = new ItemStack(Material.WOOL, 1, (short) 14);
					ItemMeta redM = red.getItemMeta();
					redM.setDisplayName(ChatColor.RED + "Join " + ChatColor.BOLD + "Red" + ChatColor.RED + " Team");
					red.setItemMeta(redM);
					
					ItemStack blue = new ItemStack(Material.WOOL, 1, (short) 11);
					ItemMeta blueM = blue.getItemMeta();
					blueM.setDisplayName(ChatColor.BLUE + "Join " + ChatColor.BOLD + "Blue" + ChatColor.BLUE + " Team");
					blue.setItemMeta(blueM);
					
					p.openInventory(selector);
					
				}else{
					return;
				}
				
			}
		}, 0L, 60L * 5);
	}
}
