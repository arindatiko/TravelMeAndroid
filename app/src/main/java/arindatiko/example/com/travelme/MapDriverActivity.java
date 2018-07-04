package arindatiko.example.com.travelme;

import android.app.ActionBar;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import arindatiko.example.com.travelme.adapter.DriverPesananMasukAdapter;
import arindatiko.example.com.travelme.model.Customer;
import arindatiko.example.com.travelme.model.Drivers;
import arindatiko.example.com.travelme.model.PesananDriver;
import arindatiko.example.com.travelme.model.Tujuan;
import arindatiko.example.com.travelme.model.User;
import arindatiko.example.com.travelme.util.GPSTracker;
import arindatiko.example.com.travelme.util.SessionManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapDriverActivity extends AppCompatActivity implements OnMapReadyCallback{

    ArrayList<Marker> dataMarker;
    ArrayList<PesananDriver> ListPesanan;
    //ArrayList<Tujuan> tujuanArray;
    ArrayList<String> tujuanArray;

    /*@BindView(R.id.rv_order_driver)
    RecyclerView rv_order_driver;
    @BindView(R.id.linear_button)
    LinearLayout linear;*/
    @BindView(R.id.btnTerimaDriver)
    Button btnTerima;
    @BindView(R.id.btnTolakDriver)
    Button btnTolak;
    @BindView(R.id.txtCust)
    TextView txtNama;
    @BindView(R.id.txtIDCust)
    TextView txtIDUser;
    @BindView(R.id.txtNomer)
    TextView txtNomer;
    @BindView(R.id.txtnominal)
    TextView txtNominal;
    @BindView(R.id.linear_button)
    RelativeLayout linear;

    private LatLng myLocation, cust_location;
    private double lat, lng;
    private GoogleMap mMap;
    private DatabaseReference pesanan_db;
    private Marker marker;
    private GPSTracker gps;
    private String id_pesanan;
    private int id_driver, i=0;

    private Customer customer;
    private Drivers driver;
    private PesananDriver pesananDriver;
    SessionManager sessionManager;
    Double total;
    View v;

    //private PesananDriver pesanan = new PesananDriver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_driver);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(this);
        id_driver = Integer.parseInt(sessionManager.getId());

        API.service_post.get_user(id_driver).enqueue(new Callback<Drivers>() {
            @Override
            public void onResponse(Call<Drivers> call, Response<Drivers> response) {
                driver = response.body();

                //Toast.makeText(MapDriverActivity.this, ""+driver.getId_user(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Drivers> call, Throwable t) {

            }
        });

        /*List<PesananDriver> pesananDrivers = new ArrayList<>();
        pesananDrivers.add(new PesananDriver("Alice","92847329",
                "058457384733","Pantai Sanggar", "300.000",
                "Travel-Pay", R.drawable.valerie));

        //RecyclerView rv_order_driver = (RecyclerView) findViewById(R.id.rv_order_driver);
        DriverPesananMasukAdapter adapter = new DriverPesananMasukAdapter(this, pesananDrivers);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_order_driver.setLayoutManager(linearLayoutManager);
        rv_order_driver.setAdapter(adapter);*/

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getDataPesanan();
    }
    
    private void getDataPesanan() {
        pesanan_db = FirebaseDatabase.getInstance().getReference();

        final ValueEventListener dataListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot pesananSnapshot = dataSnapshot.child("pesanan");
                Iterable<DataSnapshot> pesananChildren = pesananSnapshot.getChildren();

                ListPesanan = new ArrayList<>();
                //marker = new Marker[10];
                dataMarker = new ArrayList<>();
                for (final DataSnapshot pesananA : pesananChildren) {
                    pesananDriver = pesananA.getValue(PesananDriver.class);
                    id_pesanan = pesananA.getKey();
                    //pesananDriver.setId_pesanan(Integer.parseInt(id_pesanan));

                    Log.d("lng", String.valueOf(pesananDriver.getPosisi_lng()));
                    Log.d("lat", String.valueOf(pesananDriver.getPosisi_lat()));
                    Log.d("id", id_pesanan);

                    DataSnapshot tujuanSnapshot = pesananA.child("tujuan");
                    Iterable<DataSnapshot> tujuanChildren = tujuanSnapshot.getChildren();
                    tujuanArray = new ArrayList<>();
                    for(DataSnapshot tujuan : tujuanChildren){
                        //Tujuan t = tujuan.getValue(Tujuan.class);
                        String t = tujuan.getValue(String.class);
                        //Log.d("id_tujuan", t);
                        tujuanArray.add(t);
                        //pesananDriver.setTujuan(tujuanArray);
                    }
                    DataSnapshot userSnapshot = pesananA.child("user");
                    customer = userSnapshot.getValue(Customer.class);
                    pesananDriver.setUser(customer);

                    //Log.d("id_user", customer.getNama_lengkap());
                    //mMap.setOnMarkerClickListener(MapDriverActivity.this);

                    //ListPesanan.add(pesananDriver);
                    cust_location = new LatLng(pesananDriver.getPosisi_lat(), pesananDriver.getPosisi_lng());

                    MarkerOptions m = new MarkerOptions()
                            .position(cust_location)
                            .title(customer.getNama_lengkap());


                    marker = mMap.addMarker(m);
                    marker.setTag(id_pesanan);
                    ListPesanan.add(pesananDriver);
                    //dataMarker.add(marker);
                    //Log.d("marker", String.valueOf(dataMarker.size()  ));

                    //dataMarker.add(marker);

                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            int tmp = 0;
                            for(int i =0; i<ListPesanan.size(); i++){
                                if(marker.getTag().toString().equals(String.valueOf(ListPesanan.get(i).getId_pesanan()))){
                                    //tmp = marker;
                                    tmp = i;
                                    break;
                                }
                            }

                            linear.setVisibility(View.VISIBLE);
                            txtNama.setText(ListPesanan.get(tmp).getUser().getNama_lengkap());
                            txtNomer.setText(ListPesanan.get(tmp).getUser().getNo_telp());
                            txtIDUser.setText(String.valueOf(ListPesanan.get(tmp).getId_pesanan()));
                            txtNominal.setText(ListPesanan.get(tmp).getTotal_budget().toString());

                            final String id_order = String.valueOf(ListPesanan.get(tmp).getId_pesanan());

                            btnTerima.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(MapDriverActivity.this, "coba", Toast.LENGTH_SHORT).show();
                                    //pesananDriver.setStatus(1);
                                    //id_driver = Integer.parseInt(sessionManager.getId());
                                    pesanan_db.child("pesanan").child(id_order).child("id_driver").setValue(id_driver);
                                    pesanan_db.child("pesanan").child(id_order).child("driver").setValue(driver);
                                    pesanan_db.child("pesanan").child(id_order).child("status").setValue(1);
                                    Toast.makeText(MapDriverActivity.this, "Pesanan Diterima", Toast.LENGTH_SHORT).show();
                                    //pesanan_db.child("pesanan");
                                }
                            });

                            btnTolak.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    linear.setVisibility(View.GONE);
                                    //Toast.makeText(MapDriverActivity.this, "uhuk", Toast.LENGTH_SHORT).show();
                                }
                            });
                            return false;
                        }
                    });

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(cust_location));
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        pesanan_db.addValueEventListener(dataListener);
    }


    private void getDriverLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        gps = new GPSTracker(MapDriverActivity.this);
        if(gps.canGetLocation()){
            lat = gps.getLatitude();
            lng = gps.getLongitude();
            myLocation = new LatLng(lat, lng);
        }

        mMap.setMyLocationEnabled(true);
        mMap.addMarker(new MarkerOptions()
                .position(myLocation)
                .anchor(0.5f, 0.5f)
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_driver))
                .title("Posisi Saya"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
    }

    protected Marker createMarker(double latitude, double longitude){
        return mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f));
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_customer))
                //.title(title));
    }

    /*@Override
    public boolean onMarkerClick(Marker marker) {
        *//*Integer click = (Integer) marker.getTag();

        if(click != null){
            click = click + 1;
            marker.setTag(click);
            *//**//*linear.setVisibility(View.VISIBLE);*//**//*
            Toast.makeText(MapDriverActivity.this, "tes", Toast.LENGTH_SHORT).show();
        }*//*

        if(marker.equals(cust_location)){

        }
        return false;
    }*/

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
