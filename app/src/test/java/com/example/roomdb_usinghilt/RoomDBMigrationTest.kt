package com.example.roomdb_usinghilt

import android.app.Instrumentation
import androidx.room.testing.MigrationTestHelper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.roomdb_usinghilt.RoomDB.Database.UserDatabase
import com.example.roomdb_usinghilt.RoomDB.Entities.RoomEntities
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomDBMigrationTest {
    private val TEST_DB = "my_database"

    @Rule
    @JvmField
    val helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        RoomEntities::class.java.canonicalName
    )

    @Test
    fun checkMigration() {
        helper.createDatabase(TEST_DB, 1).apply {
            execSQL("INSERT INTO UserTable (name, email) VALUES ('John','john@gamil.com')")
            close()
        }
        // Open the database with version 2 and provide the migration
        helper.runMigrationsAndValidate(TEST_DB, 2, true, UserDatabase.db_migration_1_2)

        // Step 3: Open the migrated database and verify the new column exists
        val db = helper.createDatabase(TEST_DB, 2)

        // Use PRAGMA table_info to check schema
        val cursor = db.query("PRAGMA table_info(UserTable)")
        var columnExists = false
        while (cursor.moveToNext()) {
            val columnName = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            if (columnName == "isActive") {
                columnExists = true
                break
            }
        }
        cursor.close()
        db.close()

        // Assert that the new column exists
        assert(columnExists) { "Column 'isActive' does not exist in the 'User' table after migration." }

    }

}