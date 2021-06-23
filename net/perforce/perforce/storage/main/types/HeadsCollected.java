package net.perforce.perforce.storage.main.types;

import com.mongodb.client.MongoCollection;
import net.perforce.perforce.storage.MongoDB;
import net.perforce.perforce.storage.main.Main_DB;
import org.bson.Document;

public class HeadsCollected {

    private final String uuid;
    private Integer heads;
    private final MongoCollection<Document> collection = Main_DB.getPlayerDataCollection();

    public HeadsCollected(String uuid, int heads) {
        this.uuid = uuid;
        this.heads = heads;

        Document document = new Document("uuid", uuid);
        if (collection.find(document).first() == null) {
            document.append("heads", heads);
            collection.insertOne(document);
        }
    }

    public String getUuid() {
        return uuid;
    }

    public int getHeads() {
        return (int) MongoDB.getDataDocument("heads", uuid, collection);
    }

    public void setHeads(int heads) {
        this.heads = heads;
        MongoDB.setDataDocument(heads, "heads", uuid, collection);
    }

}
