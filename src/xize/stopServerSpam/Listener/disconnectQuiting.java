package xize.stopServerSpam.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import xize.stopServerSpam.StopServerSpam;

public class disconnectQuiting implements Listener {
	StopServerSpam plugin;
	public disconnectQuiting(StopServerSpam plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void checkDisconnectQuiting(PlayerKickEvent e) {
		if(e.getReason().contains("disconnect quiting") || e.getReason().contains("disconnect.quiting")) {
			e.setReason("");
		}
	}

}
