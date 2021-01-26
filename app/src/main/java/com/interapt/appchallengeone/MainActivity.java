package com.interapt.appchallengeone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        model.getColors().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> colors) {
                //update UI
            }
        });
        setContentView(R.layout.activity_main);
    }

    private float x1, x2;
    static final int MIN_DISTANCE = 150;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    if (x2 > x1) {
                        Toast.makeText(this, "Left to Right swipe", Toast.LENGTH_SHORT).show();
                        Log.d("swipe", "Left to Right swipe");
                    } else {
                        Toast.makeText(this, "Right to Left swipe", Toast.LENGTH_SHORT).show();
                        Log.d("swipe", "Right to Left swipe");
                    }

                } else {
                    // something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}

class MyViewModel extends ViewModel {
    private ArrayList<String> colors;

    public ArrayList<String> getColors() {
        if (colors == null) {
            colors = new ArrayList<>();
            Collections.addAll(colors, "blue", "green", "red", "orange", "yellow", "gray", "purple", "brown");
            loadColors();
        }
        return colors;
    }

    private void loadColors() {
        // Do an asynchronous operation to fetch colors.
    }
}