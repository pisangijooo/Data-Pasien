package com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class PasienAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<pasien> pasienList;
    private DatabaseHelper db;

    public PasienAdapter(Context context, ArrayList<pasien> pasienList) {
        this.context = context;
        this.pasienList = pasienList;
        db = new DatabaseHelper(context);
    }

    @Override
    public int getCount() {
        return pasienList.size();
    }

    @Override
    public Object getItem(int position) {
        return pasienList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView txtNama, txtDetail, txtRiwayatGejala, txtTanggal;
        Button btnEdit, btnDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        pasien p = pasienList.get(position);
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_pasien, parent, false);
            holder = new ViewHolder();
            holder.txtNama = convertView.findViewById(R.id.txtNama);
            holder.txtDetail = convertView.findViewById(R.id.txtDetail);
            holder.txtRiwayatGejala = convertView.findViewById(R.id.txtRiwayatGejala);
            holder.txtTanggal = convertView.findViewById(R.id.txtTanggal);
            holder.btnEdit = convertView.findViewById(R.id.btnEdit);
            holder.btnDelete = convertView.findViewById(R.id.btnDelete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Set text untuk setiap bagian
        holder.txtNama.setText(p.nama);
        holder.txtDetail.setText(p.email + " | " + p.jeniskelamin + " | " + p.alamat + " | " + p.goldarah);
        holder.txtRiwayatGejala.setText("Gejala: " + p.gejala + "\nRiwayat: " + p.riwayatpenyakit);
        holder.txtTanggal.setText("Tanggal Pendaftaran: " + p.tanggal);

        // Tombol Edit
        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, InputDataPasien.class);
            intent.putExtra("ID", p.id);
            intent.putExtra("NAMA", p.nama);
            intent.putExtra("EMAIL", p.email);
            intent.putExtra("JENISKELAMIN", p.jeniskelamin);
            intent.putExtra("ALAMAT", p.alamat);
            intent.putExtra("GOLDARAH", p.goldarah);
            intent.putExtra("GEJALA", p.gejala);
            intent.putExtra("RIWAYATPENYAKIT", p.riwayatpenyakit);
            intent.putExtra("TANGGAL", p.tanggal);
            context.startActivity(intent);
        });

        // Tombol Delete
        holder.btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Hapus Data")
                    .setMessage("Yakin ingin menghapus data ini?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        db.deleteData(p.id); // hapus dari database
                        pasienList.remove(position); // hapus dari list
                        notifyDataSetChanged(); // refresh tampilan
                        Toast.makeText(context, "Data dihapus", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Batal", null)
                    .show();
        });

        return convertView;
    }
}
