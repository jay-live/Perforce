package net.perforce.perforce.storage.main.types;

import com.mongodb.client.MongoCollection;
import net.perforce.perforce.storage.MongoDB;
import net.perforce.perforce.storage.main.Main_DB;
import org.bson.Document;

public class Level {

    private final String uuid;
    private Integer level;
    private Integer experience;
    private final MongoCollection<Document> collection = Main_DB.getPlayerDataCollection();

    public Level(String uuid, int level, int experience) {
        this.uuid = uuid;
        this.level = level;
        this.experience = experience;

        Document document = new Document("uuid", uuid);
        if (collection.find(document).first() == null) {
            document.append("level", level);
            document.append("experience", experience);
            collection.insertOne(document);
        }
    }

    public String getUuid() {
        return uuid;
    }

    public int getExperience() {
        return (int) MongoDB.getDataDocument("main experience", uuid, collection);
    }

    public void setExperience(int experience) {
        this.experience = experience;
        MongoDB.setDataDocument(experience, "main experience", uuid, collection);
    }

    public int getLevel() {
        return (int) MongoDB.getDataDocument("main level", uuid, collection);
    }

    public void setLevel(int level) {
        this.level = level;
        MongoDB.setDataDocument(level, "main level", uuid, collection);
    }

}
