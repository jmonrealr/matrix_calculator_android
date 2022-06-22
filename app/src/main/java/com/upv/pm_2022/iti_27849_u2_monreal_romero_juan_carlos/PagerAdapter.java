package com.upv.pm_2022.iti_27849_u2_monreal_romero_juan_carlos;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private int numTabs;

    /**
     * Constructor for {@link FragmentStatePagerAdapter}.
     * <p>
     * If {@link #BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT} is passed in, then only the current
     * Fragment is in the {@link Lifecycle.State#RESUMED} state, while all other fragments are
     * capped at {@link Lifecycle.State#STARTED}. If {@link #BEHAVIOR_SET_USER_VISIBLE_HINT} is
     * passed, all fragments are in the {@link Lifecycle.State#RESUMED} state and there will be
     * callbacks to {@link Fragment#setUserVisibleHint(boolean)}.
     *
     * @param fm       fragment manager that will interact with this adapter
     * @param numTabs determines if only current fragments are in a resumed state
     */
    public PagerAdapter(@NonNull FragmentManager fm, int numTabs) {
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numTabs = numTabs;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                CalculatorFragment tab1 = new CalculatorFragment();
                return tab1;
            case 1:
                GraphicFragment tab2 = new GraphicFragment();
                return tab2;
            default:
                return null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return numTabs;
    }
}
