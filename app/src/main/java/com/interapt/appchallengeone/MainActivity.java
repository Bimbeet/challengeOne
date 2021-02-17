package com.interapt.appchallengeone;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import com.interapt.appchallengeone.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<ColorCards> keepList = new ArrayList<>();
    ArrayList<ColorCards> discardList = new ArrayList<>();
    ArrayList<ColorCards> cardsList = new ArrayList<>();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View tView = binding.getRoot();
        setContentView(tView);
        createNewCards();
    }

    private void createNewCards() {
        ColorCards redCard = new ColorCards();
        redCard.color = "red";
        redCard.hex = "#B30E02";
        cardsList.add(redCard);
        ColorCards blueCard = new ColorCards();
        blueCard.color = "blue";
        blueCard.hex = "#0E28BA";
        cardsList.add(blueCard);
        ColorCards greenCard = new ColorCards();
        greenCard.color = "green";
        greenCard.hex = "#08B50F";
        cardsList.add(greenCard);
        ColorCards orangeCard = new ColorCards();
        orangeCard.color = "orange";
        orangeCard.hex = "#FF9800";
        cardsList.add(orangeCard);
        ColorCards yellowCard = new ColorCards();
        yellowCard.color = "yellow";
        yellowCard.hex = "#FFEB3B";
        cardsList.add(yellowCard);
        ColorCards purpleCard = new ColorCards();
        purpleCard.color = "purple";
        purpleCard.hex = "#673AB7";
        cardsList.add(purpleCard);
    }

    private float x1, translationX, firstX;
    static final int MIN_DISTANCE = 150;
    private VelocityTracker mVelocityTracker = null;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int pointerId = event.getPointerId(index);
        event.offsetLocation(translationX, 0);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                firstX = event.getRawX();
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                } else {
                    mVelocityTracker.clear();
                }
                mVelocityTracker.addMovement(event);
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                translationX += event.getRawX() - firstX;
                binding.cardView.setX(translationX / 3);
                mVelocityTracker.addMovement(event);
//                mVelocityTracker.computeCurrentVelocity(100);
//                Log.d("", "X velocity: " + mVelocityTracker.getXVelocity(pointerId));
                break;
            case MotionEvent.ACTION_UP:
                float x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    ColorCards mCard = cardsList.get(0);
                    if (x2 > x1) {
                        Log.d("swipe", "Left to Right swipe");
                        keepList.add(mCard);
                    } else {
                        Log.d("swipe", "Right to Left swipe");
                        discardList.add(mCard);
                    }
                    cardsList.remove(0);
                } else {
                    Log.d("swipe", "Tap");
                    if (binding.startingV.getVisibility() == View.VISIBLE) {
                        binding.startingV.setVisibility(View.INVISIBLE);
                        binding.tapToStart.setVisibility(View.INVISIBLE);
                        binding.cardView.setVisibility(View.VISIBLE);
                        binding.colorText.setVisibility(View.VISIBLE);
                        binding.swipeTextDir.setVisibility(View.VISIBLE);
                    } else if (binding.tapToEnd.getVisibility() == View.VISIBLE) {
                        binding.tapToEnd.setVisibility(View.INVISIBLE);
                        List<String> likedList = new ArrayList<>();
                        if (!keepList.isEmpty()) {
                            for (ColorCards i : keepList) {
                                likedList.add(i.color);
                            }
                            binding.display.setText(TextUtils.join(", ", likedList));
                            binding.display.setVisibility(View.VISIBLE);
                            binding.swipeTextDir.setVisibility(View.INVISIBLE);
                            binding.resetButton.setVisibility(View.VISIBLE);
                        }
                    }
                }
                binding.cardView.setX(125);
                break;
            case MotionEvent.ACTION_CANCEL:
                mVelocityTracker.recycle();
                break;
        }
        if (!cardsList.isEmpty()) {
            ColorCards mainCard = cardsList.get(0);
            binding.cardView.setBackgroundColor(Color.parseColor(mainCard.hex));
            binding.colorText.setText(mainCard.color);
        } else {
            if (binding.cardView.getVisibility() == View.VISIBLE) {
                binding.cardView.setVisibility(View.INVISIBLE);
                binding.colorText.setVisibility(View.INVISIBLE);
                binding.tapToEnd.setVisibility(View.VISIBLE);
                binding.tapToEnd.bringToFront();
            }
        }
        return super.onTouchEvent(event);
    }

    public void onClickBtn(View v) {
        keepList.clear();
        discardList.clear();
        binding.tapToEnd.setVisibility(View.INVISIBLE);
        binding.display.setVisibility(View.INVISIBLE);
        binding.resetButton.setVisibility(View.INVISIBLE);
        binding.startingV.setVisibility(View.VISIBLE);
        binding.tapToStart.setVisibility(View.VISIBLE);
        cardsList.clear();
        createNewCards();
    }
}