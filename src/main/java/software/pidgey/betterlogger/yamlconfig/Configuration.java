package software.pidgey.betterlogger.yamlconfig;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class Configuration {

    private static File file;
    private static FileConfiguration configFile;

    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("BetterLogger").getDataFolder().getAbsolutePath(),
                "config.yaml");

        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (Exception e){

            }
        }
    }
}
