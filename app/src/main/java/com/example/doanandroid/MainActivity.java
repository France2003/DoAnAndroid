package com.example.doanandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
        ImageButton btnSearch = findViewById(R.id.btnSearch);
        EditText editTextSearch = findViewById(R.id.editTextSearch);
        TextView textViewTitle = findViewById(R.id.textView5);
//        ConstraintLayout mainLayout = findViewById(R.id.drawerLayout);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);  // This should be TabLayout, not TableLayout
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED); // Chế độ "fixed" giúp phân phối đều
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        //TabLayout
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Sach(), "SÁCH");
        vpAdapter.addFragment(new TacGiaa(), "Tác Giả");
        vpAdapter.addFragment(new TheLoai(), "Thể Loại");
        viewPager.setAdapter(vpAdapter);
        //Search
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextSearch.getVisibility() == View.GONE) {
                    // Ẩn chữ "THƯ VIỆN", hiện ô input
                    textViewTitle.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.GONE);
                    editTextSearch.setVisibility(View.VISIBLE);
                } else {
                    // Hiện lại chữ "THƯ VIỆN", ẩn ô input
                    textViewTitle.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.VISIBLE);
                    editTextSearch.setVisibility(View.GONE);
                }
            }
        });
        findViewById(R.id.drawerLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (editTextSearch.getVisibility() == View.VISIBLE) {
                    textViewTitle.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.VISIBLE);
                    editTextSearch.setVisibility(View.GONE);
                }
                return false;
            }
        });
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
        //LogOut
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id == R.id.checkout){
                showLogoutDialog();
                return true;
            } else if (id == R.id.ThongKe) {
                Intent intent = new Intent(MainActivity.this, ThongKe.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        });
    }
    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận đăng xuất");
        builder.setMessage("Bạn có chắc chắn muốn đăng xuất?");
        builder.setPositiveButton("ĐĂNG XUẤT", (dialog, which) -> {
            Toast.makeText(this, "Bạn đã đăng xuất!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        builder.setNegativeButton("HỦY", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}