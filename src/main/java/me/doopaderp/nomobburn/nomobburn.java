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
        //start up block place listener for this plugin
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlace(EntityCombustEvent e)
    {
        if (!(e instanceof org.bukkit.event.entity.EntityCombustByBlockEvent || e instanceof org.bukkit.event.entity.EntityCombustByEntityEvent)) {
            if (e.getEntity() instanceof Monster){
                if (toggleBurn) e.setCancelled(true);
            }
        }   
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("toggleburn")) {
            if (sender.hasPermission("toggleburn.use")) {
               if (toggleBurn) toggleBurn = false;
               else toggleBurn = true;
               return true;
            }
            sender.sendMessage(ChatColor.DARK_RED + "Sorry, but you don't have permission for this command.");
            return true;
        }
        return false;
    }
}

