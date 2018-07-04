package arindatiko.example.com.travelme;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import arindatiko.example.com.travelme.model.Kuliner;
import arindatiko.example.com.travelme.model.Penginapan;
import arindatiko.example.com.travelme.model.Wisata;
import arindatiko.example.com.travelme.util.SessionManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateInfoActivity extends AppCompatActivity{

    @BindView(R.id.pic)
    ImageView uploadPic;
    @BindView(R.id.txtUpload)
    TextView txtUpload;
    @BindView(R.id.txtNama)
    EditText txtNama;
    @BindView(R.id.txtAlamat)
    EditText txtAlamat;
    @BindView(R.id.txtDesc)
    EditText txtDesc;
    @BindView(R.id.txtFasilitas)
    EditText txtFasilitas;
    @BindView(R.id.txtAkses)
    EditText txtAkses;
    @BindView(R.id.txtLat)
    EditText txtLat;
    @BindView(R.id.txtLng)
    EditText txtLng;
    @BindView(R.id.txtNoTelp)
    EditText txtTelp;
    @BindView(R.id.txtJenis)
    EditText txtJenis;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;
    @BindView(R.id.jam_buka)
    Button openHour;
    @BindView(R.id.jam_tutup)
    Button closeHour;
    @BindView(R.id.scrollView2)
    ScrollView scroll;

    //layouting
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.title2)
    TextView title2;

    SessionManager sessionManager;
    private Kuliner kuliner;
    private Wisata wisata;
    private Penginapan hotel;
    ProgressDialog progressDialog;
    TimePickerDialog timeOpen, timeClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info_wisata);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(this);

        if(sessionManager.getUserType().equals("admin_wisata")){
            loadDataWisata();
        }else if(sessionManager.getUserType().equals("admin_kuliner")){
            loadDataKuliner();
        }else{
            loadDataHotel();
        }
    }

    public void loadDataWisata(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        //layoutWisata.setVisibility(View.VISIBLE);
        txtJenis.setVisibility(View.VISIBLE);
        title1.setVisibility(View.VISIBLE);
        openHour.setVisibility(View.VISIBLE);
        title2.setVisibility(View.VISIBLE);
        closeHour.setVisibility(View.VISIBLE);

        API.service_post.get_detail_wisata(sessionManager.getId()).enqueue(new Callback<Wisata>() {
            @Override
            public void onResponse(Call<Wisata> call, Response<Wisata> response) {
                wisata = response.body();

                txtNama.setText(wisata.getNama());

                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);
                try {
                    List<Address> addresses = geocoder.getFromLocation(wisata.getPosisi_lat(), wisata.getPosisi_lng(), 1);

                    if (addresses.size() > 0) {
                        Address fetchedAddress = addresses.get(0);
                        txtAlamat.setText(fetchedAddress.getAddressLine(0));
                    } else {
                        txtAlamat.setText("-");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    txtAlamat.setText("-");
                }

                txtDesc.setText(wisata.getDeskripsi());
                txtFasilitas.setText(wisata.getFasilitas());
                txtAkses.setText(wisata.getAkses());
                txtJenis.setText(wisata.getJenis());
                txtLat.setText(wisata.getPosisi_lat().toString());
                txtLng.setText(wisata.getPosisi_lng().toString());
                openHour.setText(wisata.getJam_buka());
                closeHour.setText(wisata.getJam_tutup());

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Wisata> call, Throwable t) {
                Toast.makeText(UpdateInfoActivity.this, "Periksa Koneksi Anda", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public void loadDataKuliner(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        //layoutLain.setVisibility(View.VISIBLE);
        title1.setVisibility(View.VISIBLE);
        openHour.setVisibility(View.VISIBLE);
        title2.setVisibility(View.VISIBLE);
        closeHour.setVisibility(View.VISIBLE);
        txtTelp.setVisibility(View.VISIBLE);

        API.service_post.detail_kuliner_admin(sessionManager.getId()).enqueue(new Callback<Kuliner>() {
            @Override
            public void onResponse(Call<Kuliner> call, Response<Kuliner> response) {
                kuliner = response.body();

                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);
                try {
                    List<Address> addresses = geocoder.getFromLocation(kuliner.getPosisi_lat(), kuliner.getPosisi_lng(), 1);

                    if (addresses.size() > 0) {
                        Address fetchedAddress = addresses.get(0);
                        txtAlamat.setText(fetchedAddress.getAddressLine(0));
                    } else {
                        txtAlamat.setText("-");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    txtAlamat.setText("-");
                }

                txtNama.setText(kuliner.getNama());

                txtLat.setText(kuliner.getPosisi_lat().toString());
                txtLng.setText(kuliner.getPosisi_lng().toString());
                txtDesc.setText(kuliner.getDeskripsi());
                txtAkses.setText(kuliner.getAkses());
                txtFasilitas.setText(kuliner.getFasilitas());
                txtTelp.setText(kuliner.getNo_telp());
                openHour.setText(kuliner.getJam_buka());
                closeHour.setText(kuliner.getJam_tutup());

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Kuliner> call, Throwable t) {
                Toast.makeText(UpdateInfoActivity.this, "Periksa Koneksi Anda", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public void loadDataHotel(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        //layoutLain.setVisibility(View.VISIBLE);
        txtTelp.setVisibility(View.VISIBLE);

        API.service_post.get_detail_penginapan(sessionManager.getId()).enqueue(new Callback<Penginapan>() {
            @Override
            public void onResponse(Call<Penginapan> call, Response<Penginapan> response) {
                hotel = response.body();

                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);
                try {
                    List<Address> addresses = geocoder.getFromLocation(hotel.getPosisi_lat(), hotel.getPosisi_lng(), 1);

                    if (addresses.size() > 0) {
                        Address fetchedAddress = addresses.get(0);
                        txtAlamat.setText(fetchedAddress.getAddressLine(0));
                    } else {
                        txtAlamat.setText("-");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    //Log.e("DetailKulinerActivity", e.getMessage());
                    txtAlamat.setText("-");
                }

                txtNama.setText(hotel.getNama());
                //txtAlamat.setText(kuliner.getAlamat());

                txtLat.setText(hotel.getPosisi_lat().toString());
                txtLng.setText(hotel.getPosisi_lng().toString());
                txtDesc.setText(hotel.getDeskripsi());
                txtAkses.setText(hotel.getAkses());
                txtFasilitas.setText(hotel.getFasilitas());
                txtTelp.setText(hotel.getNo_telp());

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Penginapan> call, Throwable t) {
                Toast.makeText(UpdateInfoActivity.this, "Periksa Koneksi Anda", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    @OnClick(R.id.btnSimpan)
    public void toUpdate(View view){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        if(sessionManager.getUserType().equals("admin_wisata")) {
            API.service_post.wisata_update(wisata.getId_wisata(),
                    txtNama.getText().toString(),
                    txtAlamat.getText().toString(),
                    txtDesc.getText().toString(),
                    txtAkses.getText().toString(),
                    txtJenis.getText().toString(),
                    txtFasilitas.getText().toString(),
                    Double.valueOf(txtLat.getText().toString()),
                    Double.valueOf(txtLng.getText().toString()),
                    openHour.getText().toString(),
                    closeHour.getText().toString()).enqueue(new Callback<Wisata>() {
                @Override
                public void onResponse(Call<Wisata> call, Response<Wisata> response) {
                    wisata = response.body();

                    txtNama.setText(wisata.getNama());
                    //txtAlamat.setText(kuliner.getAlamat());
                    txtLat.setText(wisata.getPosisi_lat().toString());
                    txtLng.setText(wisata.getPosisi_lng().toString());
                    txtDesc.setText(wisata.getDeskripsi());
                    txtAkses.setText(wisata.getAkses());
                    txtJenis.setText(wisata.getJenis());
                    txtFasilitas.setText(wisata.getFasilitas());
                    openHour.setText(wisata.getJam_buka());
                    closeHour.setText(wisata.getJam_tutup());

                    Toast.makeText(UpdateInfoActivity.this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<Wisata> call, Throwable t) {
                    Toast.makeText(UpdateInfoActivity.this, "Periksa Koneksi Anda", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }else if(sessionManager.getUserType().equals("admin_kuliner")){
            API.service_post.kuliner_update(kuliner.getId_kuliner(),
                    txtNama.getText().toString(),
                    txtAlamat.getText().toString(),
                    txtTelp.getText().toString(),
                    txtDesc.getText().toString(),
                    txtAkses.getText().toString(),
                    txtFasilitas.getText().toString(),
                    Double.valueOf(txtLat.getText().toString()),
                    Double.valueOf(txtLng.getText().toString()),
                    openHour.getText().toString(),
                    closeHour.getText().toString()).enqueue(new Callback<Kuliner>() {
                @Override
                public void onResponse(Call<Kuliner> call, Response<Kuliner> response) {
                    kuliner = response.body();

                    txtNama.setText(kuliner.getNama());
                    //txtAlamat.setText(kuliner.getAlamat());
                    txtLat.setText(kuliner.getPosisi_lat().toString());
                    txtLng.setText(kuliner.getPosisi_lng().toString());
                    txtDesc.setText(kuliner.getDeskripsi());
                    txtAkses.setText(kuliner.getAkses());
                    txtFasilitas.setText(kuliner.getFasilitas());
                    txtTelp.setText(kuliner.getNo_telp());
                    openHour.setText(kuliner.getJam_buka());
                    closeHour.setText(kuliner.getJam_tutup());
                    //kuliner.setJam_tutup(kuliner.getJam_tutup());
                /*setTimeClose();
                setTimeOpen();*/
                    Toast.makeText(UpdateInfoActivity.this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<Kuliner> call, Throwable t) {
                    Toast.makeText(UpdateInfoActivity.this, "Periksa Koneksi Anda", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }else{
            API.service_post.penginapan_update(hotel.getId_penginapan(),
                    txtNama.getText().toString(),
                    txtAlamat.getText().toString(),
                    txtTelp.getText().toString(),
                    txtDesc.getText().toString(),
                    txtAkses.getText().toString(),
                    txtFasilitas.getText().toString(),
                    Double.valueOf(txtLat.getText().toString()),
                    Double.valueOf(txtLng.getText().toString())).enqueue(new Callback<Penginapan>() {
                @Override
                public void onResponse(Call<Penginapan> call, Response<Penginapan> response) {
                    hotel = response.body();

                    txtNama.setText(hotel.getNama());
                    //txtAlamat.setText(kuliner.getAlamat());
                    txtLat.setText(hotel.getPosisi_lat().toString());
                    txtLng.setText(hotel.getPosisi_lng().toString());
                    txtDesc.setText(hotel.getDeskripsi());
                    txtAkses.setText(hotel.getAkses());
                    txtFasilitas.setText(hotel.getFasilitas());
                    txtTelp.setText(hotel.getNo_telp());
                    //kuliner.setJam_tutup(kuliner.getJam_tutup());
                /*setTimeClose();
                setTimeOpen();*/
                    Toast.makeText(UpdateInfoActivity.this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<Penginapan> call, Throwable t) {
                    Toast.makeText(UpdateInfoActivity.this, "Periksa Koneksi Anda", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }
    }

    public void setTimeOpen(View view){
        Calendar calendar = Calendar.getInstance();
        int hour_open = calendar.get(Calendar.HOUR);
        int minute_open = calendar.get(Calendar.MINUTE);

        timeOpen = new TimePickerDialog(UpdateInfoActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                openHour.setText(kuliner.getJam_buka());
//                kuliner.setJam_buka(kuliner.getJam_buka());
                //closeHour.setText(hourOfDay+" : "+minute);
                openHour.setText(String.valueOf(hourOfDay)+":"+String.valueOf(minute));
            }
        }, hour_open, minute_open, true);
        timeOpen.show();
    }

    public void setTimeClose(View view){
        Calendar calendar = Calendar.getInstance();
        int hour_open = calendar.get(Calendar.HOUR);
        int minute_open = calendar.get(Calendar.MINUTE);

        timeClose = new TimePickerDialog(UpdateInfoActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //closeHour.setText(kuliner.getJam_tutup());
//                kuliner.setJam_tutup(kuliner.getJam_tutup());
                //closeHour.setText(hourOfDay+" : "+minute);
                closeHour.setText(String.valueOf(hourOfDay)+":"+String.valueOf(minute));
            }
        }, hour_open, minute_open, true);
        timeClose.show();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(UpdateInfoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
