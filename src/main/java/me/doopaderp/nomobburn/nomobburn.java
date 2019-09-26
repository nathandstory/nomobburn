package me.doopaderp.nomobburn;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.plugin.java.JavaPlugin;
import net.md_5.bungee.api.ChatColor;

public class nomobburn extends JavaPlugin implements Listener{

    private Boolean cancelBurn = true;

    @Override
    public void onEnable() {
        //register event listener
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    //Handle any combustion event(fire charge, daylight, fire aspect sword)
    @EventHandler
    public void onCombust(EntityCombustEvent e)
    {
        //check if we need to check at all
        if (cancelBurn == false) return;
        //filter out fire caused by blocks and other entities
        if (e instanceof org.bukkit.event.entity.EntityCombustByBlockEvent || e instanceof org.bukkit.event.entity.EntityCombustByEntityEvent) return;
        //make sure the mob is a monster(zombie, skeleton, zombiepigman)
        if (!(e.getEntity() instanceof Monster)) return;
        //cancel the combustion
        e.setCancelled(true);
        return;
    }

    //listen for commands
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //if the command is one of mine
        if (label.equalsIgnoreCase("toggleburn")) {
            //if the command user has permissions to use it
            if (sender.hasPermission("toggleburn.use")) {
               //if burning is on, toggle it off and visa versa
               cancelBurn = !cancelBurn;
               String enabledText = cancelBurn ? "§cdisabled" : "§aenabled";
               sender.sendMessage(ChatColor.GOLD + "Mob burning is now " + enabledText);
               enabledText = null;
               return true;
            }
            //send the user a message saying they cant use the command if they dont have permissions
            sender.sendMessage(ChatColor.DARK_RED + "Sorry, but you don't have permission for this command.");
            return true;
        }
        return false;
    }
}

