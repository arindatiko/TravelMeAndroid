package arindatiko.example.com.travelme.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import arindatiko.example.com.travelme.DetailTempatActivity;
import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.model.Kuliner;
import arindatiko.example.com.travelme.model.TempatWisata;

/**
 * Created by arindatiko on 09/04/2018.
 */

public class CustomerHomeKulinerAdapter extends RecyclerView.Adapter<CustomerHomeKulinerAdapter.MyHolder>{
    Context context;
    List<Kuliner> wisataList;

    public CustomerHomeKulinerAdapter(Context context, List<Kuliner> wisataList) {
        this.context = context;
        this.wisataList = wisataList;
    }

    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        MyHolder vh = new MyHolder(v);

        return vh;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        CardView cardItem;
        ImageView gambar;
        TextView judul, kat, alamat;

        public MyHolder(View item) {
            super(item);
            cardItem = (CardView) item.findViewById(R.id.itemWisata);
            gambar = (ImageView) item.findViewById(R.id.gambarItem);
            judul = (TextView) item.findViewById(R.id.txtAlam);
            alamat = (TextView) item.findViewById(R.id.detailTempat);
            kat = (TextView) item.findViewById(R.id.kategori);
        }
    }

    public void onBindViewHolder(MyHolder holder, int pos) {
        holder.judul.setText(wisataList.get(pos).getNama());
        //holder.gambar.setImageResource(wisataList.get(pos).getImg());
        holder.alamat.setText(wisataList.get(pos).getAlamat());
        holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(v.getContext(), DetailTempatActivity.class);
                v.getContext().startActivity(inten);
            }
        });
    }

    public int getItemCount() {
        return wisataList.size();
    }
}