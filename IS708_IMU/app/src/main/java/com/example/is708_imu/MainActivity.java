package com.example.is708_imu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView textx;
    private TextView texty;
    private TextView textz;

    private SensorManager sensorManager;
    private Sensor sensor;

    private FileWriter writer;


    private SensorEventListener gyroEventListner = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            textx.setText("X: "+Float.toString(sensorEvent.values[0]));
            texty.setText("Y: "+Float.toString(sensorEvent.values[1]));
            textz.setText("Z "+Float.toString(sensorEvent.values[2]));

            try {
                writer.write("X: "+Float.toString(sensorEvent.values[0]) + " Y: "+ sensorEvent.values[1]+" Z: "+sensorEvent.values[2] + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textx = findViewById(R.id.textx);
        texty = findViewById(R.id.texty);
        textz = findViewById(R.id.textz);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);





    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(gyroEventListner,sensor,SensorManager.SENSOR_DELAY_NORMAL);

        try {
            writer = new FileWriter(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"imu2.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensor!=null) {
            sensorManager.unregisterListener(gyroEventListner);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}