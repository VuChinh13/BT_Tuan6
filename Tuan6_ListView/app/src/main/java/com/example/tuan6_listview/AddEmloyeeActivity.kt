package com.example.tuan6_listview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tuan6_listview.databinding.ActivityAddEmloyeeBinding

class AddEmloyeeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEmloyeeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddEmloyeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btThemMoi.setOnClickListener {
            // Lấy dữ liệu từ các trường EditText
            val ten = binding.etTen.text.toString()
            val ngaySinh = binding.etNgaySinh.text.toString()
            val id = binding.etId.text.toString()
            val queQuan = binding.etQueQuan.text.toString()
            val phongBan = binding.etPhongBan.text.toString()
            val trangThai = binding.etTrangThai.text.toString()
            val trinhDoHocVan = binding.etTrinhDoHocVan.text.toString()
            val kinhNghiem = binding.etKinhNghiem.text.toString()

            // Kiểm tra dữ liệu nếu rỗng
            if (ten.isEmpty() || ngaySinh.isEmpty() || id.isEmpty() || queQuan.isEmpty() || phongBan.isEmpty() || trangThai.isEmpty() || trinhDoHocVan.isEmpty() || kinhNghiem.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            } else {
                // Tạo Intent để gửi dữ liệu về MainActivity
                val resultIntent = Intent().apply {
                    putExtra("ten", ten)
                    putExtra("ngay_sinh", ngaySinh)
                    putExtra("id", id)
                    putExtra("que_quan", queQuan)
                    putExtra("phong_ban", phongBan)
                    putExtra("trang_thai", trangThai)
                    putExtra("trinh_do_hoc_van", trinhDoHocVan)
                    putExtra("kinh_nghiem", kinhNghiem)
                }

                // Trả dữ liệu về MainActivity
                setResult(RESULT_OK, resultIntent)
                finish()  // Đóng Activity này và quay lại MainActivity
            }
        }
    }
}
