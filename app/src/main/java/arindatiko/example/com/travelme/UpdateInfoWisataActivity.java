package arindatiko.example.com.travelme;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateInfoWisataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @BindView(R.id.jam_buka)
    Button openHour;
    @BindView(R.id.jam_tutup)
    Button closeHour;
    @BindView(R.id.scrollView2)
    ScrollView scroll;
    @BindView(R.id.spinner_akses)
    Spinner spinner;

    String akses[] = {"Pilih Akses", "Mudah", "Sulit"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info_wisata);
        ButterKnife.bind(this);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, akses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), akses[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    public void setTimeOpen(View view){
        Calendar calendar = Calendar.getInstance();
        int hour_open = calendar.get(Calendar.HOUR);
        int minute_open = calendar.get(Calendar.MINUTE);

        TimePickerDialog timeOpen;
        timeOpen = new TimePickerDialog(UpdateInfoWisataActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                openHour.setText(hourOfDay+" : "+minute);
                //closeHour.setText(hourOfDay+" : "+minute);
            }
        }, hour_open, minute_open, true);
        timeOpen.show();
    }

    public void setTimeClose(View view){
        Calendar calendar = Calendar.getInstance();
        int hour_open = calendar.get(Calendar.HOUR);
        int minute_open = calendar.get(Calendar.MINUTE);

        TimePickerDialog timeClose;
        timeClose = new TimePickerDialog(UpdateInfoWisataActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                closeHour.setText(hourOfDay+" : "+minute);
                //closeHour.setText(hourOfDay+" : "+minute);
            }
        }, hour_open, minute_open, true);
        timeClose.show();
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
        setTitle("Update Informasi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }*/

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

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(UpdateInfoWisataActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
