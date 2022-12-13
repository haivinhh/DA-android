package vn.edu.stu.doanquanlygarage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import vn.edu.stu.doanquanlygarage.model.HangXe;
import vn.edu.stu.doanquanlygarage.model.Xe;
import vn.edu.stu.doanquanlygarage.utils.Data;

public class ThemDSXeActivity extends GenericActivity {
    EditText txtTenXe;
    EditText txtQGia;
    EditText txtSoCho;
    EditText txtMau;
    Spinner cbHang;
    ImageView imgXe;
    TextView tvThem;
    CardView cvBack, cvThemDSXe, cvChonHinh;
    ArrayAdapter<HangXe> adapter;
    Xe xeChon = null;
    int ma_xin_quyen_chon_anh = 53;
    HangXe hangXeChon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_them_dsxe);
        addControls();
        addEvents();
        loadData();

    }

    private void refresh(){
        txtTenXe.setText("");
        txtQGia.setText("");
        txtSoCho.setText("");
        txtMau.setText("");
        cbHang.setSelection(0);
        txtTenXe.requestFocus();
    }
    private void loadData() {
        List<HangXe> hangXeList = Data.init().getHangXeList();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, hangXeList);
        cbHang.setAdapter(adapter);
        Intent intent = getIntent();
        if (intent.hasExtra("xeSuaId")){
            int xeId = (intent.getIntExtra("xeSuaId",0));
            xeChon = Data.init().timXeTheoMa(xeId);
            txtTenXe.setText(xeChon.getTenXe());
            txtQGia.setText(xeChon.getQuocGia());
            txtSoCho.setText(xeChon.getSoCho()+"");
            txtMau.setText(xeChon.getMau());
            InputStream is = new ByteArrayInputStream(xeChon.getHinh());
            Bitmap bmp = BitmapFactory.decodeStream(is);
            imgXe.setImageBitmap(bmp);
            tvThem.setText(R.string.sua);
            int index = adapter.getPosition(xeChon.getHangXe());
            cbHang.setSelection(index);
        }
        if (hangXeList.size() > 0) {
            hangXeChon = hangXeList.get(0);
        }
    }

    private void addControls() {

        cvBack = findViewById(R.id.cvBack);
        cvThemDSXe = findViewById(R.id.cvThemDSXe);
        imgXe = findViewById(R.id.imgXe);
        cbHang = findViewById(R.id.cbHang);
        txtTenXe = findViewById(R.id.txtTenXe);
        txtQGia = findViewById(R.id.txtQGia);
        txtSoCho = findViewById(R.id.txtSoCho);
        txtMau = findViewById(R.id.txtMau);
        cvChonHinh = findViewById(R.id.cvChonHinh);
        tvThem=findViewById(R.id.tvThem);
    }

    private void addEvents() {
        cvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        cvThemDSXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLyThem();
            }
        });
        cbHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hangXeChon = adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        cvChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        ThemDSXeActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
                        , ma_xin_quyen_chon_anh
                );
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {
        if (requestCode == ma_xin_quyen_chon_anh) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, ma_xin_quyen_chon_anh);
            } else {
                Toast.makeText(getApplicationContext(), "ko co quyen truy cap vao anh cua dien thoai", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private byte[] chuyenHinh(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private void them(){

        try {
            int ma = ThreadLocalRandom.current().nextInt(1, 300000);
            String TenXe = txtTenXe.getText().toString();
            String QGia = txtQGia.getText().toString();
            String SoCho = txtSoCho.getText().toString();
            String Mau = txtMau.getText().toString();
            Bitmap bm=((BitmapDrawable)imgXe.getDrawable()).getBitmap();
            Xe xe = new Xe(ma, TenXe, Mau, Integer.valueOf(SoCho), chuyenHinh(bm), QGia);
            xe.addHangToXe(hangXeChon);
            Data.init().getXeList().add(xe);
            Toast.makeText(getApplicationContext(), R.string.add_succes, Toast.LENGTH_LONG).show();
            refresh();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), R.string.add_fail, Toast.LENGTH_LONG).show();
        }
    }

    private void XuLyThem() {
        if (xeChon == null){
            them();
        }else{
            sua();
        }


    }

    private void sua() {
        try {

            String TenXe = txtTenXe.getText().toString();
            String QGia = txtQGia.getText().toString();
            String SoCho = txtSoCho.getText().toString();
            String Mau = txtMau.getText().toString();
            Bitmap bm=((BitmapDrawable)imgXe.getDrawable()).getBitmap();
            xeChon.setTenXe(TenXe);
            xeChon.setQuocGia(QGia);
            xeChon.setSoCho(Integer.valueOf(SoCho));
            xeChon.setMau(Mau);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            xeChon.setHinh(byteArray);
            xeChon.addHangToXe(hangXeChon);

            Toast.makeText(getApplicationContext(), R.string.update_succes, Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), R.string.update_fail, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        if (requestCode == ma_xin_quyen_chon_anh && resultCode == RESULT_OK ) {

            try {
                InputStream stream = getContentResolver().openInputStream(data.getData());
                Bitmap decodeStream = BitmapFactory.decodeStream(stream);
                imgXe.setImageBitmap(decodeStream);


            } catch (Exception e) {

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}