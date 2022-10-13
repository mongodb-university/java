package com.mdbu.app;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.mdbu.aggregations.Aggregation;
import com.mdbu.crud.Crud;
import com.mdbu.transactions.Transaction;
import com.mdbu.utils.MongoClientSingleton;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;
import static java.util.Arrays.asList;


public class DemoApp {
    public static void main(final String[] args) {
        Logger root = (Logger) LoggerFactory.getLogger("org.mongodb.driver");
        // Available levels are: OFF, ERROR, WARN, INFO, DEBUG, TRACE, ALL
        root.setLevel(Level.WARN);

        String connectionString = System.getenv("MONGODB_URI");
        try (MongoClient client = MongoClients.create(connectionString)) {
            Document account1 = new Document().append("account_holder", "John Doe").append("account_id", "MDB99115881").append("balance", 1785).append("account_type", "checking").append("last_updated", new Date());
            Document account2 = new Document().append("account_holder", "Jane Doe").append("account_id", "MDB79101843").append("balance", 1500).append("account_type", "checking").append("last_updated", new Date());
            Document account3 = new Document().append("account_holder", "Mary Doe").append("account_id", "MDB63191563").append("balance", 1500).append("account_type", "checking").append("last_updated", new Date());

            List<Document> sampleDocuments = new ArrayList<>();
            sampleDocuments.add(account1);
            sampleDocuments.add(account2);
            sampleDocuments.add(account3);
            //CRUD
            Crud crud = new Crud(client);
            //INSERT MANY
            crud.insertManyDocuments(sampleDocuments);
        }
    }
}