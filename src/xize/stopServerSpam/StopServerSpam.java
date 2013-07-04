package xize.stopServerSpam;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import xize.stopServerSpam.configuration.config;

public class StopServerSpam extends JavaPlugin {
	Logger log = Logger.getLogger("Minecraft");
	
	private config cfg = new config(this);
	
	public void onEnable() {
		log.info("[StopServerSpam] enabled!");
		cfg.createConfig();
	}
	
	public void onDisable() {
		log.info("[StopServerSpam] has been disabled!");
	}

}
