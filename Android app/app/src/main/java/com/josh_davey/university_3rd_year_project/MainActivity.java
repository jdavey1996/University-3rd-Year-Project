package com.josh_davey.university_3rd_year_project;

import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

        //Log.i("CHECK-TOKEN", FirebaseInstanceId.getInstance().getToken());

        if (getIntent().hasExtra("remove_notification")) {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.cancel(0);

            SharedPreferences sharedPreferences = getSharedPreferences("motion_detection_app", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("notification_counter").apply();
        }

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

    public void selectItem(MenuItem menuItem) {
        //Replace fragment in container based on selected navigation item.
        FragmentTransaction loadFragment = getSupportFragmentManager().beginTransaction();
        switch (menuItem.getItemId()) {
            case R.id.nav_controls_fragment:
                loadFragment.replace(R.id.fragment_container, new ControlsFragment());
                break;
            case R.id.nav_detection_history_fragment:
                loadFragment.replace(R.id.fragment_container, new DetectionHistoryFragment());
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

    //Inflate toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    //Adds functions to toolbar buttons, eg logout.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.manageDeviceBtn:
                manageDeviceDialog();
                break;
        }
        return true;
    }


    public void manageDeviceDialog() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.manage_device_dialog, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this);
        alertDialogBuilderUserInput.setView(dialogView);

        final EditText input1 = (EditText) dialogView.findViewById(R.id.userInputDialog1);
        final EditText input2 = (EditText) dialogView.findViewById(R.id.userInputDialog2);


        TextView title = (TextView) dialogView.findViewById(R.id.dtitle);


        final SharedPrefs sharedPrefs = new SharedPrefs(this);
        SharedPrefs.SharedprefObj device = sharedPrefs.getPrefs();

        //If a device isn't saved in settings.
        if(device.ip==null || device.port ==null)
        {
            title.setText("No detector currently linked. Link below.");
            //Set text to say, no device linked, add a device below and press save.
        }
        else {
            //Set text, current device linked, edit nad save changes.
            title.setText("Detector status is linked. View & update below.");
            input1.setText(device.ip);
            input2.setText(device.port);

        }


        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                        if(input1.length()>0 && input2.length()>0)
                        {
                            sharedPrefs.updatePrefs(input1.getText().toString(),input2.getText().toString());
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Cannot be updated, incorrect values.", Toast.LENGTH_SHORT).show();
                        }

                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }




}