package com.fuzhucheng.rxjavartrofitrecyclerview.fragment.base;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.fuzhucheng.rxjavartrofitrecyclerview.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 符柱成 on 2016/12/2.
 */
public abstract class BannerFragment extends RecyclerViewFragment implements OnItemClickListener {

    private static final long LOOP_TIME = 5000;

    @BindView(R.id.banner)
    ConvenientBanner<String> convenientBanner;

    protected abstract List<String> getBitmapList();
    protected void loadConvenientBanner() {
        convenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, getBitmapList())
                .setPageIndicator(new int[]{R.drawable.page_indicator, R.drawable.page_indicator_focused})
                .setOnItemClickListener(this);
        convenientBanner.startTurning(LOOP_TIME);
    }

    @Override
    protected void initView() {
        super.initView();
        convenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, getBitmapList())
                .setPageIndicator(new int[]{R.drawable.page_indicator, R.drawable.page_indicator_focused})
                .setOnItemClickListener(this);
        convenientBanner.startTurning(LOOP_TIME);
    }

    @Override
    protected void loadData(){
        super.loadData();
    }
    public class LocalImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, String data) {
            Glide.with(context).load(data).into(imageView);
        }
    }
}
