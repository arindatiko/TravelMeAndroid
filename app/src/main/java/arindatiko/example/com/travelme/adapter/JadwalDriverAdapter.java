package arindatiko.example.com.travelme.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import arindatiko.example.com.travelme.DetailJadwalActivity;
import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.model.DriverJadwal;
import arindatiko.example.com.travelme.model.DriverParentJadwal;
import arindatiko.example.com.travelme.model.JadwalDriver;

/**
 * Created by arindatiko on 03/04/2018.
 */

public class JadwalDriverAdapter extends RecyclerView.Adapter<JadwalDriverAdapter.MyView> {
    Context context;
    List<DriverJadwal> list;

    public JadwalDriverAdapter(Context context, List<DriverJadwal> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal, parent, false);
        MyView vh = new MyView(v);

        return vh;
    }

    public class MyView extends RecyclerView.ViewHolder {
        TextView nama_cust, nama_tempat, alamat, tgl, akses, jam, paket, idpes;
        ImageButton down;
        LinearLayout linear_child;
        ImageView img;
        Button btnSelesai;

        public MyView(View view) {
            super(view);
            nama_tempat = (TextView) view.findViewById(R.id.nama_tempat);
            alamat = (TextView) view.findViewById(R.id.alamat);
            tgl = (TextView) view.findViewById(R.id.tgl);
            akses = (TextView) view.findViewById(R.id.akses);
            jam = (TextView) view.findViewById(R.id.jam);
            nama_cust = (TextView) view.findViewById(R.id.nama_customer);
            idpes = (TextView) view.findViewById(R.id.idPesanan);
            paket = (TextView) view.findViewById(R.id.txtPaket);
            linear_child = (LinearLayout) view.findViewById(R.id.linear_detail);
            down = (ImageButton) view.findViewById(R.id.btnDown);
            btnSelesai = (Button) view.findViewById(R.id.btnSelesai);
            img = (ImageView) view.findViewById(R.id.gambar_tempat);
        }
    }


    @Override
    public void onBindViewHolder(MyView holder, int position) {
        holder.nama_cust.setText(list.get(position).getNama());
        holder.paket.setText(list.get(position).getPaket());
        holder.idpes.setText(list.get(position).getId());
        holder.nama_cust.setText(list.get(position).getNama());
        holder.paket.setText(list.get(position).getPaket());
        holder.idpes.setText(list.get(position).getId());
        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   linear_child.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
