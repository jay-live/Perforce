package net.perforce.perforce.storage;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoDB {

    private static MongoDatabase database;

    public MongoDB() {
        ConnectDB();
    }

    public void ConnectDB() {
        MongoClient client = MongoClients.create("mongodb+srv://Perforce_Admin:wtK7RQtL8gIyJjEm@perforce.sti2r.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        database = client.getDatabase("Player_Data");
    }

    public static void setDataDocument(Object value, String identifier, String uuid, MongoCollection<Document> collection) {
        Document document = new Document("uuid", uuid);
        Bson newValue = new Document(identifier, value);
        Bson updateOperation = new Document("$set", newValue);
        collection.updateOne(document, updateOperation);
    }

    public static Object getDataDocument(String identifier, String uuid, MongoCollection<Document> collection) {
        Document document = new Document("uuid", uuid);
        if (collection.find(document).first() != null) {
            Document found = collection.find().first();
            return found.get(identifier);
        }
        System.out.println("Error! An error occurred inside of getPlayerDataDocument function inside of MongoDB.java class");
        return null;
    }

    public static MongoDatabase getDatabase() {
        return database;
    }


}
