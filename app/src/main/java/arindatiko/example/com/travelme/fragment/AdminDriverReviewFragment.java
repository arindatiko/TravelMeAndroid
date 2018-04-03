package arindatiko.example.com.travelme.fragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import arindatiko.example.com.travelme.MainActivity;
import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.adapter.AdminDriverReviewAdapter;
import arindatiko.example.com.travelme.model.Review;

public class AdminDriverReviewFragment extends Fragment {

    RecyclerView rv_review;
    View v;
    private List<Review> review;

    public AdminDriverReviewFragment(){}

    /*public static AdminDriverReviewFragment newInstance() {
        return new AdminDriverReviewFragment();
    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.title,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        review = new ArrayList<>();
        review.add(new Review("Valerie Agatha", "bagus, cantik, keren, saya suka saya suka", "23 April 2018", R.drawable.valerie));
        review.add(new Review("Valerie Agatha", "bagus, cantik, keren, saya suka saya suka", "23 April 2018", R.drawable.valerie));
        review.add(new Review("Valerie Agatha", "bagus, cantik, keren, saya suka saya suka", "23 April 2018", R.drawable.valerie));
        review.add(new Review("Valerie Agatha", "bagus, cantik, keren, saya suka saya suka", "23 April 2018", R.drawable.valerie));
        review.add(new Review("Valerie Agatha", "bagus, cantik, keren, saya suka saya suka", "23 April 2018", R.drawable.valerie));
        review.add(new Review("Valerie Agatha", "bagus, cantik, keren, saya suka saya suka", "23 April 2018", R.drawable.valerie));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_review_pelanggan, container, false);
        rv_review = (RecyclerView) v.findViewById(R.id.rv_review);

        AdminDriverReviewAdapter adapter = new AdminDriverReviewAdapter(getContext(), review);
        rv_review.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_review.setAdapter(adapter);
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

    /*public void setTitle(String title) {
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
        setTitle("Review Pelanggan");
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        *//*setHasOptionsMenu(true);*//*
    }*/
}
