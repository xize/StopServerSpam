package xize.stopServerSpam.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import xize.stopServerSpam.StopServerSpam;

public class GenericReason implements Listener {
	StopServerSpam plugin;
	public GenericReason(StopServerSpam plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void checkGenericReason(PlayerKickEvent e) {
		if(e.getReason().contains("GenericReason") || e.getReason().contains("Generic Reason")) {
			e.setReason("");
		}
	}

}
