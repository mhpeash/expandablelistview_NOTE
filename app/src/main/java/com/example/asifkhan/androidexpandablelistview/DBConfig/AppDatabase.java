package com.example.asifkhan.androidexpandablelistview.DBConfig;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.example.asifkhan.androidexpandablelistview.Model.Note;


@Database(entities = {Note.class}, version = 1, exportSchema = false)


public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract NoteDao noteDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                      AppDatabase.class, "notedb")
                      .allowMainThreadQueries() //allowMainThreadQueries() .build() fallbackToDestructiveMigration()
                      .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Company "
                    + " ADD COLUMN ref_no TEXT");
        }
    };


}