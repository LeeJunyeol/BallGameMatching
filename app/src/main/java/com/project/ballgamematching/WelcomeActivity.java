package com.project.ballgamematching;

import android.accounts.Account;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

/**
 * Created by jylee on 2017-04-27.
 */

public class WelcomeActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks
        ,GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;

    //    private Button btn_disconnect;
    private SignInButton sign_in_button;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        //UI 가져오기
        sign_in_button  = (SignInButton)findViewById(R.id.btn_welcome);
//        btn_disconnect = (Button)findViewById(R.id.btn_discon);
//        mStatus = (TextView) findViewById(R.id.status);


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();


        //로그인
        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleApiClient.connect();

//                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });

        //로그아웃
//        btn_disconnect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mGoogleApiClient.isConnected()) {
//                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
//                    Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient);
//                    mGoogleApiClient.disconnect();
//                    mStatus.setText("");
//                }
//
//            }
//        });
    }

    @Override
    public void onConnected( Bundle bundle) {
        Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
        String accountName = Plus.AccountApi.getAccountName(mGoogleApiClient);
        Account account = new Account(accountName, GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);

        if (currentPerson != null) {




        } else {
            Log.w("Main", "error");
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            mGoogleApiClient.connect();
        }
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, RC_SIGN_IN);

            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {

        }

    }
}
