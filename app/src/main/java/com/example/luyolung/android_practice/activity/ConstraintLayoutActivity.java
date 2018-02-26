package com.example.luyolung.android_practice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.example.luyolung.android_practice.R;

/**
 * Created by luyolung on 30/09/2017.
 */

public class ConstraintLayoutActivity
    extends AppCompatActivity {

    Toolbar mToolbarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
        LinearLayout parent = (LinearLayout) findViewById(R.id.lineargroup);

        mToolbarView = (Toolbar) findViewById(R.id.toolbar);
        mToolbarView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });


        /**
         * Margin testing for each component left-right connected.
         * When no margin setting, all components share same margin value
         *      1   2   3   4
         *  +---+---+---+---+---+
         *
         *  If set margin in some components, they will take the required margin
         *  then all widgets share the rest of size.
         *
         *    10+d    1+d   d   d
         *  +--------+----+---+---+
         */
        View child1 = getLayoutInflater().inflate(R.layout.item_constraint1, parent);

        /**
         * Margin testing for each component chained in
         *
         *
         */
        View child5 = getLayoutInflater().inflate(R.layout.item_constraint5, parent);

        View child2 = getLayoutInflater().inflate(R.layout.item_constraint2, parent);
        View child3 = getLayoutInflater().inflate(R.layout.item_constraint3, parent);
        View child4 = getLayoutInflater().inflate(R.layout.item_constraint4, parent);

        parent.invalidate();


//        ConstraintLayout child1 = (ConstraintLayout) findViewById(R.id.constraint1);
//        parent.addView(child1);

    }
}
