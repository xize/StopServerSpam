package xize.stopServerSpam.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import xize.stopServerSpam.StopServerSpam;
import xize.stopServerSpam.configuration.config;

public class command implements CommandExecutor {
	StopServerSpam plugin;
	public command(StopServerSpam plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("stopserverspam")) {
			if(sender.hasPermission("stopserverspam.command")) {
				if(args.length == 0) {
					sendHelp(sender);
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("reload")) {
						if(sender.hasPermission("stopserverspam.command.reload")) {
							config cfg = new config(plugin);
							cfg.reload();
							sender.sendMessage(ChatColor.GOLD + "[StopServerSpam]" + ChatColor.GRAY + " has been successfully reloaded!");
						} else {
							permission perm = new permission(plugin);
							perm.getPermissionError(sender, cmd, args);
						}
					} else if(args[0].equalsIgnoreCase("config")) {
						if(sender.hasPermission("stopserverspam.command.config")) {
							config cfg = new config(plugin);
							sender.sendMessage(ChatColor.GOLD + ".oO___[configurable options]___Oo.");
							sender.sendMessage("these are the variable key sets which you can use with /sss config enable <dotSyntax>");
							cfg.showConfigVars(sender);
						} else {
							permission perm = new permission(plugin);
							perm.getPermissionError(sender, cmd, args);
						}
					} else if(args[0].equalsIgnoreCase("enable")) {
						if(sender.hasPermission("stopserverspam.command.enable")) {
							config cfg = new config(plugin);
							if(cfg.isEnabled()) {
								sender.sendMessage(ChatColor.RED + "this plugin is allready enabled!");
							} else {
								sender.sendMessage(ChatColor.GOLD + "[StopServerSpam]" + ChatColor.GRAY + " enabling plugin");
								cfg.setEnabled();
							}
						} else {
							permission perm = new permission(plugin);
							perm.getPermissionError(sender, cmd, args);
						}
					} else if(args[0].equalsIgnoreCase("disable")) {
						if(sender.hasPermission("stopserverspam.command.disable")) {
							config cfg = new config(plugin);
							if(!cfg.isEnabled()) {
								sender.sendMessage(ChatColor.RED + "this plugin is allready disabled!");
							} else {
								sender.sendMessage(ChatColor.GOLD + "[StopServerSpam]" + ChatColor.GRAY + " disabling plugin");
								cfg.setDisabled();
							}
						} else {
							permission perm = new permission(plugin);
							perm.getPermissionError(sender, cmd, args);
						}
					} else if(args[0].equalsIgnoreCase("help")) {
						if(sender.hasPermission("help")) {
							sendHelp(sender);
						} else {
							permission perm = new permission(plugin);
							perm.getPermissionError(sender, cmd, args);
						}
					}
				} else if(args.length == 2) {
					
				}
			} else {
				permission perm = new permission(plugin);
				perm.getPermissionError(sender, cmd, args);
			}
		}
		return false;
	}
	
	public void sendHelp(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + ".oO___[StopServerSpam]___Oo.");
		sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/stopserverspam reload " + ChatColor.WHITE + ": reloads plugin configuration");
		sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/stopserverspam config " + ChatColor.WHITE + ": a list of config settings");
		sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/stopserverspam config disable (name) " + ChatColor.WHITE + ": change a setting inside the config");
		sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/stopserverspam config enable (name) " + ChatColor.WHITE + ": change a setting inside the config");
		sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/stopserverspam disable " + ChatColor.WHITE + ": disable the plugin functionality");
		sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/stopserverspam enable " + ChatColor.WHITE + ": enable the plugin functionality");
		sender.sendMessage(ChatColor.RED + "Admin: " + ChatColor.GRAY + "/stopserverspam help " + ChatColor.WHITE + ": shows the same interface as here");
	}

}
