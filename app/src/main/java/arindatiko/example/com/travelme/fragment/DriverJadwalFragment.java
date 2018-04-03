package arindatiko.example.com.travelme.fragment;

import android.app.ActionBar;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import arindatiko.example.com.travelme.MainActivity;
import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.adapter.DriverJadwalAdapter;
import arindatiko.example.com.travelme.model.DriverChildJadwal;
import arindatiko.example.com.travelme.model.DriverParentJadwal;

public class DriverJadwalFragment extends Fragment {

    View v;
    RecyclerView rv_jadwal;

    public DriverJadwalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_jadwal_driver, container, false);
        DriverJadwalAdapter adapter = new DriverJadwalAdapter(getContext(), getDummyDataToPass());

        rv_jadwal.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_jadwal.setAdapter(adapter);
        return v;
    }
    
    public void setImage(int image){
        /*((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
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
