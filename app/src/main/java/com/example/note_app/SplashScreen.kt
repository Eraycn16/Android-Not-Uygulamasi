package com.example.note_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.note_app.databinding.ActivitySplashScreenBinding
import com.example.note_app.objects.SplashControl
import java.util.*

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (SplashControl.addedFlag == true){

            binding.splashText.text = "Başarılı bir şekilde yeni not eklendi!"
            binding.splashIcon.setImageResource(R.drawable.okay)
            Timer().schedule(Task(this),1500)
        }else{
            val err = intent.getStringExtra("error")
            binding.splashText.text = err
            binding.splashIcon.setImageResource(R.drawable.close)
            Timer().schedule(Task(this),1500)
        }
    }

    class Task(val activity: SplashScreen):TimerTask(){
        override fun run() {
            //val i = Intent(activity,MainActivity::class.java)

            if (SplashControl.addedFlag==true){
                SplashControl.addedFlag=false
            }
          //  activity.startActivity(i)
            activity.finish()
        }

    }
}