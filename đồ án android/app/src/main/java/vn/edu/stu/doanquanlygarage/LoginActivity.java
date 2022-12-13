package vn.edu.stu.doanquanlygarage;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import vn.edu.stu.doanquanlygarage.model.HangXe;
import vn.edu.stu.doanquanlygarage.model.Xe;
import vn.edu.stu.doanquanlygarage.utils.Data;

public class LoginActivity extends GenericActivity {
    EditText txtTK, txtPass;
    CardView cvLogin, cvLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DataMau();
        addControls();
        addEvents();
    }

    private void DataMau() {
        Data data = Data.init();
        List<HangXe> hangXeList = new ArrayList<>();
        List<Xe> xeList = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.glc);
        ByteArrayOutputStream image1 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,50,image1);
        byte[]glc=image1.toByteArray();
        Xe xe1 = new Xe(1,"GLC-300","Trắng",7,glc,"Đức");
        HangXe hang1 = new HangXe(1,"Mercedes");
        hang1.addXeToHang(xe1);

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.glc3);
        ByteArrayOutputStream image2 = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.PNG,50,image2);
        byte[]glc3=image2.toByteArray();

        Xe xe2 = new Xe(2,"GLC-350","Đen",7,glc3,"Đức");
        HangXe hang2 = new HangXe(1,"Toyota");
        hang2.addXeToHang(xe2);

        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.spy);
        ByteArrayOutputStream image3 = new ByteArrayOutputStream();
        bitmap3.compress(Bitmap.CompressFormat.PNG,50,image3);
        byte[]spy=image3.toByteArray();

        Xe xe3 = new Xe(3,"Spyder 911","Trắng",7,spy,"Đức");
        HangXe hang3 = new HangXe(1,"Porsche");
        hang3.addXeToHang(xe3);


        hangXeList.add(hang1);
        hangXeList.add(hang2);
        hangXeList.add(hang3);
        xeList.add(xe1);
        xeList.add(xe2);
        xeList.add(xe3);
        data.setHangXeList(hangXeList);
        data.setXeList(xeList);
    }

    private void addControls() {
        txtTK = findViewById(R.id.txtTK);
        txtPass = findViewById(R.id.txtPass);
        cvLogin = findViewById(R.id.cvLogin);
        cvLogout = findViewById(R.id.cvLogout);
    }

    private void addEvents() {
        cvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = "admin";
                String password = "admin";
                if (txtTK.getText().toString().equals(username) && txtPass.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), R.string.logintc, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.loginktc, Toast.LENGTH_LONG).show();
                }
            }
        });
        cvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}