package org.utl.mygps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2, et3;
    Button b1, b2, b3, b4;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarControles();
    }

    private void iniciarControles() {
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        b1 = (Button) findViewById(R.id.btnver);
        b2 = (Button) findViewById(R.id.btngmail);
        b3 = (Button) findViewById(R.id.btnmsg);
        b4 = (Button) findViewById(R.id.btnsalir);
        //------------------------Administrador y escucha GPS-----------------------------
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener Ll = new MilocationListener();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, Ll);
    }

    //----------------clase MiLocationListener para activar el escucha de java----
    public class MilocationListener implements LocationListener {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            et1.setText(String.valueOf(location.getLatitude()));
            et2.setText(String.valueOf(location.getLongitude()));
            et3.setText(String.valueOf(location.getAltitude()));
        }
    }

    public void abrirOtra(View V){
        String url=String.format(et1.getText().toString()+","+et2.getText().toString());
        Intent i=new Intent(this, Otra.class);
        i.putExtra("ruta",url);
        startActivity(i);
    }
}