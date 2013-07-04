package xize.stopServerSpam.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import xize.stopServerSpam.StopServerSpam;

public class TimeOut implements Listener {
	StopServerSpam plugin;
	public TimeOut(StopServerSpam plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void checkTimeOut(PlayerKickEvent e) {
		if(e.getReason().contains("timeout") || e.getReason().contains("TimeOut")) {
			e.setReason("");
		}
	}
}
