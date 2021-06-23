package net.perforce.perforce.storage.main.types;

import com.mongodb.client.MongoCollection;
import net.perforce.perforce.storage.MongoDB;
import net.perforce.perforce.storage.main.Main_DB;
import org.bson.Document;

public class PlayersHidden {

    private final String uuid;
    private boolean hidden;
    private final MongoCollection<Document> collection = Main_DB.getPlayerDataCollection();

    public PlayersHidden(String uuid, boolean hidden) {
        this.uuid = uuid;
        this.hidden = hidden;

        Document document = new Document("uuid", uuid);
        if (collection.find(document).first() == null) {
            document.append("players hidden", hidden);
            collection.insertOne(document);
        }
    }

    public String getUuid() {
        return uuid;
    }

    public boolean getHidden() {
        return (boolean) MongoDB.getDataDocument("players hidden", uuid, collection);
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
        MongoDB.setDataDocument(hidden, "players hidden", uuid, collection);
    }

}
