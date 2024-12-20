package com.example.casper.Experiment2024.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.casper.Experiment2024.fragment.BookListFragment;
import com.example.casper.Experiment2024.fragment.TencentMapFragment; // 假设你有这个 Fragment
import com.example.casper.Experiment2024.fragment.WebViewFragment; // 假设你有这个 Fragment

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BookListFragment();
            case 1:
                return new WebViewFragment(); // 替换为实际的 Fragment
            case 2:
                return new TencentMapFragment(); // 替换为实际的 Fragment
            default:
                return new BookListFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Tab 的数量
    }
}