package com.mulosbron.frostsamurai

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mulosbron.frostsamurai.databinding.FragmentRegisterBinding
import java.security.MessageDigest

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
            performRegister()
        }

        binding.tvLogin.setOnClickListener {
            (activity as? MainActivity)?.replaceFragment(LoginFragment())
        }
    }

    private fun performRegister() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.fill_fields), Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(requireContext(), getString(R.string.password_mismatch), Toast.LENGTH_SHORT).show()
            return
        }

        val hashedPassword = hashPassword(password)

        val sharedPreferences = requireActivity().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putString("email", email)
            .putString("password", hashedPassword)
            .apply()

        Toast.makeText(requireContext(), getString(R.string.registration_success), Toast.LENGTH_SHORT).show()

        (activity as? MainActivity)?.replaceFragment(LoginFragment())
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
