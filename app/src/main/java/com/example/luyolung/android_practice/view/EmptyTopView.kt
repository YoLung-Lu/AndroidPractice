package com.example.luyolung.android_practice.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.luyolung.android_practice.R

/**
 * Created by luyolung on 28/03/2018.
 */
class EmptyTopView : ConstraintLayout {

    // View.
    private val mView by lazy { findViewById<TextView>(R.id.btn_empty_view) }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        // The main layout.
        inflate(context, R.layout.empty_view, this)
    }

    fun setText(text: String) {
        mView.text = text
    }

    fun setListener(listener: View.OnClickListener) {
        mView.setOnClickListener(listener)
    }
}