package com.pvpitup.arcade.teams.teamstypes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import com.pvpitup.arcade.teams.Team;

public class Blue implements Team {

	private List<String> players = new ArrayList<String>();
	
	public String getName() {
		return "Blue";
	}

	public List<String> getPlayers() {
		return players;
	}

	public ChatColor getColor() {
		return ChatColor.BLUE;
	}

}
