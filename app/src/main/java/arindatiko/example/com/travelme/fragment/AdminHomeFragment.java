package arindatiko.example.com.travelme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import arindatiko.example.com.travelme.KamarActivity;
import arindatiko.example.com.travelme.LoginActivity;
import arindatiko.example.com.travelme.AdminOrderActivity;
import arindatiko.example.com.travelme.MenuActivity;
import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.UpdateHargaActivity;
import arindatiko.example.com.travelme.UpdateInfoActivity;
import arindatiko.example.com.travelme.model.Kuliner;
import arindatiko.example.com.travelme.model.Penginapan;
import arindatiko.example.com.travelme.model.Wisata;
import arindatiko.example.com.travelme.util.SessionManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminHomeFragment extends Fragment {

    @BindView(R.id.txtNama)
    TextView txtNama;
    @BindView(R.id.txtJabatan)
    TextView txtJabatan;

    //admin wisata
    @BindView(R.id.btnHargaWisata)
    CardView btnHargaWisata;
    @BindView(R.id.btnPesananWisata)
    CardView btnPesananWisata;
    @BindView(R.id.btnInfoWisata)
    CardView btnInfoWisata;
    @BindView(R.id.layoutWisata)
    LinearLayout layoutWisata;

    //admin hotel+kuliner
    @BindView(R.id.layoutLain)
    LinearLayout layoutLain;
    @BindView(R.id.layoutLain2)
    LinearLayout layoutLain2;
    @BindView(R.id.btnHarga)
    CardView btnHarga;
    @BindView(R.id.btnPesanan)
    CardView btnPesanan;
    @BindView(R.id.btnInfo)
    CardView btnInfo;
    @BindView(R.id.btnKamar)
    CardView btnKamar;
    @BindView(R.id.btnMenu)
    CardView btnMenu;

    @BindView(R.id.btnLogout)
    Button btnLogout;

    SessionManager sessionManager;
    Wisata wisata;
    Kuliner kuliner;
    Penginapan hotel;

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

        sessionManager = new SessionManager(getActivity());
        txtNama.setText(sessionManager.getUsername());

        if(sessionManager.getUserType().equals("admin_wisata")){
            layoutWisata.setVisibility(View.VISIBLE);
        }else if(sessionManager.getUserType().equals("admin_kuliner")){
            layoutLain.setVisibility(View.VISIBLE);
            layoutLain2.setVisibility(View.VISIBLE);
            btnMenu.setVisibility(View.VISIBLE);
        }else{
            layoutLain.setVisibility(View.VISIBLE);
            layoutLain2.setVisibility(View.VISIBLE);
            btnKamar.setVisibility(View.VISIBLE);
        }

        return view;
    }

    //admin wisata
    @OnClick(R.id.btnHargaWisata)
    public void toUpdateHargaWisata(View view){
        Intent intent = new Intent(getActivity(), UpdateHargaActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.btnPesananWisata)
    public void toPesananWisata(View view){
        Intent intent = new Intent(getActivity(), AdminOrderActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.btnInfoWisata)
    public void toInfoWisata(View view){
        Intent intent = new Intent(getActivity(), UpdateInfoActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.btnHarga)
    public void toUpdateHarga(View view){
        Intent intent = new Intent(getActivity(), UpdateHargaActivity.class);
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
        Intent intent = new Intent(getActivity(), UpdateInfoActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.btnMenu)
    public void toMenu(View view){
        Intent intent = new Intent(getActivity(), MenuActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.btnKamar)
    public void toKamar(View view){
        Intent intent = new Intent(getActivity(), KamarActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.btnLogout)
    public void toLogout(View view){
//                Toast.makeText(this, String.valueOf(GlobalVariabel.getInstance().getChekcout()), Toast.LENGTH_SHORT).show();

            sessionManager.setLogin(false);
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
    }

    public void onResume(){
        super.onResume();
        //setTitle("Home");
        /*setImage(R.drawable.icon3);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setHasOptionsMenu(true);*/
    }

    /*@Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), UpdateHargaActivity.class);
        startActivity(intent);
    }*/
}
