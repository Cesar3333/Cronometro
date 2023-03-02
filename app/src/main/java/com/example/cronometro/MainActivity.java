package com.example.cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private int segundos=0;
    private boolean running;
    private List<String> tiempovueltas = new ArrayList<>();

    private int numerovultas =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTimer();
    }
    public void onInicio(View view){
        running=true;
    }

    public void onPausar(View view){
        running=false;
    }

    public void onRestaurar(View view){
        running=false;
        segundos=0;

    }



    private void startTimer() {
        final TextView Timer = findViewById(R.id.Timer);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hrs = segundos / 3600;
                int mins = (segundos % 3600) / 60;
                int sec = segundos & 60;
                String time = String.format("%02d:%02d:%02d", hrs, mins, sec);
                Timer.setText(time);
                if (running) {
                    segundos++;
                }
                handler.postDelayed(this, 0);
            }
        });
    }
    public void vueltas (View view){
        String tiempodevueltas = ((TextView) findViewById(R.id.Timer)).getText().toString();
        tiempodevueltas = numerovultas + " . " + tiempodevueltas;
        tiempovueltas.add(tiempodevueltas);
        numerovultas++;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tiempovueltas);
        ListView listView = findViewById(R.id.lv_vuelta);
        listView.setAdapter(adapter);
    }


}
