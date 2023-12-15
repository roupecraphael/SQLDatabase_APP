package at.ac.fhcampuswien.temperatureexample.data
// import all necessary external libraries here --------------------------------------------------------------------------------//
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
// @Database annotation marks the class as a Room database with a table name of lebensmittel_database ---------------------------//
@Database(entities = [LebensmittelData::class], version = 1, exportSchema = false)
abstract class LebensmittelDatabase : RoomDatabase() {
    abstract val temperatureDao: LebensmittelDao
    // the following lines are used to create a companion object that is used to create a database ------------------------------//
    companion object {
        @Volatile
        private var INSTANCE: LebensmittelDatabase? = null
        // the following lines are used to create a function that is used to create a database ----------------------------------//
        fun getInstance(context: Context): LebensmittelDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LebensmittelDatabase::class.java,
                        "lebensmittel_database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}