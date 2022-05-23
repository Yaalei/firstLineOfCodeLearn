package com.example.splitactivitydemo2;

import android.content.Context;

import androidx.startup.Initializer;
import androidx.window.embedding.SplitController;

import java.util.ArrayList;
import java.util.List;

public class WindowInitializer implements Initializer<SplitController> {
    @Override
    public SplitController create(Context context) {
        SplitController.initialize(context, R.xml.main_split_config);
        return SplitController.getInstance();
        }

    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return new ArrayList<>();
        }
}
