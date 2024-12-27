package com.example.tuan6_listview

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.CheckBox

class CustomAdapter(
    val activity: Activity,
    val list: List<OutData>,
    private val selectedItems: MutableList<OutData>,
    private val tvSelected: TextView,
    private val showCheckbox: Boolean // Thêm một tham số để kiểm tra khi nào cần hiển thị checkbox
) : ArrayAdapter<OutData>(activity, R.layout.lv_item, list) {

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contexts = activity.layoutInflater
        val rowView = contexts.inflate(R.layout.lv_item, parent, false)

        val imagePhongBan = rowView.findViewById<ImageView>(R.id.iv_phong_ban)
        val imageTrangThai = rowView.findViewById<ImageView>(R.id.iv_trang_thai)
        val ten = rowView.findViewById<TextView>(R.id.tv_ten)
        val id = rowView.findViewById<TextView>(R.id.tv_id)
        val checkBox = rowView.findViewById<CheckBox>(R.id.cb_item)

        val item = list[position]
        imagePhongBan.setImageResource(item.ivPhongBan)
        imageTrangThai.setImageResource(item.ivTrangThai)
        ten.text = item.ten
        id.text = item.id

        // Hiển thị checkbox hoặc ẩn nó tuỳ thuộc vào trạng thái showCheckbox
        checkBox.visibility = if (showCheckbox) View.VISIBLE else View.GONE

        // Kiểm tra nếu item này đã được chọn
        checkBox.isChecked = selectedItems.contains(item)

        // Xử lý khi checkbox được chọn hoặc bỏ chọn
        checkBox.setOnCheckedChangeListener { _, isChecked ->
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

        return rowView
    }
}

