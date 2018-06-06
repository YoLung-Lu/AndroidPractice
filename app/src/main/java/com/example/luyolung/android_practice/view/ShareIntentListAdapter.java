package com.example.luyolung.android_practice.view;

/**
 * Created by luyolung on 05/06/2018.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.luyolung.android_practice.R;
import com.example.luyolung.android_practice.view.ShareIntentListAdapter.ShareItemViewHolder;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.lang.ref.WeakReference;
import java.util.List;

public class ShareIntentListAdapter extends RecyclerView.Adapter<ShareItemViewHolder> {

    private final WeakReference<Context> mContext;
    private List<ResolveInfo> mItems;
    private Subject<String> mOnItemClickSub = PublishSubject.create();

    public ShareIntentListAdapter(Activity context) {
        this.mContext = new WeakReference<Context>(context);
        this.mItems = getItem(context);
    }

    public ShareIntentListAdapter(Activity context, List<ResolveInfo> items) {
        this.mContext = new WeakReference<Context>(context);
        this.mItems = items;
    }

    /**
     * On item clicked, the name of item will be passed as stream.
     * @return
     */
    public Subject<String> onItemClickSignal() {
        return mOnItemClickSub;
    }

    public void setmItems(final List<ResolveInfo> newItems) {
        mItems = newItems;
        this.notifyDataSetChanged();
    }

    @Override
    public ShareItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View item = LayoutInflater.from(mContext.get()).inflate(
            R.layout.item_share, parent, false);
        final ShareItemViewHolder holder = new ShareItemViewHolder(item);

        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickSub.onNext(holder.titleTextView.getText().toString());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ShareItemViewHolder holder, int position) {
        holder.coverImageView.setImageDrawable(
            mItems.get(position).activityInfo.loadIcon(mContext.get().getPackageManager()));
        holder.titleTextView.setText(
            mItems.get(position).activityInfo.loadLabel(mContext.get().getPackageManager()).toString());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * An example of getting sharing intent items.
     * @return
     */
    private static List<ResolveInfo> getItem(Context context) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);

        // what type of data needs to be send by sharing
        sharingIntent.setType("image/jpg");
        // package names
        PackageManager pm = context.getPackageManager();

        // list package
        return pm.queryIntentActivities(sharingIntent, 0);
    }

    ///////////////////////////////////////////////////
    // Class //////////////////////////////////////////

    class ShareItemViewHolder extends RecyclerView.ViewHolder {
        ImageView coverImageView;
        TextView titleTextView;

        ShareItemViewHolder(View itemView) {
            super(itemView);
            coverImageView = itemView.findViewById(R.id.share_image);
            titleTextView = itemView.findViewById(R.id.share_name);
        }
    }
}