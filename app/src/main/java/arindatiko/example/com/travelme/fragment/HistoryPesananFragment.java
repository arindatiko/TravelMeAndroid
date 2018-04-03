package arindatiko.example.com.travelme.fragment;

import android.app.ActionBar;
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
import arindatiko.example.com.travelme.adapter.HistoryAdapter;
import arindatiko.example.com.travelme.model.History;
import butterknife.ButterKnife;

public class HistoryPesananFragment extends Fragment {

    /*@BindView(R.id.list_history)*/
    RecyclerView rv_history;
    View v;
    /*String nameList[] = {"Vallerie Agatha", "Juki", "Alice"};
    int profil[]={R.drawable.valerie, R.drawable.juki, R.drawable.alice};*/
    private List<History> history;

    public HistoryPesananFragment(){}

    /*public static HistoryPesananFragment newInstance() {
        return new HistoryPesananFragment();
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        history = new ArrayList<>();
        history.add(new History("Alice","27 Maret 2018","Pesanan Selesai", R.drawable.alice));
        history.add(new History("Valerie","16 Maret 2018","Pesanan Ditolak", R.drawable.valerie));
        history.add(new History("Alice","27 Maret 2018","Pesanan Selesai", R.drawable.alice));
        history.add(new History("Valerie","16 Maret 2018","Pesanan Ditolak", R.drawable.valerie));
        history.add(new History("Juki","19 Maret 2018","Pesanan Sedang Berjalan",R.drawable.juki));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ButterKnife.bind(this, v);

        v = inflater.inflate(R.layout.fragment_history_pesanan, container, false);
        rv_history = (RecyclerView) v.findViewById(R.id.recycler_history);

        HistoryAdapter adapter = new HistoryAdapter(getContext(), history);
        rv_history.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_history.setAdapter(adapter);
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

   /* public void setTitle(String title) {
        *//*((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);*//*
        TextView textView = new TextView(getActivity());
        textView.setText(title);
        textView.setTextSize(20);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);

        textView.setTextColor(getResources().getColor(R.color.white));
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ((MainActivity) getActivity()).getSupportActionBar().setCustomView(textView);
    }

    public void onResume(){
        super.onResume();
        setTitle("History PesananAdmin");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        *//*setHasOptionsMenu(true);*//*
    }*/
}
