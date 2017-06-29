package com.example.samtech.homemobile2;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by Samtech on 08-05-2017.
 */
public class GCMTokenRefreshListenerService extends InstanceIDListenerService {
    //	when token refresh, start service to get new token ??
    // maybe this no application in samtech code
    @Override
    public void onTokenRefresh(){
        Intent intent = new Intent(this, GCMRegistrationIntentService.class);
        startService(intent);
    }
}
