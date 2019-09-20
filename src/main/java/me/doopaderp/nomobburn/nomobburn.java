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

    Boolean toggleBurn = true;

    @Override
    public void onEnable() {
        //register event listener
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    //Handle any combustion event(fire charge, daylight, fire aspect sword)
    @EventHandler
    public void onPlace(EntityCombustEvent e)
    {
        //filter out fire caused by blocks and other entities
        if (!(e instanceof org.bukkit.event.entity.EntityCombustByBlockEvent || e instanceof org.bukkit.event.entity.EntityCombustByEntityEvent)) {
            //make sure the mob is a monster(zombie, skeleton, zombiepigman)
            if (e.getEntity() instanceof Monster){
                //cancel the combustion
                if (toggleBurn) e.setCancelled(true);
            }
        }   
    }

    //listen for commands
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //if the command is one of mine
        if (label.equalsIgnoreCase("toggleburn")) {
            //if the command user has permissions to use it
            if (sender.hasPermission("toggleburn.use")) {
               //if burning is on, toggle it off and visa versa
               if (toggleBurn) toggleBurn = false;
               else toggleBurn = true;
               return true;
            }
            //send the user a message saying they cant use the command if they dont have permissions
            sender.sendMessage(ChatColor.DARK_RED + "Sorry, but you don't have permission for this command.");
            return true;
        }
        return false;
    }
}

