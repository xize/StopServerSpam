package xize.stopServerSpam.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import xize.stopServerSpam.StopServerSpam;

public class EndOfStream implements Listener {
	StopServerSpam plugin;
	public EndOfStream(StopServerSpam plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void checkEndOfStream(PlayerKickEvent e) {
		if(e.getReason().contains("endofstream")) {
			e.setReason("");
		}
	}

}
