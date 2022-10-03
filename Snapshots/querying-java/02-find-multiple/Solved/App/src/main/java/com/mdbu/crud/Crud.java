package com.mdbu.crud;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.BsonValue;
import org.bson.conversions.Bson;
import java.util.Iterator;

import java.util.ArrayList;
import java.util.List;

public class Crud {
    private final MongoCollection<Document> collection;

    public Crud(MongoClient client) {
        this.collection = client.getDatabase("bank").getCollection("accounts");
    }

    public void findDocuments(Bson query) {
        try (MongoCursor<Document> cursor = collection.find(query).iterator()) {
        while (cursor.hasNext()) {
            System.out.println(cursor.next().toJson());
            }
        }
    }
}
