package com.prueba.promexico.examenandroid.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.prueba.promexico.examenandroid.R;
import com.prueba.promexico.examenandroid.bean.OficinasBean;
import com.prueba.promexico.examenandroid.constant.DatabaseConstant;
import com.prueba.promexico.examenandroid.database.DatabaseHelper;
import com.prueba.promexico.examenandroid.fragment.DialogAlertFragment;
import com.prueba.promexico.examenandroid.utils.CommonBeanUtils;

import java.util.List;
import java.util.Map;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, DialogInterface.OnClickListener, GoogleMap.OnMarkerClickListener {
    public static final String TAG = MapActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private GoogleMap mGoogleMap;
    private Location mCurrentLocation;
    private Button mButtonAceptar;
    private MapFragment mMapFragment;
    private TextView mTextViewScursal;
    private GoogleApiClient mGoogleApiClient;
    private TextView mTextViewSalir;
    private DatabaseHelper mDatabaseHelper;
    private List<OficinasBean> mOficinasBeanList;
    private int mOfficeSelected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mToolbar = (Toolbar) findViewById(R.id.widget_toolbar);
        mTextViewSalir = (TextView)mToolbar.findViewById(R.id.toolbar_logout);
        mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment_map);
        mTextViewScursal = (TextView) findViewById(R.id.text_sucursal);
        mButtonAceptar = (Button) findViewById(R.id.btn_aceptar);
        initDatabase();
        initLocation();
        initListeners();
    }

    private void initDatabase(){
        mDatabaseHelper = new DatabaseHelper(this);
        try {
            mDatabaseHelper.openDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            DialogAlertFragment dialogError = DialogAlertFragment.newInstance("Error al acceder a la Base de datos");
            dialogError.setOnClickListener(this);
            dialogError.show(getSupportFragmentManager(), "dialogError");
        }
    }

    private void initLocation(){
        Log.i(TAG, "initLocation");
        List<Map<String,String>> resultOficinas = mDatabaseHelper.getSelectQueryMapValues(DatabaseConstant.QUERY_OFICINAS,null);
        mOficinasBeanList = CommonBeanUtils.getDataList(resultOficinas,OficinasBean.class);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void initListeners(){
        Log.i(TAG, "initListeners");
        mButtonAceptar.setOnClickListener(this);
        mTextViewSalir.setOnClickListener(this);
        mMapFragment.getMapAsync(this);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick");
        if (v.getId() == mTextViewSalir.getId()){
            finish();
        }
        else{
            if(mOfficeSelected > 0){
                Intent intent = new Intent(this, QuestionsActivity.class);
                intent.putExtra("officeSelected",mOfficeSelected);
                startActivity(intent);
            }
            else{
                DialogAlertFragment dialogError = DialogAlertFragment.newInstance("Debe Seleccionar una oficina del mapa");
                dialogError.show(getSupportFragmentManager(), "dialogErrorMap");
            }

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i(TAG, "onMapReady");
        mGoogleMap = googleMap;
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "onConnected");
        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        LatLng postion = new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(postion, 3));
        mGoogleMap.setOnMarkerClickListener(this);

        for (int i = 0; i < mOficinasBeanList.size(); i++){
            OficinasBean oficinasBean = mOficinasBeanList.get(i);
            String[] coordinates = oficinasBean.getCoordinates().split(",");
            if (coordinates.length == 2){
                LatLng location = new LatLng(Double.parseDouble(coordinates[0]),Double.parseDouble(coordinates[1]));

                Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                        .title(oficinasBean.getcOffice())
                        .snippet(Integer.toString(i))
                        .position(location));
            }

        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "onConnectionSuspended: " + i);

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "onConnectionFailed: " + connectionResult);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        finish();
    }



    @Override
    public boolean onMarkerClick(Marker marker) {
        mTextViewScursal.setText(marker.getTitle());
        mOfficeSelected = Integer.parseInt(marker.getSnippet());
        return false;
    }
}
