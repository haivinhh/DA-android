package vn.edu.stu.doanquanlygarage;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class GenericActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.btnAbout){
            Intent intent =new Intent(this,AboutActivity.class);
            startActivity(intent);
            return true;
        }
        else {
            if (id==R.id.btnBack){
                finish();
            }
            else{
                if(id ==R.id.btnExit){
                    finishAffinity();
                }
            }
        }

        return  super.onOptionsItemSelected(item);
    }
}
