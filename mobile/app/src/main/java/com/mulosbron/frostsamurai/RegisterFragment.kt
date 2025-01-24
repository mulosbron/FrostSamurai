package com.mulosbron.frostsamurai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.mulosbron.frostsamurai.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    // FirebaseAuth nesnesi
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        // FirebaseAuth örneğini al
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Kayıt (Sign Up) butonu
        binding.btnSignUp.setOnClickListener {
            performRegister()
        }

        // Zaten hesabın var mı? Giriş ekranına dön
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

        // Firebase Auth ile kullanıcı oluştur
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Kayıt başarılı
                    Toast.makeText(requireContext(), getString(R.string.registration_success), Toast.LENGTH_SHORT).show()

                    // Ek bilgi saklamak isterseniz (ör. score, displayName...), Realtime Database'e yazabilirsiniz:
                    val userId = auth.currentUser?.uid
                    val dbRef = FirebaseDatabase.getInstance().getReference("Users")
                    val userMap = mapOf(
                        "email" to email,
                        "score" to 0
                    )
                    userId?.let {
                        dbRef.child(it).setValue(userMap)
                    }

                    // Kayıt sonrası LoginFragment'e yönlendirme (isterseniz doğrudan ARFragment de yapabilirsiniz)
                    (activity as? MainActivity)?.replaceFragment(LoginFragment())
                } else {
                    // Kayıt başarısız
                    val message = task.exception?.localizedMessage ?: "Registration failed."
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
