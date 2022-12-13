package vn.edu.stu.doanquanlygarage.adapter;

import android.app.Activity;
import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import vn.edu.stu.doanquanlygarage.R;
import vn.edu.stu.doanquanlygarage.model.HangXe;
import vn.edu.stu.doanquanlygarage.model.Xe;

public class adapterXe extends ArrayAdapter<Xe> {
    private Activity context;
    private int layout;
    private List<Xe> xeList;
    public adapterXe(Activity context, int resource, List<Xe> xeList) {
        super(context, resource, xeList);
        this.xeList=xeList;
        this.layout=resource;
        this.context=context;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(this.layout, null);
        TextView txtTenXe = view.findViewById(R.id.txtTenXe);
        TextView txtMau = view.findViewById(R.id.txtMau);
        TextView txtHangXe= view.findViewById(R.id.txtHangXe);
        ImageView ivHinhXe = view.findViewById(R.id.ivHinhXe);
        Xe xe = this.xeList.get(position);
        txtTenXe.setText(xe.getTenXe());
        HangXe hangXe =xe.getHangXe();
        if (hangXe != null)
            txtHangXe.setText(hangXe.getTenHang());
        txtMau.setText(xe.getMau()+"");
        if (xe.getHinh() != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(xe.getHinh(),0,xe.getHinh().length);
            ivHinhXe.setImageBitmap(bitmap);
        }
        return view;

    }
}
