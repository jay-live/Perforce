package net.perforce.perforce.storage.friends.types;

import com.mongodb.client.MongoCollection;
import net.perforce.perforce.storage.MongoDB;
import net.perforce.perforce.storage.main.Main_DB;
import net.perforce.perforce.users.User;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class Friend {

    private final String uuid;
    private List<User> friends;
    private final MongoCollection<Document> collection = Main_DB.getPlayerDataCollection();

    public Friend(String uuid, List<User> friends) {
        this.uuid = uuid;
        this.friends = friends;

        Document document = new Document("uuid", uuid);
        if (collection.find(document).first() == null) {
            document.append("friends", friends);
            collection.insertOne(document);
        }
    }

    public String getUuid() {
        return uuid;
    }

    public List<User> getFriends() {
        return (List<User>) MongoDB.getPlayerDataDocument("friends", uuid, collection);
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
        MongoDB.setPlayerDataDocument(friends, "friends", uuid, collection);
    }

}
