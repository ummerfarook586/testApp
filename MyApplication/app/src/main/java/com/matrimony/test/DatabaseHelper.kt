package com.matrimony.test

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "testapp.db"
        private const val DATABASE_VERSION = 1
         const val TABLE_PROFILE = "profile"
         const val COLUMN_NAME = "name"
         const val COLUMN_AGE = "age"
         const val COLUMN_HEIGHT = "height"
         const val COLUMN_PROFESSION = "profession"
         const val COLUMN_ADDRESS = "address"
         const val COLUMN_IMAGES = "imageList"

        private const val CREATE_TABLE_PROFILE = """
            CREATE TABLE IF NOT EXISTS $TABLE_PROFILE (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_AGE INTEGER,
                $COLUMN_HEIGHT TEXT,
                $COLUMN_PROFESSION TEXT,
                $COLUMN_ADDRESS TEXT,
                $COLUMN_IMAGES TEXT
            )
        """
        private const val DROP_TABLE_PROFILE = "DROP TABLE IF EXISTS $TABLE_PROFILE"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_PROFILE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE_PROFILE)
        onCreate(db)
    }
}