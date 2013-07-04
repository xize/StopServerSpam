package xize.stopServerSpam.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import xize.stopServerSpam.StopServerSpam;

public class LostConnection implements Listener {
	StopServerSpam plugin;
	public LostConnection(StopServerSpam plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void checkLostConnection(PlayerKickEvent e) {
		if(e.getReason().contains("LostConnection") || e.getReason().contains("Lost Connection")) {
			e.setReason("");
		}
	}
}
