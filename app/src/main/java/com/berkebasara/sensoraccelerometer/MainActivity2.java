package com.berkebasara.sensoraccelerometer;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DecimalFormat;

public class MainActivity2 extends AppCompatActivity {

    DecimalFormat decimalFormat = new DecimalFormat("#.####");

    TextView txt_currentAccelx2, txt_prevAccelx2, txt_accelx2,txt_currentAccely2, txt_prevAccely2, txt_accely2,txt_currentAccelz2, txt_prevAccelz2, txt_accelz2;
    ProgressBar prog_shakeMeterr2X,
            prog_shakeMeterr2Y,
            prog_shakeMeterr2Z;


    private SensorManager mSensorManager2;
    private Sensor mAccelerometer2;
    private double accelerationCurrentValueX2;
    private double accelerationCurrentValueY2;
    private double accelerationCurrentValueZ2;
    private double accelerationPrevValueX2 ;
    private double accelerationPrevValueY2;
    private double accelerationPrevValueZ2;
    private int pointsPlotted2 = 5;
    private int graphIntervalCounter = 0;
    private Viewport viewport;
    private Button button;

    LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[] {
            new DataPoint(0, 1),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6)
    });

    private SensorEventListener sensorEventListener2 = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            float x2 = sensorEvent.values[0];
            float y2 = sensorEvent.values[1];
            float z2 = sensorEvent.values[2];

            accelerationCurrentValueX2 = x2;
            accelerationCurrentValueY2 = y2;
            accelerationCurrentValueZ2 = z2;


            double changeInAccellerationX2 = Math.abs(accelerationCurrentValueX2 - accelerationPrevValueX2);
            double changeInAccellerationY2 = Math.abs(accelerationCurrentValueY2 - accelerationPrevValueY2);
            double changeInAccellerationZ2= Math.abs(accelerationCurrentValueZ2 - accelerationPrevValueZ2);

            txt_currentAccelx2.setText("Curnt= "+ decimalFormat.format(accelerationCurrentValueX2));
            txt_accelx2.setText("acclX= "+decimalFormat.format(changeInAccellerationX2));
            txt_prevAccelx2.setText("Prev= "+decimalFormat.format(accelerationPrevValueX2));

            txt_currentAccely2.setText("Curnt= "+ decimalFormat.format(accelerationCurrentValueY2));
            txt_accely2.setText("acclY= "+decimalFormat.format(changeInAccellerationY2));
            txt_prevAccely2.setText("Prev= "+decimalFormat.format(accelerationPrevValueY2));

            txt_currentAccelz2.setText("Curnt= "+ decimalFormat.format(accelerationCurrentValueZ2));
            txt_accelz2.setText("acclZ= "+decimalFormat.format(changeInAccellerationZ2));
            txt_prevAccelz2.setText("Prev= "+decimalFormat.format(accelerationPrevValueZ2));

            prog_shakeMeterr2X.setProgress((int) changeInAccellerationX2);
            prog_shakeMeterr2Y.setProgress((int) changeInAccellerationY2);
            prog_shakeMeterr2Z.setProgress((int) changeInAccellerationZ2);

            if (changeInAccellerationX2 != 0.0001)
            {
                if (changeInAccellerationX2 >= 12.1644)
                {
                    txt_accelx2.setBackgroundColor(Color.parseColor("#fc0303"));
                }

                else if (changeInAccellerationX2 >= 6.3765 && changeInAccellerationX2 <= 12.1644)
                {

                    txt_accelx2.setBackgroundColor(Color.parseColor("#fc3503"));
                }
                else if (changeInAccellerationX2 >= 3.3354 && changeInAccellerationX2 <= 6.3765)
                {

                    txt_accelx2.setBackgroundColor(Color.parseColor("#fc7303"));
                }
                else if (changeInAccellerationX2 >= 1.7658 && changeInAccellerationX2 <= 3.3354)
                {

                    txt_accelx2.setBackgroundColor(Color.parseColor("#fc8c03"));
                }
                else if (changeInAccellerationX2 >= 0.9025 && changeInAccellerationX2 <= 1.7658)
                {

                    txt_accelx2.setBackgroundColor(Color.parseColor("#fcba03"));
                }
                else if (changeInAccellerationX2 >= 0.3825 && changeInAccellerationX2 <= 0.9025)
                {

                    txt_accelx2.setBackgroundColor(Color.parseColor("#d2fc03"));
                }
                else if (changeInAccellerationX2 >= 0.1373 && changeInAccellerationX2 <= 0.3825)
                {

                    txt_accelx2.setBackgroundColor(Color.parseColor("#03fcc2"));
                }
                else if (changeInAccellerationX2 >= 0.0166 && changeInAccellerationX2 <= 0.1373)
                {

                    txt_accelx2.setBackgroundColor(Color.parseColor("#fc03f4"));
                }

                else
                {
                    txt_accelx2.setBackgroundColor(Color.WHITE);
                }


            }
            if (changeInAccellerationY2 != 0.0001)
            {
                if (changeInAccellerationX2 >= 12.1644)
                {
                     txt_accely2.setBackgroundColor(Color.parseColor("#fc0303"));
                }

                else if (changeInAccellerationY2 >= 6.3765 && changeInAccellerationY2 <= 12.1644)
                {

                    txt_accely2.setBackgroundColor(Color.parseColor("#fc3503"));
                }
                else if (changeInAccellerationY2 >= 3.3354 && changeInAccellerationY2 <= 6.3765)
                {

                    txt_accely2.setBackgroundColor(Color.parseColor("#fc7303"));
                }
                else if (changeInAccellerationY2 >= 1.7658 && changeInAccellerationY2 <= 3.3354)
                {

                    txt_accely2.setBackgroundColor(Color.parseColor("#fc8c03"));
                }
                else if (changeInAccellerationY2 >= 0.9025 && changeInAccellerationY2 <= 1.7658)
                {

                    txt_accely2.setBackgroundColor(Color.parseColor("#fcba03"));
                }
                else if (changeInAccellerationY2 >= 0.3825 && changeInAccellerationY2 <= 0.9025)
                {

                    txt_accely2.setBackgroundColor(Color.parseColor("#d2fc03"));
                }
                else if (changeInAccellerationY2 >= 0.1373 && changeInAccellerationY2 <= 0.3825)
                {

                    txt_accely2.setBackgroundColor(Color.parseColor("#03fcc2"));
                }
                else if (changeInAccellerationY2 >= 0.0166 && changeInAccellerationY2 <= 0.1373)
                {

                    txt_accely2.setBackgroundColor(Color.parseColor("#fc03f4"));
                }

                else
                {
                    txt_accely2.setBackgroundColor(Color.WHITE);
                }


            }
            if (changeInAccellerationZ2 != 0.0001)
            {
                if (changeInAccellerationX2 >= 12.1644)
                {
                    txt_accelz2.setBackgroundColor(Color.parseColor("#fc0303"));

                }

                else if (changeInAccellerationZ2 >= 6.3765 && changeInAccellerationZ2 <= 12.1644)
                {

                    txt_accelz2.setBackgroundColor(Color.parseColor("#fc3503"));
                }
                else if (changeInAccellerationZ2 >= 3.3354 && changeInAccellerationZ2 <= 6.3765)
                {

                    txt_accelz2.setBackgroundColor(Color.parseColor("#fc7303"));
                }
                else if (changeInAccellerationZ2 >= 1.7658 && changeInAccellerationZ2 <= 3.3354)
                {

                    txt_accelz2.setBackgroundColor(Color.parseColor("#fc8c03"));
                }
                else if (changeInAccellerationZ2 >= 0.9025 && changeInAccellerationZ2 <= 1.7658)
                {

                    txt_accelz2.setBackgroundColor(Color.parseColor("#fcba03"));
                }
                else if (changeInAccellerationZ2 >= 0.3825 && changeInAccellerationZ2 <= 0.9025)
                {

                    txt_accelz2.setBackgroundColor(Color.parseColor("#d2fc03"));
                }
                else if (changeInAccellerationZ2 >= 0.1373 && changeInAccellerationZ2 <= 0.3825)
                {

                    txt_accelz2.setBackgroundColor(Color.parseColor("#03fcc2"));
                }
                else if (changeInAccellerationZ2 >= 0.0166 && changeInAccellerationZ2 <= 0.1373)
                {

                    txt_accelz2.setBackgroundColor(Color.parseColor("#fc03f4"));
                }

                else
                {
                    txt_accelz2.setBackgroundColor(Color.WHITE);
                }


            }


            pointsPlotted2++;
            series2.appendData(new DataPoint(pointsPlotted2, changeInAccellerationX2),true,pointsPlotted2);
            viewport.setMaxX(pointsPlotted2);
            viewport.setMinX(pointsPlotted2 - 200);
            series2.appendData(new DataPoint(pointsPlotted2, changeInAccellerationY2),true,pointsPlotted2);
            viewport.setMaxX(pointsPlotted2);
            viewport.setMinX(pointsPlotted2 - 200);
            series2.appendData(new DataPoint(pointsPlotted2, changeInAccellerationZ2),true,pointsPlotted2);
            viewport.setMaxX(pointsPlotted2);
            viewport.setMinX(pointsPlotted2 - 200);


            accelerationPrevValueX2 = accelerationCurrentValueX2;
            accelerationPrevValueY2 = accelerationCurrentValueY2;
            accelerationPrevValueZ2 = accelerationCurrentValueZ2;


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt_accelx2 = findViewById(R.id.txt_accelX2);
        txt_currentAccelx2 = findViewById(R.id.txt_accel2xCurrent);
        txt_prevAccelx2 = findViewById(R.id.txt_accel2xPrevious);

        txt_accely2 = findViewById(R.id.txt_accelY2);
        txt_currentAccely2 = findViewById(R.id.txt_accel2yCurrent);
        txt_prevAccely2 = findViewById(R.id.txt_accel2yPrevious);

        txt_accelz2 = findViewById(R.id.txt_accelZ2);
        txt_currentAccelz2 = findViewById(R.id.txt_accel2zCurrent);
        txt_prevAccelz2 = findViewById(R.id.txt_accel2zPrevious);

        prog_shakeMeterr2X = findViewById(R.id.prog_shakeMeter2);
        prog_shakeMeterr2Y = findViewById(R.id.prog_shakeMeter3);
        prog_shakeMeterr2Z = findViewById(R.id.prog_shakeMeter4);


            mSensorManager2 = (SensorManager)getSystemService(SENSOR_SERVICE);
            mAccelerometer2 = mSensorManager2.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        viewport = graph.getViewport();
        viewport.setScrollable(true);
        viewport.setXAxisBoundsManual(true);
        graph.addSeries(series2);





    }

    protected void onResume() {
        super.onResume();
        mSensorManager2.registerListener(sensorEventListener2, mAccelerometer2, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager2.unregisterListener(sensorEventListener2);
    }



}