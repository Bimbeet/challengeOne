package com.interapt.appchallengeone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.interapt.appchallengeone.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<View> keepList = new ArrayList<>();
    ArrayList<View> discardList = new ArrayList<>();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View tView = binding.getRoot();
        setContentView(tView);
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
                        Log.d("swipe", binding.redV.toString());
                        if (binding.redV.getVisibility() == View.VISIBLE) {
                            // red is liked
                            binding.redV.setVisibility(View.INVISIBLE);
                            binding.redV.setVisibility(View.INVISIBLE);
                            binding.blueV.setVisibility(View.VISIBLE);
                            keepList.add(binding.redV);
                        } else if (binding.blueV.getVisibility() == View.VISIBLE) {
                            binding.blueV.setVisibility(View.INVISIBLE);
                            binding.greenV.setVisibility(View.VISIBLE);
                            keepList.add(binding.blueV);
                        } else if (binding.greenV.getVisibility() == View.VISIBLE) {
                            binding.greenV.setVisibility(View.INVISIBLE);
                            binding.orangeV.setVisibility(View.VISIBLE);
                            keepList.add(binding.greenV);
                        } else if (binding.orangeV.getVisibility() == View.VISIBLE) {
                            binding.orangeV.setVisibility(View.INVISIBLE);
                            binding.yellowV.setVisibility(View.VISIBLE);
                            keepList.add(binding.orangeV);
                        } else if (binding.yellowV.getVisibility() == View.VISIBLE) {
                            binding.yellowV.setVisibility(View.INVISIBLE);
                            binding.grayV.setVisibility(View.VISIBLE);
                            keepList.add(binding.yellowV);
                        } else if (binding.grayV.getVisibility() == View.VISIBLE) {
                            binding.grayV.setVisibility(View.INVISIBLE);
                            binding.purpleV.setVisibility(View.VISIBLE);
                            keepList.add(binding.grayV);
                        } else if (binding.purpleV.getVisibility() == View.VISIBLE) {
                            binding.purpleV.setVisibility(View.INVISIBLE);
                            binding.brownV.setVisibility(View.VISIBLE);
                            keepList.add(binding.purpleV);
                        } else if (binding.brownV.getVisibility() == View.VISIBLE) {
                            binding.brownV.setVisibility(View.INVISIBLE);
                            binding.endingV.setVisibility(View.VISIBLE);
                            binding.tapToEnd.setVisibility(View.VISIBLE);
                            keepList.add(binding.brownV);
                        }
                    } else {
                        Toast.makeText(this, "Right to Left swipe", Toast.LENGTH_SHORT).show();
                        Log.d("swipe", "Right to Left swipe");
                        if (binding.redV.getVisibility() == View.VISIBLE) {
                            // red is disliked
                            binding.redV.setVisibility(View.INVISIBLE);
                            binding.redV.setVisibility(View.INVISIBLE);
                            binding.blueV.setVisibility(View.VISIBLE);
                            discardList.add(binding.redV);
                        } else if (binding.blueV.getVisibility() == View.VISIBLE) {
                            binding.blueV.setVisibility(View.INVISIBLE);
                            binding.greenV.setVisibility(View.VISIBLE);
                            discardList.add(binding.blueV);
                        } else if (binding.greenV.getVisibility() == View.VISIBLE) {
                            binding.greenV.setVisibility(View.INVISIBLE);
                            binding.orangeV.setVisibility(View.VISIBLE);
                            discardList.add(binding.greenV);
                        } else if (binding.orangeV.getVisibility() == View.VISIBLE) {
                            binding.orangeV.setVisibility(View.INVISIBLE);
                            binding.yellowV.setVisibility(View.VISIBLE);
                            discardList.add(binding.orangeV);
                        } else if (binding.yellowV.getVisibility() == View.VISIBLE) {
                            binding.yellowV.setVisibility(View.INVISIBLE);
                            binding.grayV.setVisibility(View.VISIBLE);
                            discardList.add(binding.yellowV);
                        } else if (binding.grayV.getVisibility() == View.VISIBLE) {
                            binding.grayV.setVisibility(View.INVISIBLE);
                            binding.purpleV.setVisibility(View.VISIBLE);
                            discardList.add(binding.grayV);
                        } else if (binding.purpleV.getVisibility() == View.VISIBLE) {
                            binding.purpleV.setVisibility(View.INVISIBLE);
                            binding.brownV.setVisibility(View.VISIBLE);
                            discardList.add(binding.purpleV);
                        } else if (binding.brownV.getVisibility() == View.VISIBLE) {
                            binding.brownV.setVisibility(View.INVISIBLE);
                            binding.endingV.setVisibility(View.VISIBLE);
                            binding.tapToEnd.setVisibility(View.VISIBLE);
                            discardList.add(binding.brownV);
                        }
                    }
                } else {
                    Log.d("swipe", "Tap");
                    if (binding.startingV.getVisibility() == View.VISIBLE) {
                        binding.startingV.setVisibility(View.INVISIBLE);
                        binding.tapToStart.setVisibility(View.INVISIBLE);
                        binding.redV.setVisibility(View.VISIBLE);
                    } else if (binding.endingV.getVisibility() == View.VISIBLE) {
                        binding.endingV.setVisibility(View.INVISIBLE);
//                        Toast.makeText(this, keepList, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}