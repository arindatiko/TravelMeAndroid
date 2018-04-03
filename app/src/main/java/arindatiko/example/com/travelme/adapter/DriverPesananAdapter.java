package arindatiko.example.com.travelme.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import arindatiko.example.com.travelme.DetailJadwalActivity;
import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.model.JadwalDriver;

/**
 * Created by arindatiko on 02/04/2018.
 */

public class DriverPesananAdapter extends RecyclerView.Adapter<DriverPesananAdapter.MyView> {
    Context context;
    List<JadwalDriver> list;

    public DriverPesananAdapter(Context context, List<JadwalDriver> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal, parent, false);
        MyView vh = new MyView(v);

        return vh;
    }

    public class MyView extends RecyclerView.ViewHolder{
        ImageView gambar;
        TextView nama, paket, bayar, tgl, jam;
        ImageButton next;
        public MyView (View view){
            super(view);
            gambar = (ImageView) view.findViewById(R.id.profil_customer);
            nama = (TextView) view.findViewById(R.id.nama_customer);
            paket = (TextView) view.findViewById(R.id.txtPaket);
            bayar = (TextView) view.findViewById(R.id.txtBayar);
            tgl = (TextView) view.findViewById(R.id.txtTanggal);
            jam = (TextView) view.findViewById(R.id.txtWaktu);
            next = (ImageButton) view.findViewById(R.id.btnNext);
        }
    }


    @Override
    public void onBindViewHolder(DriverPesananAdapter.MyView holder, int position) {
        holder.nama.setText(list.get(position).getNama());
        holder.paket.setText(list.get(position).getPaket());
        holder.bayar.setText(list.get(position).getBayar());
        holder.tgl.setText(list.get(position).getTgl());
        holder.jam.setText(list.get(position).getWaktu());
        holder.gambar.setImageResource(list.get(position).getGambar());
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DetailJadwalActivity.class);
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
