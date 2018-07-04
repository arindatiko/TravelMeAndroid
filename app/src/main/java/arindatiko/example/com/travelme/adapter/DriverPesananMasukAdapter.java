package arindatiko.example.com.travelme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.model.PesananDriver;

/**
 * Created by arindatiko on 01/04/2018.
 */

public class DriverPesananMasukAdapter {
//public class DriverPesananMasukAdapter extends RecyclerView.Adapter<DriverPesananMasukAdapter.Holder> {
    /*Context context;
    List<PesananDriver> orderList;

    public DriverPesananMasukAdapter(Context context, List<PesananDriver> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    public Holder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesanan_driver, parent, false);
        Holder vh = new Holder(v);

        return vh;
    }

    public int getItemCount(){
        return orderList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView nama, nominal, id, via, tempat, nomor;
        ImageView gambar;
        Button btnTerima, btnTolak;
        public Holder(View item){
            super(item);
            btnTerima = (Button) item.findViewById(R.id.btnTerimaDriver);
            btnTolak = (Button) item.findViewById(R.id.btnTolakDriver);
            nama = (TextView) item.findViewById(R.id.txtCust);
            nomor = (TextView) item.findViewById(R.id.txtNomer);
            id = (TextView) item.findViewById(R.id.txtIDCust);
            via = (TextView) item.findViewById(R.id.txtVia);
            tempat = (TextView) item.findViewById(R.id.txtObjek);
            nominal = (TextView) item.findViewById(R.id.txtnominal);
            gambar = (ImageView) item.findViewById(R.id.profil_cust);
        }
    }

    public void onBindViewHolder(Holder holder, int position){
        holder.nama.setText(orderList.get(position).getNama());
        holder.id.setText(orderList.get(position).getId());
        holder.nominal.setText(orderList.get(position).getHarga());
        holder.tempat.setText(orderList.get(position).getTempat());
        holder.via.setText(orderList.get(position).getVia());
        holder.nomor.setText(orderList.get(position).getNomor());
        holder.gambar.setImageResource(orderList.get(position).getFoto());

        holder.btnTerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Pesanan diterima", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Pesanan Ditolak", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
