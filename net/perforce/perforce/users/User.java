package net.perforce.perforce.users;

import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class User {

    private final UUID uuid;
    private final OfflinePlayer player;
    private final String name;
    private final String displayName;

    public User(OfflinePlayer player) {
        this.uuid = player.getUniqueId();
        this.player = player;
        this.name = player.getName();
        this.displayName = player.getPlayer().getDisplayName();
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public OfflinePlayer getPlayer() {
        return this.player;
    }

    public String getName() {
        return this.name;
    }

    public String getDispayName() {
        return this.displayName;
    }

}
