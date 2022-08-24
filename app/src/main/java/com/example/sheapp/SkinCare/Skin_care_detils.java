package com.example.sheapp.SkinCare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.sheapp.R;
import com.example.sheapp.RecipeFragment;
import com.example.sheapp.TreatmentsFragment;
import com.example.sheapp.commentFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Skin_care_detils extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_care_detils);


        tabLayout =findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new RecipeFragment(),"Recipe");
        adapter.addFragment(new TreatmentsFragment(),"Treatments");
        adapter.addFragment(new commentFragment(), "Comment ");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);}



    }

class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mList = new ArrayList<>();
    private final List<String> mTitleList = new ArrayList<>();
    public ViewPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int i) {
        return mList.get(i);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mList.add(fragment);
        mTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
