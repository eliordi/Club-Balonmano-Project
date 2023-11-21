package es.jcc.project

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
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.navigation.NavigationBarView
import es.jcc.project.Login.LoginFragment
import es.jcc.project.MainApp.PlayersFragment
import es.jcc.project.databinding.ActivityAppBinding
import java.util.Locale


class AppActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener, PlayersFragment.PlayersFragmentListener {

    private lateinit var binding: ActivityAppBinding

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
            true
        }
        R.id.clasificaciones -> {
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
        R.id.galeria -> {
            true
        }
        else -> false
    }

    override fun onPrimeraButtonClicked() {
        Toast.makeText(this, "Perfect", Toast.LENGTH_SHORT).show()
    }

    override fun onSegonaButtonClicked() {

    }

}