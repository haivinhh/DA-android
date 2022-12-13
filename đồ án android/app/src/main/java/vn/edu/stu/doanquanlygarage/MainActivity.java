package vn.edu.stu.doanquanlygarage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends GenericActivity {
    CardView cvDSXe , cvHXe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        cvDSXe = findViewById(R.id.cvDSXe);
        cvHXe = findViewById(R.id.cvHXe);
    }

    private void addEvents() {
        cvDSXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, DSXeActivity.class);
                startActivity(intent);
            }
        });
        cvHXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, DSHangXeActivity.class);
                startActivity(intent);
            }
        });
    }


}