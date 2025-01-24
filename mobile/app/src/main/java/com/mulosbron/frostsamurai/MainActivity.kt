package com.mulosbron.frostsamurai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        // Uygulama ilk açıldığında giriş kontrolü
        if (isLoggedIn()) {
            // Giriş yapılmışsa ARFragment
            replaceFragment(ARFragment())
        } else {
            // Giriş yoksa LoginFragment
            replaceFragment(LoginFragment())
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_ar -> {
                    if (isLoggedIn()) {
                        replaceFragment(ARFragment())
                    } else {
                        replaceFragment(LoginFragment())
                    }
                    true
                }
                R.id.nav_market -> {
                    if (isLoggedIn()) {
                        replaceFragment(MarketFragment())
                    } else {
                        replaceFragment(LoginFragment())
                    }
                    true
                }
                R.id.nav_login -> {
                    replaceFragment(LoginFragment())
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Kullanıcı oturum açmış mı?
     */
    fun isLoggedIn(): Boolean {
        val user = FirebaseAuth.getInstance().currentUser
        return user != null
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
