package arindatiko.example.com.travelme;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import arindatiko.example.com.travelme.adapter.FasilitasAdapter;
import arindatiko.example.com.travelme.model.Fasilitas;

public class FasilitasActivity extends AppCompatActivity {

    final Context context = this;
    private EditText output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasilitas);
        //ButterKnife.bind(this);

        List<Fasilitas> fasilitas = new ArrayList<>();
        fasilitas.add(new Fasilitas("Pakir", R.drawable.ic_parkir));
        fasilitas.add(new Fasilitas("Toilet", R.drawable.ic_toilet));
        fasilitas.add(new Fasilitas("Wi-fi", R.drawable.ic_wifi));
        fasilitas.add(new Fasilitas("Musholla", R.drawable.ic_mosque));

        RecyclerView rv_fasilitas= (RecyclerView) findViewById(R.id.rv_fasilitas);
        FasilitasAdapter adapter = new FasilitasAdapter(this, fasilitas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_fasilitas.setLayoutManager(linearLayoutManager);
        rv_fasilitas.setAdapter(adapter);

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_tambah_fasilitas);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btnSimpan = (Button) dialog.findViewById(R.id.btnSimpanFasilitas);
                EditText txtTambah = (EditText) dialog.findViewById(R.id.txtFasBaru);

                btnSimpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(FasilitasActivity.this, "Fasilitas telah ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                });

                txtTambah.setText("");
                dialog.show();

            }
        });
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

    /*public void setTitle(String title) {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(20);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);

        textView.setTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);
    }

    public void onResume(){
        super.onResume();
        setTitle("Update Fasilitas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public void onBackPressed(){
        Intent i = new Intent (FasilitasActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
