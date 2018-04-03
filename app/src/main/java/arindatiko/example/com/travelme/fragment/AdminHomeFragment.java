package arindatiko.example.com.travelme.fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import arindatiko.example.com.travelme.FasilitasActivity;
import arindatiko.example.com.travelme.MainActivity;
import arindatiko.example.com.travelme.AdminOrderActivity;
import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.UpdateHargaActivity;
import arindatiko.example.com.travelme.UpdateInfoWisataActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminHomeFragment extends Fragment {

    @BindView(R.id.btnHarga)
    CardView btnHarga;
    @BindView(R.id.btnFasilitas)
    CardView btnFasilitas;
    @BindView(R.id.btnPesanan)
    CardView btnPesanan;
    @BindView(R.id.btnInfo)
    CardView btnInfo;

    public AdminHomeFragment() {
        // Required empty public constructor
    }
    /*public static AdminHomeFragment newInstance() {
        return new AdminHomeFragment();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btnHarga)
    public void toUpdateHarga(View view){
        Intent intent = new Intent(getActivity(), UpdateHargaActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.btnFasilitas)
    public void toFasilitas(View view){
        Intent intent = new Intent(getActivity(), FasilitasActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.btnPesanan)
    public void toPesanan(View view){
        Intent intent = new Intent(getActivity(), AdminOrderActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.btnInfo)
    public void toInfo(View view){
        Intent intent = new Intent(getActivity(), UpdateInfoWisataActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    public void setImage(int image){
        ImageView imag = new ImageView(getActivity());
        imag.setImageResource(image);
        imag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,95));
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ((MainActivity) getActivity()).getSupportActionBar().setCustomView(imag);
    }

    /*public void setTitle(String title) {
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = new TextView(getActivity());
        textView.setText(title);/*((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView.setTextSize(20);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);

        textView.setTextColor(getResources().getColor(R.color.black));

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ((MainActivity) getActivity()).getSupportActionBar().setCustomView(textView);
    }*/

    public void onResume(){
        super.onResume();
        //setTitle("Home");
        setImage(R.drawable.icon3);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setHasOptionsMenu(true);
    }

    /*@Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), UpdateHargaActivity.class);
        startActivity(intent);
    }*/
}
