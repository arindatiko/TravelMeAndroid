package arindatiko.example.com.travelme.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.model.Fasilitas;

/**
 * Created by arindatiko on 18/03/2018.
 */

public class FasilitasAdapter extends RecyclerView.Adapter<FasilitasAdapter.HolderView> {

    Context context;
    List<Fasilitas> fasilitasList;

    public FasilitasAdapter(Context context, List<Fasilitas> fasilitasList) {
        this.context = context;
        this.fasilitasList = fasilitasList;
    }

    public HolderView onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fasilitas, parent, false);
        HolderView vh = new HolderView(v);
        return vh;
    }

    public void onBindViewHolder(HolderView holder, int position){
        holder.fasil.setText(fasilitasList.get(position).getNama());
        holder.gambar.setImageResource(fasilitasList.get(position).getImg());
    }

    public int getItemCount(){
        return fasilitasList.size();
    }

    public class HolderView extends RecyclerView.ViewHolder{
        TextView fasil;
        ImageView gambar;
        CardView card;

        public HolderView(View item){
            super(item);
            fasil = (TextView) item.findViewById(R.id.txtFasilitas);
            gambar = (ImageView) item.findViewById(R.id.icon_fasilitas);
            card = (CardView) item.findViewById(R.id.card_fasilitas);
        }
    }
}
