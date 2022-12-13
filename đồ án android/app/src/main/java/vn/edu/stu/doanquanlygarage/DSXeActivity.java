package vn.edu.stu.doanquanlygarage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import vn.edu.stu.doanquanlygarage.adapter.adapterXe;
import vn.edu.stu.doanquanlygarage.model.Xe;
import vn.edu.stu.doanquanlygarage.utils.Data;

public class DSXeActivity extends GenericActivity {
    FloatingActionButton fabThemDSXe;
    ListView lvDSXe;


    adapterXe adapter;
    int requestCodeUpdate = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsxe);
        addControls();
        addEvents();
       loadData();
    }

    private void loadData() {
        List<Xe> xeList = Data.init().getXeList();
        adapter = new adapterXe(DSXeActivity.this,R.layout.custom_item_xe,xeList);
        lvDSXe.setAdapter(adapter);
    }

    private void addControls() {

        fabThemDSXe = findViewById(R.id.fabThemDSXe);
        lvDSXe = findViewById(R.id.lvDSXe);
    }

    private void addEvents() {
        fabThemDSXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(DSXeActivity.this,ThemDSXeActivity.class);
                startActivityForResult(intent,113);
            }
        });
        lvDSXe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Xe xe = adapter.getItem(i);

                Intent intent2 = new Intent(DSXeActivity.this,ThemDSXeActivity.class);
                intent2.putExtra("xeSuaId",xe.getMa());
                startActivityForResult(intent2,requestCodeUpdate);

            }
        });
        lvDSXe.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                XuLyXoa(i);
                return true;
            }
        });

    }

    private void XuLyXoa(int index) {
        String thongBao = "Bạn có chắc chắn muốn xóa không?";
        AlertDialog.Builder builder = new AlertDialog.Builder(DSXeActivity.this);
        builder.setTitle("Thông báo xóa");
        builder.setMessage(thongBao);
        builder.setIcon(R.drawable.attention);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    Xe xe = adapter.getItem(index);
                    for (Xe x : Data.init().getXeList()){
                        if (x.getMa() == xe.getMa()){
                            Data.init().getXeList().remove(xe);
                            x = null;
                            break;
                        }
                    }adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),R.string.delete_succes, Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),R.string.delete_fail, Toast.LENGTH_LONG).show();

                }
            }
        }).setNegativeButton("Không xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadData();
    }


}