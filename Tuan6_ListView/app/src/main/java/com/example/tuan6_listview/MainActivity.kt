package com.example.tuan6_listview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tuan6_listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var customAdapter: CustomAdapter
    private var list = mutableListOf<OutData>()
    private var toolbarVisible = false
    private var originalList = mutableListOf<OutData>() // Lưu trữ danh sách gốc
    private val selectedItems = mutableListOf<OutData>() // Danh sách các mục đã chọn


    // Khai báo ActivityResultLauncher để nhận kết quả từ EditActivity
    private val editEmployeeLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val ten = data?.getStringExtra("ten") ?: ""
            val ngaySinh = data?.getStringExtra("ngay_sinh") ?: ""
            val id = data?.getStringExtra("id") ?: ""
            val queQuan = data?.getStringExtra("que_quan") ?: ""
            val phongBan = (data?.getStringExtra("phong_ban") ?: "").trim()
            val trangThai = (data?.getStringExtra("trang_thai") ?: "").trim()
            val trinhDoHocVan = data?.getStringExtra("trinh_do_hoc_van") ?: ""
            val kinhNghiem = data?.getStringExtra("kinh_nghiem") ?: ""

            // Tìm nhân viên theo ID
            val index = list.indexOfFirst { it.id == id }
            if (index != -1) {
                if (phongBan == "Lập trình viên") {
                    if (trangThai == "Thực tập sinh") {
                        list[index] = OutData(
                            R.drawable.ic_dev,
                            phongBan,
                            R.drawable.ic_intern, // thực tập sinh
                            trangThai,
                            id,
                            ten,
                            ngaySinh,
                            queQuan,
                            trinhDoHocVan,
                            kinhNghiem
                        )
                        customAdapter.notifyDataSetChanged()
                    } else {
                        list[index] = OutData(
                            R.drawable.ic_dev,
                            phongBan,
                            R.drawable.ic_official_employee, // nhân viên chính thức
                            trangThai,
                            id,
                            ten,
                            ngaySinh,
                            queQuan,
                            trinhDoHocVan,
                            kinhNghiem
                        )
                        customAdapter.notifyDataSetChanged()
                    }
                } else if (phongBan == "Thiết kế") {
                    if (trangThai == "Thực tập sinh") {
                        list[index] = OutData(
                            R.drawable.ic_desgin,
                            phongBan,
                            R.drawable.ic_intern, // thực tập sinh
                            trangThai,
                            id,
                            ten,
                            ngaySinh,
                            queQuan,
                            trinhDoHocVan,
                            kinhNghiem
                        )
                        customAdapter.notifyDataSetChanged()
                    } else {
                        list[index] = OutData(
                            R.drawable.ic_desgin,
                            phongBan,
                            R.drawable.ic_official_employee, // nhân viên chính thức
                            trangThai,
                            id,
                            ten,
                            ngaySinh,
                            queQuan,
                            trinhDoHocVan,
                            kinhNghiem
                        )
                        customAdapter.notifyDataSetChanged()
                    }
                } else if (phongBan == "Kinh doanh") {
                    if (trangThai == "Thực tập sinh") {
                         list[index] = OutData(
                            R.drawable.ic_business,
                            phongBan,
                            R.drawable.ic_intern, // thực tập sinh
                            trangThai,
                            id,
                            ten,
                            ngaySinh,
                            queQuan,
                            trinhDoHocVan,
                            kinhNghiem
                        )
                        customAdapter.notifyDataSetChanged()
                    } else {
                        list[index] = OutData(
                            R.drawable.ic_business,
                            phongBan,
                            R.drawable.ic_official_employee, // nhân viên chính thức
                            trangThai,
                            id,
                            ten,
                            ngaySinh,
                            queQuan,
                            trinhDoHocVan,
                            kinhNghiem
                        )
                        customAdapter.notifyDataSetChanged()
                    }
                } else if (phongBan == "Marketing") {
                    if (trangThai == "Thực tập sinh") {
                        list[index] = OutData(
                            R.drawable.ic_marketing,
                            phongBan,
                            R.drawable.ic_intern, // thực tập sinh
                            trangThai,
                            id,
                            ten,
                            ngaySinh,
                            queQuan,
                            trinhDoHocVan,
                            kinhNghiem
                        )
                        customAdapter.notifyDataSetChanged()
                    } else {
                        list[index] = OutData(
                            R.drawable.ic_marketing,
                            phongBan,
                            R.drawable.ic_official_employee, // nhân viên chính thức
                            trangThai,
                            id,
                            ten,
                            ngaySinh,
                            queQuan,
                            trinhDoHocVan,
                            kinhNghiem
                        )
                        customAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }



    // Khai báo ActivityResultLauncher để nhận kết quả từ AddEmloyeeActivity
    private val addEmployeeLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val ten = data?.getStringExtra("ten") ?: ""
            val ngaySinh = data?.getStringExtra("ngay_sinh") ?: ""
            val id = data?.getStringExtra("id") ?: ""
            val queQuan = data?.getStringExtra("que_quan") ?: ""
            val phongBan = (data?.getStringExtra("phong_ban") ?: "").trim()
            val trangThai = (data?.getStringExtra("trang_thai") ?: "").trim()
            val trinhDoHocVan = data?.getStringExtra("trinh_do_hoc_van") ?: ""
            val kinhNghiem = data?.getStringExtra("kinh_nghiem") ?: ""


            // Tạo đối tượng OutData mới và thêm vào danh sách
            // Đoạn code này dùng để set icon thôi nếu phòng ban là lập trình viên thì thêm icon là ic_dev
            // nếu là Thiết kế thì thêm icon là ic_desgin
            if (phongBan == "Lập trình viên") {
                if (trangThai == "Thực tập sinh") {
                    val newEmployee = OutData(
                        R.drawable.ic_dev,
                        phongBan,
                        R.drawable.ic_intern, // thực tập sinh
                        trangThai,
                        id,
                        ten,
                        ngaySinh,
                        queQuan,
                        trinhDoHocVan,
                        kinhNghiem
                    )
                    list.add(newEmployee)
                    customAdapter.notifyDataSetChanged()
                } else {
                    val newEmployee = OutData(
                        R.drawable.ic_dev,
                        phongBan,
                        R.drawable.ic_official_employee, // nhân viên chính thức
                        trangThai,
                        id,
                        ten,
                        ngaySinh,
                        queQuan,
                        trinhDoHocVan,
                        kinhNghiem
                    )
                    list.add(newEmployee)
                    customAdapter.notifyDataSetChanged()
                }
            } else if (phongBan == "Thiết kế") {
                if (trangThai == "Thực tập sinh") {
                    val newEmployee = OutData(
                        R.drawable.ic_desgin,
                        phongBan,
                        R.drawable.ic_intern, // thực tập sinh
                        trangThai,
                        id,
                        ten,
                        ngaySinh,
                        queQuan,
                        trinhDoHocVan,
                        kinhNghiem
                    )
                    list.add(newEmployee)
                    customAdapter.notifyDataSetChanged()
                } else {
                    val newEmployee = OutData(
                        R.drawable.ic_desgin,
                        phongBan,
                        R.drawable.ic_official_employee, // nhân viên chính thức
                        trangThai,
                        id,
                        ten,
                        ngaySinh,
                        queQuan,
                        trinhDoHocVan,
                        kinhNghiem
                    )
                    list.add(newEmployee)
                    customAdapter.notifyDataSetChanged()
                }
            } else if (phongBan == "Kinh doanh") {
                if (trangThai == "Thực tập sinh") {
                    val newEmployee = OutData(
                        R.drawable.ic_business,
                        phongBan,
                        R.drawable.ic_intern, // thực tập sinh
                        trangThai,
                        id,
                        ten,
                        ngaySinh,
                        queQuan,
                        trinhDoHocVan,
                        kinhNghiem
                    )
                    list.add(newEmployee)
                    customAdapter.notifyDataSetChanged()
                } else {
                    val newEmployee = OutData(
                        R.drawable.ic_business,
                        phongBan,
                        R.drawable.ic_official_employee, // nhân viên chính thức
                        trangThai,
                        id,
                        ten,
                        ngaySinh,
                        queQuan,
                        trinhDoHocVan,
                        kinhNghiem
                    )
                    list.add(newEmployee)
                    customAdapter.notifyDataSetChanged()
                }
            } else if (phongBan == "Marketing") {
                if (trangThai == "Thực tập sinh") {
                    val newEmployee = OutData(
                        R.drawable.ic_marketing,
                        phongBan,
                        R.drawable.ic_intern, // thực tập sinh
                        trangThai,
                        id,
                        ten,
                        ngaySinh,
                        queQuan,
                        trinhDoHocVan,
                        kinhNghiem
                    )
                    list.add(newEmployee)
                    customAdapter.notifyDataSetChanged()
                } else {
                    val newEmployee = OutData(
                        R.drawable.ic_marketing,
                        phongBan,
                        R.drawable.ic_official_employee, // nhân viên chính thức
                        trangThai,
                        id,
                        ten,
                        ngaySinh,
                        queQuan,
                        trinhDoHocVan,
                        kinhNghiem
                    )
                    list.add(newEmployee)
                    customAdapter.notifyDataSetChanged()
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list.add(
            OutData(
                R.drawable.ic_dev,
                "Lập trình viên",
                R.drawable.ic_intern,
                "Thực tập sinh",
                "01",
                "Vũ Văn Chính",
                "13/08/2004",
                "Hải Dương",
                "Đã học qua các ngôn ngữ cơ bản",
                "Đang học năm 3 đại học"
            )
        )
        list.add(
            OutData(
                R.drawable.ic_business,
                "Kinh doanh",
                R.drawable.ic_official_employee,
                "Chính thức",
                "02",
                "Nguyễn Văn Long",
                "13/08/2004",
                "Hải Dương",
                "Đã làm ở các doanh nghiệp",
                "Đã tốt nghiệp đại học"
            )
        )
        list.add(
            OutData(
                R.drawable.ic_desgin,
                "Thiết kế",
                R.drawable.ic_intern,
                "Thực tập sinh",
                "03",
                "Trần Trung Hiếu",
                "13/08/2004",
                "Hải Dương",
                "Đã làm việc ở một số công ty",
                "Đã tốt nghiệp đại học"
            )
        )
        list.add(
            OutData(
                R.drawable.ic_dev,
                "Lập trình viên",
                R.drawable.ic_intern,
                "Thực tập sinh",
                "04",
                "Vũ Văn Chính",
                "13/08/2004",
                "Hải Dương",
                "Đã học qua các ngôn ngữ cơ bản",
                "Đang học năm 3 đại học"
            )
        )
        list.add(
            OutData(
                R.drawable.ic_dev,
                "Lập trình viên",
                R.drawable.ic_intern,
                "Thực tập sinh",
                "05",
                "Vũ Văn Chính",
                "13/08/2004",
                "Hải Dương",
                "Đã học qua các ngôn ngữ cơ bản",
                "Đang học năm 3 đại học"
            )
        )
        list.add(
            OutData(
                R.drawable.ic_dev,
                "Lập trình viên",
                R.drawable.ic_intern,
                "Thực tập sinh",
                "06",
                "Vũ Văn Chính",
                "13/08/2004",
                "Hải Dương",
                "Đã học qua các ngôn ngữ cơ bản",
                "Đang học năm 3 đại học"
            )
        )
        list.add(
            OutData(
                R.drawable.ic_dev,
                "Lập trình viên",
                R.drawable.ic_intern,
                "Thực tập sinh",
                "07",
                "Vũ Văn Chính",
                "13/08/2004",
                "Hải Dương",
                "Đã học qua các ngôn ngữ cơ bản",
                "Đang học năm 3 đại học"
            )
        )

        list.add(
            OutData(
                R.drawable.ic_dev,
                "Lập trình viên",
                R.drawable.ic_intern,
                "Thực tập sinh",
                "07",
                "Hoàng Văn Hải",
                "13/08/2004",
                "Hải Dương",
                "Đã học qua các ngôn ngữ cơ bản",
                "Đang học năm 3 đại học"
            )
        )
        customAdapter = CustomAdapter(this,list, selectedItems, binding.tvSelected)
        binding.lvMain.adapter = customAdapter

        originalList.addAll(list) // Lưu lại danh sách gốc

        registerForContextMenu(binding.lvMain)

        // Khởi tạo CustomAdapter và gắn vào ListView
        customAdapter = CustomAdapter(this, list, selectedItems, binding.tvSelected)
        binding.lvMain.adapter = customAdapter

        // Xử lý sự kiện tìm kiếm
        binding.btTimKiem.setOnClickListener {
            val query = binding.svTimKiem.query.toString().trim() // Lấy từ khóa tìm kiếm từ SearchView
            if (query.isNotEmpty()) {
                // Tìm kiếm trong danh sách nhân viên
                val filteredList = originalList.filter {
                    it.ten.contains(query, ignoreCase = true) // Tìm kiếm theo tên, không phân biệt chữ hoa chữ thường
                }

                if (filteredList.isNotEmpty()) {
                    list.clear()
                    list.addAll(filteredList)
                    customAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this, "Không tìm thấy nhân viên nào", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Nếu từ khóa tìm kiếm trống, hiển thị lại danh sách đầy đủ
                list.clear()
                list.addAll(originalList)
                customAdapter.notifyDataSetChanged()
            }
        }


        // Set onItemClickListener cho ListView
        binding.lvMain.setOnItemClickListener { _, _, position, _ ->
            val clickedItem = list[position] // Lấy đối tượng được chọn

            // Tạo Intent để chuyển sang InformationActivity
            val intent = Intent(this, InformationActivity::class.java).apply {
                putExtra("ten", clickedItem.ten)
                putExtra("ngay_sinh", clickedItem.ngaySinh)
                putExtra("id", clickedItem.id)
                putExtra("que_quan", clickedItem.queQuan)
                putExtra("phong_ban", clickedItem.tvPhongBan)
                putExtra("trang_thai", clickedItem.tvTrangThai)
                putExtra("trinh_do_hoc_van", clickedItem.trinhDoHocVan)
                putExtra("kinh_nghiem", clickedItem.kinhNghiem)
            }
            startActivity(intent)
        }

        // Xử lí thêm nhân viên
        binding.btThemMoi.setOnClickListener {
            val intent = Intent(this, AddEmloyeeActivity::class.java)
            addEmployeeLauncher.launch(intent)
        }

        // thao tác khi mà ấn x
        binding.ivClose.setOnClickListener {
            // khi mà ấn x thì quay về trạng thái bình thưởng ẩn đi thanh toolbar và hiện lên button
            binding.llToolbar.visibility = View.GONE
            binding.llButon.visibility = View.VISIBLE
        }
    }


    // Xử lý các lựa chọn trong Context Menu
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val selectedItem = list[info.position]

        return when (item.itemId) {
            R.id.menu_edit -> {
                // Chuyển đến EditActivity để chỉnh sửa thông tin nhân viên
                val intent = Intent(this, EditActivity::class.java).apply {
                    putExtra("ten", selectedItem.ten)
                    putExtra("ngay_sinh", selectedItem.ngaySinh)
                    putExtra("id", selectedItem.id)
                    putExtra("que_quan", selectedItem.queQuan)
                    putExtra("phong_ban", selectedItem.tvPhongBan)
                    putExtra("trang_thai", selectedItem.tvTrangThai)
                    putExtra("trinh_do_hoc_van", selectedItem.trinhDoHocVan)
                    putExtra("kinh_nghiem", selectedItem.kinhNghiem)
                }
                editEmployeeLauncher.launch(intent)  // Mở EditActivity
                true
            }
            R.id.menu_delete -> {
                // Hiển thị thanh menu phía trên
                binding.llToolbar.visibility = View.VISIBLE
                // ẩn nút thêm phía dưới đi
                binding.llButon.visibility = View.GONE
                // Hiển thị checkbox trong customAdapter
                customAdapter.notifyDataSetChanged()
                true
            }
             else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Chỉ tạo menu cho ListView
        if (v == binding.lvMain) {
            val inflater = menuInflater
            inflater.inflate(R.menu.context_menu, menu) // Inflate menu từ resource
        }
    }


}