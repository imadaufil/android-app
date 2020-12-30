package com.example.mysteriousapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private final static String FRAGMENT_NUMBER_KEY = "Fragment_Number";
    private final static String FRAGMENT_STORED_KEY = "Fragment_Stored";
    private DrawerLayout drawerLayout;
    private SelectableNavigationView navigationView;
    private SparseArray<Fragment> fragmentArray;
    private Fragment currentFragment;
    private Toolbar toolbar;

    private int currentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNavigationElements();

        ImageView imageView = (ImageView) findViewById(R.id.imageLayout);
        currentLayout = 0;
        ImageView view = (ImageView) findViewById(R.id.imageLayout);
        view.setImageResource(R.drawable.ic_viewgrid_2);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imageView = (ImageView) findViewById(R.id.imageLayout);
                if (currentLayout == 0) {
                    imageView.setImageResource(R.drawable.ic_viewlist);
                    currentLayout = 1;
                }
                else {
                    imageView.setImageResource(R.drawable.ic_viewgrid_2);
                    currentLayout = 0;
                }
                }
            });


        if (savedInstanceState != null) {
            //State had been saved so we need to restore the right fragment
            //No need to check the menu item because savedInstance restores automatically the view states
            //We need to store the fragment inside our array so it won't be recreated
            currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, FRAGMENT_STORED_KEY);
            replaceFragment(currentFragment);
            fragmentArray.append(savedInstanceState.getInt(FRAGMENT_NUMBER_KEY), currentFragment);
        } else {
            //Set default screen to "My Selection", i.e. the first menu element
            navigationView.setSelectedItem(navigationView.getMenu().getItem(0));
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


    private void setupNavigationElements() {

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        fragmentArray = new SparseArray<>(3);

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getOrder() == 2) {
                    //Log off and then
                    logoff();
                    //We don't want to set the selected item to selected state
                    return false;
                }
                //If selection is not logoff, display the right screen
                if (navigationView.getCheckedItem() != menuItem) {
                    replaceFragment(getSelectedMenuFragment(menuItem.getOrder()));
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private Fragment getSelectedMenuFragment(int position) {
        TextView textV = (TextView) findViewById(R.id.textTitlte);
        Fragment selectedFragment = fragmentArray.get(position);
        if (selectedFragment == null) {
            switch (position) {
                //List screen
                //Favorites screen
                case 1:
                    textV.setText("Saved For Later");
                    selectedFragment = SavedForLaterFragment.newInstance();
                    break;
                //Default, let's go back to list screen
                default:
                    textV.setText("Most Popular");
                    selectedFragment = TopStoriesFragment.newInstance();
                    break;
            }
            fragmentArray.append(position, selectedFragment);
        }
        switch (position) {
            case 1:
                textV.setText("Saved For Later");
                break;
            default:
                textV.setText("Most Popular");
                break;
        }
        currentFragment = selectedFragment;
        return selectedFragment;
    }

    private void replaceFragment(Fragment newFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void logoff() {
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(FRAGMENT_NUMBER_KEY, navigationView.getCheckedItem().getOrder());
        getSupportFragmentManager().putFragment(savedInstanceState, FRAGMENT_STORED_KEY, currentFragment);

    }
}