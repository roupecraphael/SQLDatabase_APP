package at.ac.fhcampuswien.temperatureexample
// import all necessary external libraries here ----------------------------------------------------------------------------//
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import at.ac.fhcampuswien.temperatureexample.R
// import all necessary internal classes here-------------------------------------------------------------------------------//
import at.ac.fhcampuswien.temperatureexample.data.LebensmittelData
import at.ac.fhcampuswien.temperatureexample.data.LebensmittelDatabase
import at.ac.fhcampuswien.temperatureexample.databinding.ActivitySecondBinding
import at.ac.fhcampuswien.temperatureexample.databinding.ActivityMainBinding
// this is the main activity class that is called when the app is started --------------------------------------------------//
class MainActivity : AppCompatActivity() {
    // Private variables are declared here -------------------------------------------------------------------------------------//
    private lateinit var binding: ActivityMainBinding
    private var locations = listOf<LebensmittelData>()
    private var index = 0
    // overide function is called when the app is started ----------------------------------------------------------------------//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// utilizes the databinding library to bind the layout to the activity -----------------------------------------------------//
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
// the following lines are used to bind the layout elements to the activity variables --------------------------------------//
        val LebensmittelView: EditText = findViewById(R.id.lebensmittel)
        val GewichtView: EditText = findViewById(R.id.gewicht)
        val KohlenhydrateView: EditText = findViewById(R.id.kohlenhydrate)
//the following values are used to create a database and insert data into it -----------------------------------------------//
        val db = LebensmittelDatabase.getInstance(applicationContext)
        val dao = db.temperatureDao
        locations = dao.getAllLocations()
        binding.temp = locations[0]
// the following lines create a val that uses the button id to find the button and then set an onclick listener to change --//
// to a second activity when the button is pressed -------------------------------------------------------------------------//
        val goToAnotherClass: Button
        goToAnotherClass =
            findViewById<View>(R.id.showdata) as Button //find the button by its assigned id
        goToAnotherClass.setOnClickListener { // TODO Auto-generated method stub
            val myIntent = Intent(
                this,
                SecondActivity::class.java
            )
            startActivity(myIntent)
        }
// the following lines create a val that uses the button id to find the button and then set an onclick listener to  --------//
// add data from the edit text fields to the database ----------------------------------------------------------------------//
        val addDatatodatabase: Button
        addDatatodatabase = findViewById(R.id.adddata) as Button
        addDatatodatabase.setOnClickListener {
            val lebensmitteldaten = LebensmittelView.text.toString()
            val gewichtdaten = GewichtView.text.toString().toLong()
            val kohlenhydratedaten = KohlenhydrateView.text.toString().toLong()
            val tempData =
                LebensmittelData(lebensmittel = lebensmitteldaten, gewicht = gewichtdaten, kohlenhydrate = kohlenhydratedaten)
            dao.insert(tempData)
        }
    }
}
// the following class is used to create a second activity that is called when the button is pressed and displays the data -//
// from the database in a recycler view  -----------------------------------------------------------------------------------//
class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        val view: RecyclerView = findViewById(R.id.LebensmittelListe)
        val db = LebensmittelDatabase.getInstance(applicationContext)
        val dao = db.temperatureDao
        val adapter = LebensmittelAdapter(dao.getAllLocations())
        view.adapter = adapter
        view.layoutManager = LinearLayoutManager(this)
    }
}
// the following class Viewholder is used to bind the R.id.lebensmittelid, R.id.gewichtid and R.id.kohlenhydrateid to  -----//
// variables so they can be used by recycler view in the second activity ---------------------------------------------------//
class LebensmittelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val lebensmittelName: TextView = itemView.findViewById(R.id.lebensmittelid)
    val gewicht: TextView = itemView.findViewById(R.id.gewichtid)
    val kohlenhydrate: TextView = itemView.findViewById(R.id.kohlenhydrateid)
}
// the following class is used to create an adapter that is used by the ViewHolder to update the display of the data -------//
// from the database in the second activity --------------------------------------------------------------------------------//
class LebensmittelAdapter(private val lebensmittelList: List<LebensmittelData>) :
    RecyclerView.Adapter<LebensmittelViewHolder>() {
    // the following functions are used to create the ViewHolder ---------------------------------------------------------------//
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LebensmittelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_database, parent, false)
        return LebensmittelViewHolder(view)
    }
    // the following functions are used to bind the data from the database to the ViewHolder -----------------------------------//
    override fun onBindViewHolder(holder: LebensmittelViewHolder, position: Int) {
        val lebensmitteln = lebensmittelList[position]
        holder.lebensmittelName.text = lebensmitteln.lebensmittel
        holder.gewicht.text = lebensmitteln.gewicht.toString()
        holder.kohlenhydrate.text  = lebensmitteln.kohlenhydrate.toString()
    }
    // the following function is used to get the number of items in the database -----------------------------------------------//
    override fun getItemCount() = lebensmittelList.size
}



