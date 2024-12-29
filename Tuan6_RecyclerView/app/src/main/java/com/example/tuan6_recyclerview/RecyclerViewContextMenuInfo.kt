package com.example.tuan6_recyclerview

import android.view.ContextMenu
import android.view.View

    class RecyclerViewContextMenuInfo(view: View, position: Int) : ContextMenu.ContextMenuInfo {
    val view = view
    val position = position
}

