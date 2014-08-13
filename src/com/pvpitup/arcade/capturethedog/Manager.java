package com.pvpitup.arcade.capturethedog;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

import com.pvpitup.arcade.Arcade;
import com.pvpitup.arcade.GameState;

public class Manager implements Listener {
	
	static int time = 0;
	public static void gameStart() {
			Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Arcade"), new Runnable() {
				public void run() {
					if (time == 0) {
						Arcade.state = GameState.PREGAME;
						Bukkit.broadcastMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "3");
						time ++;
					} else if (time == 1) {
						Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "2");
						time ++;
					} else if (time == 2) {
						Arcade.state = GameState.GAME;
						Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "1");
					    time ++;
					} else if (time == 3) {
						Bukkit.broadcastMessage(Arcade.prefix + ChatColor.BOLD + "GO!");
					}
			}
		}, 0L, 15L);
	}
}
