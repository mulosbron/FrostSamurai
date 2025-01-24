package com.mulosbron.frostsamurai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.mulosbron.frostsamurai.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    // FirebaseAuth
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Oturum durumu kontrolü
        if (auth.currentUser != null) {
            // Kullanıcı giriş yapmışsa Logout arayüzü göster
            showLogoutUI()
        } else {
            // Kullanıcı giriş yapmamışsa Login arayüzü göster
            showLoginUI()
        }

        // "Şifremi Unuttum" tıklaması
        binding.tvForgotPassword.setOnClickListener {
            // Burada Firebase Password Reset akışı ekleyebilirsiniz
            // auth.sendPasswordResetEmail(kullaniciEmail).addOnSuccessListener { ... }
        }

        // Giriş Yap butonu
        binding.btnLogin.setOnClickListener {
            performLogin()
        }

        // Kayıt Ol butonu
        binding.tvRegister.setOnClickListener {
            (activity as? MainActivity)?.replaceFragment(RegisterFragment())
        }

        // Çıkış butonu
        binding.btnLogout.setOnClickListener {
            performLogout()
        }
    }

    private fun performLogin() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Email and password cannot be empty.", Toast.LENGTH_SHORT).show()
            return
        }

        // Firebase Auth ile giriş yap
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Giriş başarılı
                    Toast.makeText(requireContext(), "Login successful.", Toast.LENGTH_SHORT).show()
                    // Örnek: ARFragment'a yönlendirebilirsiniz
                    (activity as? MainActivity)?.replaceFragment(ARFragment())
                } else {
                    // Giriş başarısız
                    val message = task.exception?.localizedMessage ?: "Login failed."
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun performLogout() {
        auth.signOut()
        Toast.makeText(requireContext(), "Çıkış yapıldı.", Toast.LENGTH_SHORT).show()
        showLoginUI()
    }

    private fun showLogoutUI() {
        // Giriş bileşenlerini gizle
        binding.etEmail.visibility = View.GONE
        binding.etPassword.visibility = View.GONE
        binding.tvForgotPassword.visibility = View.GONE
        binding.btnLogin.visibility = View.GONE
        binding.tvRegister.visibility = View.GONE

        // Logout butonunu göster
        binding.btnLogout.visibility = View.VISIBLE
    }

    private fun showLoginUI() {
        // Giriş bileşenlerini göster
        binding.etEmail.visibility = View.VISIBLE
        binding.etPassword.visibility = View.VISIBLE
        binding.tvForgotPassword.visibility = View.VISIBLE
        binding.btnLogin.visibility = View.VISIBLE
        binding.tvRegister.visibility = View.VISIBLE

        // Logout butonunu gizle
        binding.btnLogout.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
