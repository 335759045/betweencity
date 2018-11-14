package com.betweencity.tm.betweencity.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.betweencity.tm.betweencity.R;
import com.betweencity.tm.betweencity.base.BaseActivity;
import com.betweencity.tm.betweencity.base.BasePresentImpl;
import com.betweencity.tm.betweencity.commom.Constans;
import com.betweencity.tm.betweencity.mvp.ui.fragment.FourFragment;
import com.betweencity.tm.betweencity.mvp.ui.fragment.OneFragment;
import com.betweencity.tm.betweencity.mvp.ui.fragment.ThreeFragment;
import com.betweencity.tm.betweencity.mvp.ui.fragment.TwoFragment;
import com.betweencity.tm.betweencity.utils.SystemHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.v4_drawerlayout)
    DrawerLayout v4Drawerlayout;
    @BindView(R.id.include_main_ig_left)
    ImageView includeMainIgLeft;
    @BindView(R.id.include_main_title)
    TextView includeMainTitle;
    @BindView(R.id.include_main_ig_search)
    ImageView includeMainIgSearch;
    @BindView(R.id.include_main_ig_more)
    ImageView includeMainIgMore;
    @BindView(R.id.fragment_controller)
    FrameLayout fragmentController;
    @BindView(R.id.include_main_rb1)
    RadioButton includeMainRb1;
    @BindView(R.id.include_main_rb2)
    RadioButton includeMainRb2;
    @BindView(R.id.include_main_rb3)
    RadioButton includeMainRb3;
    @BindView(R.id.include_main_rb4)
    RadioButton includeMainRb4;
    @BindView(R.id.include_main_rg)
    RadioGroup includeMainRg;
    @BindView(R.id.v4_drawerlayout_frame)
    FrameLayout v4DrawerlayoutFrame;
    @BindView(R.id.act_main_L)
    LinearLayout actMainL;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;

    private DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            // 得到contentView
            View content = v4Drawerlayout.getChildAt(0);
            int offset = (int) (drawerView.getWidth() * slideOffset);
            content.setTranslationX(offset);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
        }

        @Override
        public void onDrawerClosed(View drawerView) {
        }

        @Override
        public void onDrawerStateChanged(int newState) {
        }
    };

    @Override
    protected BasePresentImpl createPresent() {
        return null;
    }

    @Override
    protected void initTitle() {
        hideTitleView();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemHelper.setMargins(v4DrawerlayoutFrame, 0, Constans.P_STATUSBAR_HEIGHT, 0, 0);
            actMainL.setPadding(0, Constans.P_STATUSBAR_HEIGHT, 0, 0);
        }
        fragmentManager = getSupportFragmentManager();
        showFragment(0);

//        v4Drawerlayout.openDrawer(Gravity.LEFT);
//        showDrawerLayout();
        //设置不覆盖主题界面
//        v4Drawerlayout.setScrimColor(Color.TRANSPARENT);
        v4Drawerlayout.addDrawerListener(drawerListener);

        includeMainRg.setOnCheckedChangeListener(this);
    }

    /**
     * 显示fragment
     */
    private void showFragment(int index) {
        fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);/*想要显示一个fragment,先隐藏所有fragment，防止重叠*/
        switch (index) {
            case 0:
                /*如果fragment1已经存在则将其显示出来*/
                if (oneFragment != null)
                    fragmentTransaction.show(oneFragment);
                    /*否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add*/
                else {
                    oneFragment = new OneFragment();
                    fragmentTransaction.add(R.id.fragment_controller, oneFragment);
                }
                break;
            case 1:
                if (twoFragment != null)
                    fragmentTransaction.show(twoFragment);
                else {
                    twoFragment = new TwoFragment();
                    fragmentTransaction.add(R.id.fragment_controller, twoFragment);
                }
                break;
            case 2:
                if (threeFragment != null)
                    fragmentTransaction.show(threeFragment);
                else {
                    threeFragment = new ThreeFragment();
                    fragmentTransaction.add(R.id.fragment_controller, threeFragment);
                }
                break;
            case 3:
                if (fourFragment != null)
                    fragmentTransaction.show(fourFragment);
                else {
                    fourFragment = new FourFragment();
                    fragmentTransaction.add(R.id.fragment_controller, fourFragment);
                }
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏fragment
     */
    private void hideFragment(FragmentTransaction ft) {
        if (oneFragment != null)
            ft.hide(oneFragment);
        if (twoFragment != null)
            ft.hide(twoFragment);
        if (threeFragment != null)
            ft.hide(threeFragment);
        if (fourFragment != null)
            ft.hide(fourFragment);
    }

    /**
     * 隐藏显示侧滑
     */
    private void showDrawerLayout() {
        if (!v4Drawerlayout.isDrawerOpen(Gravity.LEFT)) {
            v4Drawerlayout.openDrawer(Gravity.LEFT);
        } else {
            v4Drawerlayout.closeDrawer(Gravity.LEFT);
        }
    }

    @Override
    protected void setUpData() {

    }

    @Override
    protected boolean getretunData() {
        return true;
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void reFreshData() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @OnClick({R.id.include_main_ig_left, R.id.include_main_ig_search, R.id.include_main_ig_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.include_main_ig_left:
                showDrawerLayout();
                break;
            case R.id.include_main_ig_search:
                break;
            case R.id.include_main_ig_more:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.include_main_rb1:
                //打开手势滑动
                v4Drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                includeMainIgLeft.setVisibility(View.VISIBLE);
                break;
            case R.id.include_main_rb2:
                //关闭手势滑动
                v4Drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                includeMainIgLeft.setVisibility(View.GONE);
                break;
            case R.id.include_main_rb3:
                //关闭手势滑动
                v4Drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                includeMainIgLeft.setVisibility(View.GONE);
                break;
            case R.id.include_main_rb4:
                //关闭手势滑动
                v4Drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                includeMainIgLeft.setVisibility(View.GONE);
                break;
        }
    }
}
