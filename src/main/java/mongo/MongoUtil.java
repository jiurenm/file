package mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

/**
 * file
 *
 * @author Administrator
 * @date 2019/3/1 22:23
 */
public class MongoUtil {

    private static MongoDatabase connect(String databaseName) {
        MongoClient mongoClient = new MongoClient("localhost",27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
        System.out.println("Connect Successfully");
        return mongoDatabase;
    }

    public static void write(List<Document> documentList, String databaseName, String collectionName) {
        MongoCollection<Document> collection = connect(databaseName).getCollection(collectionName);
        collection.insertMany(documentList);
        System.out.println("Insert Successfully");
    }
}
