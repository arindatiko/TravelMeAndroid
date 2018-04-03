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
import arindatiko.example.com.travelme.adapter.HargaMinumanAdapter;
import arindatiko.example.com.travelme.model.Minuman;
import butterknife.ButterKnife;

public class MinumanFragment extends Fragment {

    RecyclerView rv_minuman;
    View v;
    private List<Minuman> minuman;

    public MinumanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        minuman = new ArrayList<>();
        minuman.add(new Minuman("Cenil","12.000",R.drawable.cenil));
        minuman.add(new Minuman("Gatot","10.000",R.drawable.gatot));
        minuman.add(new Minuman("Gethuk","15.000",R.drawable.gethuk));
        minuman.add(new Minuman("Geti","19.000",R.drawable.geti));
        minuman.add(new Minuman("Jenang Grendul","5.000",R.drawable.grendul));
        minuman.add(new Minuman("Kerupuk Rambak","120.000",R.drawable.rambak));
        minuman.add(new Minuman("Punten","10.000",R.drawable.punten));
        minuman.add(new Minuman("Geti","19.000",R.drawable.geti));
        minuman.add(new Minuman("Jenang Grendul","5.000",R.drawable.grendul));
        minuman.add(new Minuman("Kerupuk Rambak","120.000",R.drawable.rambak));
        minuman.add(new Minuman("Punten","10.000",R.drawable.punten));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ButterKnife.bind(this, v);

        v = inflater.inflate(R.layout.fragment_minuman, container, false);
        rv_minuman = (RecyclerView) v.findViewById(R.id.recycler_minuman);

        HargaMinumanAdapter adapter = new HargaMinumanAdapter(getContext(), minuman);
        rv_minuman.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_minuman.setAdapter(adapter);
        return v;
    }
}
