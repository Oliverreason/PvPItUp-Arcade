package com.pvpitup.arcade.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.pvpitup.arcade.Arcade;
import com.pvpitup.arcade.GameTypes;
import com.pvpitup.arcade.teams.TeamException;
import com.pvpitup.arcade.teams.TeamManager;
import com.pvpitup.arcade.teams.teamstypes.Spectators;

public class onDeath implements Listener {
	
	@EventHandler
	public void Death(PlayerDeathEvent event) {
		GameTypes game = Arcade.getGame();
		Player p = (Player) event.getEntity();
		
		event.getDrops().clear();
		
		if (game == GameTypes.LOBBY) {
			event.setDeathMessage(null);
			
		} else if (game == GameTypes.LMS) {
			event.setDeathMessage(Arcade.getTeam(p.getName()).getColor() + p.getName() + " knocked off " + Arcade.getTeam(p.getKiller().getName()).getColor() + p.getKiller().getName());
			
			p.sendMessage("");
			p.sendMessage(Arcade.prefix + ChatColor.RED + "You have been knocked off!");
			try {
				TeamManager.joinTeam(p, new Spectators());
			} catch (TeamException e) {
				e.printStackTrace();
			}
			
			p.getKiller().sendMessage("");
			p.getKiller().sendMessage(Arcade.prefix + ChatColor.YELLOW + "You have knocked off: " + ChatColor.GOLD + p.getName());
		}
	}
}
