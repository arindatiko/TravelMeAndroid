package arindatiko.example.com.travelme;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import arindatiko.example.com.travelme.adapter.DriverPesananMasukAdapter;
import arindatiko.example.com.travelme.model.PesananDriver;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MapDriverActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    @BindView(R.id.rv_order_driver)
    RecyclerView rv_order_driver;
    @BindView(R.id.linear_button)
    LinearLayout linear;
    /*@BindView(R.id.btnTerimaDriver)
    CardView btnTerima;
    @BindView(R.id.btnTolakDriver)
    CardView btnTolak;*/

    private GoogleMap mMap;
    private Marker myMarker, myMarker2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_driver);
        ButterKnife.bind(this);

        List<PesananDriver> pesananDrivers = new ArrayList<>();
        pesananDrivers.add(new PesananDriver("Alice","92847329",
                "058457384733","Pantai Sanggar", "300.000",
                "Travel-Pay", R.drawable.valerie));

        //RecyclerView rv_order_driver = (RecyclerView) findViewById(R.id.rv_order_driver);
        DriverPesananMasukAdapter adapter = new DriverPesananMasukAdapter(this, pesananDrivers);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_order_driver.setLayoutManager(linearLayoutManager);
        rv_order_driver.setAdapter(adapter);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-7.323884, 112.740587);
        LatLng kl = new LatLng(-7.321883, 112.750521);

        myMarker = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker 1"));
        myMarker2 = mMap.addMarker(new MarkerOptions().position(kl).title("Marker 2"));
        mMap.setOnMarkerClickListener(this);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Integer click = (Integer) marker.getTag();

        if(click != null){
            click = click + 1;
            marker.setTag(click);
            linear.setVisibility(View.VISIBLE);
        }

        /*if(marker.equals(myMarker) && marker.equals(myMarker2)){
            linear.setVisibility(View.VISIBLE);
        }*/
        return false;
    }

    public void setImage(int image){
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView imag = new ImageView(this);
        imag.setImageResource(image);
        imag.setLayoutParams(new LinearLayout.LayoutParams(650,95));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(imag);
    }

    public void onResume(){
        super.onResume();
        setImage(R.drawable.icon3);
        //setTitle("Update Harga");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*setHasOptionsMenu(true);*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
