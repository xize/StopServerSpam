package xize.stopServerSpam.Listener;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

import xize.stopServerSpam.StopServerSpam;
import xize.stopServerSpam.configuration.config;

public class handler {	
	StopServerSpam plugin;
	public handler(StopServerSpam plugin) {
		this.plugin = plugin;
	}
	
	Logger log = Logger.getLogger("Minecraft");
	
	public void activateListener() {
			log.info("activated");
			if(checkKey("consoleWarnings.EndOfStream")) { getListener(new EndOfStream(plugin)); }
			if(checkKey("consoleWarnings.LostConnection")) { getListener(new LostConnection(plugin)); }
			if(checkKey("consoleWarnings.GenericReason")) { getListener(new GenericReason(plugin)); }
			if(checkKey("consoleWarnings.TimeOut")) { getListener(new TimeOut(plugin)); }
			if(checkKey("consoleWarnings.disconnectQuiting")) { getListener(new disconnectQuiting(plugin)); }
	}
	
	public void getListener(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, plugin);
	}
	
	public boolean checkKey(String path) {
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "config.yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.getBoolean(path)) {
					return true;
				} else {
					return false;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkPlugin() {
		config cfg = new config(plugin);
		if(!cfg.isEnabled()) {
			return true;
		}
		return false;
	}
}
