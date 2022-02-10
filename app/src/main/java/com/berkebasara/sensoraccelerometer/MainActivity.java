package com.berkebasara.sensoraccelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import java.text.BreakIterator;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

        TextView txt_currentAccel, txt_prevAccel, txt_acceleration, txt_accelerationX, txt_accelerationY, txt_accelerationZ,txt_prevAccelX, txt_currentAccelX, txt_prevAccelY, txt_currentAccelY, txt_prevAccelZ,txt_currentAccelZ, textView;
        ProgressBar prog_shakeMeter;

        // define the sensor variables.
        private  SensorManager mSensorManager;
        private  Sensor mAccelerometer;

        private double accelerationCurrentValue;
        private double accelerationCurrentValueX,
                accelerationPreviousValueX,
                accelerationCurrentValueY,
                accelerationPreviousValueY,
                accelerationCurrentValueZ,
                accelerationPreviousValueZ;

     private double accelerationPreviousValue;

     private int pointsPlotted = 5; // Number of plotted in the graph.
     private int graphIntervalCounter = 0; // Counter variable(Sensor moved).
     private Viewport viewport;
     private Button button;


    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
            new DataPoint(0, 1),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6),


    });



    DecimalFormat decimalFormat = new DecimalFormat("#.####");

    private BreakIterator mTextField;
    private SensorEventListener sensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                //Provides solution of how much phone moved?(x,y,z coordinates)

                accelerationCurrentValue = Math.sqrt((x*x + y*y + z*z));
                accelerationCurrentValueX = x;
                accelerationCurrentValueY = y;
                accelerationCurrentValueZ = z;

                double changeInAccelerationX = Math.abs((accelerationCurrentValueX - accelerationPreviousValueX));
                double changeInAccelerationY = Math.abs((accelerationCurrentValueY - accelerationPreviousValueY));
                double changeInAccelerationZ = Math.abs((accelerationCurrentValueZ - accelerationPreviousValueZ));
                double changeInAcceleration = Math.abs((accelerationCurrentValue - accelerationPreviousValue));
                accelerationPreviousValue = accelerationCurrentValue;
                accelerationPreviousValueX = accelerationCurrentValueX;
                accelerationPreviousValueY = accelerationCurrentValueY;
                accelerationPreviousValueZ = accelerationCurrentValueZ;


                // Updated numbers
                txt_currentAccel.setText(("Current = "+ decimalFormat.format(accelerationCurrentValue)));
                txt_prevAccel.setText(("Prev = "+ decimalFormat.format(accelerationPreviousValue)));
                txt_acceleration.setText(("AcceleChange = "+ decimalFormat.format(changeInAcceleration)));

                txt_currentAccelX.setText(("CrntX = "+ decimalFormat.format(accelerationCurrentValueX)));
                txt_prevAccelX.setText(("PrevX = "+ decimalFormat.format(accelerationPreviousValueX)));
                txt_accelerationX.setText(("AcclChgX = "+ decimalFormat.format(changeInAccelerationX)));

                txt_currentAccelY.setText(("CrntY = "+ decimalFormat.format(accelerationCurrentValueY)));
                txt_prevAccelY.setText(("PrevY = "+ decimalFormat.format(accelerationPreviousValueY)));
                txt_accelerationY.setText(("AcclChgY = "+ decimalFormat.format(changeInAccelerationY)));

                txt_currentAccelZ.setText(("CrntZ = "+ decimalFormat.format(accelerationCurrentValueZ)));
                txt_prevAccelZ.setText(("PrevZ = "+ decimalFormat.format(accelerationPreviousValueZ)));
                txt_accelerationZ.setText(("AcclChgZ = "+ decimalFormat.format(changeInAccelerationZ)));






                prog_shakeMeter.setProgress((int) changeInAcceleration);// Visual olarak change i görmemizi sağlıyor.

                // Dalganın amplitude na göre renk değişimi sağlıyor.
            if (changeInAcceleration != 0.0001)
            {
                if (changeInAcceleration >= 12.1644)
                {
                    txt_acceleration.setBackgroundColor(Color.parseColor("#fc0303"));
                }

                else if (changeInAcceleration >= 6.3765 && changeInAcceleration <= 12.1644)
                {

                    txt_acceleration.setBackgroundColor(Color.parseColor("#fc3503"));
                }
                else if (changeInAcceleration >= 3.3354 && changeInAcceleration <= 6.3765)
                {

                    txt_acceleration.setBackgroundColor(Color.parseColor("#fc7303"));
                }
                else if (changeInAcceleration >= 1.7658 && changeInAcceleration <= 3.3354)
                {

                    txt_acceleration.setBackgroundColor(Color.parseColor("#fc8c03"));
                }
                else if (changeInAcceleration >= 0.9025 && changeInAcceleration <= 1.7658)
                {

                    txt_acceleration.setBackgroundColor(Color.parseColor("#fcba03"));
                }
                else if (changeInAcceleration >= 0.3825 && changeInAcceleration <= 0.9025)
                {

                    txt_acceleration.setBackgroundColor(Color.parseColor("#d2fc03"));
                }
                else if (changeInAcceleration >= 0.1373 && changeInAcceleration <= 0.3825)
                {

                    txt_acceleration.setBackgroundColor(Color.parseColor("#03fcc2"));
                }
                else if (changeInAcceleration >= 0.0166 && changeInAcceleration <= 0.1373)
                {

                    txt_acceleration.setBackgroundColor(Color.parseColor("#fc03f4"));
                }

                else
                {
                    txt_acceleration.setBackgroundColor(Color.WHITE);
                }


            }

                // update the graph
                pointsPlotted +=1;// x valuelerin değişmesi için.

                series.appendData(new DataPoint(pointsPlotted, changeInAcceleration),true,pointsPlotted);
                viewport.setMaxX(pointsPlotted);
                viewport.setMinX(pointsPlotted - 200);
        }
            

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }; // Listen any changes on accelerometer.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity2();
            }
        });





        txt_acceleration = findViewById(R.id.txt_accel);
        txt_currentAccel = findViewById(R.id.txt_currentAcel);
        txt_prevAccel = findViewById(R.id.txt_prevAccel);

        txt_accelerationX = findViewById(R.id.txt_accelX);
        txt_currentAccelX = findViewById(R.id.txt_currentAcelX);
        txt_prevAccelX = findViewById(R.id.txt_prevAccelX);

        txt_accelerationY = findViewById(R.id.txt_accelY);
        txt_currentAccelY = findViewById(R.id.txt_currentAcelY);
        txt_prevAccelY = findViewById(R.id.txt_prevAccelY);

        txt_accelerationZ = findViewById(R.id.txt_accelZ);
        txt_currentAccelZ = findViewById(R.id.txt_currentAcelZ);
        txt_prevAccelZ = findViewById(R.id.txt_prevAccelZ);


        prog_shakeMeter = findViewById(R.id.prog_shakeMeter);


        // initialize sensor objects
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Graph Code

        GraphView graph = (GraphView) findViewById(R.id.graph);
        viewport = graph.getViewport();
        viewport.setScrollable(true);
        viewport.setXAxisBoundsManual(true);
        graph.addSeries(series);




    }

    public void MainActivity2()
    {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(sensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        // SensorManager uses SensorEventListener which uses in onResume part.
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(sensorEventListener);

    }









}





