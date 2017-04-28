package com.project.ballgamematching;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;



/**
 * Created by JUO on 2017. 4. 26..
 */

public class WelcomeActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    public GoogleApiClient mGoogleApiClient;

    private static final String TAG = "WelcomeActivity";
    private static final int RC_SIGN_IN = 9001;

    private SignInButton sign_in_btn;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);






            // btn listener
            findViewById(R.id.btn_welcome).setOnClickListener(this);
//        findViewById(R.id.sign_out_button).setOnClickListener(this);


            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();


            sign_in_btn = (SignInButton) findViewById(R.id.btn_welcome);
            sign_in_btn.setSize(SignInButton.SIZE_STANDARD);
            sign_in_btn.setScopes(gso.getScopeArray());




    }

    @Override
    protected void onStart() {
        super.onStart();



        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if(opr.isDone()){
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult (result);
        }else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());


        if (result.isSuccess()){

            // signed 성공, authenticated UI 보여주기
            GoogleSignInAccount acct = result.getSignInAccount();
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("acct", acct);

            startActivity(intent);
            finish();

        }else {
        }
    }


    private void signIn(){
        Intent singInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(singInIntent, RC_SIGN_IN);
    }


    public void signOut(){


        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Intent relode = new Intent(WelcomeActivity.this, WelcomeActivity.class);
                        startActivity(relode);
                        finish();
                    }


                });

    }







    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: " + connectionResult);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_welcome:
                signIn();
                break;
        }
    }

}
