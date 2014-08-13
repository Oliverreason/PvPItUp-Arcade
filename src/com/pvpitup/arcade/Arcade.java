package com.pvpitup.arcade;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.pvpitup.arcade.commands.ArcadeCommand;
import com.pvpitup.arcade.commands.VoteCommand;
import com.pvpitup.arcade.events.UtilEvents;
import com.pvpitup.arcade.events.onChat;
import com.pvpitup.arcade.events.onDeath;
import com.pvpitup.arcade.events.onJoin;
import com.pvpitup.arcade.events.onLeaveArcade;
import com.pvpitup.arcade.events.onMove;
import com.pvpitup.arcade.events.onQuit;
import com.pvpitup.arcade.teams.Team;
import com.pvpitup.arcade.teams.teamstypes.Blue;
import com.pvpitup.arcade.teams.teamstypes.Red;
import com.pvpitup.arcade.teams.teamstypes.Spectators;
import com.pvpitup.capturethedog.commands.CTC_Create;
import com.pvpitup.capturethedog.commands.CTC_SetBlue;
import com.pvpitup.capturethedog.commands.CTC_SetRed;
import com.pvpitup.capturethedog.commands.CTC_SetSpec;

public class Arcade extends JavaPlugin {
	
	public static Arcade instance;
	
	public static GameState state;
	public static GameTypes gameType;
	
	public static FileConfiguration ctcConfig = null;
    public static File ctcFile;
    
    public static FileConfiguration config = null;
    public static File file;
    
    public static String prefix = "[" + ChatColor.DARK_AQUA + "PvPitUP" + ChatColor.WHITE + "] ";
    
    private static List<Team> teams = new ArrayList<Team>();
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		SettingsManager.getInstance().setup(this);
		
		state = GameState.INLOBBY;
		
		tempData.voteRunnable();
		
		getCommand("vote").setExecutor(new VoteCommand());
		getCommand("arcade").setExecutor(new ArcadeCommand());
		getCommand("ctc_create").setExecutor(new CTC_Create());
		getCommand("ctc_setblue").setExecutor(new CTC_SetBlue());
		getCommand("ctc_setred").setExecutor(new CTC_SetRed());
		getCommand("ctc_setspec").setExecutor(new CTC_SetSpec());
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new onJoin(), this);
		pm.registerEvents(new onQuit(), this);
		pm.registerEvents(new onLeaveArcade(), this);
		pm.registerEvents(new onDeath(), this);
		pm.registerEvents(new onChat(), this);
		pm.registerEvents(new onMove(), this);
		pm.registerEvents(new UtilEvents(), this);
		
		teams.add(new Red());
		teams.add(new Blue());
		teams.add(new Spectators());
	}
	
	public static List<Team> getTeams() {
		return teams;
	}
	
	public static Team getTeam(String name) {
		for (Team team : getTeams()) {
			if (team.getName().contains(name)) {
			return team;
			}
		}
			return null;
	}
	
	public static Arcade getInstance() {
		return instance;
	}
	
	public static GameTypes getGame() {
		return gameType;
	}
	
	public static GameState getState() {
		return state;
	}
}
