package com.example.ambientlightsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView ambientLightTV;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ambientLightTV=findViewById(R.id.ambientLightTV);
        //sensor manager
        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager!=null){
            //sensor
            Sensor ambientLightSensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if(ambientLightSensor!=null){
                //now register this sensor with SensorEventListener
                sensorManager.registerListener(this,ambientLightSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
            else{
                Toast.makeText(this, "Ambient Light Sensor not detected", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Sensor service not detected", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType()==Sensor.TYPE_LIGHT){
            String values="Value: "+sensorEvent.values[0];
            ambientLightTV.setText(values);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}