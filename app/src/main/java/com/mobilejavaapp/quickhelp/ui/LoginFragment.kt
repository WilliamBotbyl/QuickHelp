package com.mobilejavaapp.quickhelp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.mobilejavaapp.quickhelp.MainActivity
import com.mobilejavaapp.quickhelp.R


class LoginFragment : Fragment() {

    private lateinit var constraintLayout: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signInTextView: TextView = view.findViewById(R.id.textViewSignIn)
        val signUpTextView: TextView = view.findViewById(R.id.textViewSignUp)
        val layoutTabsOuter: LinearLayout = view.findViewById(R.id.layoutTabsOuter)
        val loginFragmentLayout: ConstraintLayout = view.findViewById(R.id.loginFragmentLayout)
        (activity as? MainActivity)?.showUnderlineForSignIn(true, view)

        val emailInput: TextInputEditText = view.findViewById(R.id.signInEmail)
        val passwordInput: TextInputEditText = view.findViewById(R.id.signInPassword)
        val usernameInput: TextInputEditText = view.findViewById(R.id.signInName)

        signUpTextView.setOnClickListener {
            navigateRegister()
        }

        signInTextView.setOnClickListener {
            var auth = FirebaseAuth.getInstance()


            if (emailInput.text?.isNotBlank() == true && passwordInput.text?.isNotBlank() == true && usernameInput.text?.isNotBlank() == true) {
                if (auth.currentUser != null) {
                    Toast.makeText(
                        requireContext(),
                        "You must register first",
                        Toast.LENGTH_LONG
                    )
                        .show()
                } else {
                    navigateHome()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Enter  all inputs",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }

    }

    private fun navigateHome(){
        val dashBoardFragment = DashBoardFragment()

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, dashBoardFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun navigateRegister(){
        val registerFragment = RegisterFragment()

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, registerFragment)
            .addToBackStack(null)
            .commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        view?.let { (activity as? MainActivity)?.showUnderlineForSignIn(false, it) }
    }

}