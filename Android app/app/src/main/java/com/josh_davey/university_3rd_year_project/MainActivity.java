package com.josh_davey.university_3rd_year_project;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Variables
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    String selectedItemTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting custom toolbar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //onClick functionaity for selecting a navigation menu item.
        final NavigationView navMenu = (NavigationView) findViewById(R.id.nav_view);
        navMenu.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //Replace current fragment based on click.
                        selectItem(menuItem);
                        //Close drawer layout on click.
                        drawerLayout.closeDrawer(navMenu);

                        return true;
                    }
                });


        //Setting functionality for when drawer is opened and closed.
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                //Setting title to selected item.
                getSupportActionBar().setTitle(selectedItemTitle);
            }

            public void onDrawerOpened(View view) {
                //Setting title to app title.
                getSupportActionBar().setTitle("Motion detector");
            }
        };

        //Sync drawer toggle icon with drawer open or closed.
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);

        //Select the current item (loading fragment in position 0 onCreate, and setting correct title.
        selectItem(navMenu.getMenu().getItem(0));
        getSupportActionBar().setTitle(selectedItemTitle);
    }

    public void selectItem(MenuItem menuItem)
    {
        //Replace fragment in container based on selected navigation item.
        FragmentTransaction loadFragment = getSupportFragmentManager().beginTransaction();
        switch (menuItem.getItemId()) {
            case R.id.nav_controls_fragment:
                loadFragment.replace(R.id.fragment_container, new ControlsFragment());
                break;
        }
        loadFragment.commit();
        menuItem.setChecked(true);
        selectedItemTitle = menuItem.getTitle().toString();
    }

    //Overriding functionality to close the drawer when pressing the back button only if the drawer is open.
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}