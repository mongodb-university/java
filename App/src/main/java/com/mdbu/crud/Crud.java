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
        //TODO implement insertOne code here
    }

    public void insertManyDocuments(List<Document> documents) {
        //TODO Add code to insert many documents
    }

    public void updateOneDocument(Bson query, Bson update) {
        //TODO: Implement the updateOne operation
    }

    public void updateManyDocuments(Bson query, Bson update) {
        //TODO: Implement the updateMany operation
    }

    public void findOneDocument(Bson query) {
        //TODO: implement the findOne query
    }

    public void findDocuments(Bson query) {
        //TODO: implement the find query
    }

    public void deleteOneDocument(Bson query) {
        //TODO: implement the deleteOne operation
    }

    public void deleteManyDocuments(Bson query) {
        //TODO: implement the deleteMany operation
    }
}
