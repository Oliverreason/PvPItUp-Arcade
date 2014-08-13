package com.pvpitup.arcade.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import com.pvpitup.arcade.Arcade;
import com.pvpitup.arcade.GameState;

public class UtilEvents implements Listener {
	
	// Block Events
	
	@EventHandler
	public void onBlockBurn(BlockBurnEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player p = event.getPlayer();
		if (p.isOp()) {
			event.setCancelled(false);
		} else {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		if (p.isOp()) {
			event.setCancelled(false);
		} else {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onLeaveDecay(LeavesDecayEvent event) {
		event.setCancelled(true);
	}
	
	// Player Events
	
	@EventHandler
	public void onPlayerFish(PlayerFishEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerItemDrop(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerHitPlayer(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			//Player p = (Player) e.getEntity();
			//Player damager = (Player) e.getDamager();
			GameState state = Arcade.getState();
			
			if (state == GameState.INLOBBY || state == GameState.PREGAME) {
				e.setCancelled(true);
			}
		}else{
			return;
		}
	}
	
	@EventHandler
	public void onPlayerHit(EntityDamageEvent event) {
		GameState state = Arcade.getState();
		
		if (state == GameState.INLOBBY || state == GameState.PREGAME) {
			event.setCancelled(true);
		}else{
			event.setCancelled(false);
		}
	}
	
	// Weather Events
	
	@EventHandler
	public void onLightningStrike(LightningStrikeEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		event.setCancelled(true);
	}
	
	// Other Events
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		e.setFoodLevel(20);
	}
	
}
