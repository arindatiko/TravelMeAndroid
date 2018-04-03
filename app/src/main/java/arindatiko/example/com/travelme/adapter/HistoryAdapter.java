package arindatiko.example.com.travelme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.model.History;

/**
 * Created by arindatiko on 27/03/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyHolder> {
    Context context;
    List<History> dataHistory;

    public HistoryAdapter(Context context, List<History> dataHistory) {
        this.context = context;
        this.dataHistory = dataHistory;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
        final  MyHolder holder = new MyHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.txtnama.setText(dataHistory.get(position).getNama());
        holder.foto.setImageResource(dataHistory.get(position).getProfil());
        holder.status.setText(dataHistory.get(position).getStatus());
        holder.tgl.setText(dataHistory.get(position).getTgl());

        /*holder.list_hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = Intent(this, OrderHotelActivity)
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return dataHistory.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{
        private TextView txtnama, tgl, status;
        private ImageView foto;
        private LinearLayout list_hist ;

        public MyHolder(View view){
            super(view);
            foto = (ImageView) view.findViewById(R.id.foto_profil);
            txtnama = (TextView) view.findViewById(R.id.nama);
            status = (TextView) view.findViewById(R.id.status_pesanan);
            tgl = (TextView) view.findViewById(R.id.tgl_selesai);
        }
    }
    /*Context context;
    String nameList[];
    int profil[];
    LayoutInflater inflater;

    public HistoryAdapter(Context applicationContext, String[] nameList, int[] profil) {
        this.context = context;
        this.nameList = nameList;
        this.profil = profil;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return nameList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_history, null);
        ImageView foto = (ImageView) convertView.findViewById(R.id.foto_profil);
        TextView nama = (TextView) convertView.findViewById(R.id.nama);
        TextView status = (TextView) convertView.findViewById(R.id.status_pesanan);
        TextView tgl = (TextView) convertView.findViewById(R.id.tgl_selesai);

        nama.setText(nameList[position]);
        foto.setImageResource(profil[position]);

        return convertView;
    }*/
}
