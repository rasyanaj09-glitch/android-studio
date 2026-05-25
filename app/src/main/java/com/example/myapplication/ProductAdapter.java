package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton; // Tambahkan import ini
import android.widget.TextView;
import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<roduct> productList;
    private OnProductActionListener actionListener;

    public interface OnProductActionListener {
        void onItemClick(roduct product); // Fitur klik per baris
        void onEdit(roduct product);
        void onDelete(roduct product, int position);
    }

    public ProductAdapter(Context context, ArrayList<roduct> productList, OnProductActionListener actionListener) {
        this.context = context;
        this.productList = productList;
        this.actionListener = actionListener;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        }

        roduct product = productList.get(position);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        TextView tvStock = convertView.findViewById(R.id.tvStock);


        ImageButton btnEditRow = convertView.findViewById(R.id.btnEditRow);
        ImageButton btnDeleteRow = convertView.findViewById(R.id.btnDeleteRow);

        tvName.setText(product.getName());
        tvPrice.setText("Harga: Rp " + product.getPrice());
        tvStock.setText("Stok: " + product.getStock());


        convertView.setOnClickListener(v -> actionListener.onItemClick(product));


        btnEditRow.setOnClickListener(v -> actionListener.onEdit(product));
        btnDeleteRow.setOnClickListener(v -> actionListener.onDelete(product, position));

        return convertView;
    }
}
