package net.perforce.perforce.storage.main.types;

import com.mongodb.client.MongoCollection;
import net.perforce.perforce.storage.MongoDB;
import net.perforce.perforce.storage.main.Main_DB;
import org.bson.Document;

public class Coins {

    private final String uuid;
    private double coins;
    private final MongoCollection<Document> collection = Main_DB.getPlayerDataCollection();

    public Coins(String uuid, double coins) {
        this.uuid = uuid;
        this.coins = coins;

        Document document = new Document("uuid", uuid);
        if (collection.find(document).first() == null) {
            document.append("coins", coins);
            collection.insertOne(document);
        }
    }

    public String getUuid() {
        return uuid;
    }

    public double getCoins() {
        return (double) MongoDB.getDataDocument("coins", uuid, collection);
    }

    public void setCoins(double coins) {
        this.coins = coins;
        MongoDB.setDataDocument(coins, "coins", uuid, collection);
    }

}
