package com.example.luyolung.android_practice.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.luyolung.android_practice.R;
import com.example.luyolung.android_practice.view.ShareIntentListAdapter;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import java.util.List;

/**
 * Created by luyolung on 30/09/2017.
 */

public class ShareActivity extends AppCompatActivity {

    private static final int AR_SHARE = 1;

    private RecyclerView mView;


    CompositeDisposable mDisposable;
    ShareIntentListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        mView = findViewById(R.id.content_view);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mDisposable = new CompositeDisposable();

        mView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShareIntentListAdapter(this, getActivityList());
        mView.setAdapter(mAdapter);

        mDisposable.add(
            mAdapter.onItemClickSignal()
            .subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    System.out.println(s);
                }
            })
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        switch (requestCode) {
            case AR_SHARE:
                System.out.println(resultCode);
                System.out.println(data.toString());
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    private List<ResolveInfo> getActivityList() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);

        // what type of data needs to be send by sharing
        sharingIntent.setType("image/jpg");

        // package names
        PackageManager pm = getPackageManager();

        // list package
        return pm.queryIntentActivities(sharingIntent, 0);
    }
}
