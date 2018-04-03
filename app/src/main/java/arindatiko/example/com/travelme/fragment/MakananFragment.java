package arindatiko.example.com.travelme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.adapter.HargaMakananAdapter;
import arindatiko.example.com.travelme.model.Makanan;
import butterknife.ButterKnife;

public class MakananFragment extends Fragment {

    RecyclerView rv_makanan;
    View v;
    private List<Makanan> makanan;

    public MakananFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        makanan = new ArrayList<>();
        makanan.add(new Makanan("Cenil","12.000",R.drawable.cenil));
        makanan.add(new Makanan("Gatot","10.000",R.drawable.gatot));
        makanan.add(new Makanan("Gethuk","15.000",R.drawable.gethuk));
        makanan.add(new Makanan("Geti","19.000",R.drawable.geti));
        makanan.add(new Makanan("Jenang Grendul","5.000",R.drawable.grendul));
        makanan.add(new Makanan("Kerupuk Rambak","120.000",R.drawable.rambak));
        makanan.add(new Makanan("Punten","10.000",R.drawable.punten));
        makanan.add(new Makanan("Geti","19.000",R.drawable.geti));
        makanan.add(new Makanan("Jenang Grendul","5.000",R.drawable.grendul));
        makanan.add(new Makanan("Kerupuk Rambak","120.000",R.drawable.rambak));
        makanan.add(new Makanan("Punten","10.000",R.drawable.punten));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ButterKnife.bind(this, v);

        v = inflater.inflate(R.layout.fragment_makanan, container, false);
        rv_makanan = (RecyclerView) v.findViewById(R.id.recycler_makanan);

        HargaMakananAdapter adapter = new HargaMakananAdapter(getContext(), makanan);
        rv_makanan.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_makanan.setAdapter(adapter);
        return v;
    }
}
