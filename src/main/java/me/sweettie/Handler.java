package me.sweettie;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Handler implements Listener {

    public static List<String> dangerousWords = new ArrayList<>();
    public static Boolean randomPlayer = true;

    @EventHandler
    public void chat(PlayerChatEvent event){
        if(event.getPlayer().getWorld().equals(Bukkit.getWorlds().get(0))) {
            String message = event.getMessage().toLowerCase();
            for (int i = 0; i < dangerousWords.size(); i++) {
                if (message.contains(dangerousWords.get(i).toLowerCase())) {
                    if (randomPlayer) {
                        Random random = new Random();
                        ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
                        int index = random.nextInt(players.size());
                        Player target = players.get(index);
                        Location playerLocation = event.getPlayer().getLocation();
                        Location location = new Location(event.getPlayer().getWorld(), playerLocation.getBlockX() + getRandomInt(-10.0, 10.0), playerLocation.getBlockY(), playerLocation.getBlockZ() + getRandomInt(-10.0, 10.0));
                        createStrikeLight(location,target);
                    }
                    else{
                        Player target = event.getPlayer();
                        Location playerLocation = event.getPlayer().getLocation();
                        Location location = new Location(event.getPlayer().getWorld(), playerLocation.getBlockX() + getRandomInt(-10.0, 10.0), playerLocation.getBlockY(), playerLocation.getBlockZ() + getRandomInt(-10.0, 10.0));
                        createStrikeLight(location,target);
                    }
                }
            }
        }
    }
    public static double getRandomInt(double min, double max){
        double x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }
    public void createStrikeLight(Location loc,Player target){
        target.getPlayer().getWorld().strikeLightningEffect(loc);
    }


}
