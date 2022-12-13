package vn.edu.stu.doanquanlygarage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;

import java.util.jar.Manifest;

public class AboutActivity extends GenericActivity {

    private  static  final  int REQUEST_CALL =1;
    private CardView cvCall;
     MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        addControls();
        addEvents();


    }
    private void addControls() {

        cvCall =findViewById(R.id.cvCall);
        mapView = findViewById(R.id.mapView);
    }

    private void addEvents() {
        cvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0909412222"));
                startActivity(intent);
            }
        });
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                handleMap();
            }
        });
    }

    private void handleMap() {
        Point pointSTU = Point.fromLngLat(106.67772674813946, 10.738102290467015);

        AnnotationPlugin plugin = AnnotationPluginImplKt.getAnnotations(mapView);

        PointAnnotationManager manager = PointAnnotationManagerKt.createPointAnnotationManager(
                plugin,
                new AnnotationConfig()
        );
        PointAnnotationOptions optionsSTU = new PointAnnotationOptions()
                .withPoint(pointSTU).withIconImage(BitmapFactory.decodeResource(this.getResources(), R.drawable.locationpin));

        manager.create(optionsSTU);

        CameraOptions cameraOptions = new CameraOptions.Builder()
                .center(pointSTU)
                .zoom(17.0)
                .bearing(0.0)
                .pitch(0.0)
                .build();


        mapView.getMapboxMap().setCamera(cameraOptions);
    }




}