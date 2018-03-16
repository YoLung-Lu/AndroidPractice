package com.example.luyolung.android_practice;

/**
 * Created by luyolung on 14/08/2017.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.luyolung.android_practice.activity.ConstraintLayoutActivity;
import com.example.luyolung.android_practice.activity.KTConstraintLayoutActivity;
import com.my.comp.TakePhotoDelegateActivity;
import com.my.core.protocol.IDrawerViewLayout;
import com.my.core.protocol.IProgressBarView;
import com.my.core.util.ViewUtil;
import com.my.widget.adapter.SampleMenuAdapter;
import com.my.widget.adapter.SampleMenuAdapter.SampleMenuItem;

public class StartActivity
    extends AppCompatActivity
    implements IProgressBarView {

    Toolbar mToolbar;
    ListView mStartMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }

        // List menu.
        mStartMenu = (ListView) findViewById(R.id.menu);
        mStartMenu.setAdapter(onCreateSampleMenu());
        mStartMenu.setOnItemClickListener(onClickSampleMenuItem());

//        // The collage editor.
//        mCollageEditor = (CollageLayout) findViewById(R.id.collage_editor);

//        // Example: Chain multiple observables.
//        doHttpRequests();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_start, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggleDrawerMenu();
                return true;
            case R.id.item_take_photo:
                startActivity(new Intent(this, TakePhotoDelegateActivity.class));
                return true;
            case R.id.item_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgressBar() {
        ViewUtil
            .with(this)
            .setProgressBarCancelable(false)
            .showProgressBar(getString(R.string.loading));
    }

    @Override
    public void showProgressBar(String msg) {
        showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        ViewUtil
            .with(this)
            .hideProgressBar();
    }

    @Override
    public void updateProgress(int progress) {
        showProgressBar();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private void toggleDrawerMenu() {
        // DO NOTHING
    }

    private IDrawerViewLayout.OnDrawerStateChange onMenuStateChange() {
        return new IDrawerViewLayout.OnDrawerStateChange() {
            @Override
            public void onOpenDrawer() {
                mToolbar.setNavigationIcon(R.drawable.icon_toolbar_close);
            }

            @Override
            public void onCloseDrawer() {
                mToolbar.setNavigationIcon(R.drawable.icon_list_black_24px);
            }
        };
    }

    @SuppressWarnings({"unchecked"})
    private SampleMenuAdapter onCreateSampleMenu() {
        return new SampleMenuAdapter(
            this,
            new SampleMenuItem[]{
//                new SampleMenuItem(
//                    "CollageEditor",
//                    "A view-based collage editor (on-going).",
//                    new OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(StartActivity.this,
//                                CollageEditorActivity.class)
//                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                        }
//                    }),
//                // TODO: Move to the custom view exp.
//                new SampleMenuItem(
//                    "Notification",
//                    "Fire notifications and lead the user to the Activity " +
//                        "in the current task or in a new task.",
//                    new OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(StartActivity.this,
//                                NotificationSampleActivity.class)
//                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                        }
//                    }),
//                new SampleMenuItem(
//                    "Services",
//                    "Use the service to do a long operation in the background " +
//                        "and notify the binding Activity the processing status.\n" +
//                        "The service might be still alive when the task is moved " +
//                        "to the background.",
//                    new OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(StartActivity.this,
//                                ServiceSampleActivity.class)
//                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                        }
//                    }),
                new SampleMenuItem(
                    "Constraint Layout",
                    "Practice on every kind of constraint relationship.\n",
                    new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(StartActivity.this,
                                KTConstraintLayoutActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        }
                    }),
                new SampleMenuItem(
                    "BroadcastReceiver",
                    "(constructing)",
                    new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }),
                new SampleMenuItem(
                    "RxJava-2",
                    "(constructing).",
                    new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }),
                new SampleMenuItem(
                    "OkHttp",
                    "(constructing).",
                    new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }),
            });
    }

    private AdapterView.OnItemClickListener onClickSampleMenuItem() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                View view,
                int position,
                long id) {
                final SampleMenuItem item = (SampleMenuItem) parent.getAdapter()
                    .getItem(position);
                item.onClickListener.onClick(view);
            }
        };
    }

}
