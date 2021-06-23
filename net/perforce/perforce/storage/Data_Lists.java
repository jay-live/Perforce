package net.perforce.perforce.storage;

import net.perforce.perforce.storage.gamemodes.duels.types.*;
import net.perforce.perforce.storage.main.types.*;
import net.perforce.perforce.storage.friends.types.Friend;
import net.perforce.perforce.users.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Data_Lists {

    public static Map<UUID, Coins> Player_Stats_Coins = new HashMap<>();
    public static Map<UUID, Level> Player_Stats_Level = new HashMap<>();
    public static Map<UUID, HeadsCollected> Player_Stats_HeadsCollected = new HashMap<>();
    public static Map<UUID, PlayersHidden> Player_Stats_PlayersHidden = new HashMap<>();

    public static Map<UUID, Friend> Player_Friends = new HashMap<>();

    public static Map<UUID, Duels_Coins> Player_Duels_Coins = new HashMap<>();
    public static Map<UUID, Duels_Level> Player_Duels_Level = new HashMap<>();
    public static Map<UUID, Duels_Wins> Player_Duels_Wins = new HashMap<>();
    public static Map<UUID, Duels_Kills> Player_Duels_Kills = new HashMap<>();

    public static void removeUser(User user) {
        UUID uuid = user.getUuid();
        Player_Stats_Coins.remove(uuid);
        Player_Stats_Level.remove(uuid);
        Player_Stats_HeadsCollected.remove(uuid);
        Player_Stats_PlayersHidden.remove(user.getUuid());

        Player_Duels_Coins.remove(uuid);
        Player_Duels_Level.remove(uuid);
        Player_Duels_Wins.remove(uuid);
        Player_Duels_Kills.remove(uuid);

        Player_Friends.remove(uuid);
    }


}
