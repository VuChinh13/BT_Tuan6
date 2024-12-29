package com.example.tuan6_recyclerview

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tuan6_recyclerview.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận dữ liệu từ bên MainActivity
        val ten = intent.getStringExtra(EXTRA_TEN)
        val ngaySinh = intent.getStringExtra(EXTRA_NGAY_SINH)
        val id = intent.getStringExtra(EXTRA_ID)
        val queQuan = intent.getStringExtra(EXTRA_QUE_QUAN)
        val phongBan = intent.getStringExtra(EXTRA_PHONG_BAN)
        val trangThai = intent.getStringExtra(EXTRA_TRANG_THAI)
        val trinhDoHocVan = intent.getStringExtra(EXTRA_TRINH_DO_HOC_VAN)
        val kinhNghiem = intent.getStringExtra(EXTRA_KINH_NGHIEM)

        // Tạo một danh sách các lựa chọn cho Spinner
        val jobOptions = arrayOf("Lập trình viên", "Kinh doanh", "Thiết kế", "Marketing")
        val statusOptions = arrayOf("Nhân viên chính thức", "Thực tập sinh")

        // Tạo ArrayAdapter cho Spinner công việc
        val jobAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, jobOptions)
        jobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spPhongBan.adapter = jobAdapter

        // Tạo ArrayAdapter cho Spinner trạng thái
        val statusAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statusOptions)
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spTrangThai.adapter = statusAdapter

        // Thiết lập giá trị mặc định cho spPhongBan (Công việc)
        val phongBanIndex = jobOptions.indexOf(phongBan)
        if (phongBanIndex >= 0) {
            binding.spPhongBan.setSelection(phongBanIndex)
        }

        // Thiết lập giá trị mặc định cho spTrangThai (Trạng thái)
        val trangThaiIndex = statusOptions.indexOf(trangThai)
        if (trangThaiIndex >= 0) {
            binding.spTrangThai.setSelection(trangThaiIndex)
        }


        // Hiển thị dữ liệu lên giao diện
        binding.etTen.append(ten)
        binding.etNgaySinh.append(ngaySinh)
        binding.etId.text.append(id)
        binding.etQueQuan.append(queQuan)
        binding.etTrinhDoHocVan.append(trinhDoHocVan)
        binding.etKinhNghiem.append(kinhNghiem)


        // Xử lí sự kiện khi mà người dùng nhấn vào nút chỉnh sửa
        binding.btChinhSua.setOnClickListener {
            // Lấy dữ liệu từ các trường EditText
            val ten = binding.etTen.text.toString()
            val ngaySinh = binding.etNgaySinh.text.toString()
            val id = binding.etId.text.toString()
            val queQuan = binding.etQueQuan.text.toString()
            val phongBan = binding.spPhongBan.selectedItem.toString()
            val trangThai = binding.spTrangThai.selectedItem.toString()
            val trinhDoHocVan = binding.etTrinhDoHocVan.text.toString()
            val kinhNghiem = binding.etKinhNghiem.text.toString()

            // Kiểm tra dữ liệu nếu rỗng
            if (ten.isEmpty() || ngaySinh.isEmpty() || id.isEmpty() || queQuan.isEmpty() || phongBan.isEmpty() || trangThai.isEmpty() || trinhDoHocVan.isEmpty() || kinhNghiem.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            } else {
                // Tạo Intent để gửi dữ liệu về MainActivity
                val resultIntent = Intent().apply {
                    putExtra(EXTRA_TEN, ten)
                    putExtra(EXTRA_NGAY_SINH, ngaySinh)
                    putExtra(EXTRA_ID, id)
                    putExtra(EXTRA_QUE_QUAN, queQuan)
                    putExtra(EXTRA_PHONG_BAN, phongBan)
                    putExtra(EXTRA_TRANG_THAI, trangThai)
                    putExtra(EXTRA_TRINH_DO_HOC_VAN, trinhDoHocVan)
                    putExtra(EXTRA_KINH_NGHIEM, kinhNghiem)
                }

                // Trả dữ liệu về MainActivity
                setResult(RESULT_OK, resultIntent)
                finish()  // Đóng Activity này và quay lại MainActivity
            }
        }
    }
}