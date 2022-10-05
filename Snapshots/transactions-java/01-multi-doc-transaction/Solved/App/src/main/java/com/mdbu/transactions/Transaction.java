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
import java.util.Date;

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
            UUID transfer = UUID.randomUUID();
            String transferId = transfer.toString();
            try {
                session.withTransaction(() -> {
                    MongoCollection<Document> accountsCollection = client.getDatabase("bank").getCollection("accounts");
                    MongoCollection<Document> transfersCollection = client.getDatabase("bank").getCollection("transfers");
    
    
                    Bson senderAccountFilter = eq("account_id", accountIdOfSender);
                    Bson debitUpdate = Updates.combine(inc("balance", -1 * transactionAmount),push("transfers_complete", transferId));
    
                    Bson receiverAccountId = eq("account_id", accountIdOfReceiver);
                    Bson credit = Updates.combine(inc("balance", transactionAmount), push("transfers_complete", transferId));
    
                    transfersCollection.insertOne(session, new Document("_id", new ObjectId()).append("transfer_id", transferId).append("to_account", accountIdOfReceiver).append("from_account", accountIdOfSender).append("amount", transactionAmount));
                    accountsCollection.updateOne(session, senderAccountFilter, debitUpdate);
                    accountsCollection.updateOne(session, receiverAccountId, credit);
                    return null;
                });
            } catch (RuntimeException e) {
                throw e;
            }
        }
    }
}
