package com.mobilejavaapp.quickhelp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.mobilejavaapp.quickhelp.MainActivity
import com.mobilejavaapp.quickhelp.R


class RegisterFragment : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.showUnderlineForSignUp(true, view)

        val signIniew: TextView = view.findViewById(R.id.textViewSignIn)


        val usernameInput: TextInputEditText = view.findViewById(R.id.signInName)
        val emailInput: TextInputEditText = view.findViewById(R.id.signInEmail)
        val passwordInput: TextInputEditText = view.findViewById(R.id.signInPassword)

        firebaseAuth = FirebaseAuth.getInstance()

        val registerButton: MaterialButton = view.findViewById(R.id.registerButton)
        registerButton.setOnClickListener {
            if (emailInput.text?.isNotBlank() == true && passwordInput.text?.isNotBlank() == true && usernameInput.text?.isNotBlank() == true) {
            firebaseAuth.createUserWithEmailAndPassword(
                emailInput.toString(),
                passwordInput.toString()
            )
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        navigateDashboard()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            task.exception.toString(),
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
        }
        }
        signIniew.setOnClickListener {
            val loginFragment = LoginFragment()

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, loginFragment)
                .addToBackStack(null)
                .commit()
        }

    }
    private fun navigateDashboard(){
        val dashBoardFragment = DashBoardFragment()

        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, dashBoardFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        view?.let { (activity as? MainActivity)?.showUnderlineForSignUp(false, it) }
    }
}
