package me.sweettie;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Handler implements Listener {

    public static List<String> dangerousWords = new ArrayList<>();
    public static Boolean randomPlayer = true;
    public static int radius;

    @EventHandler
    public void chat(AsyncPlayerChatEvent event) {
        if (event.getPlayer().getWorld() != Bukkit.getWorlds().get(0)) return;
        String message = event.getMessage().toLowerCase();
        for (String s : dangerousWords) {
            if (!message.contains(s)) return;
            Player player;
            if (randomPlayer) {
                Random random = new Random();
                player = Bukkit.getWorlds().get(0).getPlayers().get(random.nextInt(Bukkit.getWorlds().get(0).getPlayers().size()));
            } else {
                player = event.getPlayer();
            }
            Location loc = player.getLocation();
            loc.setY(player.getWorld().getHighestBlockYAt(loc.getBlockX(), loc.getBlockZ()));
            createStrikeLight(loc);
        }
    }

    public static double getRandomInt(double min, double max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    public void createStrikeLight(Location loc) {
        Location location = new Location(loc.getWorld(), loc.getBlockX() + getRandomInt(-radius, radius), loc.getY(), loc.getBlockZ() + getRandomInt(-radius, radius));
        Objects.requireNonNull(loc.getWorld()).strikeLightningEffect(location);
    }


}
