package com.mdbu.crud;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

public class Crud {
    private final MongoCollection<Document> collection;

    public Crud(MongoClient client) {
        this.collection = client.getDatabase("bank").getCollection("accounts");
    }

    public void insertOneDocument(Document doc) {
        System.out.println("Inserting one account document");  
        InsertOneResult result = collection.insertOne(doc);    
        BsonValue id = result.getInsertedId();  
        System.out.println("Inserted document Id: " + id);
    }
}
