package com.example.tuan6_listview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tuan6_listview.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận dữ liệu từ bên MainActivity
        val ten = intent.getStringExtra("ten")
        val ngaySinh = intent.getStringExtra("ngay_sinh")
        val id = intent.getStringExtra("id")
        val queQuan = intent.getStringExtra("que_quan")
        val phongBan = intent.getStringExtra("phong_ban")
        val trangThai = intent.getStringExtra("trang_thai")
        val trinhDoHocVan = intent.getStringExtra("trinh_do_hoc_van")
        val kinhNghiem = intent.getStringExtra("kinh_nghiem")

        // Hiển thị dữ liệu lên giao diện
        binding.etTen.append(ten)
        binding.etNgaySinh.append(ngaySinh)
        binding.etId.text.append(id)
        binding.etQueQuan.append(queQuan)
        binding.etPhongBan.append(phongBan)
        binding.etTrangThai.append(trangThai)
        binding.etTrinhDoHocVan.append(trinhDoHocVan)
        binding.etKinhNghiem.append(kinhNghiem)



        // Xử lí sự kiện khi mà người dùng nhấn vào nút chỉnh sửa
        binding.btChinhSua.setOnClickListener {
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