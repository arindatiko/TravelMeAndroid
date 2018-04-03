package arindatiko.example.com.travelme.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import arindatiko.example.com.travelme.DetailJadwalActivity;
import arindatiko.example.com.travelme.R;
import arindatiko.example.com.travelme.model.DriverChildJadwal;
import arindatiko.example.com.travelme.model.DriverParentJadwal;

/**
 * Created by arindatiko on 03/04/2018.
 */

//INI TRIAL

public class DriverJadwalAdapter extends RecyclerView.Adapter<DriverJadwalAdapter.My> {
    Context context;
    private ArrayList<DriverParentJadwal> parents;
    //List<DriverChildJadwal> child;


    public DriverJadwalAdapter(ArrayList<DriverParentJadwal> parenst) {
        this.parents = parents;
    }

    @Override
    public My onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_jadwal, parent, false);
        My vh = new My(v);

        return vh;
    }

    public class My extends RecyclerView.ViewHolder {
        TextView nama_cust, paket, id;
        ImageButton down;
        LinearLayout linear_child;

        public My(View view) {
            super(view);
            nama_cust = (TextView) view.findViewById(R.id.nama_customer);
            id = (TextView) view.findViewById(R.id.idPesanan);
            paket = (TextView) view.findViewById(R.id.txtPaket);
            linear_child = (LinearLayout) view.findViewById(R.id.linear_detail);
            down = (ImageButton) view.findViewById(R.id.btnDown);

            linear_child.setVisibility(View.GONE);
            int jumMaxChild = 0;
            for (int i = 0; i < parents.size(); i++) {
                int jumMaxSize = parents.get(i).getChildData().size();
                if (jumMaxSize > jumMaxChild)
                    jumMaxChild = jumMaxSize;
            }
            for (int i = 0; i < jumMaxChild; i++) {
                TextView textView = new TextView(context);
                textView.setId(i);
                textView.setPadding(0, 20, 0, 20);
                textView.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //down.setOnClickListener((View.OnClickListener) view.getContext());
                // textView.setOnClickListener(view.getContext());
                linear_child.addView(textView, layoutParams);
            }
            down.setOnClickListener((View.OnClickListener) view.getContext());
        }


        public void onClick(View view) {
            if (view.getId() == R.id.btnDown) {
                if (linear_child.getVisibility() == View.VISIBLE) {
                    linear_child.setVisibility(View.GONE);
                } else {
                    linear_child.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(context, "Tes", Toast.LENGTH_SHORT).show();

            }
        }
    }


    @Override
    public void onBindViewHolder(My holder, int position) {
        DriverParentJadwal parent = parents.get(position);

        holder.nama_cust.setText(parent.getNama());

        //menampilkan data child
        int noOfChildTextViews = holder.linear_child.getChildCount();
        int jumlahChild = parent.getChildData().size();
        if(jumlahChild < noOfChildTextViews){
            for(int i = jumlahChild; i < noOfChildTextViews; i++){
                TextView nama_tempat = (TextView) holder.linear_child.getChildAt(i);
                nama_tempat.setVisibility(View.GONE);
                TextView alamat = (TextView) holder.linear_child.getChildAt(i);
                alamat.setVisibility(View.GONE);
                TextView tgl = (TextView) holder.linear_child.getChildAt(i);
                tgl.setVisibility(View.GONE);
                TextView jam = (TextView) holder.linear_child.getChildAt(i);
                jam.setVisibility(View.GONE);
                TextView akses = (TextView) holder.linear_child.getChildAt(i);
                akses.setVisibility(View.GONE);
                ImageView img = (ImageView) holder.linear_child.getChildAt(i);
                img.setVisibility(View.GONE);
                Button btnSelesai = (Button) holder.linear_child.getChildAt(i);
                btnSelesai.setVisibility(View.GONE);
            }
        }
        for(int i = 0; i < jumlahChild; i++){
            TextView nama_tempat = (TextView) holder.linear_child.getChildAt(i);
            nama_tempat.setText(parent.getChildData().get(i).getNama());
            TextView alamat = (TextView) holder.linear_child.getChildAt(i);
            alamat.setText(parent.getChildData().get(i).getAlamat());
            TextView tgl = (TextView) holder.linear_child.getChildAt(i);
            tgl.setText(parent.getChildData().get(i).getTgl());
            TextView jam = (TextView) holder.linear_child.getChildAt(i);
            jam.setText(parent.getChildData().get(i).getJam());
            TextView akses = (TextView) holder.linear_child.getChildAt(i);
            akses.setText(parent.getChildData().get(i).getAkses());
            ImageView img = (ImageView) holder.linear_child.getChildAt(i);
            img.setImageResource(parent.getChildData().get(i).getImg());
            Button btnSelesai = (Button) holder.linear_child.getChildAt(i);
            btnSelesai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Selesai", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return parents.size();
    }
}
