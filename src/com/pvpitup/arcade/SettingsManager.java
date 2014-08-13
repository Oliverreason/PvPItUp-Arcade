package com.pvpitup.arcade;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class SettingsManager {

	public SettingsManager() {
	}

	static SettingsManager instance = new SettingsManager();

	public static SettingsManager getInstance() {
		return instance;
	}

	Plugin p;

	FileConfiguration CTCconfig; // ctc.yml
	File cfile;

	FileConfiguration LMSconfig; // ctc.yml
	File lfile;

	FileConfiguration data; // config.yml
	File dfile;

	public void setup(Plugin p) {
		dfile = new File(p.getDataFolder(), "config.yml");
		data = p.getConfig();
		// config.options().copyDefaults(true);
		// saveConfig();

		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}

		cfile = new File(p.getDataFolder(), "ctc.yml");

		if (!cfile.exists()) {
			try {
				cfile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger()
						.severe(ChatColor.RED + "Could not create ctc.yml!");
			}
		}

		lfile = new File(p.getDataFolder(), "lms.yml");

		if (!lfile.exists()) {
			try {
				lfile.createNewFile();
				LMSconfig.set("LMS.NumberOfArenas", "0");
			} catch (IOException e) {
				Bukkit.getLogger().severe(
						ChatColor.RED + "Could not create lms.yml");
			}
		}

		LMSconfig = YamlConfiguration.loadConfiguration(lfile);
		CTCconfig = YamlConfiguration.loadConfiguration(cfile);
	}

	public FileConfiguration getData() {
		return data;
	}

	public void saveData() {
		try {
			data.save(dfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger()
					.severe(ChatColor.RED + "Could not save data.yml!");
		}
	}

	public void reloadData() {
		data = YamlConfiguration.loadConfiguration(dfile);
	}

	public FileConfiguration getCtcConfig() { // ctc
		return CTCconfig;
	}

	public void saveCtcConfig() { // ctc
		try {
			CTCconfig.save(cfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger()
					.severe(ChatColor.RED + "Could not save ctc.yml!");
		}
	}

	public void reloadCtcConfig() { // ctc
		CTCconfig = YamlConfiguration.loadConfiguration(cfile);
	}

	public FileConfiguration getLmsConfig() { // lms
		return LMSconfig;
	}

	public void saveLmsConfig() { // lms
		try {
			LMSconfig.save(lfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger()
					.severe(ChatColor.RED + "Could not save lms.yml!");
		}
	}

	public void reloadLmsConfig() { // lms
		LMSconfig = YamlConfiguration.loadConfiguration(lfile);
	}

	public PluginDescriptionFile getDesc() {
		return p.getDescription();
	}
}
