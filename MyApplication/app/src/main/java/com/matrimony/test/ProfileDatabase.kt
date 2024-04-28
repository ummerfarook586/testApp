package com.matrimony.test

import android.content.ContentValues
import android.content.Context
import com.matrimony.test.DatabaseHelper.Companion.COLUMN_ADDRESS
import com.matrimony.test.DatabaseHelper.Companion.COLUMN_AGE
import com.matrimony.test.DatabaseHelper.Companion.COLUMN_HEIGHT
import com.matrimony.test.DatabaseHelper.Companion.COLUMN_IMAGES
import com.matrimony.test.DatabaseHelper.Companion.COLUMN_NAME
import com.matrimony.test.DatabaseHelper.Companion.COLUMN_PROFESSION
import com.matrimony.test.DatabaseHelper.Companion.TABLE_PROFILE

class ProfileDatabase(context: Context) {

    private val databaseHelper = DatabaseHelper(context)


    fun insert(profile: Profile) {
        val db = databaseHelper.writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_NAME, profile.name)
            put(COLUMN_AGE, profile.age)
            put(COLUMN_HEIGHT, profile.height)
            put(COLUMN_PROFESSION, profile.profession)
            put(COLUMN_ADDRESS, profile.address)
            // Handle image list appropriately (consider database column type)
            put(COLUMN_IMAGES, profile.imageList) // Modify if imageList needs special treatment
        }

        db.insert(TABLE_PROFILE, null, values)

        // close the database connection
        db.close()
    }

    fun getAll(): ArrayList<Profile> {
        val list = ArrayList<Profile>()

        // get the readable database
        val db = databaseHelper.readableDatabase

        // select all data from the table
        val cursor = db.rawQuery("SELECT * FROM $TABLE_PROFILE", null)

        // iterate through the cursor and add the data to the list
        try {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val age = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE))
                val height = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HEIGHT))
                val profession = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROFESSION))
                val address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS))
                val images = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGES))
                list.add(Profile(name, age, height, profession, address, images,id))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        cursor.close()
        db.close()

        return list
    }

    fun update(id: Int, name: String, code: String, image: ByteArray) {
        // get the writable database
        val db = databaseHelper.writableDatabase

        // create the ContentValues objec
        val values = ContentValues().apply {
            put("employee_name", name)
            put("employee_code", code)
            put("employee_image", image)
        }

// update the data in the table
        db.update("employee", values, "id = ?", arrayOf(id.toString()))

// close the database connection
        db.close()
    }

    fun delete(id: Int) {
        // get the writable database
        val db = databaseHelper.writableDatabase

        // delete the data from the table
        db.delete("employee", "id = ?", arrayOf(id.toString()))

        // close the database connection
        db.close()
    }


    fun getSelected(id: Int): Profile? {
        val db = databaseHelper.readableDatabase
        var profile: Profile? = null

        val cursor = db.rawQuery("SELECT * FROM $TABLE_PROFILE WHERE id =$id", null)
        try {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val age = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE))
                val height = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HEIGHT))
                val profession = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROFESSION))
                val address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS))
                val images = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGES))
                profile = Profile(name, age, height, profession, address, images,id)
            }
        } catch (e: Exception) {
            e.printStackTrace()

        }
        return profile
    }

    data class Profile(
        val name: String,
        val age: Int,
        val height: String,
        val profession: String,
        val address: String,
        val imageList: String,
        val id:Int?=null,
    )

}