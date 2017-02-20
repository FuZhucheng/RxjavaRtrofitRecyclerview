package com.fuzhucheng.rxjavartrofitrecyclerview.fragment.base;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.fuzhucheng.rxjavartrofitrecyclerview.R;
import com.fuzhucheng.rxjavartrofitrecyclerview.widget.FullyLinearLayoutManager;

import butterknife.BindView;

/**
 * Created by ${符柱成} on 2017/1/12.
 */

public abstract class RecyclerViewFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected static final int REFRESH = 0x101;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    protected RecyclerView.Adapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH:
                    swipeRefreshLayout.setRefreshing(false);
                    break;
            }
        }
    };


    @Override
    protected void initView() {
        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);


        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorArenaGreen, R.color.colorAccent, R.color.colorMainDividerLine);

    }
    @Override
    protected void initEvent() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void loadData(){
        adapterBuilder();
        recyclerView.setAdapter(adapter);
    }
    //子activity设置适配器
    protected abstract void adapterBuilder();
    @Override
    public void onRefresh() {
        //todo 网络请求任务
        handler.sendEmptyMessageDelayed(REFRESH, 3000);
    }
}
