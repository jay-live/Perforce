package net.perforce.perforce.storage.gamemodes.duels.types;

import com.mongodb.client.MongoCollection;
import net.perforce.perforce.storage.MongoDB;
import net.perforce.perforce.storage.gamemodes.duels.Duels_DB;
import org.bson.Document;

public class Duels_Kills {

    private final String uuid;
    private Integer kills;
    private Integer deaths;
    private double killDeathRatio;
    private final MongoCollection<Document> collection = Duels_DB.getPlayerDataCollection();

    public Duels_Kills(String uuid, int kills, int deaths, double killDeathRatio) {
        this.uuid = uuid;
        this.kills = kills;
        this.deaths = deaths;
        this.killDeathRatio = killDeathRatio;


        Document document = new Document("uuid", uuid);
        if (collection.find(document).first() == null) {
            document.append("kills", kills);
            document.append("deaths", deaths);
            document.append("kill / death ratio", killDeathRatio);
            collection.insertOne(document);
        }
    }

    public String getUuid() {
        return uuid;
    }

    public int getKills() {
        return (int) MongoDB.getDataDocument("kills", uuid, collection);
    }
    public void setKills(int kills) {
        this.kills = kills;
        MongoDB.setDataDocument(kills, "kills", uuid, collection);
    }

    public int getDeaths() {
        return (int) MongoDB.getDataDocument("deaths", uuid, collection);
    }
    public void setDeaths(int deaths) {
        this.deaths = deaths;
        MongoDB.setDataDocument(deaths, "deaths", uuid, collection);
    }

    public double getKillDeathRatio() {
        return (double) MongoDB.getDataDocument("kill / death ratio", uuid, collection);
    }
    public void setKillDeathRatio() {
        this.killDeathRatio = this.kills / this.deaths;
        MongoDB.setDataDocument(killDeathRatio, "kills / deaths ratio", uuid, collection);
    }

}
