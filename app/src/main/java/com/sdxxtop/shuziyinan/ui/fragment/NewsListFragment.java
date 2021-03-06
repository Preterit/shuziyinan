package com.sdxxtop.shuziyinan.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sdxxtop.shuziyinan.R;
import com.sdxxtop.shuziyinan.base.BaseMvpFragment;
import com.sdxxtop.shuziyinan.model.bean.AllarticleBean;
import com.sdxxtop.shuziyinan.model.bean.LearnNewsBean;
import com.sdxxtop.shuziyinan.presenter.NewsListFragmentPresenter;
import com.sdxxtop.shuziyinan.presenter.contract.NewsListFragmentContract;
import com.sdxxtop.shuziyinan.ui.adapter.BannerViewLearnHolder;
import com.sdxxtop.shuziyinan.ui.adapter.NewsListAdapter;
import com.sdxxtop.shuziyinan.ui.widget.mzbanner.MZBannerView;
import com.sdxxtop.shuziyinan.ui.widget.mzbanner.holder.MZHolderCreator;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class NewsListFragment extends BaseMvpFragment<NewsListFragmentPresenter> implements NewsListFragmentContract.IView {
    private static final String TAG = "NewsListFragment";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smart_refresh;

    private NewsListAdapter adapter;
    /******  1:新闻学习  2:罗庄发布  *******/
    private int type = 1;
    /*********  1:下拉刷新,2:上拉加载  *********/
    private int isLoadMore = 1;

    private boolean isFirstLoading;
    private MZBannerView mMZBanner;


    public static NewsListFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_news_list;
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            type = getArguments().getInt("type");
//            mPresenter.loadData(0,type);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsListAdapter(new ArrayList<>(), getContext(), type);
        recyclerView.setAdapter(adapter);
        //刷新
        smart_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isLoadMore = 1;
                mPresenter.loadData(0, type);
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        smart_refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                isLoadMore = 2;
                int itemCount = 0;
                if (adapter != null) {
                    itemCount = adapter.getItemCount();
                }
                mPresenter.loadData(itemCount, type);
                refreshLayout.finishLoadMore();
            }
        });

        View headerView = View.inflate(getContext(), R.layout.item_banner_header, null);
        mMZBanner = headerView.findViewById(R.id.banner);
        mMZBanner.setIndicatorVisible(false);
        adapter.addHeaderView(headerView);

        if (getUserVisibleHint()) {
            loadData();
            Logger.e("setUserVisibleHint1", type);
        }
    }

    private void loadData() {
//        if (isFirstLoading) {
//            return;
//        }
        isFirstLoading = true;
        mPresenter.loadData(0, type);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mPresenter != null) {
            loadData();
            Logger.e("setUserVisibleHint2", type);
        }
    }

    @Override
    public void showData(AllarticleBean bean) {
        closeLoadingDialog();
        List<AllarticleBean.WheelPlanting> banner = bean.wheel_planting;
        if (banner != null) {
            if (banner.size() == 0) {
                mMZBanner.setVisibility(View.GONE);
            }
            // 设置数据
            mMZBanner.setPages(banner, new MZHolderCreator<BannerViewLearnHolder>() {
                @Override
                public BannerViewLearnHolder createViewHolder() {
                    return new BannerViewLearnHolder();
                }
            });
            mMZBanner.start();
        }
        List<LearnNewsBean> data = bean.all_article;
        if (data != null) {
            if (isLoadMore == 2) {
                adapter.addData(data);
            } else {
                adapter.replaceData(data);
            }
        }
    }

    @Override
    public void showError(String error) {
        closeLoadingDialog();
    }

    @Override
    public void setIsFirstLoading(boolean isFirstLoading) {
        super.setIsFirstLoading(isFirstLoading);
        this.isFirstLoading = isFirstLoading;
    }

}
