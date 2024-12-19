package com.example.casper.Experiment2024.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.casper.Experiment2024.fragment.BookListFragment;
import com.example.casper.Experiment2024.fragment.WebViewFragment;
import com.example.casper.Experiment2024.fragment.TencentMapFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BookListFragment(); // 图书 Fragment
            case 1:
                return new WebViewFragment(); // 搜索 Fragment
            case 2:
                return new TencentMapFragment(); // 地图 Fragment
            default:
                return new BookListFragment(); // 默认返回图书 Fragment
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Tab 数量
    }
}