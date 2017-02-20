package com.fuzhucheng.rxjavartrofitrecyclerview.fragment;

import android.view.View;
import android.widget.LinearLayout;

import com.fuzhucheng.rxjavartrofitrecyclerview.R;
import com.fuzhucheng.rxjavartrofitrecyclerview.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 符柱成 on 2016/12/3.
 */
public class ArenaFragment extends BaseFragment {

    @BindView(R.id.get_on_list)
    LinearLayout getOnList;
    @BindView(R.id.lang_ya)
    LinearLayout langYa;
    @BindView(R.id.choose)
    LinearLayout choose;

    @Override
    protected void initEvent() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_arena;
    }



    @OnClick({R.id.get_on_list, R.id.lang_ya, R.id.choose})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_on_list:
//                startActivity(new Intent(getActivity(), GetOnListActivity.class));
                break;

            case R.id.lang_ya:
//                startActivity(new Intent(getActivity(), LangYaActivity.class));
                break;

            case R.id.choose:
//                startActivity(new Intent(getActivity(), ChooseActivity.class));
                break;
        }
    }
}
