package at.ac.fhcampuswien.temperatureexample.data
// import all necessary external libraries here --------------------------------------------------------------------------------//
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
// @Dao annotation marks the class as a Room database Dao ---------------------------------------------------------------------//
@Dao
interface LebensmittelDao {
// the following lines are used to create a function that is used to insert data into the database ------------------------------//
    @Insert
    fun insert(data: LebensmittelData)
// the following lines are used to create a function that is used to update data in the database -------------------------------//
    @Update
    fun update(data: LebensmittelData)
// the following lines are used to create a function that is used to get data from the database --------------------------------//
    @Query("SELECT * FROM lebensmittel_data WHERE dataId = :key")
    fun getById(key: Long): LebensmittelData?
// the following lines are used to create a function that is used to get lebensmittel data by id from the database -------------//
    @Query("SELECT * FROM lebensmittel_data WHERE lebensmittel = :lebensmittel")
    fun getByLebensmittel(lebensmittel: String): LebensmittelData?
// the following lines are used to create a function that is used to get all data from the database -----------------------------//
    @Query("SELECT * FROM lebensmittel_data")
    fun getAllLocations(): List<LebensmittelData>
// the following lines are used to create a function that is used to delete data from the database ------------------------------//
    @Query("DELETE from lebensmittel_data WHERE dataId= :key")
    fun delete(key:Long): Int
// the following lines are used to create a function that is used to delete all data from the database --------------------------//
    @Query("DELETE FROM lebensmittel_data")
    fun deleteAll(): Void
}
