package com.pvpitup.arcade.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.pvpitup.arcade.Arcade;
import com.pvpitup.arcade.GameState;

public class onMove implements Listener {
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		//Player p = e.getPlayer();
		if (Arcade.getState() == GameState.PREGAME) {
		}else{
			return;
		}
	}

}
