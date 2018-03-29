package com.example.luyolung.android_practice.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.example.luyolung.android_practice.R
import com.example.luyolung.android_practice.controller.TabBarController
import com.example.luyolung.android_practice.view.EmptyTopView
import com.example.luyolung.android_practice.view.MyTabItem
import com.example.luyolung.android_practice.view.Tab1BottomView
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by luyolung on 26/02/2018.
 */


class RecyclerViewActivity : AppCompatActivity() {

    val NO_TAB = "NO_TAB"

    // Activity request code
    val AR_TEST = 1

    // Save instant
    val SAVE_KEY_TAB_NAME = "save_key_tab_name"

    // State for picker.
    var currentTabName: String = NO_TAB

    private lateinit var mTopContainer: FrameLayout
    private lateinit var mBottomContainer: FrameLayout
    private lateinit var mTabBar: LinearLayout
    private val mTabBarController: TabBarController = TabBarController()

    private val mTabDisposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)


        mTopContainer = findViewById(R.id.top_view)
        mBottomContainer = findViewById(R.id.picker_container)
        mTabBar = findViewById(R.id.picker_selector)

        initPage()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Save the state for Picker.
        outState.putString(SAVE_KEY_TAB_NAME, currentTabName)
    }

    // Restore the state of sub-component (picker)
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null) {
            restoreStates(savedInstanceState)
        }
    }

    fun restoreStates(savedInstanceState: Bundle) {
        // Restore state for Picker.
        currentTabName = savedInstanceState.getString(SAVE_KEY_TAB_NAME)
        if (currentTabName != NO_TAB) {
            initPicker()
            mTabBarController.openTabBar(currentTabName)
            mTabBarController.invalidate()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            AR_TEST -> print("back from test ~~~")
            else    -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun initPage() {
        currentTabName = NO_TAB
        mTabDisposables.clear()
        print("")

        val v = TextView(mTopContainer.context)
        v.gravity = Gravity.CENTER
        v.setText("Open Picker!")
        v.setBackgroundColor(Color.GREEN)
        v.setOnClickListener({
            mTopContainer.removeAllViews()
            initPicker()
            mTabBarController.openTabBar()
        })
        mTopContainer.addView(v)
    }

    private fun initPicker() {

        // TODO: move to somewhere else?
        mTopContainer.removeAllViews()
        mBottomContainer.removeAllViews()
        mTabBar.removeAllViews()

        // Disposable.
        mTabDisposables.addAll(
                mTabBarController
                        .onOpen()
                        .subscribe { name ->
                            print("")
                            currentTabName = name
                        },

                mTabBarController
                        .onSwitchTab()
                        .subscribe { name ->
                            print("")
                            currentTabName = name
                        },

                mTabBarController
                        .onClose()
                        .subscribe { ignored ->
                            initPage()
                        }
        )

        // Init containers.
        mTabBarController.setViews(mTopContainer, mBottomContainer, mTabBar)

        // Add tab1.
        val name = "Close"
        val emptyView = EmptyTopView(this)
        emptyView.setText(name)
        emptyView.setBackgroundColor(Color.YELLOW)
        emptyView.setListener(View.OnClickListener {
            mTabBarController.closeTabBar()
        })

        // Add tab2.
        val name2 = "Navigate"
        val emptyView2 = EmptyTopView(this)
        emptyView2.setText(name2)
        emptyView2.setBackgroundColor(Color.BLUE)
        emptyView2.setListener(View.OnClickListener {
            startActivityForResult(
                    Intent(this@RecyclerViewActivity,
                            KTConstraintLayoutActivity::class.java)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP),
                    AR_TEST)
        })

        mTabBarController.addItem(MyTabItem(name, emptyView, Tab1BottomView(this)))
        mTabBarController.addItem(MyTabItem(name2, emptyView2, Tab1BottomView(this)))

        for (a in 1..2) {
            mTabBarController.addItem(generateTab(a))
        }
    }

    private fun generateTab(number: Int) : MyTabItem {
        val name = "Tab" + number
        val emptyView = EmptyTopView(this)
        val emptyView2 = EmptyTopView(this)
        emptyView.setText(name + " ~")
        emptyView2.setText(name)
        return MyTabItem(name, emptyView, emptyView2)
    }
}