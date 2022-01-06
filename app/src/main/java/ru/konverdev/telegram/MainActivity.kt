package ru.konverdev.telegram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ru.konverdev.telegram.databinding.ActivityMainBinding
import ru.konverdev.telegram.ui.fragments.ChatsFragment
import ru.konverdev.telegram.ui.objects.AppDrawer

class MainActivity : AppCompatActivity() {
    private lateinit var mBinging: ActivityMainBinding
    private lateinit var mAppDrawer : AppDrawer
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinging = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinging.root)
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction()
            .replace(R.id.dataContainer, ChatsFragment())
            .commit()

        mToolbar = mBinging.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)

        setSupportActionBar(mToolbar)

        mAppDrawer.create()
    }
}