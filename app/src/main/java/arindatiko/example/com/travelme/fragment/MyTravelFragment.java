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
import java.util.List;

import arindatiko.example.com.travelme.MainActivity;
import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.adapter.CustomerHomeKulinerAdapter;
import arindatiko.example.com.travelme.adapter.CustomerHomePenginapanAdapter;
import arindatiko.example.com.travelme.adapter.CustomerHomeWisataAdapter;
import arindatiko.example.com.travelme.model.Kuliner;
import arindatiko.example.com.travelme.model.Penginapan;
import arindatiko.example.com.travelme.model.TempatWisata;

public class MyTravelFragment extends Fragment {
    RecyclerView rv_home1;
    View v;

    private List<TempatWisata> home;
    TempatWisata wisata;
    private List<Penginapan> hotel;
    Penginapan penginapan;
    private List<Kuliner> kuliner;
    Kuliner restoran;

    public MyTravelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        home = new ArrayList<>();
        home.add(new TempatWisata("Pantai Sanggar", "Desa Tanggunggunung, Kecamatan Tanggunggunung, Kabupaten Tulungagung", R.drawable.banyumulok));
        home.add(new TempatWisata("Pantai Sanggar", "Desa Tanggunggunung, Kecamatan Tanggunggunung, Kabupaten Tulungagung", R.drawable.lawean));
        home.add(new TempatWisata("Pantai Sanggar", "Desa Tanggunggunung, Kecamatan Tanggunggunung, Kabupaten Tulungagung", R.drawable.budeg));
        home.add(new TempatWisata("Pantai Sanggar", "Desa Tanggunggunung, Kecamatan Tanggunggunung, Kabupaten Tulungagung", R.drawable.sanggar));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_my_travel, container, false);

        rv_home1 = (RecyclerView) v.findViewById(R.id.rv_travel);
        CustomerHomeWisataAdapter adapter = new CustomerHomeWisataAdapter(getContext(), home);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv_home1.setLayoutManager(linearLayoutManager);
        rv_home1.setAdapter(adapter);

        return v;
    }

    /*public void setImage(int image){
        *//*((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);*//*
        ImageView imag = new ImageView(getActivity());
        imag.setImageResource(image);
        imag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,95));
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ((MainActivity) getActivity()).getSupportActionBar().setCustomView(imag);
    }*/

    public void onResume(){
        super.onResume();
        //setTitle("Home");
        /*setImage(R.drawable.icon3);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setHasOptionsMenu(true);*/
    }
}
