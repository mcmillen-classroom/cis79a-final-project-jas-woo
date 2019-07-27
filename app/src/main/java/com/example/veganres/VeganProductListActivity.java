package com.example.veganres;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class VeganProductListActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener
{
    private Location mCurrentLocation;
    MapView mMapView;
    private GalleryItem mMapItem;

    private static final String REQUEST_TJOES_LIST = "DialogTJoes";

    public static Intent newIntent(MainActivity mainActivity)
    {
        Intent ret = new Intent(mainActivity, VeganProductListActivity.class);

        return ret;
    }

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegan_product_list);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void showNoticeDialog()
    {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new TJoesProductsFragment();
        dialog.show(getSupportFragmentManager(), "TJoesProductsList");
    }
    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    /*@Override
    public void onDialogPositiveClick(DialogFragment dialog)
    {
        // User touched the dialog's positive button
    }*/


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        LatLng tJoesBerkeley = new LatLng(37.871926, -122.2755017);
        Marker berkeley = mMap.addMarker(new MarkerOptions().position(tJoesBerkeley).title("Trader Joe's - Berkeley"));
        berkeley.setTag("berkeley");

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(tJoesBerkeley));

        LatLng tJoesElCerrito = new LatLng(37.902562, -122.298955);
        Marker elcerrito = mMap.addMarker(new MarkerOptions().position(tJoesElCerrito).title("Trader Joe's - El Cerrito Plaza"));
        elcerrito.setTag("el_cerrito");

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(tJoesElCerrito));

        LatLng tJoesRockridge = new LatLng(37.849334, -122.252792);
        Marker rockridge = mMap.addMarker(new MarkerOptions().position(tJoesRockridge).title("Trader Joe's - Rockridge"));
        rockridge.setTag("rockridge");

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(tJoesRockridge));

        LatLng tJoesLakeshore = new LatLng(37.810659, -122.244155);
        Marker lakeshore = mMap.addMarker(new MarkerOptions().position(tJoesLakeshore).title("Trader Joe's - Lakeshore"));
        lakeshore.setTag("lakeshore");
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(tJoesLakeshore));

        LatLng tJoesEmeryville = new LatLng(37.841200, -122.293697);
        Marker emeryville =  mMap.addMarker(new MarkerOptions().position(tJoesEmeryville).title("Trader Joe's - Emeryville"));
        emeryville.setTag("emeryville");
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tJoesEmeryville, 12f));

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(tJoesEmeryville));

        LatLng tJoesAlameda = new LatLng(37.758709, -122.251613);
        Marker alameda =  mMap.addMarker(new MarkerOptions().position(tJoesAlameda).title("Trader Joe's - Alameda"));
        alameda.setTag("alameda");

        mMap.setOnInfoWindowClickListener(this);

        //updateUI();
    }

    @Override
    public void onInfoWindowClick(Marker marker)
    {
        Intent launchListIntent = new Intent(this, TraderJoesProductItems.class);
        launchListIntent.putExtra("store_location", marker.getTag().toString());
        startActivity(launchListIntent);
    }
    /*private void updateUI()
    {
        if(mMap == null)
        {
            return;
        }

        LatLng itemPoint = new LatLng(mMapItem.getLat(), mMapItem.getLon());
        LatLng myPoint = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());

        MarkerOptions myMarker = new MarkerOptions().position(myPoint);
        mMap.addMarker(myMarker);

        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(itemPoint)
                .include(myPoint)
                .build();
        int margin = getResources().getDimensionPixelSize(R.dimen.map_inset_margin);
        CameraUpdate update = CameraUpdateFactory.newLatLngBounds(bounds, margin);
        mMap.animateCamera(update);
    }*/

}
