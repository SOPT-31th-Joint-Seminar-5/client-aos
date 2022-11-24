package com.sopt31th.runday.presentation.yongmin.home.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ExerciseItemDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (position % 2 == 0) {
            outRect.left = 16
            outRect.right = 3
        } else {
            outRect.left = 3
            outRect.right = 16
        }

        if (position < 2) {
            outRect.top = 20
            outRect.bottom = 6
        } else {
            outRect.top = 6
            outRect.bottom = 6
        }
    }
}
