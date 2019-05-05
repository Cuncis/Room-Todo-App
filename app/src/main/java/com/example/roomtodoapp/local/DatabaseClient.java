package com.example.roomtodoapp.local;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {
    private Context context;
    private static DatabaseClient instance;

    private AppDatabase appDatabase;

    public DatabaseClient(Context context) {
        this.context = context;
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "MyTodos").build();
    }

    public static synchronized DatabaseClient getInstance(Context ctx) {
        if (instance == null) {
            instance = new DatabaseClient(ctx);
        }

        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
