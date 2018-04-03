package arindatiko.example.com.travelme;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import arindatiko.example.com.travelme.adapter.AdminPesananAdapter;
import arindatiko.example.com.travelme.model.PesananAdmin;

public class AdminOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        List<PesananAdmin> pesanan = new ArrayList<>();
        pesanan.add(new PesananAdmin("Alice","23 Feb 2018","06.43 PM", R.drawable.alice));
        pesanan.add(new PesananAdmin("Valerie Agatha", "12 Maret 2018", "05.00 AM",R.drawable.valerie));
        pesanan.add(new PesananAdmin("Juki", "05 Januari 2018", "07.00 PM",R.drawable.juki));

        RecyclerView rv_order = (RecyclerView) findViewById(R.id.recycler_order);
        AdminPesananAdapter adapter = new AdminPesananAdapter(this, pesanan);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_order.setLayoutManager(linearLayoutManager);
        rv_order.setAdapter(adapter);
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
        setTitle("PesananAdmin Masuk");
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

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(AdminOrderActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
