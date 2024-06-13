package GenericUtilities;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Mongo_DB {
	
     	
	
	
	     public static void main(String agrs[]) {
	
     	    String dbURI="mongodb+srv://mahesh_naravane:passwmahesh_naravaneord@flexiloans-staging-db.x5n5qb0.mongodb.net/";
 
             // Connect to MongoDB
             try (MongoClient mongoClient = new MongoClient(new MongoClientURI(dbURI));) {
                 // Access the "test" database
                 MongoDatabase database = mongoClient.getDatabase("FlexiLoansDB");

                 // Access the "users" collection
                 MongoCollection<Document> collection = database.getCollection("pd");

                 // Insert a new document
                 Document newUser = new Document("username", "john")
                         .append("email", "john@example.com")
                         .append("age", 30);
                 collection.insertOne(newUser);

                 // Find a document
                 Document foundUser = collection.find(new Document("username", "john")).first();
                 System.out.println("Found user: " + foundUser);

                 // Update a document
                 collection.updateOne(new Document("username", "john"),
                         new Document("$set", new Document("age", 31)));

                 // Delete a document
                 collection.deleteOne(new Document("username", "john"));
             } catch (Exception e) {
                 e.printStackTrace();
             } 
         }     	
    

}
