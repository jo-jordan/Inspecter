package com.specter.inspecter.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.specter.inspecter.R;
import com.specter.inspecter.base.App;
import com.specter.inspecter.image.GlideApp;
import com.specter.inspecter.ui.entity.IndexImageMultipleItemEntity;
import com.specter.inspecter.util.ColorUtil;

import java.util.List;

import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;

public class IndexImageListAdapter extends BaseMultiItemQuickAdapter<IndexImageMultipleItemEntity, BaseViewHolder> {

    public IndexImageListAdapter(List<IndexImageMultipleItemEntity> data) {
        super(data);
        addItemType(IndexImageMultipleItemEntity.INDEX_TYPE_IMAGE, R.layout.item_index_image);
    }

    private int mCardWidth = 0;

    @Override
    protected void convert(BaseViewHolder helper, IndexImageMultipleItemEntity item) {
        if (mCardWidth == 0) {
            View view = helper.itemView;
            view.measure(0, 0);
            mCardWidth = view.getWidth();
        }

        loadImage(helper, item);

        helper.setBackgroundColor(R.id.iv_pic, ColorUtil.getRandomColor())
                .addOnClickListener(R.id.fl_image_container);
        Log.d(TAG, "now position: " + helper.getAdapterPosition());
    }

    private void loadImage(BaseViewHolder helper, IndexImageMultipleItemEntity item) {
        ProgressBar progressBar = helper.itemView.findViewById(R.id.pb_image_load_indicator);
        ProgressManager.getInstance().addResponseListener(item.getPictureUrl(), getGlideListener(progressBar));
        ImageView imageView = helper.itemView.findViewById(R.id.iv_pic);
        imageView.layout(0, 0, 0, 0);

        GlideApp
                .with(App.getApplication())
                .load(item.getPictureUrl())
                .skipMemoryCache(true)
                .centerCrop()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .onlyRetrieveFromCache(false)
                .into(imageView);
    }

    private ProgressListener getGlideListener(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        return new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                int progress = progressInfo.getPercent();
                progressBar.setProgress(progress);
                Log.d(TAG, "--Glide-- " + progress + " %");
            }

            @Override
            public void onError(long id, Exception e) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(0);
                    }
                });
            }
        };
    }
}
