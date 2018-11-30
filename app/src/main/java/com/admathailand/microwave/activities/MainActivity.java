package com.admathailand.microwave.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.admathailand.microwave.adapters.MicrowaveLogAdapter;
import com.admathailand.microwave.objects.Microwave;
import com.admathailand.microwave.R;
import com.admathailand.microwave.states.MicrowaveState;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn10Sec,btn1Min,btn10Min,btnStart,btnClear,btnOpen;
    private TextView tvSec, tvMin;
    private Microwave microwave;
    private RecyclerView rvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        setOnClickListener();

        rvLog.setHasFixedSize(true);
        rvLog.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        final MicrowaveLogAdapter microwaveLogAdapter = new MicrowaveLogAdapter(MainActivity.this,rvLog);
        rvLog.setAdapter(microwaveLogAdapter);

        microwave = new Microwave(MainActivity.this,microwaveLogAdapter, tvSec, tvMin);
    }

    private void findViewById(){
        btn10Sec = findViewById(R.id.btn10Sec);
        btn1Min = findViewById(R.id.btn1Min);
        btn10Min = findViewById(R.id.btn10Min);
        btnStart = findViewById(R.id.btnStart);
        btnClear = findViewById(R.id.btnClear);
        btnOpen = findViewById(R.id.btnOpen);
        tvSec = findViewById(R.id.tvSec);
        tvMin = findViewById(R.id.tvMin);
        rvLog = findViewById(R.id.rvLog);
    }

    private void setOnClickListener(){
        btn10Sec.setOnClickListener(this);
        btn1Min.setOnClickListener(this);
        btn10Min.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnOpen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        final MicrowaveState microwaveState = microwave.getCurrentState();
        switch (id){
            case R.id.btn10Sec:
                microwaveState.plus10Sec(microwave);
                break;
            case R.id.btn1Min:
                microwaveState.plus1Min(microwave);
                break;
            case R.id.btn10Min:
                microwaveState.plus10Min(microwave);
                break;
            case R.id.btnStart:
                microwaveState.start(microwave);
                break;
            case R.id.btnClear:
                microwaveState.clear(microwave);
                break;
            case R.id.btnOpen:
                microwaveState.open(microwave);

                if (microwave.isOpened()){
                    btnOpen.setText("Close");
                    microwave.getMicrowaveSoundManager().playOpening();
                }else{
                    btnOpen.setText("Open");
                    microwave.getMicrowaveSoundManager().playClosing();
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        microwave.getMicrowaveSoundManager().releasePool();
    }
}
