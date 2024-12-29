package com.example.tuan6_recyclerview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tuan6_recyclerview.databinding.ActivityMainBinding

const val EXTRA_TEN = "extra_ten"
const val EXTRA_NGAY_SINH = "extra_ngay_sinh"
const val EXTRA_ID = "extra_id"
const val EXTRA_QUE_QUAN = "extra_que_quan"
const val EXTRA_PHONG_BAN = "extra_phong_ban"
const val EXTRA_TRANG_THAI = "extra_trang_thai"
const val EXTRA_TRINH_DO_HOC_VAN = "extra_trinh_do_hoc_van"
const val EXTRA_KINH_NGHIEM = "extra_kinh_nghiem"


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RvAdapter
    private var list = mutableListOf<OutData>()
    private val selectedItems = mutableListOf<OutData>() // Danh sách các mục đã chọn
    private var showCheckbox = false // Biến để kiểm tra khi nào hiển thị checkbox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Thêm các dữ liệu để có thể test
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
                "Nhân viên chính thức",
                "02",
                "Nguyễn Văn Long",
                "13/08/2002",
                "Hải Hưng",
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
                "13/08/2002",
                "Hà Nội",
                "Đã làm việc ở một số công ty",
                "Đã tốt nghiệp đại học"
            )
        )
        list.add(
            OutData(
                R.drawable.ic_marketing,
                "Marketing",
                R.drawable.ic_intern,
                "Thực tập sinh",
                "04",
                "Nguyễn Thị Yến",
                "13/08/2003",
                "Thái Bình",
                "Đã làm 1 số công ty",
                "Đã tốt nghiệp"
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
                "13/08/2002",
                "Hà Nội",
                "Đã làm việc ở một số công ty",
                "Đã tốt nghiệp đại học"
            )
        )


        //Chỉ lên khởi tạo Adapter 1 lần
        rvAdapter = RvAdapter(
            list,
            selectedItems,
            binding.tvSelected,
            showCheckbox,
            // Xử lí sự kiện khi mà click vào 1 item trên AdapterView
            object : RvInterface {
                // xử lí sự kiện edit
                override fun OnClickItem(position: Int) {
                    // Lấy đối tượng được chọn
                    val clickedItem = list[position]
                    // Tạo Intent để chuyển sang InformationActivity
                    val intent = Intent(this@MainActivity, InformationActivity::class.java).apply {
                        putExtra(EXTRA_TEN, clickedItem.ten)
                        putExtra(EXTRA_NGAY_SINH, clickedItem.ngaySinh)
                        putExtra(EXTRA_ID, clickedItem.id)
                        putExtra(EXTRA_QUE_QUAN, clickedItem.queQuan)
                        putExtra(EXTRA_PHONG_BAN, clickedItem.tvPhongBan)
                        putExtra(EXTRA_TRANG_THAI, clickedItem.tvTrangThai)
                        putExtra(EXTRA_TRINH_DO_HOC_VAN, clickedItem.trinhDoHocVan)
                        putExtra(EXTRA_KINH_NGHIEM, clickedItem.kinhNghiem)
                    }
                    startActivity(intent)
                }

                override fun OnClickItemEdit(position: Int) {
                    // Lấy đối tượng được chọn
                    val clickedItem = list[position]
                    // Tạo Intent để chuyển sang InformationActivity
                    val intent = Intent(this@MainActivity, EditActivity::class.java).apply {
                        putExtra(EXTRA_TEN, clickedItem.ten)
                        putExtra(EXTRA_NGAY_SINH, clickedItem.ngaySinh)
                        putExtra(EXTRA_ID, clickedItem.id)
                        putExtra(EXTRA_QUE_QUAN, clickedItem.queQuan)
                        putExtra(EXTRA_PHONG_BAN, clickedItem.tvPhongBan)
                        putExtra(EXTRA_TRANG_THAI, clickedItem.tvTrangThai)
                        putExtra(EXTRA_TRINH_DO_HOC_VAN, clickedItem.trinhDoHocVan)
                        putExtra(EXTRA_KINH_NGHIEM, clickedItem.kinhNghiem)
                    }
                    editEmployeeLauncher.launch(intent)  // Mở EditActivity
                }

                override fun OnClickDeleteItem(positon: Int, holder: RvAdapter.ViewHolder) {
                    // lúc này hiển thị ra thanh toobar dòng thời ẩn đi nút phía dưới cùng a
                    binding.llToolbar.visibility = View.VISIBLE
                    binding.llButon.visibility = View.GONE
                    showCheckbox = false
                    val a = binding.rvMain.adapter as RvAdapter? // ép kiểu sang kiểu RvAdapter
                    a?.let {
                        it.showCheckbox = true
                        it.notifyDataSetChanged()
                    }

                }
            })

        binding.rvMain.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMain.adapter = rvAdapter



        // Xử lí họat động tìm kiếm
        binding.svTimKiem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                val originalList: MutableList<OutData>
                originalList = list.toMutableList()
                // Thực hiện tìm kiếm khi người dùng nhập vào ô tìm kiếm
                val query = newText.toString().trim()
                if (query.isNotEmpty()) {
                    // Tìm kiếm trong danh sách nhân viên
                    val filteredList = originalList.filter {
                        it.ten.contains(
                            query,
                            ignoreCase = true
                        ) // Tìm kiếm theo tên, không phân biệt chữ hoa chữ thường
                    }

                    if (filteredList.isNotEmpty()) {
                        originalList.clear()
                        originalList.addAll(filteredList)
                        val a = binding.rvMain.adapter as RvAdapter? // ép kiểu sang kiểu RvAdapter
                        a?.let {
                            it.list = originalList
                            it.notifyDataSetChanged()
                        }
                    }
                } else {
                    // Nếu từ khóa tìm kiếm trống, hiển thị lại danh sách đầy đủ
                    originalList.clear()
                    originalList.addAll(list)
                    val a = binding.rvMain.adapter as RvAdapter? // ép kiểu sang kiểu RvAdapter
                    a?.let {
                        it.list = originalList
                        it.notifyDataSetChanged()
                    }
                }
                return true
            }
        })


        // Xử lí thêm nhân viên
        binding.btThemMoi.setOnClickListener {
            val intent = Intent(this, AddEmployeeActivity::class.java)
            addEmployeeLauncher.launch(intent)
        }


        // Khi nhấn "X" trong toolbar trở về trạng thái thường
        binding.ivClose.setOnClickListener {
            // Ẩn toolbar và checkbox, hiển thị nút thêm
            binding.llToolbar.visibility = View.GONE
            binding.llButon.visibility = View.VISIBLE
            selectedItems.clear()
            val a = binding.rvMain.adapter as RvAdapter?
            a?.let {
                it.showCheckbox = false
                it.notifyDataSetChanged()
            }
        }

        // Khi nhấn icon bỏ chọn tất cả các checked
        binding.ivClearChecked.setOnClickListener {
            selectedItems.clear()
            binding.tvSelected.text = "Đã chọn: 0"
            rvAdapter.notifyDataSetChanged()
        }

        // Khi nhấn icon delete để xóa các item đã chọn
        binding.ivDelete.setOnClickListener {
            list.removeAll(selectedItems)
            selectedItems.clear()
            // Quay lại trạng thái ban đầu
            binding.llToolbar.visibility = View.GONE
            binding.llButon.visibility = View.VISIBLE
            val a = binding.rvMain.adapter as RvAdapter?
            a?.let {
                it.showCheckbox = false
                it.notifyDataSetChanged()
            }
        }
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        // Chỉ tạo context menu cho ListView
        if (v == binding.rvMain) {
            // Inflating context menu
            menuInflater.inflate(R.menu.context_menu, menu)
        }
    }


    // Khai báo ActivityResultLauncher để nhận kết quả từ EditActivity
    private val editEmployeeLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val ten = data?.getStringExtra(EXTRA_TEN) ?: ""
                val ngaySinh = data?.getStringExtra(EXTRA_NGAY_SINH) ?: ""
                val id = data?.getStringExtra(EXTRA_ID) ?: ""
                val queQuan = data?.getStringExtra(EXTRA_QUE_QUAN) ?: ""
                val phongBan = data?.getStringExtra(EXTRA_PHONG_BAN) ?: ""
                val trangThai = data?.getStringExtra(EXTRA_TRANG_THAI) ?: ""
                val trinhDoHocVan = data?.getStringExtra(EXTRA_TRINH_DO_HOC_VAN) ?: ""
                val kinhNghiem = data?.getStringExtra(EXTRA_KINH_NGHIEM) ?: ""

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
                        }
                    }
                    rvAdapter.notifyDataSetChanged()
                }
            }
        }


    // Khai báo ActivityResultLauncher để nhận kết quả từ AddEmloyeeActivity
     val addEmployeeLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val ten = data?.getStringExtra(EXTRA_TEN) ?: ""
                val ngaySinh = data?.getStringExtra(EXTRA_NGAY_SINH) ?: ""
                val id = data?.getStringExtra(EXTRA_ID) ?: ""
                val queQuan = data?.getStringExtra(EXTRA_QUE_QUAN) ?: ""
                val phongBan = data?.getStringExtra(EXTRA_PHONG_BAN) ?: ""
                val trangThai = data?.getStringExtra(EXTRA_TRANG_THAI) ?: ""
                val trinhDoHocVan = data?.getStringExtra(EXTRA_TRINH_DO_HOC_VAN) ?: ""
                val kinhNghiem = data?.getStringExtra(EXTRA_KINH_NGHIEM) ?: ""

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
                        rvAdapter.notifyDataSetChanged()
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
                    }
                }
                // Khi mà thêm 1 phần tử vào thì chỉ cần chèn phần tử vào cuối
                rvAdapter.notifyItemInserted(list.size - 1)
            }
        }
}
