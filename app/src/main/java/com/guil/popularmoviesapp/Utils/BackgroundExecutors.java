package com.guil.popularmoviesapp.Utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class BackgroundExecutors {

    private static BackgroundExecutors instance;

    public static BackgroundExecutors getInstance() {
        if(instance == null) {
            instance = new BackgroundExecutors();
        }
        return instance;
    }

    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);
    public ScheduledExecutorService networkIO() {
        return mNetworkIO;
    }
}
