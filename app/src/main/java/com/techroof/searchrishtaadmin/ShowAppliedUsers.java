package com.techroof.searchrishtaadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.techroof.searchrishtaadmin.Adapters.AppliedFragmentViewPagerAdapter;
import com.techroof.searchrishtaadmin.AppliedFragments.ActivatedFragment;
import com.techroof.searchrishtaadmin.AppliedFragments.PendingFragment;

public class ShowAppliedUsers extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_applied_users);
        tabLayout=findViewById(R.id.tablayoutShowApplied);
        viewPager=findViewById(R.id.viewpager_ShowApplied);
        tabLayout.setupWithViewPager(viewPager);
        AppliedFragmentViewPagerAdapter viewPagerAdapter=
                new AppliedFragmentViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addfragment(new PendingFragment(),"REQUESTED");
        viewPagerAdapter.addfragment(new ActivatedFragment(),"ACTIVATED");
        viewPager.setAdapter(viewPagerAdapter);
    }
}