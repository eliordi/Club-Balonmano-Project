package es.jcc.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.snackbar.Snackbar
import es.jcc.project.Dialogs.MyDialog
import es.jcc.project.Dialogs.MyDialog2
import es.jcc.project.Login.LoginFragment
import es.jcc.project.Login.ResetPassFragment
import es.jcc.project.Login.SignupFragment
import es.jcc.project.Login.WelcomeFragment

class LoginActivity : AppCompatActivity(), WelcomeFragment.WelcomeFragmentListener,
    LoginFragment.LoginFragmentListener, SignupFragment.SignupFragmentListener,
    MyDialog.MyDialogListener, MyDialog2.MyDialogListener, ResetPassFragment.ResetPassFragmentListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Project)
        setContentView(R.layout.activity_login)

    }

    override fun onLoginButtonClicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<LoginFragment>(R.id.fContainer)
            addToBackStack(null)
        }
    }

    override fun onSignupButtonClicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SignupFragment>(R.id.fContainer)
            addToBackStack(null)
        }
    }

    override fun onBackButtonClicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<WelcomeFragment>(R.id.fContainer)
            addToBackStack(null)
        }
    }

    override fun onLoginButton2Clicked() {
        val intent = Intent(this, AppActivity::class.java)
        startActivity(intent)
    }

    override fun onResetButtonClicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ResetPassFragment>(R.id.fContainer)
            addToBackStack(null)
        }
    }

    override fun onBackButton2Clicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<WelcomeFragment>(R.id.fContainer)
            addToBackStack(null)
        }
    }

    override fun onSignupButton2Clicked() {
        val intent = Intent(this, AppActivity::class.java)
        startActivity(intent)
    }

    override fun onDialogPositiveClick() {
        //nasin
    }

    override fun onDialogPositiveClick2() {
        //nasin
    }

    override fun onSendButtonClicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<LoginFragment>(R.id.fContainer)
            addToBackStack(null)
        }
    }

    override fun onBackButton3Clicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<LoginFragment>(R.id.fContainer)
            addToBackStack(null)
        }
    }


}