package com.sdxxtop.shuziyinan.ui.activity;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sdxxtop.shuziyinan.R;
import com.sdxxtop.shuziyinan.base.BaseMvpActivity;
import com.sdxxtop.shuziyinan.model.bean.EventIndexBean;
import com.sdxxtop.shuziyinan.presenter.TaskAgentsPresenter;
import com.sdxxtop.shuziyinan.presenter.contract.TaskAgentsContract;
import com.sdxxtop.shuziyinan.ui.adapter.TaskAgentsAdapter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * 待办任务
 */
public class TaskAgentsActivity extends BaseMvpActivity<TaskAgentsPresenter> implements TaskAgentsContract.IView {
    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    private TaskAgentsAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_task_agents;
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TaskAgentsAdapter(R.layout.item_task_agents_recycler);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadData(0, 1);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (mPresenter != null) {
                    mPresenter.loadData(mAdapter.getData().size(), 1);
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if (mPresenter != null) {
                    mPresenter.loadData(0, 1);
                }
            }
        });

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showData(int page, EventIndexBean eventIndexBean) {
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
            mSmartRefreshLayout.finishLoadMore();
        }
        List<EventIndexBean.EventBean> event = eventIndexBean.getEvent();
        if (event == null) {
            return;
        }

        if (page == 0 && event.size() != 0) {
            mAdapter.replaceData(event);
        }

        if (page != 0) {
            mAdapter.addData(event);
        }
    }
}
