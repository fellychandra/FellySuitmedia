package com.example.fellycsuitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.fellycsuitmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btncheck.setOnClickListener(View.OnClickListener {
            val palindrom: String = binding.txpalindrom.text.toString().trim()

            //menghilangkan space yang ada
            val hilangspace = palindrom.replace(" ", "")

            if (palindrom.isEmpty()) {
                binding.txpalindrom.error = "Palindrom Harus diisi"
                binding.txpalindrom.requestFocus()
            }else{
                val sbs = StringBuilder(hilangspace)

                //membalikkan string
                val reverseStrs = sbs.reverse().toString()
                //bandingin text terbalik dengan inputan
                if (hilangspace.equals(reverseStrs, ignoreCase = true)){
                    Toast.makeText(applicationContext, "isPalindrome", Toast.LENGTH_SHORT).show()
                    Log.e("testpalindrom","test:${hilangspace.equals(reverseStrs, ignoreCase = true)} ")
                }else{
                    Toast.makeText(applicationContext, "not Palindrome", Toast.LENGTH_SHORT).show()
                    Log.e("testnopalindrom","test:${hilangspace.equals(reverseStrs, ignoreCase = true)} ")
                }
            }
        })

        binding.btnnext.setOnClickListener(View.OnClickListener {
            val nama: String = binding.txnama.text.toString()
            if (nama.isEmpty()) {
                binding.txnama.error = "Masukkan Nama"
                binding.txnama.requestFocus()
            }else{
                val intent = Intent(this@MainActivity, SecondActivity::class.java).apply {
                    putExtra("nama", nama)
                }
                startActivity(intent)
            }
        })
    }
}