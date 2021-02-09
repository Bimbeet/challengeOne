package com.interapt.appchallengeone;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.interapt.appchallengeone.databinding.ActivityMainBinding;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> keepList = new ArrayList<>();
    ArrayList<String> discardList = new ArrayList<>();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View tView = binding.getRoot();
        setContentView(tView);
    }

    private float x1;
    static final int MIN_DISTANCE = 150;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    if (x2 > x1) {
                        Log.d("swipe", "Left to Right swipe");
                        if (binding.redV.getVisibility() == View.VISIBLE) {
                            binding.redV.setVisibility(View.INVISIBLE);
                            binding.redV.setVisibility(View.INVISIBLE);
                            binding.blueV.setVisibility(View.VISIBLE);
                            keepList.add(getString(R.string.red));
                        } else if (binding.blueV.getVisibility() == View.VISIBLE) {
                            binding.blueV.setVisibility(View.INVISIBLE);
                            binding.greenV.setVisibility(View.VISIBLE);
                            keepList.add(getString(R.string.blue));
                        } else if (binding.greenV.getVisibility() == View.VISIBLE) {
                            binding.greenV.setVisibility(View.INVISIBLE);
                            binding.orangeV.setVisibility(View.VISIBLE);
                            keepList.add(getString(R.string.green));
                        } else if (binding.orangeV.getVisibility() == View.VISIBLE) {
                            binding.orangeV.setVisibility(View.INVISIBLE);
                            binding.yellowV.setVisibility(View.VISIBLE);
                            keepList.add(getString(R.string.orange));
                        } else if (binding.yellowV.getVisibility() == View.VISIBLE) {
                            binding.yellowV.setVisibility(View.INVISIBLE);
                            binding.grayV.setVisibility(View.VISIBLE);
                            keepList.add(getString(R.string.yellow));
                        } else if (binding.grayV.getVisibility() == View.VISIBLE) {
                            binding.grayV.setVisibility(View.INVISIBLE);
                            binding.purpleV.setVisibility(View.VISIBLE);
                            keepList.add(getString(R.string.gray));
                        } else if (binding.purpleV.getVisibility() == View.VISIBLE) {
                            binding.purpleV.setVisibility(View.INVISIBLE);
                            binding.brownV.setVisibility(View.VISIBLE);
                            keepList.add(getString(R.string.purple));
                        } else if (binding.brownV.getVisibility() == View.VISIBLE) {
                            binding.brownV.setVisibility(View.INVISIBLE);
                            binding.tapToEnd.setVisibility(View.VISIBLE);
                            binding.tapToEnd.bringToFront();
                            keepList.add(getString(R.string.brown));
                        }
                    } else {
                        Log.d("swipe", "Right to Left swipe");
                        if (binding.redV.getVisibility() == View.VISIBLE) {
                            binding.redV.setVisibility(View.INVISIBLE);
                            binding.redV.setVisibility(View.INVISIBLE);
                            binding.blueV.setVisibility(View.VISIBLE);
                            discardList.add(getString(R.string.red));
                        } else if (binding.blueV.getVisibility() == View.VISIBLE) {
                            binding.blueV.setVisibility(View.INVISIBLE);
                            binding.greenV.setVisibility(View.VISIBLE);
                            discardList.add(getString(R.string.blue));
                        } else if (binding.greenV.getVisibility() == View.VISIBLE) {
                            binding.greenV.setVisibility(View.INVISIBLE);
                            binding.orangeV.setVisibility(View.VISIBLE);
                            discardList.add(getString(R.string.green));
                        } else if (binding.orangeV.getVisibility() == View.VISIBLE) {
                            binding.orangeV.setVisibility(View.INVISIBLE);
                            binding.yellowV.setVisibility(View.VISIBLE);
                            discardList.add(getString(R.string.orange));
                        } else if (binding.yellowV.getVisibility() == View.VISIBLE) {
                            binding.yellowV.setVisibility(View.INVISIBLE);
                            binding.grayV.setVisibility(View.VISIBLE);
                            discardList.add(getString(R.string.yellow));
                        } else if (binding.grayV.getVisibility() == View.VISIBLE) {
                            binding.grayV.setVisibility(View.INVISIBLE);
                            binding.purpleV.setVisibility(View.VISIBLE);
                            discardList.add(getString(R.string.gray));
                        } else if (binding.purpleV.getVisibility() == View.VISIBLE) {
                            binding.purpleV.setVisibility(View.INVISIBLE);
                            binding.brownV.setVisibility(View.VISIBLE);
                            discardList.add(getString(R.string.purple));
                        } else if (binding.brownV.getVisibility() == View.VISIBLE) {
                            binding.brownV.setVisibility(View.INVISIBLE);
                            binding.tapToEnd.setVisibility(View.VISIBLE);
                            binding.tapToEnd.bringToFront();
                            discardList.add(getString(R.string.brown));
                        }
                    }
                } else {
                    Log.d("swipe", "Tap");
                    if (binding.startingV.getVisibility() == View.VISIBLE) {
                        binding.startingV.setVisibility(View.INVISIBLE);
                        binding.tapToStart.setVisibility(View.INVISIBLE);
                        binding.redV.setVisibility(View.VISIBLE);
                        binding.swipeTextDir.setVisibility(View.VISIBLE);
                    } else if (binding.tapToEnd.getVisibility() == View.VISIBLE) {
                        binding.tapToEnd.setVisibility(View.INVISIBLE);
                        binding.display.setText(TextUtils.join(", ", keepList));
                        binding.display.setVisibility(View.VISIBLE);
                        binding.swipeTextDir.setVisibility(View.INVISIBLE);
                        binding.resetButton.setVisibility(View.VISIBLE);
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void onClickBtn(View v) {
        keepList.clear();
        discardList.clear();
        binding.display.setVisibility(View.INVISIBLE);
        binding.resetButton.setVisibility(View.INVISIBLE);
        binding.startingV.setVisibility(View.VISIBLE);
        binding.tapToStart.setVisibility(View.VISIBLE);
    }
}