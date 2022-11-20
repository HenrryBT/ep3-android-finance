package com.henrrybeltran.ep3.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.henrrybeltran.ep3.fragments.BillsFragment;
import com.henrrybeltran.ep3.fragments.GiftsFragment;
import com.henrrybeltran.ep3.fragments.MovementFragment;
import com.henrrybeltran.ep3.fragments.RegisterFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private static final int pages_size = 4;
    private final ArrayList<Fragment> fragments = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                fragments.add(new RegisterFragment());
                break;
            case 1:
                fragments.add(new MovementFragment());
                break;
            case 2:
                fragments.add(new BillsFragment());
                break;
            default:
                fragments.add(new GiftsFragment());
                break;
        }

        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return pages_size;
    }
}