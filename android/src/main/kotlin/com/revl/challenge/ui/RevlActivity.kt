package com.revl.challenge.ui

import android.app.Activity
import android.os.Bundle
import kotterknife.bindView
import com.revl.challenge.R
import com.revl.challenge.navigator.Navigator

class RevlActivity : Activity() {

    private val navigator: Navigator.View by bindView(R.id.navigator)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onBackPressed() {
        if (!navigator.pop()) {
            super.onBackPressed()
        }
    }

}
