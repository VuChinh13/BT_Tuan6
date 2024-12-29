package com.example.tuan6_recyclerview
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tuan6_recyclerview.databinding.RvItemBinding

// cần truyền vào binding
class RvAdapter(
    var list: MutableList<OutData>,
    var selectedItems: MutableList<OutData>,
    var tvSelected: TextView,
    var showCheckbox: Boolean,
    var rvInterface: RvInterface
):RecyclerView.Adapter<RvAdapter.ViewHolder>(){


    // đoạn code này trả về viewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false)
        val binding = RvItemBinding.bind(view) // sử dụng ViewBinding
        return ViewHolder(binding) // truyền ViewBinding vào ViewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.apply{
            // gán dữ liệu cho các giá trị
            val item = list[position]
            imageTrangThai.setImageResource(item.ivTrangThai)
            imagePhongBan.setImageResource(item.ivPhongBan)
            ten.text = item.ten
            id.text = item.id

            //Kiểm tra checkBox
            checkBox.visibility = if (showCheckbox) View.VISIBLE else View.GONE

            //Kiểm tra xem item này đã được chọn hay chưa
            checkBox.isChecked = selectedItems.contains(item)

            //Xử lí checkBox được chọn hoặc là bỏ
            checkBox.setOnCheckedChangeListener{_, isChecked ->
                if (isChecked) {
                    if (!selectedItems.contains(item)) {
                        selectedItems.add(item)
                    }
                } else {
                    selectedItems.remove(item)
                }

                // Cập nhật số lượng mục đã chọn và hiển thị lên TextView
                val selectedCount = selectedItems.size
                tvSelected.text = "Đã chọn: $selectedCount" // Cập nhật số lượng
            }

            holder.itemView.setOnClickListener {
                // hiển thị thông tin chi tiết của nhân viên
                rvInterface.OnClickItem(position)
                true
            }

            // Lắng nghe sự kiện long click để hiển thị menu
            holder.itemView.setOnLongClickListener { view ->
                val popupMenu = PopupMenu(view.context,view)
                val inflater: MenuInflater = popupMenu.menuInflater
                inflater.inflate(R.menu.context_menu, popupMenu.menu)  // menu_item là ID của menu XML bạn đã định nghĩa

                // Bắt sự kiện khi chọn item từ menu
                popupMenu.setOnMenuItemClickListener { itemMenu ->
                    when (itemMenu.itemId) {
                        R.id.menu_edit -> {
                            //Lắng nghe sự kiện khi mà chọn chỉnh sửa
                            rvInterface.OnClickItemEdit(position)
                            true
                        }
                        R.id.menu_delete -> {
                            rvInterface.OnClickDeleteItem(position, holder)
                            true
                        }
                        else -> false
                    }
                }

                // Hiển thị PopupMenu
                popupMenu.show()
                true  // Trả về true để ngừng xử lý sự kiện long click mặc định
            }
        }
    }


    inner class ViewHolder(val binding: RvItemBinding): RecyclerView.ViewHolder(binding.root) {
        val imagePhongBan: ImageView
        val imageTrangThai: ImageView
        val ten: TextView
        val id: TextView
        val checkBox: CheckBox
        init {
            imagePhongBan = binding.ivPhongBan
            imageTrangThai = binding.ivTrangThai
            ten = binding.tvTen
            id = binding.tvId
            checkBox = binding.cbItem
        }
    }


}