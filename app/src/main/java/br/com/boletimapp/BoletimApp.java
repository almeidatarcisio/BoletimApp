package br.com.fasj.unibrasapp;

import android.app.Application;
import android.os.SystemClock;

import java.util.concurrent.TimeUnit;

public class BoletimApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));
    }
}
