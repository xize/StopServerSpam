package xize.stopServerSpam.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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
						if(sender.hasPermission("stopserverspam.command")) {
							sendHelp(sender);
						} else {
							permission perm = new permission(plugin);
							perm.getPermissionError(sender, cmd, args);
						}
					}
				} else if(args.length == 2) {
					if(args[0].equalsIgnoreCase("config")) {
						if(args[1].equalsIgnoreCase("disable") || args[1].equalsIgnoreCase("enable")) {
							if(sender.hasPermission("stopserverspam.command.config.changeable")) {
								sender.sendMessage("warning you need atleast one more argument in dot syntax!\nuse \"/" + cmd.getName() + " config\" to see in the list");
							} else {
								permission perm = new permission(plugin);
								perm.getPermissionError(sender, cmd, args);
							}
						}
					}
				} else if(args.length == 3) {
					config cfg = new config(plugin);
					if(args[0].equalsIgnoreCase("config")) {
						if(args[1].equalsIgnoreCase("enable")) {
							if(sender.hasPermission("stopserverspam.command.config.changeable")) {
								if(args[2].equalsIgnoreCase("disable-plugin")) {
									sender.sendMessage(ChatColor.RED + "warning use \"/stopserverspam config\" instead!");
									return false;
								}
								try {
									File f = new File(plugin.getDataFolder() + File.separator + "config.yml");
									if(f.exists()) {
										FileConfiguration con = YamlConfiguration.loadConfiguration(f);
										if(con.isSet(args[2])) {
											con.set(args[2], true);
											con.save(f);
											sender.sendMessage(ChatColor.GOLD + "[StopServerSpam]" + ChatColor.GRAY + " has saved your config changes!");
											cfg.reload();
										} else {
											sender.sendMessage(ChatColor.RED + "warning this is a invalid config path, please make sure you typed the correct dot syntax");
										}
									} else {
										sender.sendMessage(ChatColor.RED + "a error has been occuried, please reload the server or check if the plugin has write access");
										return false;
									}
								} catch(Exception e) {
									e.printStackTrace();
								}
							} else {
								permission perm = new permission(plugin);
								perm.getPermissionError(sender, cmd, args);
							}
						} else if(args[1].equalsIgnoreCase("disable")) {
							if(sender.hasPermission("stopserverspam.command.config.changeable")) {
								if(args[2].equalsIgnoreCase("disable-plugin")) {
									sender.sendMessage(ChatColor.RED + "warning use \"/stopserverspam config\" instead!");
									return false;
								}
								try {
									File f = new File(plugin.getDataFolder() + File.separator + "config.yml");
									if(f.exists()) {
										FileConfiguration con = YamlConfiguration.loadConfiguration(f);
										if(con.isSet(args[2])) {
											con.set(args[2], false);
											con.save(f);
											sender.sendMessage(ChatColor.GOLD + "[StopServerSpam]" + ChatColor.GRAY + " has saved your config changes!");
											cfg.reload();
										} else {
											sender.sendMessage(ChatColor.RED + "warning this is a invalid config path, please make sure you typed the correct dot syntax");
										}
									} else {
										sender.sendMessage(ChatColor.RED + "a error has been occuried, please reload the server or check if the plugin has write access");
										return false;
									}
								} catch(Exception e) {
									e.printStackTrace();
								}
							} else {
								permission perm = new permission(plugin);
								perm.getPermissionError(sender, cmd, args);
							}
						}
					}
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
