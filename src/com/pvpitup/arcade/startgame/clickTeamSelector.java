package com.pvpitup.arcade.startgame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.pvpitup.arcade.teams.TeamException;
import com.pvpitup.arcade.teams.TeamManager;
import com.pvpitup.arcade.teams.teamstypes.Blue;
import com.pvpitup.arcade.teams.teamstypes.Red;

public class clickTeamSelector implements Listener {
	
	@EventHandler
	public void onClickTeamSelector(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		
		Inventory selector = Bukkit.createInventory(p, 9, "Team Selector");
		
		ItemStack red = new ItemStack(Material.WOOL, 1, (short) 14);
		ItemMeta redM = red.getItemMeta();
		redM.setDisplayName(ChatColor.RED + "Join " + ChatColor.BOLD + "Red" + ChatColor.RED + " Team");
		red.setItemMeta(redM);
		
		ItemStack blue = new ItemStack(Material.WOOL, 1, (short) 11);
		ItemMeta blueM = blue.getItemMeta();
		blueM.setDisplayName(ChatColor.BLUE + "Join " + ChatColor.BOLD + "Blue" + ChatColor.BLUE + " Team");
		blue.setItemMeta(blueM);
		
		selector.setItem(2, red);
		selector.setItem(6, blue);
		
		if (p.getInventory().getItemInHand().getType() == Material.COMPASS) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				p.openInventory(selector);
			}
		}else{
			return;
		}
		
	}
	
	@EventHandler
	public void onTeamSelectorClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equalsIgnoreCase("Team Selector")) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Join " + ChatColor.BOLD + "Red" + ChatColor.RED + " Team")) {
				
				try {
					TeamManager.joinTeam(p, new Red());
				} catch (TeamException e1) {
					e1.printStackTrace();
				}
				
				e.setCancelled(true);
				e.setCurrentItem(new ItemStack(Material.AIR));
				p.closeInventory();
			}else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "Join " + ChatColor.BOLD + "Blue" + ChatColor.BLUE + " Team")){
				
				try {
					TeamManager.joinTeam(p, new Blue());
				} catch (TeamException e1) {
					e1.printStackTrace();
				}
				
				e.setCancelled(true);
				e.setCurrentItem(new ItemStack(Material.AIR));
				p.closeInventory();
			}else{
				return;
			}
		}else{
			return;
		}
	}
	

}
