package com.pvpitup.arcade.teams;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.pvpitup.arcade.Arcade;
import com.pvpitup.arcade.GameState;

public class TeamManager {
	
	public static HashMap<Player, Team> teams = new HashMap<Player, Team>();
	
	public static void joinTeam(Player p, Team team) throws TeamException {
		if (Arcade.getState() == GameState.LOBBY) {
			for (Team t : Arcade.getTeams()) {
				if (t.getPlayers().contains(p.getName())) {
					t.getPlayers().remove(p.getName());
				}else{
					teams.put(p, team);
				}
			}
			
			team.getPlayers().add(p.getName());
			p.sendMessage(Arcade.prefix + ChatColor.YELLOW + "You are on team: " + team.getColor() + team.getName());
		} else {
			throw new TeamException("Teams can't be changed unless the game is in lobby");
		}
	}
}
