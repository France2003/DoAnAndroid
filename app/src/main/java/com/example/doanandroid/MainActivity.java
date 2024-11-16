package com.example.doanandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    //Khai báo
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);  // This should be TabLayout, not TableLayout
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED); // Chế độ "fixed" giúp phân phối đều
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        //TabLayout
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Sach(), "SÁCH");
        vpAdapter.addFragment(new TacGia(), "Tác Giả");
        vpAdapter.addFragment(new TheLoai(), "Thể Loại");
        viewPager.setAdapter(vpAdapter);
        // Sự Kiện
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView text = (TextView) tab.getCustomView();
                if (text != null) {
                    text.setTextColor(getResources().getColor(R.color.tab_selected_text_color));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView text = (TextView) tab.getCustomView();
                if (text != null) {
                    text.setTextColor(getResources().getColor(R.color.tab_unselected_text_color));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Không cần thay đổi gì khi tab được chọn lại
            }
        });
        //Sự kiện navigate
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        //
        NavigationView navigationView = findViewById(R.id.navigateView);
        navigationView.setItemIconTintList(null);
        //
//        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
//        NavigationUI.setupWithNavController(navigationView, navController);
        ///DotsMenu
    }
}