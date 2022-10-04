package com.mdbu.utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class MongoClientSingleton {
    private static  MongoClient client;

    private MongoClientSingleton() {
        ConnectionString connectionString = new ConnectionString(getConnectionString());
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        client = MongoClients.create(settings);
    }

    public static synchronized MongoClient getClient() {
        if (client == null) {
            new MongoClientSingleton();
        }
        return client;
    }

    private String getConnectionString() {
        String connStr = null;
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("atlas.properties")) {
            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find atlas.properties");
            }
            prop.load(input);
            connStr = prop.getProperty("MONGODB.URI");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return connStr;
    }
}
