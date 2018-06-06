package com.example.luyolung.android_practice.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import com.example.luyolung.android_practice.R;

/**
 * Created by luyolung on 30/09/2017.
 */

public class CardViewActivity
    extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);
        LinearLayout parent = (LinearLayout) findViewById(R.id.lineargroup);

    }

}
