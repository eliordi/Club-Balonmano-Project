package es.jcc.project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.snackbar.Snackbar
import es.jcc.project.Login.LoginFragment
import es.jcc.project.MainApp.PlayersFragment
import es.jcc.project.MainApp.TeamsFragment
import es.jcc.project.databinding.ActivityAppBinding
import java.util.Locale
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import es.jcc.project.MainApp.ChatFragment
import es.jcc.project.MainApp.HomeFragment


class AppActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener{

    private lateinit var binding: ActivityAppBinding
    val CONTACT_REQUEST_CODE = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)
        binding.bottomNavigation.setOnItemSelectedListener(this)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId){

        R.id.action_settings -> {
            //Settings fragment
            true
        }
        R.id.action_permissions -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    CONTACT_REQUEST_CODE
                )
            }else {
                Toast.makeText(
                    this,
                    "Permission already granted",
                    Toast.LENGTH_LONG
                ).show()
            }
            true
        }
        R.id.action_logout -> {
            reiniciarApp()
            true
        }
        else -> false
    }


    private fun reiniciarApp() {
        // Intenta reiniciar la aplicación creando una nueva instancia de la actividad principal
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.inicio -> {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<HomeFragment>(R.id.fCView)
                addToBackStack(null)
            }
            true
        }
        R.id.clasificaciones -> {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<TeamsFragment>(R.id.fCView)
                addToBackStack(null)
            }
            true
        }
        R.id.jugadores -> {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<PlayersFragment>(R.id.fCView)
                addToBackStack(null)
            }
            true
        }
        R.id.chat -> {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ChatFragment>(R.id.fCView)
                addToBackStack(null)
            }
            true
        }
        R.id.imagenes -> {
            true
        }
        else -> false
    }


}