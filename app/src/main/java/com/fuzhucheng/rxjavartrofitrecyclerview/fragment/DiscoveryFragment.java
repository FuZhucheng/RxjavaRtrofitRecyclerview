package com.fuzhucheng.rxjavartrofitrecyclerview.fragment;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.fuzhucheng.rxjavartrofitrecyclerview.R;
import com.fuzhucheng.rxjavartrofitrecyclerview.bean.AbilityBean;
import com.fuzhucheng.rxjavartrofitrecyclerview.bean.HotColumnBean;
import com.fuzhucheng.rxjavartrofitrecyclerview.fragment.base.BaseFragment;
import com.fuzhucheng.rxjavartrofitrecyclerview.requests.MovieRequest;
import com.fuzhucheng.rxjavartrofitrecyclerview.utils.RetrofitUtil;
import com.fuzhucheng.rxjavartrofitrecyclerview.utils.ToastUtils;
import com.fuzhucheng.rxjavartrofitrecyclerview.widget.FullyLinearLayoutManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 符柱成 on 2016/12/3.
 */
public class DiscoveryFragment extends BaseFragment {

    @BindView(R.id.hot_column_recycler_view)
    RecyclerView hotColumnRecyclerView;

    private List<HotColumnBean> hotColumnDatas = new ArrayList<>();
    private CommonAdapter<HotColumnBean> hotColumnAdapter;
    private MovieRequest movieRequest;

    @Override
    protected void initView() {
        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);

        hotColumnRecyclerView.setLayoutManager(layoutManager);
        hotColumnRecyclerView.setNestedScrollingEnabled(false);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadData() {
        getMovie();
        // TODO 网络加载优惠栏目图片


    }
    private void getMovie() {
        movieRequest = RetrofitUtil.getInstance().create(MovieRequest.class);
        movieRequest.getMovies(0, 10)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AbilityBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(getActivity(), getString(R.string.request_error));
                    }

                    @Override
                    public void onNext(AbilityBean abilityBean) {
                        hotColumnDatas.clear();
                        ToastUtils.showToast(getActivity(), getString(R.string.request_success));
                        for (int i = 0; i < abilityBean.subjects.size(); i++) {
                            hotColumnDatas.add(new HotColumnBean(abilityBean.subjects.get(i).casts.get(1).avatars.medium));
                        }

                        hotColumnAdapter = new CommonAdapter<HotColumnBean>(getActivity(), R.layout.item_fragment_discovery_hot_column, hotColumnDatas) {
                            @Override
                            protected void convert(ViewHolder holder, final HotColumnBean hotColumnBean, final int position) {

                                ImageView circleImageView = holder.getView(R.id.fragment_discovery_gallery_Image);
                                Glide.with(getActivity()).load(hotColumnBean.getImage()).into(circleImageView);
                            }
                        };
                        hotColumnRecyclerView.setAdapter(hotColumnAdapter);
                    }
                });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discovery;
    }


}
