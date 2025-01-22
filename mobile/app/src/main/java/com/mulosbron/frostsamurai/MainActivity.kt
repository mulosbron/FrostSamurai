package com.mulosbron.frostsamurai

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        // Uygulama ilk açıldığında giriş kontrolü yap
        if (isLoggedIn()) {
            // Kullanıcı giriş yaptıysa ARFragment göster
            replaceFragment(ARFragment())
        } else {
            // Giriş yoksa LoginFragment göster
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
     * Yardımcı fonksiyon: Kullanıcı giriş yapmış mı?
     * SharedPreferences içinde kaydedilmiş email/password var mı diye kontrol eder.
     */
    fun isLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("email", null)
        val savedPassword = sharedPreferences.getString("password", null)
        // Eğer email veya password kayıtlı değilse giriş yapılmamış kabul edilir.
        return !savedEmail.isNullOrEmpty() && !savedPassword.isNullOrEmpty()
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
