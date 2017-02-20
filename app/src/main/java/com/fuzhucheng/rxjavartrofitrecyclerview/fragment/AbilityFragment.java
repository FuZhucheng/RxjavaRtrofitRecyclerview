package com.fuzhucheng.rxjavartrofitrecyclerview.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fuzhucheng.rxjavartrofitrecyclerview.bean.AbilityItem;
import com.fuzhucheng.rxjavartrofitrecyclerview.R;
import com.fuzhucheng.rxjavartrofitrecyclerview.bean.AbilityBean;
import com.fuzhucheng.rxjavartrofitrecyclerview.fragment.base.BannerFragment;
import com.fuzhucheng.rxjavartrofitrecyclerview.requests.MovieRequest;
import com.fuzhucheng.rxjavartrofitrecyclerview.utils.RetrofitUtil;
import com.fuzhucheng.rxjavartrofitrecyclerview.utils.ToastUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 符柱成 on 2016/12/2.
 */
public class AbilityFragment extends BannerFragment implements View.OnClickListener {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<AbilityItem> datas = new ArrayList<>();
    private CommonAdapter<AbilityItem> adapter;
    private MovieRequest movieRequest;


    private List<String> bitmapList = new ArrayList<>();


    @Override
    protected List<String> getBitmapList() {
        return bitmapList;
    }

    @Override
    protected void initView() {
        super.initView();

    }


    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    protected void loadData() {
        getMovie();
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
                        swipeRefreshLayout.setRefreshing(false);
                        datas.clear();
                        ToastUtils.showToast(getActivity(), getString(R.string.request_success));
                        for (int i = 0; i < abilityBean.subjects.size(); i++) {
                            datas.add(new AbilityItem(abilityBean.subjects.get(i).images.medium, abilityBean.subjects.get(i).title, abilityBean.subjects.get(i).year));
                            bitmapList.add(abilityBean.subjects.get(i).images.large);
                        }
                        loadConvenientBanner();

                        adapter = new CommonAdapter<AbilityItem>(getActivity(), R.layout.item_fragment_ability_recyclerview, datas) {
                            @Override
                            protected void convert(ViewHolder holder, final AbilityItem abilityItem, final int position) {

                                holder.setText(R.id.place, abilityItem.getTitle());
                                holder.setText(R.id.price, abilityItem.getContent());
                                ImageView circleImageView = holder.getView(R.id.image);
                                Glide.with(getActivity()).load(abilityItem.getImage()).into(circleImageView);
                            }
                        };
                        recyclerView.setAdapter(adapter);
                    }
                });
    }


    @Override
    protected void adapterBuilder() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ability1;
    }


    @Override
    public void onRefresh() {
        super.onRefresh();
        getMovie();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.root_view:
//                startActivity(new Intent(getActivity(), IntroduceActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(int position) {

    }
}