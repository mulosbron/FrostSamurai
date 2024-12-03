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

        binding.tvForgotPassword.setOnClickListener {

        }

        binding.btnLogin.setOnClickListener {
            performLogin()
        }

        binding.tvRegister.setOnClickListener {
            (activity as? MainActivity)?.replaceFragment(RegisterFragment())
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
                (activity as? MainActivity)?.replaceFragment(ARFragment())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val messageDigest = MessageDigest.getInstance("SHA-256")
        val digest = messageDigest.digest(bytes)
        return digest.joinToString("") { "%02x".format(it) }
    }
}
