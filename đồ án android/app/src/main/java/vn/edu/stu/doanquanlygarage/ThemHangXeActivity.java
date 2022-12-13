package vn.edu.stu.doanquanlygarage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import vn.edu.stu.doanquanlygarage.model.HangXe;
import vn.edu.stu.doanquanlygarage.utils.Data;

public class ThemHangXeActivity extends GenericActivity {
    CardView cvBack,cvThemDSHXe;
    TextView txtTenHangXe;
    TextView txtThemHang;

    HangXe hangChon = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hang_xe);

        addControls();
        loadDataInIntent();
        addEvents();
    }

    private void loadDataInIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("hangId")){
            int id = intent.getIntExtra("hangId",-1);
            for (HangXe hangXe : Data.init().getHangXeList()){
                if (hangXe.getMa() == id){
                    hangChon = hangXe;
                    break;
                }
            }
            if (hangChon != null){
                txtTenHangXe.setText(hangChon.getTenHang());
                txtThemHang.setText(R.string.sua);
            }

        }

    }

    private void addControls() {
        txtThemHang = findViewById(R.id.txtThemHang);
        cvBack=findViewById(R.id.cvBack);
        cvThemDSHXe=findViewById(R.id.cvThemDSHXe);
        txtTenHangXe=findViewById(R.id.txtTenHangXe);
    }

    private void addEvents() {
        cvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
        cvThemDSHXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hangChon == null){
                    them();
                }else{
                    update();
                }

            }
        });
    }

    private void update() {
        try {
            String tenHang = txtTenHangXe.getText().toString();

            hangChon.setTenHang(txtTenHangXe.getText().toString());

            Toast.makeText(getApplicationContext(), R.string.update_succes, Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), R.string.update_fail, Toast.LENGTH_LONG).show();
        }
    }

    private  void them(){
        try {
            String tenHang = txtTenHangXe.getText().toString();
            int ma = ThreadLocalRandom.current().nextInt(1, 300000);
            HangXe hangXe = new HangXe(ma, tenHang);
            Data.init().getHangXeList().add(hangXe);
            Toast.makeText(getApplicationContext(), R.string.add_succes, Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), R.string.add_fail, Toast.LENGTH_LONG).show();
        }
    }

}