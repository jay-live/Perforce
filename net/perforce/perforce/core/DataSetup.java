package net.perforce.perforce.core;

import net.perforce.perforce.Main;
import net.perforce.perforce.storage.Data_Lists;
import net.perforce.perforce.storage.main.Main_DB;
import net.perforce.perforce.users.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class DataSetup implements Listener {

    private final Main plugin = Main.getInstance;
    private Main_DB mainDB;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        User user = new User(event.getPlayer());
        mainDB.addNewPlayer(user);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        User user = new User(event.getPlayer());
        Data_Lists.removeUser(user);
    }

}
