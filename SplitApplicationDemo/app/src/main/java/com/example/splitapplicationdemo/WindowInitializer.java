package com.example.splitapplicationdemo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;
import androidx.window.embedding.SplitController;

import java.util.ArrayList;
import java.util.List;

public class WindowInitializer implements Initializer<SplitController> {
    @NonNull
    @Override
    public SplitController create(@NonNull Context context) {
        SplitController.initialize(context,R.xml.main_split_config);
        return SplitController.getInstance();
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return new ArrayList<>();
    }
}
