package com.example.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.weatherapp.singletone.Constants;
import com.example.weatherapp.singletone.ProgressDialog;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//implements NavigationView.OnNavigationItemSelectedListener
    public Toolbar toolbar;
    public DrawerLayout drawerLayout;
    public NavController navController;
    public NavigationView navigationView;
    private Constants constants;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constants = Constants.getInstance(MainActivity.this);
        ProgressDialog.loaderForPagination(MainActivity.this);



        setupNavigation();
    }


    public void setupNavigation()
    {
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        navController = Navigation.findNavController(this,R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout);

        NavigationUI.setupWithNavController(navigationView,navController);

        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.nav_host_fragment),drawerLayout);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        menuItem.setCheckable(true);
        drawerLayout.closeDrawers();

        int id = menuItem.getItemId();

        switch (id)
        {
            case R.id.m_first:
//                Toast.makeText(getApplicationContext(),"Default is Clicked!",Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.nav_montreal);
                break;
            case R.id.m_second:
//                Toast.makeText(getApplicationContext(),"Second is Clicked!",Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.nav_london);
                break;
            case R.id.m_third:
//                Toast.makeText(getApplicationContext(),"Third is Clicked!",Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.sanFranFragment);
                break;
            case R.id.m_fourth:
//                Toast.makeText(getApplicationContext(),"fourth is Clicked!",Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.dubaiFragment);
                break;
            case R.id.m_fifth:
//                Toast.makeText(getApplicationContext(),"fifth is Clicked!",Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.indiaFragment);
                break;
            case R.id.m_sixth:
//                Toast.makeText(getApplicationContext(),"sixth is Clicked!",Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.moskowFragment);
                break;

//                <item
//            android:id="@+id/m_fourth"
//            android:title="Dubai" />
//
//        <item
//            android:id="@+id/m_fifth"
//            android:title="India" />
//
//        <item
//            android:id="@+id/m_sixth"
//            android:title="Moskow" />

        }

        return true;
    }
}