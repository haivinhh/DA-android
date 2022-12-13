package vn.edu.stu.doanquanlygarage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import vn.edu.stu.doanquanlygarage.adapter.adapterHang;
import vn.edu.stu.doanquanlygarage.adapter.adapterXe;
import vn.edu.stu.doanquanlygarage.model.HangXe;
import vn.edu.stu.doanquanlygarage.model.Xe;
import vn.edu.stu.doanquanlygarage.utils.Data;

public class DSHangXeActivity extends GenericActivity {
    FloatingActionButton fabThemDSHXe;
    ListView lvDSHXe;
    adapterHang adapter;
    int requestCodeUpdate = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dshang_xe);
        addControls();
        addEvents();
        loadData();
    }

    private void loadData() {
        List<HangXe> hangXeList = Data.init().getHangXeList();
        adapter = new adapterHang(DSHangXeActivity.this,R.layout.custom_item_hang,hangXeList);
        lvDSHXe.setAdapter(adapter);
    }

    private void addControls() {

        fabThemDSHXe = findViewById(R.id.fabThemDSHXe);
        lvDSHXe = findViewById(R.id.lvDSHXe);

    }

    private void addEvents() {
        fabThemDSHXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(DSHangXeActivity.this,ThemHangXeActivity.class);
                startActivityForResult(intent,113);
            }
        });
        lvDSHXe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(DSHangXeActivity.this,ThemHangXeActivity.class);
                intent.putExtra("hangId",adapter.getItem(i).getMa());
                startActivityForResult(intent,113);
            }
        });
        lvDSHXe.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                HangXe hangXe = adapter.getItem(i);
                int size = hangXe.getXeList().size();
                String dongThongBaoCoXe = size > 0
                        ?"Có" +size +" xe trong hãng này bạn có muốn xóa không ?"
                        :"Bạn có muốn xóa không ?";
                AlertDialog.Builder builder = new AlertDialog.Builder(DSHangXeActivity.this);
                builder.setTitle("Thông báo xóa");
                builder.setMessage(dongThongBaoCoXe);
                builder.setIcon(R.drawable.attention);

                builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        xoaNgay(hangXe);
                    }
                }).setNegativeButton("Không Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                return true;
            }
        });
    }

    private void xoaNgay(HangXe hangXe) {
        try{
            Data.init().getXeList().removeAll(hangXe.getXeList());
            Data.init().getHangXeList().remove(hangXe);
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(),R.string.delete_succes, Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),R.string.delete_fail, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadData();
    }
}