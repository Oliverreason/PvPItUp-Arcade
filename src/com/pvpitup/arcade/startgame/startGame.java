package com.pvpitup.arcade.startgame;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.pvpitup.arcade.Arcade;
import com.pvpitup.arcade.GameTypes;
import com.pvpitup.arcade.SettingsManager;
import com.pvpitup.arcade.tempData;

public class startGame implements Listener {
	
	SettingsManager sm = SettingsManager.getInstance();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (Bukkit.getOnlinePlayers().length >= 2) {
			int voteCTC = tempData.getVoteCTD();
			int voteLMS = tempData.getVoteLMS();
			if (voteCTC == voteLMS) {
				Random rand = new Random();
				int rando = rand.nextInt(1);
				if (rando == 0) {
					tempData.setType("CTC");
					return;
				}else if (rando == 1) {
					tempData.setType("LMS");
					return;
				}
			}else if (voteCTC > voteLMS) {
				Arcade.gameType = GameTypes.CTD;
			}else{
				Arcade.gameType = GameTypes.LMS;
			}
			tempData.startGame();
		}else{
			return;
		}
	}
	
	public void start() {
		for (Player online : Bukkit.getOnlinePlayers()) {
			World world = (World) sm.getLmsConfig().get("");
			double x = sm.getLmsConfig().getDouble("");
			double y = sm.getLmsConfig().getDouble("");
			double z = sm.getLmsConfig().getDouble("");
			online.teleport(new Location(world, x, y, z));
		}
	}
}
