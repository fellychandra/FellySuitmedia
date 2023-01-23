package com.example.fellycsuitmedia

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fellycsuitmedia.databinding.ActivityThirdBinding
import com.google.firebase.database.*

class ThirdActivity: AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    private var firebaseDataBase: FirebaseDatabase?= null
    private var databaseReference: DatabaseReference? = null
    private var list = mutableListOf<Profile>()

    private var adapter: ProfileAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: androidx.appcompat.widget.Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title="Third Screen"

        firebaseDataBase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDataBase?.getReference("ap")
        initRecyclerView()


        getData()
    }

    private fun getData() {
        adapter = ProfileAdapter()
        binding.apply {
            recyclerView.layoutManager =  LinearLayoutManager(this@ThirdActivity)
            recyclerView.adapter = adapter
        }
    }

    private fun initRecyclerView() {
        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (ds in snapshot.children){
                    val id = ds.key
                    val foto = ds.child("foto").value.toString()
                    val nama = ds.child("nama_lengkap").value.toString()
                    val email = ds.child("email").value.toString()

                    val profile = Profile(id = id, foto = foto, nama = nama, email = email)
                    list.add(profile)
                }
                Log.e("ooooo","size:${list.size} ")

                adapter?.setItems(list)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("oooo", "onCancelled :${error.toException()}")
            }
        })
    }
}