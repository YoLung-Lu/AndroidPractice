package com.example.luyolung.android_practice.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.LinearLayout
import com.example.luyolung.android_practice.R

/**
 * Created by luyolung on 26/02/2018.
 */


class KTConstraintLayoutActivity : AppCompatActivity() {

    private lateinit var mToolbarView: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)
        val parent = findViewById<LinearLayout>(R.id.lineargroup)

        mToolbarView = findViewById<Toolbar>(R.id.toolbar)
        mToolbarView.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }


        /**
         * Margin testing for each component left-right connected.
         * When no margin setting, all components share same margin value
         * 1   2   3   4
         * +---+---+---+---+---+
         *
         * If set margin in some components, they will take the required margin
         * then all widgets share the rest of size.
         *
         * 10+d    1+d   d   d
         * +--------+----+---+---+
         */
        val child1 = layoutInflater.inflate(R.layout.item_constraint1, parent)

        /**
         * Margin testing for each component chained in
         *
         *
         */
        val child5 = layoutInflater.inflate(R.layout.item_constraint5, parent)

        val child2 = layoutInflater.inflate(R.layout.item_constraint2, parent)
        val child3 = layoutInflater.inflate(R.layout.item_constraint3, parent)
        val child4 = layoutInflater.inflate(R.layout.item_constraint4, parent)

        parent.invalidate()


        //        ConstraintLayout child1 = (ConstraintLayout) findViewById(R.id.constraint1);
        //        parent.addView(child1);

    }
}
