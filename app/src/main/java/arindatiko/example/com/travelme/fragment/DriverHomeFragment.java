package arindatiko.example.com.travelme.fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import arindatiko.example.com.travelme.MainActivity;
import arindatiko.example.com.travelme.MapDriverActivity;
import arindatiko.example.com.travelme.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverHomeFragment extends Fragment {

    @BindView(R.id.btnSwitch)
    Switch btnSwitch;
    @BindView(R.id.status_driver)
    TextView status_driver;
    @BindView(R.id.btnAmbil)
    CardView btnAmbil;
    @BindView(R.id.nama_driver)
    TextView nama_driver;

    String status1, status2;
    public DriverHomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_driver, container, false);
        ButterKnife.bind(this, v);

        btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton button, boolean isChecked){
                if(isChecked)
                    btnSwitch.getTextOn();
                else
                    btnSwitch.getTextOff();
            }
        });

        return v;
    }

    @OnClick (R.id.btnAmbil)
    public void toMap (View v){
        Intent i = new Intent(getActivity(), MapDriverActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    public void setImage(int image){
        ImageView imag = new ImageView(getActivity());
        imag.setImageResource(image);
        imag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,95));
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ((MainActivity) getActivity()).getSupportActionBar().setCustomView(imag);
    }

    public void onResume(){
        super.onResume();
        //setTitle("Home");
        setImage(R.drawable.icon3);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setHasOptionsMenu(true);
    }
}
