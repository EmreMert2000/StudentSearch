package com.example.studentsearch

// DatabaseHelper.kt

import android.R
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.ArrayAdapter

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "students.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "students"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_NUMBER = "number"
        private const val COLUMN_SCORE = "score"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_NUMBER INTEGER PRIMARY KEY, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_SCORE INTEGER)"

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(studentName: String, studentNumber: String, studentScore: Int): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, studentName)
        contentValues.put(COLUMN_NUMBER, studentNumber)
        contentValues.put(COLUMN_SCORE, studentScore)

        val result = db.insert(TABLE_NAME, null, contentValues)
        db.close()

        // Ekleme işleminin başarılı olup olmadığını kontrol edin ve sonucu döndürün
        return result != -1L
    }

    //I will do it later...
    fun getAllData(): ArrayList<String> {
        val dataList = ArrayList<String>()
        val db = this.readableDatabase
        val columns = arrayOf(COLUMN_NAME, COLUMN_NUMBER, COLUMN_SCORE) // Sütun adlarını burada belirtmelisiniz
        val cursor: Cursor = db.query(TABLE_NAME, columns, null, null, null, null, null)
        val nameIndex = cursor.getColumnIndex(COLUMN_NAME) // Sütun indeksi burada alınır
        val numberIndex = cursor.getColumnIndex(COLUMN_NUMBER)
        val scoreIndex = cursor.getColumnIndex(COLUMN_SCORE)

        while (cursor.moveToNext()) {
            val name = cursor.getString(nameIndex) // Sütun indeksini kullanarak veriyi al
            val number = cursor.getString(numberIndex)
            val score =cursor.getInt(scoreIndex)
            dataList.add(Triple(name,number,score).toString())
        }

        cursor.close()
        db.close()
        return dataList
    }
}
