package com.example.mysteriousapp.presentation.article;

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

import com.example.mysteriousapp.R;
import com.example.mysteriousapp.data.di.FakeDependencyInjection;
import com.example.mysteriousapp.presentation.article.saved_for_later.fragment.SavedForLaterFragment;
import com.example.mysteriousapp.presentation.article.articles.fragment.MostPopularFragment;
import com.facebook.stetho.Stetho;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private final static String FRAGMENT_NUMBER_KEY = "Fragment_Number";
    private final static String FRAGMENT_STORED_KEY = "Fragment_Stored";
    private DrawerLayout drawerLayout;
    private SelectableNavigationView navigationView;
    private SparseArray<Fragment> fragmentArray;
    private Fragment currentFragment;
    private Toolbar toolbar;
    private ImageView menuBtn;
    private ImageView layoutToggleBtn;
    private TextView toolbarText;

    private int currentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setApplicationContext(this);
        setContentView(R.layout.activity_main);

        menuBtn = (ImageView) findViewById(R.id.menuBtn);
        currentLayout = 0;

        setupNavigationElements();

        /*layoutToggleBtn = (ImageView) findViewById(R.id.layoutToggleBtn);
        layoutToggleBtn.setImageResource(R.drawable.ic_viewgrid_2);
        layoutToggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentLayout == 0) {
                    layoutToggleBtn.setImageResource(R.drawable.ic_viewlist);
                    currentLayout = 1;
                }
                else {
                    layoutToggleBtn.setImageResource(R.drawable.ic_viewgrid_2);
                    currentLayout = 0;
                }
                }
            });*/
        toolbarText = (TextView) findViewById(R.id.toolbarText);
        if (savedInstanceState != null) {
            currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, FRAGMENT_STORED_KEY);
            replaceFragment(currentFragment);
            fragmentArray.append(savedInstanceState.getInt(FRAGMENT_NUMBER_KEY), currentFragment);
        } else {
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
        menuBtn.setOnClickListener(new View.OnClickListener() {
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
                    logoff();
                    return false;
                }
                if (navigationView.getCheckedItem() != menuItem) {
                    replaceFragment(getSelectedMenuFragment(menuItem.getOrder()));
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private Fragment getSelectedMenuFragment(int position) {
        Fragment selectedFragment = fragmentArray.get(position);
        if (selectedFragment == null) {
            switch (position) {
                case 1:
                    toolbarText.setText("Saved for Later");
                    selectedFragment = SavedForLaterFragment.newInstance();
                    break;
                default:
                    toolbarText.setText("Headlines");
                    selectedFragment = MostPopularFragment.newInstance();
                    break;
            }
            fragmentArray.append(position, selectedFragment);
        }
        switch (position) {
            case 1:
                toolbarText.setText("Saved For Later");
                break;
            default:
                toolbarText.setText("Headlines");
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
        //savedInstanceState.putInt(FRAGMENT_NUMBER_KEY, navigationView.getCheckedItem().getOrder());
        getSupportFragmentManager().putFragment(savedInstanceState, FRAGMENT_STORED_KEY, currentFragment);

    }
}