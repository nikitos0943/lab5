package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void  onBtnShowGroupsClick(View view){
        Intent intent = new Intent(this, GroupsListActivity.class);
        startActivity(intent);
    }


}