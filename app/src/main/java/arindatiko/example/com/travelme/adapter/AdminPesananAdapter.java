package arindatiko.example.com.travelme.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import arindatiko.example.com.travelme.OrderHotelActivity;
import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.model.PesananAdmin;

/**
 * Created by arindatiko on 18/03/2018.
 */

public class AdminPesananAdapter extends RecyclerView.Adapter<AdminPesananAdapter.MyHolderView> {
    Context context;
    List<PesananAdmin> orderList;

    public AdminPesananAdapter(Context context, List<PesananAdmin> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    public MyHolderView onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        MyHolderView vh = new MyHolderView(v);

        return vh;
    }

    public void onBindViewHolder(MyHolderView holder, int position){
        holder.nama.setText(orderList.get(position).getNama());
        holder.gambar.setImageResource(orderList.get(position).getFoto());
        holder.tgl_order.setText(orderList.get(position).getTgl_order());
        holder.waktu_order.setText(orderList.get(position).getWaktu_order());
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OrderHotelActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    public int getItemCount(){
        return orderList.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder{
        TextView nama, tgl_order, waktu_order;
        ImageView gambar;
        CardView card;
        Button btnDetail;

        public MyHolderView(View item){
            super(item);
            tgl_order = (TextView) item.findViewById(R.id.tgl_order);
            waktu_order = (TextView) item.findViewById(R.id.jam_order);
            nama = (TextView) item.findViewById(R.id.txtOrder);
            gambar = (ImageView) item.findViewById(R.id.foto_order);
            card = (CardView) item.findViewById(R.id.card_order);
            btnDetail = (Button) item.findViewById(R.id.btnDetail);
        }
    }
}
