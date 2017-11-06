package id.sch.smktelkom_mlg.learn.googlemaps_movingmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    static final CameraPosition TBN = CameraPosition.builder()
            .target(new LatLng(1.2836, 103.8604))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();
    static final CameraPosition PAPUA = CameraPosition.builder()
            .target(new LatLng(48.8584, 2.2945))
            .zoom(17)
            .bearing(0)
            .tilt(45)
            .build();
    static final CameraPosition BALI = CameraPosition.builder()
            .target(new LatLng(37.5665, 126.9780))
            .zoom(17)
            .bearing(0)
            .tilt(45)
            .build();
    static final CameraPosition JKT = CameraPosition.builder()
            .target(new LatLng(52.3702, 4.8952))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();

    GoogleMap m_map;
    boolean mapReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPapua = (Button) findViewById(R.id.btnPapua);
        btnPapua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(PAPUA);
            }
        });

        Button btnBali = (Button) findViewById(R.id.btnBali);
        btnBali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(BALI);
            }
        });

        Button btnJkt = (Button) findViewById(R.id.btnJkt);
        btnJkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(JKT);
            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        Toast toast = Toast.makeText(getApplicationContext(), "Map is Ready!", Toast.LENGTH_SHORT);
        toast.show();
        mapReady = true;
        m_map = map;
        m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        flyTo(TBN);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void flyTo(CameraPosition target) {
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 10000, null);
    }
}
