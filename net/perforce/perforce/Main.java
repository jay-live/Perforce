package net.perforce.perforce;

import net.perforce.jayapi.JayAPI;
import net.perforce.perforce.storage.MongoDB;
import net.perforce.perforce.storage.main.Main_DB;
import net.perforce.perforceapi.PerforceAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main getInstance;
    public JayAPI jayAPI = (JayAPI) Bukkit.getServer().getPluginManager().getPlugin("JayAPI");
    public PerforceAPI perforceAPI = (PerforceAPI) Bukkit.getServer().getPluginManager().getPlugin("PerforceAPI");

    public void onEnable() {
        getInstance = this;
        registerData();
        new MongoDB();
    }

    public void onDisable() {
    }

    public void registerData() {
        new MongoDB();
        new Main_DB();
    }

}
