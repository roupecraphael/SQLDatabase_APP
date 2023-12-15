package at.ac.fhcampuswien.temperatureexample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LebensmittelDao {

    @Insert
    fun insert(data: LebensmittelData)

    @Update
    fun update(data: LebensmittelData)

    @Query("SELECT * FROM lebensmittel_data WHERE dataId = :key")
    fun getById(key: Long): LebensmittelData?

    @Query("SELECT * FROM lebensmittel_data WHERE lebensmittel = :lebensmittel")
    fun getByLebensmittel(lebensmittel: String): LebensmittelData?

    @Query("SELECT * FROM lebensmittel_data")
    fun getAllLocations(): List<LebensmittelData>

    @Query("DELETE from lebensmittel_data WHERE dataId= :key")
    fun delete(key:Long): Int

    @Query("DELETE FROM lebensmittel_data")
    fun deleteAll(): Void
}
