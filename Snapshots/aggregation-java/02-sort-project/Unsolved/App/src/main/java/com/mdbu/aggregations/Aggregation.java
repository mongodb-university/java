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
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;

import java.util.List;

public class Aggregation {
    private final MongoCollection<Document> collection;

    public Aggregation(MongoClient client) {
        this.collection = client.getDatabase("banking").getCollection("accounts");
    }

    public void showGBPBalancesForCheckingAccounts(MongoCollection<Document> accounts) {
        Bson matchStage = null; //TODO define the match balance & account stage
        Bson sortStage = null; //TODO define the sort stage
        Bson projectStage = null; //TODO: define the projection stage
        System.out.println("Display aggregation results");
        accounts.aggregate(asList(matchStage,sortStage, projectStage)).forEach(document -> System.out.print(document.toJson()));
    }
    
}

