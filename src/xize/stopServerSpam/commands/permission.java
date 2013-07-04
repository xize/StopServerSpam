package xize.stopServerSpam.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import xize.stopServerSpam.StopServerSpam;

public class permission {
	StopServerSpam plugin;
	public permission(StopServerSpam plugin) {
		this.plugin = plugin;
	}
	
	public void getPermissionError(CommandSender sender, Command cmd, String[] args) {
		if(cmd.getName().equalsIgnoreCase("stopserverspam")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.RED + "you don't have permission for /" + cmd.getName() + "\n" + ChatColor.GRAY + "permission: stopserverspam.command");
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("reload")) {
					sender.sendMessage(ChatColor.RED + "you don't have permission for /" + cmd.getName() + args[0] + "\n" + ChatColor.GRAY + "permission: stopserverspam.command.reload");
				} else if(args[0].equalsIgnoreCase("config")) {
					sender.sendMessage(ChatColor.RED + "you don't have permission for /" + cmd.getName() + args[0] + "\n" + ChatColor.GRAY + "permission: stopserverspam.command.config");
				} else if(args[0].equalsIgnoreCase("enable")) {
					sender.sendMessage(ChatColor.RED + "you don't have permission for /" + cmd.getName() + args[0] + "\n" + ChatColor.GRAY + "permission: stopserverspam.command.enable");
				} else if(args[0].equalsIgnoreCase("disable")) {
					sender.sendMessage(ChatColor.RED + "you don't have permission for /" + cmd.getName() + args[0] + "\n" + ChatColor.GRAY + "permission: stopserverspam.command.disable");
				} else if(args[0].equalsIgnoreCase("help")) {
					sender.sendMessage(ChatColor.RED + "you don't have permission for /" + cmd.getName() + args[0] + "\n" + ChatColor.GRAY + "permission: stopserverspam.command");
				}
			}
		}
	}

}
