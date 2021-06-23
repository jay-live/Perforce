package net.perforce.perforce.storage.main;

import com.mongodb.client.MongoCollection;
import net.perforce.perforce.storage.Data_Lists;
import net.perforce.perforce.storage.MongoDB;
import net.perforce.perforce.storage.main.types.Coins;
import net.perforce.perforce.storage.main.types.HeadsCollected;
import net.perforce.perforce.storage.main.types.PlayersHidden;
import net.perforce.perforce.storage.main.types.Level;
import net.perforce.perforce.users.User;
import org.bson.Document;

import java.util.UUID;

public class Main_DB {

    private static MongoCollection<Document> collection;

    public Main_DB() {
        collection = MongoDB.getDatabase().getCollection("data");
    }

    public void loadPlayerData(User user) {
        UUID uuid = user.getUuid();
        Document document = (Document) getPlayerDataCollection()
                .find(new Document("uuid", uuid.toString())).first();

        int heads = document.getInteger("heads");
        double coins = document.getDouble("coins");
        int level = document.getInteger("level");
        int experience = document.getInteger("experience");
        boolean hidden = document.getBoolean("players hidden");

        Data_Lists.Player_Stats_HeadsCollected.put(uuid, new HeadsCollected(uuid.toString(), heads));
        Data_Lists.Player_Stats_Coins.put(uuid, new Coins(uuid.toString(), coins));
        Data_Lists.Player_Stats_Level.put(uuid, new Level(uuid.toString(), level, experience));
        Data_Lists.Player_Stats_PlayersHidden.put(uuid, new PlayersHidden(uuid.toString(), hidden));
    }

    public void addNewPlayer(User user) {
        UUID uuid = user.getUuid();
        if (getPlayerDataCollection().find(new Document("uuid", uuid.toString())).first() == null) {
            Data_Lists.Player_Stats_Coins.put(uuid, new Coins(uuid.toString(), 0));
            Data_Lists.Player_Stats_Level.put(uuid, new Level(uuid.toString(), 0, 0));
            Data_Lists.Player_Stats_HeadsCollected.put(uuid, new HeadsCollected(uuid.toString(), 0));
            Data_Lists.Player_Stats_PlayersHidden.put(uuid, new PlayersHidden(uuid.toString(), false));
        } else {
            loadPlayerData(user);
        }
    }

    public static MongoCollection<Document> getPlayerDataCollection() {
        return collection;
    }

}
