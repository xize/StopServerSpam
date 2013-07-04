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
						sender.sendMessage(ChatColor.GRAY + "disable-plugin:" + ChatColor.GREEN + "false (plugin filtering is enabled)");
					} else {
						sender.sendMessage(ChatColor.GRAY + "disable-plugin:" + ChatColor.RED + "true (plugin does nothing)");
					}

				}
				if(con.isSet("consoleWarnings.EndOfStream")) {
					if(con.isBoolean("consoleWarnings.EndOfStream")) {
						sender.sendMessage(ChatColor.GRAY + "consoleWarnings.EndOfStream:" + ChatColor.GREEN + "true");
					} else {
						sender.sendMessage(ChatColor.GRAY + "consoleWarnings.EndOfStream:" + ChatColor.RED + "false");
					}

				}
				if(con.isSet("consoleWarnings.LostConnection")) {
					if(con.isBoolean("consoleWarnings.LostConnection")) {
						sender.sendMessage(ChatColor.GRAY + "consoleWarnings.LostConnection:" + ChatColor.GREEN + "true");
					} else {
						sender.sendMessage(ChatColor.GRAY + "consoleWarnings.LostConnection:" + ChatColor.RED + " false");
					}

				}
				if(con.isSet("consoleWarnings.GenericReason")) {
					if(con.isBoolean("consoleWarnings.GenericReason")) {
						sender.sendMessage(ChatColor.GRAY + "consoleWarnings.GenericReason:" +ChatColor.GREEN + " true");
					} else {
						sender.sendMessage(ChatColor.GRAY + "consoleWarnings.GenericReason:" + ChatColor.RED + " false");
					}

				}
				if(con.isSet("consoleWarnings.TimeOut")) {
					if(con.isBoolean("consoleWarnings.TimeOut")) {
						sender.sendMessage(ChatColor.GRAY + "consoleWarnings.TimeOut:" + ChatColor.GREEN + "true");
					} else {
						sender.sendMessage(ChatColor.GRAY + "consoleWarnings.TimeOut:" + ChatColor.RED + "false");
					}

				}
				if(con.isSet("consoleWarnings.disconnectQuiting")) {
					if(con.isBoolean("consoleWarnings.disconnectQuiting")) {
						sender.sendMessage(ChatColor.GRAY + "consoleWarnings.disconnectQuiting:" + ChatColor.GREEN + "true");
					} else {
						sender.sendMessage(ChatColor.GRAY + "consoleWarnings.disconnectQuiting:" + ChatColor.RED + "false");
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

	public boolean isEnabled() {
		try {
			File f = new File(plugin.getDataFolder() + File.separator + "config.yml");
			FileConfiguration con = YamlConfiguration.loadConfiguration(f);
			if(!con.getBoolean("disable-plugin")) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean setEnabled() {
		try {
			if(isEnabled()) {
				try {
					File f = new File(plugin.getDataFolder() + File.separator + "config.yml");
					FileConfiguration con = YamlConfiguration.loadConfiguration(f);
					con.set("disable-plugin", false);
					con.save(f);
					reload();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else {

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean setDisabled() {
		try {
			if(!isEnabled()) {
				try {
					File f = new File(plugin.getDataFolder() + File.separator + "config.yml");
					FileConfiguration con = YamlConfiguration.loadConfiguration(f);
					con.set("disable-plugin", true);
					con.save(f);
					reload();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else {

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
