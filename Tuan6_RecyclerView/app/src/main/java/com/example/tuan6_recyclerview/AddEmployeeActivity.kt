package com.example.tuan6_recyclerview

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tuan6_recyclerview.databinding.ActivityAddEmployeeBinding

class AddEmployeeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEmployeeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

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



        // Bấm nút "Thêm mới" để gửi dữ liệu
        binding.btThemMoi.setOnClickListener {
            // Lấy dữ liệu từ các trường EditText
            val ten = binding.etTen.text.toString()
            val ngaySinh = binding.etNgaySinh.text.toString()
            val id = binding.etId.text.toString()
            val queQuan = binding.etQueQuan.text.toString()
            val phongBan = binding.spPhongBan.selectedItem.toString()
            val trangThai = binding.spTrangThai.selectedItem.toString()
            val trinhDoHocVan = binding.etTrinhDoHocVan.text.toString()
            val kinhNghiem = binding.etKinhNghiem.text.toString()

            // Tạo Intent để gửi dữ liệu sang Activity khác
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

            // Trả dữ liệu về Activity gọi
            setResult(RESULT_OK, resultIntent)
            finish()  // Đóng Activity này và quay lại Activity trước
        }
    }
}