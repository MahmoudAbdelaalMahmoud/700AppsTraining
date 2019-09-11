package com.mahmoud.a700appstraining;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.mahmoud.a700appstraining.Fragment.IMainActivity;
import com.mahmoud.a700appstraining.Fragment.TrainingFragment;
import com.mahmoud.a700appstraining.Fragment.TrainingFragment2;


public class MainActivity extends AppCompatActivity implements IMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        getSupportFragmentManager().findFragmentById(R.id.locationFragment).onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void showToast(int type,String message) {
        switch (type){
            case 1:
//                TrainingFragment2 fragment2 = (TrainingFragment2) getSupportFragmentManager().findFragmentById(R.id.locationFragment2);
//                fragment2.setFromOne(message);
                break;
            case 2:
//                TrainingFragment fragment = (TrainingFragment) getSupportFragmentManager().findFragmentById(R.id.locationFragment);
//                fragment.setFromTwo(message);
                break;
        }
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
