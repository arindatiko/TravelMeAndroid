package arindatiko.example.com.travelme;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateHargaActivity extends AppCompatActivity {

    @BindView(R.id.btnParkir)
    CardView btnParkir;
    @BindView(R.id.btnMasuk)
    CardView btnMasuk;
    @BindView(R.id.btnKamar)
    CardView btnKamar;
    @BindView(R.id.btnMenu)
    CardView btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_harga);

        ButterKnife.bind(this);
    }

    @OnClick (R.id.btnParkir)
    public void toParkir (View v){
        Intent intent = new Intent(UpdateHargaActivity.this, ParkirActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick (R.id.btnMasuk)
    public void toMasuk (View v){
        Intent intent = new Intent(UpdateHargaActivity.this, MasukActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick (R.id.btnKamar)
    public void toKamar (View v){
        Intent intent = new Intent(UpdateHargaActivity.this, KamarActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick (R.id.btnMenu)
    public void toMenu (View v){
        Intent intent = new Intent(UpdateHargaActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
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
textView.setTe
        textView.setTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);
    }
*/
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

    public void onBackPressed(){
        Intent i = new Intent(UpdateHargaActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
