package com.project.ballgamematching;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;


/**
 * Created by JUO on 2017. 4. 28..
 */


public class SignOutActivity extends Activity implements GoogleApiClient.OnConnectionFailedListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        WelcomeActivity wa = new WelcomeActivity();
        wa.signOut();


    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
