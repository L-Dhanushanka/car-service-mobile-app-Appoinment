package com.example.car_service_wecarcare;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class CustomLinearLayoutManager extends LinearLayoutManager {

        private static final String TAG = "";

        public CustomLinearLayoutManager(Context context) {
            super(context);
        }

        public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public CustomLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        //Generate constructors

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

            try {

                super.onLayoutChildren(recycler, state);

            } catch (IndexOutOfBoundsException e) {

                Log.e(TAG, "Inconsistency detected");
            }

        }
    }

