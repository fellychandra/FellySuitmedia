package com.example.fellycsuitmedia

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.fellycsuitmedia.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val toolbar: androidx.appcompat.widget.Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title="Second Screen"


        var nama = intent.getStringExtra("nama").toString()
        binding.otnama.setText(nama).toString()

        binding.btnchoose.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
            startActivity(intent)
        })
    }
}