package arindatiko.example.com.travelme;

import android.app.ActionBar;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

import com.google.android.gms.location.LocationServices;
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
import java.util.Map;

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

public class MapDriverActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    ArrayList<Marker> dataMarker;
    ArrayList<PesananDriver> ListPesanan = new ArrayList<>();
    ArrayList<Tujuan> tujuanArray;
    //ArrayList<String> tujuanArray;

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
    private int id_driver, j=0;

    private Customer customer;
    private Drivers driver;
    SessionManager sessionManager;
    private LocationManager locationManager;
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

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

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

        //getDataPesanan();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        gps = new GPSTracker(MapDriverActivity.this);
        if(gps.canGetLocation()){
            lat = gps.getLatitude();
            lng = gps.getLongitude();
            myLocation = new LatLng(lat, lng);
        }else{
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }

        mMap.setMyLocationEnabled(true);

        getPesanan();
    }

    /*private void getDataPesanan() {
        pesanan_db = FirebaseDatabase.getInstance().getReference();

        final ValueEventListener dataListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot pesananSnapshot = dataSnapshot.child("pesanan");
                Iterable<DataSnapshot> pesananChildren = pesananSnapshot.getChildren();

                ListPesanan = new ArrayList<>();
                dataMarker = new ArrayList<>();
                for (DataSnapshot pesananA : pesananChildren) {
                    id_pesanan = pesananA.getKey();
                    PesananDriver pesananDriver = pesananA.getValue(PesananDriver.class);
                    //Map<String, PesananDriver> pesananDriver = (Map<String, PesananDriver>) pesananA.getValue();
                    //PesananDriver pesananDriver = pesananA.getValue(PesananDriver.class);
                    //pesananDriver.setId_pesanan(Integer.parseInt(id_pesanan));

                    Log.d("cek", pesananDriver.toString());
                    //Log.d("cek", pesananDriver.get(id_pesanan).toString());

                    Log.d("lng", String.valueOf(pesananDriver.getPosisi_lng()));
                    Log.d("lat", String.valueOf(pesananDriver.getPosisi_lat()));
                    Log.d("id", id_pesanan);

                    DataSnapshot tujuanSnapshot = pesananA.child("tujuan");
                    Iterable<DataSnapshot> tujuanChildren = tujuanSnapshot.getChildren();
                    tujuanArray = new ArrayList<>();
                    for(DataSnapshot tujuan : tujuanChildren){
                        Tujuan t = tujuan.getValue(Tujuan.class);
                        //String t = tujuan.getValue(String.class);
                        //Log.d("id_tujuan", t);
                        tujuanArray.add(t);
                        String id_tujuan = tujuan.getKey();
                        //pesananDriver.setTujuan(tujuanArray);
                    }
                    DataSnapshot userSnapshot = pesananA.child("user");
                    customer = userSnapshot.getValue(Customer.class);
                    //pesananDriver.put("user", customer);
                    pesananDriver.setUser(customer);

                    cust_location = new LatLng(pesananDriver.getPosisi_lat(), pesananDriver.getPosisi_lng());

                    MarkerOptions m = new MarkerOptions()
                            .position(cust_location)
                            .title(customer.getNama_lengkap());


                    marker = mMap.addMarker(m);
                    marker.setTag(id_pesanan);
                    ListPesanan.add(pesananDriver);

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
                                    getDriverLocation();

                                    pesanan_db.child("pesanan").child(id_order).child("id_driver").setValue(id_driver);
                                    pesanan_db.child("pesanan").child(id_order).child("driver").setValue(driver);
                                    pesanan_db.child("pesanan").child(id_order).child("status").setValue(1);
                                    Toast.makeText(MapDriverActivity.this, "Pesanan Diterima", Toast.LENGTH_SHORT).show();
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
    }*/

    private void getPesanan(){
        pesanan_db = FirebaseDatabase.getInstance().getReference().child("pesanan");

        pesanan_db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, @Nullable String s) {
                final PesananDriver pesananDriver = dataSnapshot.getValue(PesananDriver.class);
                Log.d("coba", dataSnapshot.getValue().toString());

                final String id_pesanan = dataSnapshot.getKey();
                ListPesanan.add(pesananDriver);

                Log.d("id", String.valueOf(pesananDriver.getId_pesanan()));

                for (int i = 0; i < ListPesanan.size(); i++) {

                }
                cust_location = new LatLng(pesananDriver.getPosisi_lat(), pesananDriver.getPosisi_lng());
                MarkerOptions m = new MarkerOptions()
                        .position(cust_location);
                marker = mMap.addMarker(m);
                marker.setTag(id_pesanan);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(cust_location));

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        int tmp = 0;
                        for(int i = 0; i < ListPesanan.size(); i++){
                            if(marker.getTag().equals(String.valueOf(ListPesanan.get(i).getId_pesanan()))) {
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
                                dataSnapshot.getRef().child("id_driver").setValue(id_driver);
                                dataSnapshot.getRef().child("driver").setValue(driver);
                                dataSnapshot.getRef().child("driver").child("posisi_lat").setValue(lat);
                                dataSnapshot.getRef().child("driver").child("posisi_lng").setValue(lng);
                                dataSnapshot.getRef().child("status").setValue(1);
                                //dataSnapshot.getRef().removeValue();

                                API.service_post.update_driver(id_pesanan, id_driver).enqueue(new Callback<PesananDriver>() {
                                    @Override
                                    public void onResponse(Call<PesananDriver> call, Response<PesananDriver> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<PesananDriver> call, Throwable t) {

                                    }
                                });
                                Toast.makeText(MapDriverActivity.this, "Pesanan Diterima", Toast.LENGTH_SHORT).show();
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

                /*ListPesanan = new ArrayList<>();
                ListPesanan.add(pesananDriver);

                for (j = 0; j < ListPesanan.size(); j++) {
                    //String id_pesanan = String.valueOf(ListPesanan.get(j).getId_pesanan());

                    Log.d("id", id_pesanan);
                    cust_location = new LatLng(ListPesanan.get(j).getPosisi_lat(), ListPesanan.get(j).getPosisi_lng());
                    MarkerOptions m = new MarkerOptions()
                            .position(cust_location);
                    marker = mMap.addMarker(m);
                    marker.setTag(id_pesanan);

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
                                    dataSnapshot.getRef().child("id_driver").setValue(id_driver);
                                    dataSnapshot.getRef().child("driver").setValue(driver);
                                    dataSnapshot.getRef().child("status").setValue(1);
                                    *//*pesanan_db.child("pesanan").child(id_order).child("id_driver").setValue(id_driver);
                                    pesanan_db.child("pesanan").child(id_order).child("driver").setValue(driver);
                                    pesanan_db.child("pesanan").child(id_order).child("status").setValue(1);*//*
                                    Toast.makeText(MapDriverActivity.this, "Pesanan Diterima", Toast.LENGTH_SHORT).show();
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
                }*/
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getDriverLocation() {

    }

    protected Marker createMarker(double latitude, double longitude){
        return mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f));
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_customer))
                //.title(title));
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

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
