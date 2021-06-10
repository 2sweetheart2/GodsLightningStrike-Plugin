package me.sweettie;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static Logger log = Logger.getLogger("Minecraft");
    private List<String> words = new ArrayList<>();
    private Boolean randomPlayer = true;

    @Override
    public void onEnable() {
        getLogger().info("ENABLE!");
        File config = new File(getDataFolder() + File.separator+"config.yml");
        if(!config.exists()){
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
        Bukkit.getPluginManager().registerEvents(new Handler(),this);
        words = getConfig().getStringList("dangerousWords");
        randomPlayer = getConfig().getBoolean("randomPlayer");

        setDangerousWords();
    }

    @Override
    public void onDisable() {
        getLogger().info("DISABLE!");
    }

    private void setDangerousWords(){
        Handler.dangerousWords = words;
        Handler.randomPlayer = randomPlayer;
    }
}
