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

        try (MongoClient client = MongoClientSingleton.getClient()) {
            Document sampleDocument = new Document("_id", new ObjectId())
                    .append("account_id", "MDB255054629")
                    .append("account_holder", "Mai Kalange")
                    .append("account_type", "savings")
                    .append("balance", 2340);
            //CRUD
            Crud crud = new Crud(client);
            //INSERT ONE
            crud.insertOneDocument(sampleDocument);

            Document account1 = new Document().append("account_holder", "John Doe").append("account_id", "MDB99115881").append("balance", 1785).append("account_type", "checking");
            Document account2 = new Document().append("account_holder", "Jane Doe").append("account_id", "MDB79101843").append("balance", 1500).append("account_type", "checking");
            Document account3 = new Document().append("account_holder", "Mary Doe").append("account_id", "MDB63191563").append("balance", 1500).append("account_type", "checking");

            List<Document> sampleDocuments = new ArrayList<>();
            sampleDocuments.add(account1);
            sampleDocuments.add(account2);
            sampleDocuments.add(account3);
            //INSERT MANY
            crud.insertManyDocuments(sampleDocuments);

            //FIND ONE
            Bson documentToFind = and(gte("balance", 1000), eq("account_type", "checking"));
            crud.findOneDocument(documentToFind);

            //FIND
            Bson documentsToFind = and(gte("balance", 1000), eq("account_type", "checking"));
            crud.findDocuments(documentsToFind);

            //UPDATE ONE
            Bson query = null; //TODO: define the query variable
            Bson update = null; //TODO: define the update variable
            crud.updateOneDocument(query, update);

            //UPDATE MANY
            Document updateManyFilter = new Document().append("account_type", "savings");
            Bson updatesForMany = Updates.combine(Updates.set("minimum_balance", 100));
            crud.updateManyDocuments(updateManyFilter, updatesForMany);

            //DELETE ONE
            Bson documentToDelete = eq("account_holder", "John Doe");
            crud.deleteOneDocument(documentToDelete);

            //DELETE MANY
            Bson documentsToDelete = eq("balance", 1500);
            crud.deleteManyDocuments(documentsToDelete);

            //Transaction
            Transaction txn = new Transaction(client);
            var senderAccountFilter = "";
            var receiverAccountFilter = "";
            double transferAmount = 200;
            txn.transferMoney(senderAccountFilter, transferAmount, receiverAccountFilter);

            //Aggregation
            Aggregation agg = new Aggregation(client);

            agg.showAccountTypeSummary();
            agg.showGBPBalancesForCheckingAccounts();
        }
    }
}