package com.georgeqwu.goals;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by glookogeorge on 5/25/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
