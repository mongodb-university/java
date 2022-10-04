package com.mdbu.transactions;

import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.TransactionBody;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.push;

public class Transaction {
    private final MongoClient client;

    public Transaction(MongoClient client) {
        this.client = client;
    }

    public void transferMoney(String accountIdOfSender, double transactionAmount, String accountIdOfReceiver) {
        try (ClientSession session = client.startSession()) {
            UUID transferId = UUID.randomUUID();
            try {
                session.withTransaction(() -> {
                    //TODO: Add transaction code here
                    return null;
                });
            } catch (RuntimeException e) {
                // some error handling
            }
        }
    }
}
