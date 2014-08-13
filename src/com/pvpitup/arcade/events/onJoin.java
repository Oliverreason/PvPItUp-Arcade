package com.pvpitup.arcade.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.pvpitup.arcade.Arcade;

public class onJoin implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(Arcade.prefix + ChatColor.AQUA + "Welcome " + ChatColor.GREEN + p.getName() + " to the game!");
		
		p.getInventory().clear();
	}
}
