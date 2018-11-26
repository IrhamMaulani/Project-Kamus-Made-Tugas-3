package com.example.user.kamus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.kamus.R;
import com.example.user.kamus.model.KamusModel;

import java.util.ArrayList;

public class KamusAdapter extends RecyclerView.Adapter<KamusAdapter.KamusHolder> {

    private ArrayList<KamusModel> mData = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public KamusAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public KamusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kamus, parent, false);
        return new KamusHolder(view);
    }

    public void addItem(ArrayList<KamusModel> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(KamusHolder holder, int position) {
        holder.textKalimat.setText(mData.get(position).getKalimat());

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class KamusHolder extends RecyclerView.ViewHolder {
        private TextView textKalimat;
        public KamusHolder(View itemView) {
            super(itemView);
            textKalimat = (TextView) itemView.findViewById(R.id.text_kalimat);

        }
    }
}
