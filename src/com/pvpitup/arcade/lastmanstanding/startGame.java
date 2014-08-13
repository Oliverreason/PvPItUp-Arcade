package com.pvpitup.arcade.lastmanstanding;

import org.bukkit.World;

import com.pvpitup.arcade.Arcade;
import com.pvpitup.arcade.GameState;
import com.pvpitup.arcade.GameTypes;
import com.pvpitup.arcade.SettingsManager;

public class startGame {
	
	SettingsManager sm = SettingsManager.getInstance();
	
	public void onStartGame() {
		
		Arcade.gameType = GameTypes.LMS;
		Arcade.state = GameState.PREGAME;
		
		World world = (World) sm.getLmsConfig().get("");
		double x = sm.getLmsConfig().getDouble("");
		double y = sm.getLmsConfig().getDouble("");
		double z = sm.getLmsConfig().getDouble("");
		double yaw = sm.getLmsConfig().getDouble("");
		double pitch = sm.getLmsConfig().getDouble("");
		
	}
}
