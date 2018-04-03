package arindatiko.example.com.travelme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.model.Review;

/**
 * Created by arindatiko on 03/04/2018.
 */

public class AdminDriverReviewAdapter extends RecyclerView.Adapter<AdminDriverReviewAdapter.HolderMy> {
        Context context;
        List<Review> list;

public AdminDriverReviewAdapter(Context context, List<Review> list) {
        this.context = context;
        this.list = list;
        }

@Override
public HolderMy onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        HolderMy vh = new HolderMy(v);

        return vh;
        }

public class HolderMy extends RecyclerView.ViewHolder{
    ImageView gambar;
    TextView nama, pesan, tgl;
    public HolderMy (View view){
        super(view);
        gambar = (ImageView) view.findViewById(R.id.img_cust);
        nama = (TextView) view.findViewById(R.id.nama_cust);
        tgl = (TextView) view.findViewById(R.id.tgl_review);
        pesan = (TextView) view.findViewById(R.id.txtReview);
    }
}


    @Override
    public void onBindViewHolder(AdminDriverReviewAdapter.HolderMy holder, int position) {
        holder.nama.setText(list.get(position).getNama());
        holder.tgl.setText(list.get(position).getTgl());
        holder.pesan.setText(list.get(position).getReview());
        holder.gambar.setImageResource(list.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
