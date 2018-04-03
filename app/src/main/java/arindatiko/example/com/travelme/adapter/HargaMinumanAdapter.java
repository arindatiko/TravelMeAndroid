package arindatiko.example.com.travelme.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.model.Minuman;

/**
 * Created by arindatiko on 17/03/2018.
 */

public class HargaMinumanAdapter extends RecyclerView.Adapter<HargaMinumanAdapter.MyViewHolder> {
    Context context;
    List<Minuman> data;
    Dialog dialog;

    public HargaMinumanAdapter(Context context, List<Minuman> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_makanan, parent, false);
        final MyViewHolder holder = new MyViewHolder(v);

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_harga);

        holder.list_makanan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView txtHargaSebelum = (TextView) dialog.findViewById(R.id.harga_sebelum);
                EditText txtHargaSesudah = (EditText) dialog.findViewById(R.id.harga_sesudah);

                txtHargaSebelum.setText(data.get(holder.getAdapterPosition()).getHarga());
                txtHargaSesudah.setText(data.get(holder.getAdapterPosition()).getHargaBaru());

                dialog.show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtNama.setText(data.get(position).getNama());
        holder.txtHarga.setText(data.get(position).getHarga());
        holder.imgFoto.setImageResource(data.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtHarga;
        private ImageView imgFoto;
        private LinearLayout list_makanan;

        public MyViewHolder(View itemView){
            super(itemView);

            list_makanan = (LinearLayout) itemView.findViewById(R.id.list_makanan);
            txtNama = (TextView) itemView.findViewById(R.id.nama_makanan);
            txtHarga = (TextView) itemView.findViewById(R.id.harga_makanan);
            imgFoto = (ImageView) itemView.findViewById(R.id.img_makanan);
        }
    }
}
