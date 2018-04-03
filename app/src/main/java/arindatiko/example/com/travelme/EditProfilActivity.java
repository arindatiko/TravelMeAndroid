package arindatiko.example.com.travelme;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class EditProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
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

    public void onBackPressed(){
        Intent i = new Intent(EditProfilActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
