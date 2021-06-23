package net.perforce.perforce.storage.gamemodes.duels;

import com.mongodb.client.MongoCollection;
import net.perforce.perforce.storage.Data_Lists;
import net.perforce.perforce.storage.MongoDB;
import net.perforce.perforce.storage.gamemodes.duels.types.Duels_Coins;
import net.perforce.perforce.storage.gamemodes.duels.types.Duels_Kills;
import net.perforce.perforce.storage.gamemodes.duels.types.Duels_Level;
import net.perforce.perforce.storage.gamemodes.duels.types.Duels_Wins;
import net.perforce.perforce.storage.main.types.Coins;
import net.perforce.perforce.storage.main.types.HeadsCollected;
import net.perforce.perforce.storage.main.types.Level;
import net.perforce.perforce.storage.main.types.PlayersHidden;
import net.perforce.perforce.users.User;
import org.bson.Document;

import java.util.UUID;

public class Duels_DB {

    private static MongoCollection<Document> collection;

    public Duels_DB() {
        collection = MongoDB.getDatabase().getCollection("duels");
    }

    public void loadPlayerData(User user) {
        UUID uuid = user.getUuid();
        Document document = (Document) getPlayerDataCollection()
                .find(new Document("uuid", uuid.toString())).first();

        double coins = document.getDouble("coins");
        int level = document.getInteger("level");
        int experience = document.getInteger("experience");

        int wins = document.getInteger("wins");
        int loses = document.getInteger("loses");
        int winStreak = document.getInteger("win streak");
        int highestWinStreak = document.getInteger("highest win streak");
        double winLossRatio = document.getInteger("win / loss ratio");

        int kills = document.getInteger("kills");
        int deaths = document.getInteger("deaths");
        double killDeathRatio = document.getInteger("kill / death ratio");

        Data_Lists.Player_Duels_Coins.put(uuid, new Duels_Coins(uuid.toString(), coins));
        Data_Lists.Player_Duels_Level.put(uuid, new Duels_Level(uuid.toString(), level, experience));
        Data_Lists.Player_Duels_Wins.put(uuid, new Duels_Wins(uuid.toString(), wins, loses, winStreak, highestWinStreak, winLossRatio));
        Data_Lists.Player_Duels_Kills.put(uuid, new Duels_Kills(uuid.toString(), kills, deaths, killDeathRatio));
    }

    public void addNewPlayer(User user) {
        UUID uuid = user.getUuid();
        if (getPlayerDataCollection().find(new Document("uuid", uuid.toString())).first() == null) {
            Data_Lists.Player_Duels_Coins.put(uuid, new Duels_Coins(uuid.toString(), 0));
            Data_Lists.Player_Duels_Level.put(uuid, new Duels_Level(uuid.toString(), 0, 0));
            Data_Lists.Player_Duels_Wins.put(uuid, new Duels_Wins(uuid.toString(), 0, 0, 0, 0, 0));
        } else {
            loadPlayerData(user);
        }
    }

    public static MongoCollection<Document> getPlayerDataCollection() {
        return collection;
    }

}
