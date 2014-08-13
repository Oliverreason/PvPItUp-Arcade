package com.pvpitup.arcade.teams;

import java.util.List;

import org.bukkit.ChatColor;

public interface Team {
	
	public String getName();
	
	public List<String> getPlayers();
	
	public ChatColor getColor();
}
