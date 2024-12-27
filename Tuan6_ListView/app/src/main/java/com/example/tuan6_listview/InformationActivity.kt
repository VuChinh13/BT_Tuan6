package com.example.tuan6_listview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tuan6_listview.databinding.ActivityInformationBinding

class InformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận dữ liệu từ Intent
        val ten = intent.getStringExtra("ten")
        val ngaySinh = intent.getStringExtra("ngay_sinh")
        val id = intent.getStringExtra("id")
        val queQuan = intent.getStringExtra("que_quan")
        val phongBan = intent.getStringExtra("phong_ban")
        val trangThai = intent.getStringExtra("trang_thai")
        val trinhDoHocVan = intent.getStringExtra("trinh_do_hoc_van")
        val kinhNghiem = intent.getStringExtra("kinh_nghiem")

        // Hiển thị dữ liệu lên giao diện
        binding.tvTen.text = ten
        binding.tvNgaySinh.text = ngaySinh
        binding.tvId.text = id
        binding.tvQueQuan.text = queQuan
        binding.tvPhongBan.text = phongBan
        binding.tvTrangThai.text = trangThai
        binding.tvTrinhDoHocVan.text = trinhDoHocVan
        binding.tvKinhNghiem.text = kinhNghiem

    }
}


