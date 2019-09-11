package com.mahmoud.a700appstraining.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mahmoud.a700appstraining.R;

public class TrainingFragment extends Fragment {

    private IMainActivity iMainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IMainActivity){
            this.iMainActivity = (IMainActivity)context;
        }
    }
    TextView textView2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout myLinear = new LinearLayout(getContext());
        TextView textView = new TextView(getContext());
        textView2 = new TextView(getContext());
        textView.setText("test Fragment11");
        textView2.setText("test Fragment11");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iMainActivity.showToast(1,"TEST INFO TO SHOW DATA In Fragment11");
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

    public void setFromTwo(String message) {
        textView2.setText(message);
    }

//    public static interface IMainActivity{
//        public void showToast(String message);
//    }
}
