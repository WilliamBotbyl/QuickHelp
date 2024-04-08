package com.mobilejavaapp.quickhelp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    fun showUnderlineForSignIn(show: Boolean, view: View) {
        val underlineView = view.findViewById<View>(R.id.underlineView)
        underlineView.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    fun showUnderlineForSignUp(show: Boolean, view: View) {
        val underlineView = view.findViewById<View>(R.id.underlineView)
        underlineView.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}