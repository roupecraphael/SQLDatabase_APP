package at.ac.fhcampuswien.temperatureexample.data
// import all necessary external libraries here --------------------------------------------------------------------------------//
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
// @Entity annotation marks the class as a Room database entity with a table name of lebensmittel_data ---------------------------//
@Entity(tableName = "lebensmittel_data")
data class LebensmittelData(
    @PrimaryKey(autoGenerate = true)
    var dataId: Long = 0,
    @ColumnInfo(name = "Lebensmittel")
    var lebensmittel: String,
    @ColumnInfo(name = "Gewicht")
    var gewicht: Long,
    @ColumnInfo(name = "Kohlenhydrate")
    var kohlenhydrate: Long
)
// the following lines are used to create a val that uses the data from the database to create a form text that can be displayed--//
{
    val gewichtText: String
        get() {
            return "$gewicht g"
        }
    val kohlenhydrateText: String
        get() {
            return "$kohlenhydrate BE"
        }
}
