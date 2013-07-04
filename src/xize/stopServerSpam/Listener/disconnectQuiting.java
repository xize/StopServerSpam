package xize.stopServerSpam.Listener;

import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import xize.stopServerSpam.StopServerSpam;

public class disconnectQuiting implements Listener {
	StopServerSpam plugin;
	public disconnectQuiting(StopServerSpam plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void checkDisconnect2(final PlayerQuitEvent e) {
		Bukkit.getLogger().addHandler(new Handler()
		{

			@Override
			public void close() throws SecurityException {
				// TODO Auto-generated method stub

			}

			@Override
			public void flush() {
				// TODO Auto-generated method stub

			}

			@Override
			public void publish(LogRecord record) {
				if(record.getMessage().equalsIgnoreCase(e.getPlayer().getName() + " lost connection: disconnect.quitting")) {
					Bukkit.broadcastMessage("normal disconnect");
				}
			}
		});
	}

}
