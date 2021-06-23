package net.perforce.perforce.storage.gamemodes.duels.types;

import com.mongodb.client.MongoCollection;
import net.perforce.perforce.storage.MongoDB;
import net.perforce.perforce.storage.gamemodes.duels.Duels_DB;
import org.bson.Document;

public class Duels_Wins {

    private final String uuid;
    private Integer wins;
    private Integer loses;
    private Integer winStreak;
    private Integer highestWinStreak;
    private double winLossRatio;
    private final MongoCollection<Document> collection = Duels_DB.getPlayerDataCollection();

    public Duels_Wins(String uuid, int wins, int loses, int winStreak, int highestWinStreak, double winLossRatio) {
        this.uuid = uuid;
        this.wins = wins;
        this.loses = loses;
        this.winStreak = winStreak;
        this.highestWinStreak = highestWinStreak;
        this.winLossRatio = winLossRatio;


        Document document = new Document("uuid", uuid);
        if (collection.find(document).first() == null) {
            document.append("wins", wins);
            document.append("loses", loses);
            document.append("win streak", winStreak);
            document.append("highest win streak", highestWinStreak);
            document.append("win / loss ratio", winLossRatio);
            collection.insertOne(document);
        }
    }

    public String getUuid() {
        return uuid;
    }

    public int getWins() {
        return (int) MongoDB.getDataDocument("wins", uuid, collection);
    }
    public void setWins(int wins) {
        this.wins = wins;
        MongoDB.setDataDocument(wins, "wins", uuid, collection);
    }

    public int getLoses() {
        return (int) MongoDB.getDataDocument("loses", uuid, collection);
    }
    public void setLoses(int loses) {
        this.loses = loses;
        MongoDB.setDataDocument(loses, "loses", uuid, collection);
    }

    public int getWinStreak() {
        return (int) MongoDB.getDataDocument("win streak", uuid, collection);
    }
    public void setWinStreak(int winStreak) {
        this.winStreak = winStreak;
        MongoDB.setDataDocument(wins, "win streak", uuid, collection);
    }

    public int getHighestWinStreak() {
        return (int) MongoDB.getDataDocument("highest win streak", uuid, collection);
    }
    public void setHighestWinStreak(int highestWinStreak) {
        this.highestWinStreak = highestWinStreak;
        MongoDB.setDataDocument(wins, "highest win streak", uuid, collection);
    }

    public double getWinLossRatio() {
        return (double) MongoDB.getDataDocument("win / loss ratio", uuid, collection);
    }
    public void setWinLossRatio() {
        this.winLossRatio = this.wins / this.loses;
        MongoDB.setDataDocument(winLossRatio, "win / loss ratio", uuid, collection);
    }

}
