package arindatiko.example.com.travelme;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

import arindatiko.example.com.travelme.fragment.AdminDriverProfilFragment;
import arindatiko.example.com.travelme.fragment.AdminHomeFragment;
import arindatiko.example.com.travelme.fragment.DriverHomeFragment;
import arindatiko.example.com.travelme.fragment.HistoryPesananFragment;
import arindatiko.example.com.travelme.fragment.PesananFragment;
import arindatiko.example.com.travelme.util.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation_bottom)
    AHBottomNavigation navigation;

    SessionManager sessionManager;
    private String currentLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();

        sessionManager = new SessionManager(this);

        navigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        navigation.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));
        navigation.setAccentColor(Color.parseColor("#36A9E3"));
        navigation.setInactiveColor(Color.parseColor("#95989A"));

        if(sessionManager.getUserType().equals("driver")){
            AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home", R.drawable.ic_ai_home);
            AHBottomNavigationItem item2 = new AHBottomNavigationItem("Orders", R.drawable.ic_orders);
            AHBottomNavigationItem item3 = new AHBottomNavigationItem("Pick Order", R.drawable.ic_pickup);
            AHBottomNavigationItem item4 = new AHBottomNavigationItem("History Order", R.drawable.history);
            AHBottomNavigationItem item5 = new AHBottomNavigationItem("My Profile", R.drawable.ic_ai_account);

            navigation.addItem(item1);
            navigation.addItem(item2);
            navigation.addItem(item3);
            navigation.addItem(item4);
            navigation.addItem(item5);

            DriverHomeFragment fragment = new DriverHomeFragment();
            if(savedInstanceState == null){
                fragment.setArguments(getIntent().getExtras());
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.frame_container, fragment)
                        .commit();
            }

            navigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
                @Override
                public boolean onTabSelected(int position, boolean wasSelected) {
                    Fragment fragment = null;
                    switch (position) {
                        case 0:
                            fragment = new AdminHomeFragment();
                            break;
                        case 1:
                            fragment = new PesananFragment();
                            break;
                        case 2:
                            fragment = new DriverHomeFragment();
                            Intent intent = new Intent(MainActivity.this, MapDriverActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                            break;
                        case 3:
                            fragment = new HistoryPesananFragment();
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
                            .replace(R.id.frame_container, fragment)
                            .commit();
                    return true;
                }
            });
        }else{
            AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home", R.drawable.ic_home);
            AHBottomNavigationItem item2 = new AHBottomNavigationItem("History Pesanan", R.drawable.history);
            //AHBottomNavigationItem item3 = new AHBottomNavigationItem("Review Pelanggan",R.drawable.review);
            AHBottomNavigationItem item4 = new AHBottomNavigationItem("Profil", R.drawable.profil);
            navigation.addItem(item1);
            navigation.addItem(item2);
            //navigation.addItem(item3);
            navigation.addItem(item4);
            AdminHomeFragment fragment = new AdminHomeFragment();
            if (savedInstanceState == null){
                fragment.setArguments(getIntent().getExtras());
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.frame_container, fragment)
                        .commit();
            }
            navigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
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
                        /*case 2:
                            fragment = new AdminDriverReviewFragment();
                            break;*/
                        case 2:
                            fragment = new AdminDriverProfilFragment();
                            break;
                        default:
                            break;
                    }
                    fragment.setArguments(getIntent().getExtras());
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_container, fragment)
                            .commit();
                    return true;
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        exit(0);
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
