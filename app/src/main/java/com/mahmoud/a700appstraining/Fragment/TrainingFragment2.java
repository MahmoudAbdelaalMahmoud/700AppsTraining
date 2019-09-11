package com.mahmoud.a700appstraining.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TrainingFragment2 extends Fragment {

    private IMainActivity iMainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IMainActivity){
            this.iMainActivity = (IMainActivity)context;
        }
    }
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout myLinear = new LinearLayout(getContext());
        textView = new TextView(getContext());
        TextView textView2 = new TextView(getContext());
        textView.setText("test Fragment22");
        textView2.setText("test Fragment22");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iMainActivity.showToast(2,"TEST INFO TO SHOW DATA in Fragment2");
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        myLinear.setLayoutParams(params);
        myLinear.setLayoutParams(params);
        myLinear.setLayoutParams(params);

        myLinear.addView(textView);
        myLinear.addView(textView2);

        myLinear.setOrientation(LinearLayout.VERTICAL);

        return myLinear;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void setFromOne(String message){
        textView.setText(message);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

//    public static interface IMainActivity2{
//        public void showToast(String message);
//    }
}
