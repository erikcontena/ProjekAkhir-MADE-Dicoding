package com.example.projekakhir.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.projekakhir.R;
import com.example.projekakhir.ui.notifications.Favorite.MovieFragment;
import com.example.projekakhir.ui.notifications.Favorite.SectionViewPager;
import com.example.projekakhir.ui.notifications.Favorite.TvShowFragment;
import com.google.android.material.tabs.TabLayout;

public class NotificationsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = view.findViewById(R.id.view_pager);
        TabLayout tabs = view.findViewById(R.id.tabs);
        setupViewPager(viewPager);
        tabs.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionViewPager sectionViewPager = new SectionViewPager(getChildFragmentManager());

        sectionViewPager.addFragment(new MovieFragment(), getString(R.string.title_movie));

        sectionViewPager.addFragment(new TvShowFragment(), getString(R.string.title_tv_show));

        viewPager.setAdapter(sectionViewPager);
    }

}