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
import com.example.mysteriousapp.presentation.article.articles.fragment.articles.HomeFragment;
import com.example.mysteriousapp.presentation.article.articles.fragment.articles.SportsFragment;
import com.example.mysteriousapp.presentation.article.articles.fragment.articles.TechnologyFragment;
import com.example.mysteriousapp.presentation.article.saved_for_later.fragment.SavedForLaterFragment;
import com.facebook.stetho.Stetho;
import com.google.android.material.navigation.NavigationView;

/**
 * Main Activity
 */
public class MainActivity extends AppCompatActivity {

    private final static String FRAGMENT_NUMBER_KEY = "Fragment_Number";
    private final static String FRAGMENT_STORED_KEY = "Fragment_Stored";
    private DrawerLayout drawerLayout;
    private SelectableNavigationView navigationView;
    private SparseArray<Fragment> fragmentArray;
    private Fragment currentFragment;
    private ImageView menuBtn;
    private TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setApplicationContext(this);
        setContentView(R.layout.activity_main);

        menuBtn = (ImageView) findViewById(R.id.menuBtn);

        setupNavigationElements();
        toolbarText = (TextView) findViewById(R.id.toolbarText);

        // charger les fragment existant pour eviter une pile de fragment dans le cache
        if (savedInstanceState != null) {
            currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, FRAGMENT_STORED_KEY);
            replaceFragment(currentFragment);
            fragmentArray.append(savedInstanceState.getInt(FRAGMENT_NUMBER_KEY), currentFragment);
        } else {
            navigationView.setSelectedItem(navigationView.getMenu().getItem(0));
        }
    }


    /**
     * Setup the navigation elements for the hamburger menu
     */
    private void setupNavigationElements() {
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        fragmentArray = new SparseArray<>(5);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getOrder() == 4) {
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

    /**
     * Returns Fragment depending position
     * @param position of the selected fragment
     * @return Fragment selected
     */
    private Fragment getSelectedMenuFragment(int position) {
        Fragment selectedFragment = fragmentArray.get(position);
        if (selectedFragment == null) {
            switch (position) {
                case 1:
                    toolbarText.setText("Saved for Later");
                    selectedFragment = SavedForLaterFragment.newInstance();
                    break;
                case 2:
                    toolbarText.setText("Sports");
                    selectedFragment = SportsFragment.newInstance();
                    break;
                case 3:
                    toolbarText.setText("Technology");
                    selectedFragment = TechnologyFragment.newInstance();
                    break;
                default:
                    toolbarText.setText("Headlines");
                    selectedFragment = HomeFragment.newInstance();
                    break;
            }
            fragmentArray.append(position, selectedFragment);
        }
        switch (position) {
            case 1:
                toolbarText.setText("Saved For Later");
                break;
            case 2:
                toolbarText.setText("Sports");
                break;
            case 3:
                toolbarText.setText("Technology");
                break;
            default:
                toolbarText.setText("Headlines");
                break;
        }
        currentFragment = selectedFragment;
        return selectedFragment;
    }

    /**
     * Replace the current fragment by newFrament
     * @param newFragment new Fragment
     */
    private void replaceFragment(Fragment newFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * Exit the app
     */
    private void logoff() {
        finish();
    }

    /**
     * Save the state of the fragment
     * @param savedInstanceState Bundle
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        getSupportFragmentManager().putFragment(savedInstanceState, FRAGMENT_STORED_KEY, currentFragment);
    }

}