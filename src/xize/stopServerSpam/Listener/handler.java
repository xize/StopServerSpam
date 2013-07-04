package xize.stopServerSpam.Listener;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

import xize.stopServerSpam.StopServerSpam;

public class handler {	
	StopServerSpam plugin;
	public handler(StopServerSpam plugin) {
		this.plugin = plugin;
	}
	
	public void activateListener() {
		if(!checkKey("disable-plugin")) {
			if(checkKey("consoleWarnings.EndOfStream")) { getListener(new EndOfStream(plugin)); }
			if(checkKey("consoleWarnings.LostConnection")) { getListener(new LostConnection(plugin)); }
			if(checkKey("consoleWarnings.GenericReason")) { getListener(new GenericReason(plugin)); }
			if(checkKey("consoleWarnings.TimeOut")) { getListener(new TimeOut(plugin)); }
			if(checkKey("consoleWarnings.disconnect.quiting")) { getListener(new disconnectQuiting(plugin)); }
		}
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
}
