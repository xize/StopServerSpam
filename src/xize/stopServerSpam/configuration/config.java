package xize.stopServerSpam.configuration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.HandlerList;
import xize.stopServerSpam.StopServerSpam;
import xize.stopServerSpam.Listener.handler;

public class config {
	StopServerSpam plugin;
	public config(StopServerSpam plugin) {
		this.plugin = plugin;
	}
	
	Logger log = Logger.getLogger("Minecraft");
	
	public void createConfig() {
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "config.yml");
			if(f.exists()) {
				log.info("[StopServerSpam] config found!, checking for config updates");
				checkConfig(f);
			} else {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				FileConfigurationOptions opt = con.options();
				opt.header("Default config for StopServerSpam");
				con.set("disable-plugin", false);
				con.set("consoleWarnings.EndOfStream", true);
				con.set("consoleWarnings.LostConnection", true);
				con.set("consoleWarnings.GenericReason", true);
				con.set("consoleWarnings.TimeOut", true);
				con.set("consoleWarnings.disconnectQuiting", false);
				con.save(f);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkConfig(File f) throws IOException {
		FileConfiguration con = YamlConfiguration.loadConfiguration(f);
		String[] list = {
			"disable-plugin",
			"consoleWarnings.EndOfStream",
			"consoleWarnings.LostConnection",
			"consoleWarnings.GenericReason",
			"consoleWarnings.TimeOut",
			"consoleWarnings.disconnectQuiting"
		};
		for(int i = 0; i < list.length; i++) {
			if(!con.isSet(list[i])) {
				con.set(list[i], true);
				log.info("[StopServerSpam] new config entry added!");
				con.save(f);
			}
		}
		return false;
	}
	
	public void reload() {
		createConfig();
		HandlerList.unregisterAll(plugin);
		handler Handler = new handler(plugin);
		Handler.activateListener();
		
	}
	
	public void showConfigVars(CommandSender sender) {
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "config.yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.isSet("disable-plugin")) {
					if(!con.isBoolean("disable-plugin")) {
						sender.sendMessage("disable-plugin: false");
					} else {
						sender.sendMessage("disable-plugin: true");
					}
					
				}
				if(con.isSet("consoleWarnings.EndOfStream")) {
					if(con.isBoolean("consoleWarnings.EndOfStream")) {
						sender.sendMessage("consoleWarnings.EndOfStream: true");
					} else {
						sender.sendMessage("consoleWarnings.EndOfStream: false");
					}
					
				}
				if(con.isSet("consoleWarnings.LostConnection")) {
					if(con.isBoolean("consoleWarnings.LostConnection")) {
						sender.sendMessage("consoleWarnings.LostConnection: true");
					} else {
						sender.sendMessage("consoleWarnings.LostConnection: false");
					}
					
				}
				if(con.isSet("consoleWarnings.GenericReason")) {
					if(con.isBoolean("consoleWarnings.GenericReason")) {
						sender.sendMessage("consoleWarnings.GenericReason: true");
					} else {
						sender.sendMessage("consoleWarnings.GenericReason: false");
					}
					
				}
				if(con.isSet("consoleWarnings.TimeOut")) {
					if(con.isBoolean("consoleWarnings.TimeOut")) {
						sender.sendMessage("consoleWarnings.TimeOut: true");
					} else {
						sender.sendMessage("consoleWarnings.TimeOut: false");
					}
					
				}
				if(con.isSet("consoleWarnings.disconnectQuiting")) {
					if(con.isBoolean("consoleWarnings.disconnectQuiting")) {
						sender.sendMessage("consoleWarnings.disconnectQuiting: true");
					} else {
						sender.sendMessage("consoleWarnings.disconnectQuiting: false");
					}
					
				}
			} else {
				sender.sendMessage(ChatColor.RED + "warning config.yml whas not found, retry this command if you want to see a new list");
				createConfig();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
