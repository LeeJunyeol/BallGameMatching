package com.project.ballgamematching;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jylee on 2017-04-28.
 */

public class ManageClient extends Activity {

    public MobileServiceClient getClient(Activity activity) throws MalformedURLException{
        // Create the Mobile Service Client instance, using the provided
        // Mobile Service URL and key
        MobileServiceClient mClient = new MobileServiceClient(
                    "https://ballgamematching.azurewebsites.net",
                    activity);

            // Extend timeout from default of 10s to 20s
            mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
                @Override
                public OkHttpClient createOkHttpClient() {
                    OkHttpClient client = new OkHttpClient();
                    client.setReadTimeout(20, TimeUnit.SECONDS);
                    client.setWriteTimeout(20, TimeUnit.SECONDS);
                    return client;

                }
            });
        return mClient;
    }
}