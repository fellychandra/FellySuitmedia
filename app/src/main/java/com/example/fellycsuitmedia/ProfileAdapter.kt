package com.example.fellycsuitmedia

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class ProfileAdapter :RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    private  var profileList = mutableListOf<Profile>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.custom_list, parent,false)
        return ProfileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
       val profile = profileList[position]
        holder.setItem(profile)

        holder.itemView.setOnClickListener(){ v ->
            val intent = Intent(v.context, SecondActivity::class.java).apply {
                putExtra("nama", profile.nama)
            }
            v.context.startActivity(intent)
            true
        }
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    fun setItems(list: MutableList<Profile>){
        this.profileList =list
        notifyDataSetChanged()
    }

    class ProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var tvNama: TextView? = null
        private var tvEmail: TextView? = null

        private lateinit var storage: StorageReference
        private lateinit var bitmap: Bitmap
        private lateinit var localFile: File


        fun setItem(data: Profile){
            tvNama = itemView.findViewById(R.id.tx_nama)
            tvEmail = itemView.findViewById(R.id.tx_email)

            storage = FirebaseStorage.getInstance().getReference().child(data.foto.toString())
            localFile = File.createTempFile("felly", "jpg")
            storage.getFile(localFile).addOnFailureListener {

            }.addOnSuccessListener { taskSnapshot ->
                bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                var image = itemView.findViewById<ImageView>(R.id.imageView)
                image.setImageBitmap(bitmap)
            }

            tvNama?.text = data.nama
            tvEmail?.text = data.email
        }
    }
}