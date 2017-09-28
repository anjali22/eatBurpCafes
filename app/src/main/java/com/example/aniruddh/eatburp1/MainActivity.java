package com.example.aniruddh.eatburp1;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import static android.R.attr.fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    JSONObject response, profile_pic_data, profile_pic_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent intent = getIntent();
        String jsondata = intent.getStringExtra("userProfile");
        Log.w("Jsondata", jsondata);*/


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        toolbar.setTitle("Dharamshala");
        toolbar.setSubtitle("");

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setFragment(new dataFragment());//init

        //

       /* TextView user_name = (TextView) findViewById(R.id.tv_name);
        TextView user_email = (TextView) findViewById(R.id.tv_email);

        ImageView user_picture = (ImageView) findViewById(R.id.iv_picture);

        try {
            response = new JSONObject(jsondata);
            user_email.setText(response.get("email").toString());
            user_name.setText(response.get("name").toString());
            profile_pic_data = new JSONObject(response.get("picture").toString());
            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
            Picasso.with(this).load(profile_pic_url.getString("url")).into(user_picture);

        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        return true;



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action

            setTitle("Home Screen");

            setFragment(new dataFragment());
        } else if (id == R.id.nav_about_us) {

            setTitle("About Us");

            AboutUsFragment firstFragment =new AboutUsFragment();
            FragmentManager fragmentManager =getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, firstFragment).commit();

        } else if (id == R.id.nav_feedback) {

            setTitle("Feedback");

            FeedbackFragment secondFragment =new FeedbackFragment();
            FragmentManager fragmentManager2 =getSupportFragmentManager();
            fragmentManager2.beginTransaction().replace(R.id.content_main,secondFragment).commit();



        } else if (id == R.id.nav_nearby) {

            setTitle("NewsFeed");

            NearByFragment thirdFragment =new NearByFragment();
            FragmentManager fragmentManager3 =getSupportFragmentManager();
            fragmentManager3.beginTransaction().replace(R.id.content_main,thirdFragment).commit();


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }


            return true;
        }

        public void setFragment(Fragment fragment){

            if(fragment!=null){

                FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_main,fragment ).commit();
            }





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }


    public void openSearchWindow(MenuItem item) {
        startActivity(new Intent(MainActivity.this, SearchActivity.class));

    }
}
