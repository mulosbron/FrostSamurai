package com.mulosbron.frostsamurai

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mulosbron.frostsamurai.databinding.FragmentLoginBinding
import java.security.MessageDigest

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Eğer kullanıcı giriş yaptıysa, login bileşenlerini gizle, logout butonunu göster
        if ((activity as? MainActivity)?.isLoggedIn() == true) {
            showLogoutUI()
        } else {
            showLoginUI()
        }

        // "Şifremi Unuttum" tıklaması
        binding.tvForgotPassword.setOnClickListener {
            // Unutulan şifre ile ilgili ek işlem yapılabilir
        }

        // "Giriş Yap" butonuna tıklama
        binding.btnLogin.setOnClickListener {
            performLogin()
        }

        // "Kayıt Ol" (Register) tıklaması
        binding.tvRegister.setOnClickListener {
            (activity as? MainActivity)?.replaceFragment(RegisterFragment())
        }

        // "Çıkış Yap" butonu
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

        val sharedPreferences = requireActivity().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("email", null)
        val savedPassword = sharedPreferences.getString("password", null)
        val hashedPassword = hashPassword(password)

        when {
            savedEmail == null || savedEmail != email || savedPassword != hashedPassword -> {
                Toast.makeText(requireContext(), "Email or password does not match.", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(requireContext(), "Login successful.", Toast.LENGTH_SHORT).show()
                // Giriş başarılı olduğuna göre ARFragment'e geçiş
                (activity as? MainActivity)?.replaceFragment(ARFragment())
            }
        }
    }

    private fun performLogout() {
        // SharedPreferences içindeki kullanıcı bilgilerini sil
        val sharedPreferences = requireActivity().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        // Kullanıcıyı tekrar login ekranına dönüp, login bileşenlerini göster
        Toast.makeText(requireContext(), "Çıkış yapıldı.", Toast.LENGTH_SHORT).show()
        showLoginUI()
    }

    private fun showLogoutUI() {
        // Login bileşenlerini gizle
        binding.etEmail.visibility = View.GONE
        binding.etPassword.visibility = View.GONE
        binding.tvForgotPassword.visibility = View.GONE
        binding.btnLogin.visibility = View.GONE
        binding.tvRegister.visibility = View.GONE

        // Logout butonunu göster
        binding.btnLogout.visibility = View.VISIBLE
    }

    private fun showLoginUI() {
        // Login bileşenlerini göster
        binding.etEmail.visibility = View.VISIBLE
        binding.etPassword.visibility = View.VISIBLE
        binding.tvForgotPassword.visibility = View.VISIBLE
        binding.btnLogin.visibility = View.VISIBLE
        binding.tvRegister.visibility = View.VISIBLE

        // Logout butonunu gizle
        binding.btnLogout.visibility = View.GONE
    }

    private fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val messageDigest = MessageDigest.getInstance("SHA-256")
        val digest = messageDigest.digest(bytes)
        return digest.joinToString("") { "%02x".format(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
