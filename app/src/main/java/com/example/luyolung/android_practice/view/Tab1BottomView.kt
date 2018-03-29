package com.example.luyolung.android_practice.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.example.luyolung.android_practice.R

/**
 * Created by luyolung on 28/03/2018.
 */
class Tab1BottomView : ConstraintLayout {

    // View.
    private val mBtnApply by lazy { findViewById<ImageView>(R.id.btn_apply) }
    private val mBtnOpenOtherPicker by lazy { findViewById<View>(R.id.btn_other_picker_2) }

    // Backgrounds view & controller.
    private val mBackgroundsView by lazy { findViewById<RecyclerView>(R.id.background_list) }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        // The main layout.
        inflate(context, R.layout.view_background_picker, this)

    }
}