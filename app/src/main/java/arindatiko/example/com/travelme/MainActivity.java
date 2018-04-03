package arindatiko.example.com.travelme;

import android.support.v7.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import arindatiko.example.com.travelme.fragment.AdminDriverReviewFragment;
import arindatiko.example.com.travelme.fragment.DriverHomeFragment;
import arindatiko.example.com.travelme.fragment.DriverJadwalFragment;
import arindatiko.example.com.travelme.fragment.AdminDriverProfilFragment;
import arindatiko.example.com.travelme.fragment.HistoryPesananFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    /*@BindView(R.id.bottom_nav)
    BottomNavigationView navigation;*/
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //toolbar = getSupportActionBar();
        //getSupportActionBar().setTitle("Home");

        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.light_grey));
        bottomNavigation.setAccentColor(getResources().getColor(R.color.navbottom));
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.darker_gray));

        //admin
        /*AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home", R.drawable.ic_home);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("History PesananAdmin", R.drawable.history);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Review Pelanggan",R.drawable.review);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Profil", R.drawable.profil);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);

        AdminHomeFragment fragment = new AdminHomeFragment();
        if (savedInstanceState == null){
            fragment.setArguments(getIntent().getExtras());
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameid, fragment)
                    .commit();
        }

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                Fragment fragment = null;
                switch (position){
                    case 0:
                        fragment = new AdminHomeFragment();
                        break;
                    case 1:
                        fragment = new HistoryPesananFragment();
                        break;
                    case 2:
                        fragment = new AdminDriverReviewFragment();
                        break;
                    case 3:
                        fragment = new AdminDriverProfilFragment();
                        break;
                    default:
                        break;
                }
                fragment.setArguments(getIntent().getExtras());
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameid, fragment)
                        .commit();
                return true;
            }
        });*/

        //driver
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Dashboard", R.drawable.ic_home);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("History Pesanan", R.drawable.history);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Jadwal Saya",R.drawable.ic_schedule);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Review Pelanggan",R.drawable.review);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("Profil", R.drawable.profil);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);

        DriverHomeFragment fragment = new DriverHomeFragment();
        if (savedInstanceState == null){
            fragment.setArguments(getIntent().getExtras());
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameid, fragment)
                    .commit();
        }

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                Fragment fragment = null;
                switch (position){
                    case 0:
                        fragment = new DriverHomeFragment();
                        break;
                    case 1:
                        fragment = new HistoryPesananFragment();
                        break;
                    case 2:
                        fragment = new DriverJadwalFragment();
                        break;
                    case 3:
                        fragment = new AdminDriverReviewFragment();
                        break;
                    case 4:
                        fragment = new AdminDriverProfilFragment();
                        break;
                    default:
                        break;
                }
                fragment.setArguments(getIntent().getExtras());
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameid, fragment)
                        .commit();
                return true;
            }
        });
    }
}
