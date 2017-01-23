package com.appbaco.appbaco.controllers.activity;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.appbaco.appbaco.R;
import com.appbaco.appbaco.utilities.AppbacoDatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MainScreen.OnFragmentInteractionListener,
        AccountList.OnFragmentInteractionListener,
        CategoryList.OnFragmentInteractionListener,
        TransactionsList.OnFragmentInteractionListener,
        SearchList.OnFragmentInteractionListener,
        Configuration.OnFragmentInteractionListener,
        AboutList.OnFragmentInteractionListener {

    public static AppbacoDatabaseHelper appbacoDatabaseHelper;
    public static SQLiteDatabase appbacoDatabase;
    public static Toolbar toolbar;
    public static ArrayList<Integer> colors = new ArrayList<>();
    public String currentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Fragment fragment;
            Class fragmentClass = MainScreen.class;
            currentFrame = "MainFragment";
            try {
                fragment = (Fragment) fragmentClass.newInstance();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Abrimos la base de datos
        appbacoDatabaseHelper = new AppbacoDatabaseHelper(this, "AppBacoDataBase", null, 6);
        appbacoDatabase = appbacoDatabaseHelper.getWritableDatabase();
        //--

        this.setColors();
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

    private void setColors() {
        if (colors == null) {
            colors = new ArrayList<>();
        }
        colors.add(0xffe57373);
        colors.add(0xfff06292);
        colors.add(0xffba68c8);
        colors.add(0xff9575cd);
        colors.add(0xff7986cb);
        colors.add(0xff64b5f6);
        colors.add(0xff4fc3f7);
        colors.add(0xff4dd0e1);
        colors.add(0xff4db6ac);
        colors.add(0xff81c784);
        colors.add(0xffaed581);
        colors.add(0xffff8a65);
        colors.add(0xffd4e157);
        colors.add(0xffffd54f);
        colors.add(0xffffb74d);
        colors.add(0xffa1887f);
        colors.add(0xff90a4ae);
    }

    public static ArrayList<Integer> getColors() {
        return colors;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //TODO Manejar accion pantalla principal
        if (id == R.id.nav_mainmenu) {
            // Manejar accion account
            replaceFragments(MainScreen.class);
        } else if (id == R.id.nav_acount) {
            // Manejar accion cuentas
            replaceFragments(AccountList.class);
        } else if (id == R.id.nav_categories) {
            // Manejar accion categorias
            replaceFragments(CategoryList.class);
        } else if (id == R.id.nav_transactions) {
            // Manejar accion transacciones
            replaceFragments(TransactionsList.class);
        } else if (id == R.id.nav_search) {
            // Manejar accion consultas
            replaceFragments(SearchList.class);
        } else if (id == R.id.nav_config) {
            // Manejar accion configuracion
            replaceFragments(Configuration.class);
        } else if (id == R.id.nav_about) {
            // Manejar accion sobre app
            replaceFragments(AboutList.class);
        }

        return true;
    }


    //Metodo utilizado para abrir fragments. es ejecutado cuando se hace clic en el menu
    //y llamado desde el metodo onNavigationItemSelected
    public void replaceFragments(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        currentFrame = fragmentClass.getSimpleName();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d("myApp", "onFragmentInteraction");
    }
}
