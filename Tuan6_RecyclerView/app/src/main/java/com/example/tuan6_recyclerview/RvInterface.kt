package com.example.tuan6_recyclerview

import android.app.Activity
import android.content.Context

interface RvInterface {
    // tạo 2 phương thức
    // 1 phương thức là dùng cho click và 1 phương thức là dùng cho onLongClick
    fun OnClickItem(position: Int)
    fun OnClickItemEdit(position: Int)
    fun OnClickDeleteItem(positon: Int, holder: RvAdapter.ViewHolder)
}