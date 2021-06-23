package net.perforce.perforce.storage.friends;

import com.mongodb.client.MongoCollection;
import net.perforce.perforce.storage.Data_Lists;
import net.perforce.perforce.storage.MongoDB;
import net.perforce.perforce.storage.main.types.Coins;
import net.perforce.perforce.storage.main.types.HeadsCollected;
import net.perforce.perforce.storage.main.types.Level;
import net.perforce.perforce.storage.main.types.PlayersHidden;
import net.perforce.perforce.users.User;
import org.bson.Document;

import java.util.UUID;

public class Friends_DB {

    private static MongoCollection<Document> collection;

    public Friends_DB() {
        collection = MongoDB.getDatabase().getCollection("friends");
    }

    public void loadPlayerData(User user) {
        UUID uuid = user.getUuid();
        Document document = (Document) getPlayerDataCollection()
                .find(new Document("uuid", uuid.toString())).first();

        int heads = document.getInteger("heads");

        Data_Lists.Player_Stats_HeadsCollected.put(uuid, new HeadsCollected(uuid.toString(), heads));
    }

    public void addNewPlayer(User user) {
        UUID uuid = user.getUuid();
        if (getPlayerDataCollection().find(new Document("uuid", uuid.toString())).first() == null) {
            Data_Lists.Player_Stats_Coins.put(uuid, new Coins(uuid.toString(), 0));
        } else {
            loadPlayerData(user);
        }
    }

    public static MongoCollection<Document> getPlayerDataCollection() {
        return collection;
    }

}
