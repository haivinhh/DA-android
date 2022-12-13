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


import java.util.List;

import vn.edu.stu.doanquanlygarage.R;
import vn.edu.stu.doanquanlygarage.model.HangXe;
import vn.edu.stu.doanquanlygarage.model.Xe;

public class adapterHang extends ArrayAdapter<HangXe> {
    private Activity context;
    private int layout;
    private List<HangXe> hangXeList;

    public adapterHang( Activity context, int resource,  List<HangXe> hangXeList) {
        super(context, resource, hangXeList);
        this.hangXeList=hangXeList;
        this.layout=resource;
        this.context=context;
    }
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(this.layout, null);
        TextView txtTenHangXe = view.findViewById(R.id.txtTenHangXe);
        HangXe hangXe = this.hangXeList.get(position);
        txtTenHangXe.setText(hangXe.getTenHang());
        return view;
    }
}
