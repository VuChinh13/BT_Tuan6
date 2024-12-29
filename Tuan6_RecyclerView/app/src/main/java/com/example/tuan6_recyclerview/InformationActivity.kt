package com.example.tuan6_recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tuan6_recyclerview.databinding.ActivityInformationBinding

class InformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận dữ liệu từ Intent
        val ten = intent.getStringExtra(EXTRA_TEN)
        val ngaySinh = intent.getStringExtra(EXTRA_NGAY_SINH)
        val id = intent.getStringExtra(EXTRA_ID)
        val queQuan = intent.getStringExtra(EXTRA_QUE_QUAN)
        val phongBan = intent.getStringExtra(EXTRA_PHONG_BAN)
        val trangThai = intent.getStringExtra(EXTRA_TRANG_THAI)
        val trinhDoHocVan = intent.getStringExtra(EXTRA_TRINH_DO_HOC_VAN)
        val kinhNghiem = intent.getStringExtra(EXTRA_KINH_NGHIEM)


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