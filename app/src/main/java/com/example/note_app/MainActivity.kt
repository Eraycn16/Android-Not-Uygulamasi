package com.example.note_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.note_app.databinding.ActivityMainBinding
import com.example.note_app.objects.SplashControl
import com.example.note_app.realm.DB

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DB(this)

        binding.btnAdd.setOnClickListener {
            val baslik = binding.notBaslik.text
            val icerik = binding.notIcerik.text
            if (baslik.isNotEmpty()){
                if (icerik.isNotEmpty()){
                    if(binding.control.isChecked){
                        db.noteAdd(baslik.toString(),icerik.toString())
                        SplashControl.addedFlag=true
                        val i = Intent(this,SplashScreen::class.java)
                        startActivity(i)
                        binding.notBaslik.text.clear()
                        binding.notIcerik.text.clear()
                        binding.notBaslik.requestFocus()
                    }else{
                        val i = Intent(this,SplashScreen::class.java)
                        i.putExtra("error","Not eklemeyi kabul etmelisiniz!!")
                        startActivity(i)
                        binding.control.requestFocus()
                    }
                }else{
                    binding.notIcerik.error = "Icerik alanı boş bırakılamaz!"
                 binding.notIcerik.requestFocus()
                }
            }else{
                binding.notBaslik.error="Başlık alanı boş bırakılamaz!"
                binding.notBaslik.requestFocus()
            }
        }

        binding.btnList.setOnClickListener {
            val i = Intent(this,ListActivity::class.java)
            startActivity(i)
        }
    }
}