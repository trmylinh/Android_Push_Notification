package com.example.startactivityfromnotification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.startactivityfromnotification.databinding.ActivityListProductBinding

private lateinit var binding: ActivityListProductBinding
class ListProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGotodetail.setOnClickListener{
            val intent = Intent(this@ListProductActivity, DetailActivity::class.java)
            startActivity(intent);
        }
    }
}