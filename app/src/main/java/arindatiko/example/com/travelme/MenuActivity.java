package arindatiko.example.com.travelme;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toolbar;

import arindatiko.example.com.travelme.adapter.ViewPagerAdapter;
import arindatiko.example.com.travelme.fragment.MakananFragment;
import arindatiko.example.com.travelme.fragment.MinumanFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {

    /*@BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;*/
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    /*@BindView(R.id.btnMakanan)
    Button btnMakanan;
    @BindView(R.id.btnMinuman)
    Button btnMinuman;*/

    private ViewPagerAdapter adapter;
    private int[] tabIcons = {
            R.drawable.ic_food,
            R.drawable.ic_drinks
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

       // setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setupViewPager(viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new MakananFragment(), "Makanan");
        adapter.addFragment(new MinumanFragment(), "Minuman");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));

        setupTabIcons();

        /*final MakananFragment fragment = new MakananFragment();
        if(savedInstanceState==null){
            fragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(R.id.frame_menu,fragment).commit();
        }

        btnMakanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment1 = new MakananFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_menu, fragment1);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btnMinuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = new MinumanFragment();
                FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                transaction2.replace(R.id.frame_menu, fragment2);
                transaction2.addToBackStack(null);
                transaction2.commit();
            }
        });*/
    }



    private void setupTabIcons(){
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    public void setImage(int image){
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView imag = new ImageView(this);
        imag.setImageResource(image);
        imag.setLayoutParams(new LinearLayout.LayoutParams(550,80));
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
        Intent intent = new Intent(MenuActivity.this, UpdateHargaActivity.class);
        startActivity(intent);
        finish();
    }
}
