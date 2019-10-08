package com.bhardwaj.abhishek.payrollapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
     Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =  findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout)findViewById(R.id.activity_main);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.Open,R.string.Close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView =  findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);

        //to change the color of Communicate
        Menu menu= navigationView.getMenu(); // getting instance of menu

     /*   MenuItem tools = menu.findItem(R.id.comm);
        SpannableString s =  new SpannableString(tools.getTitle());
        s.setSpan(new TextAppearanceSpan(this,R.style.TextAppearance44),0,s.length(),0);
        tools.setTitle(s); // assigning new string as item's title
*/

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.home:
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                        break;

                    case R.id.calender:
                        openCalender();
                        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                        break;

                    case R.id.earnings:
                        openEarnings();
                        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                        break;

                    case R.id.message:
                       openMessages();
                        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                        break;

                    case R.id.flight:
                        Toast.makeText(MainActivity.this, "Flight", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FlightFragment()).commit();
                        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                        break;

                    default:
                        return true;


                }

                return true;

            }

        });


        TabLayout tl = (TabLayout)findViewById(R.id.tabLayout);
        tl.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        openCalender();
                        break;

                    case 1:
                        openEarnings();
                        break;

                    case 2:
                        openMessages();
                        break;

                    default:
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });





    }

    private void openMessages() {
        Toast.makeText(MainActivity.this, "Message", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MessageFragment()).commit();
    }

    private void openEarnings() {
        Toast.makeText(MainActivity.this, "Earnings", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new EarningFragment()).commit();
    }

    private void openCalender() {
        Toast.makeText(MainActivity.this, "Calender", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CalenderFragment()).commit();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      /*  if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
