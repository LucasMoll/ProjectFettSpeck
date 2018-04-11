package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.HomeFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.LebenbsmittelFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.MenuesFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.ProfilFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.StatistikFragment;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null){
        setName(navigationView);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();}
    }

    private void setName(NavigationView navigationView) {
        View headerView = navigationView.getHeaderView(0);
        TextView mName;
        TextView mEMAIL;
        mName = (TextView) headerView.findViewById(R.id.Name);
        mEMAIL = (TextView) headerView.findViewById(R.id.textView);
        mName.setText(getName());
        mEMAIL.setText(getEMail());
    }


    private String getName() {
        String s = "";
        DataAdapter mDbHelper = new DataAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getName();

        cursor.moveToFirst();
        while (!cursor.isAfterLast())

        {
            s = cursor.getString(0);
            cursor.moveToNext();
        }
        cursor.close();
        return s;

    }

    private String getEMail() {
        String s = "";
        DataAdapter mDbHelper = new DataAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getEMail();

        cursor.moveToFirst();
        while (!cursor.isAfterLast())

        {
            s = cursor.getString(cursor.getColumnIndex("EMail"));
            cursor.moveToNext();
        }
        cursor.close();
        return s;

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
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = null;

        switch (id){
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;

            case R.id.nav_statistik:
                fragment = new StatistikFragment();
                break;

            case R.id.nav_menues:
                fragment = new MenuesFragment();
                break;

            case R.id.nav_lebensmittel:
                fragment = new LebenbsmittelFragment();
                break;

            case R.id.nav_profil:
                fragment = new ProfilFragment();
                break;

            default: break;

        }


        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).attach(fragment).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
