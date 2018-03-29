package com.example.luyolung.android_practice.controller

import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.example.luyolung.android_practice.view.MyTabItem
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by luyolung on 28/03/2018.
 */
class TabBarController {
    val mItemList: MutableList<MyTabItem> = mutableListOf()
    lateinit var mTopContainer: FrameLayout
    lateinit var mBottomContainer: FrameLayout
    lateinit var mTabView: LinearLayout

    private val mSwitchTabSignal = PublishSubject.create<String>()
    private val mCloseSignal = PublishSubject.create<Any>()
    private val mOpenSignal = PublishSubject.create<String>()


    fun addItem(item: MyTabItem) {
        mItemList.add(item)
    }

    fun setViews(topContainer: FrameLayout, bottomContainer: FrameLayout, tabView: LinearLayout) {
        mTopContainer = topContainer
        mBottomContainer = bottomContainer
        mTabView = tabView
    }

    fun openTabBar() {
        openTabBar(mItemList[0].name)
    }

    fun openTabBar(name: String) {
        // Update tabs.
        for (item in mItemList) {
            val v = TextView(mTabView.context)
            v.setText(item.name + "~~~~~")
            v.setOnClickListener({
                switchTab(item.name)
            })
            mTabView.addView(v)
        }

        open(name)
        mOpenSignal.onNext(name)
    }

    fun switchTab(name: String) {
        // Remove views.
        mTopContainer.removeAllViews()
        mBottomContainer.removeAllViews()

        open(name)
        mSwitchTabSignal.onNext(name)
    }

    private fun open(name: String) {

        // Find item
        val item = mItemList.find { it -> it.name == name }

        // Replace views.
        mTopContainer.addView(item?.topView)
        mBottomContainer.addView(item?.bottomView)
    }

    fun closeTabBar() {
        mItemList.clear()
        mTopContainer.removeAllViews()
        mBottomContainer.removeAllViews()
        mTabView.removeAllViews()

        mCloseSignal.onNext(0)
    }

    fun onOpen(): Observable<String> {
        return mOpenSignal
    }

    fun onSwitchTab(): Observable<String> {
        return mSwitchTabSignal
    }

    fun onClose(): Observable<Any> {
        return mCloseSignal
    }

    fun invalidate() {
        mTopContainer.invalidate()
        mTopContainer.postInvalidate()
        mBottomContainer.invalidate()
        mTabView.invalidate()
    }
}