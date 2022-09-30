package com.mdbu.aggregations;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.mdbu.aggregations.Aggregation;

import static com.mongodb.client.model.Sorts.orderBy;
import static java.util.Arrays.asList;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Sorts.descending;

import java.util.List;

public class Aggregation {

    private final MongoCollection<Document> collection;

    public Aggregation(MongoClient client) {
        this.collection = client.getDatabase("banking").getCollection("accounts");
    }

    public void showAccountTypeSummary() {
        Bson matchStage = null; //TODO: define the match stage
        Bson groupStage = null; //TODO: define the group stage
        List<Bson> pipeline = List.of(matchStage, groupStage);
        AggregateIterable<Document> result = collection.aggregate(pipeline);
        result.forEach(document -> System.out.println(document.toJson()));
    }

    public void showGBPBalancesForCheckingAccounts() {
        Bson matchStage = null; //TODO define the match balance & account stage
        Bson sortStage = null; //TODO define the sort stage
        Bson projectStage = null; //TODO: define the projection stage
        var mspPipeline = asList(matchStage, sortStage, projectStage );
        AggregateIterable<Document> result = collection.aggregate(mspPipeline);

        System.out.println("Display aggregation results");
        result.forEach(document -> System.out.println(document.toJson()));
    }
}
