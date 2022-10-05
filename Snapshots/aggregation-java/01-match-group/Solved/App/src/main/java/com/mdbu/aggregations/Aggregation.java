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

    public void showAccountTypeSummary(MongoCollection<Document> accounts) {
        Bson matchStage = Aggregates.match(lt("balance",1000));
        Bson groupStage = Aggregates.group("$account_type",sum("total_balance", "$balance"),avg("average_balance", "$balance"));
        System.out.println("Display aggregation results");
        accounts.aggregate(Arrays.asList(matchStage, groupStage)).forEach(document->System.out.print(document.toJson()));
    }

}

