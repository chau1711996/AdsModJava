package dev.chau.adsmodjava.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import dev.chau.adsmodjava.fragment.GridFragment;
import dev.chau.adsmodjava.fragment.VerticalFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new VerticalFragment();
        }
        return new GridFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
