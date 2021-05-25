package com.barnese.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textAns;
    Map<Button,String> buttons = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textAns = findViewById(R.id.textAns);
        textAns.setText("0");


        buttons.put(findViewById(R.id.button0), "0");
        buttons.put(findViewById(R.id.button1), "1");
        buttons.put(findViewById(R.id.button2), "2");
        buttons.put(findViewById(R.id.button3), "3");
        buttons.put(findViewById(R.id.button4), "4");
        buttons.put(findViewById(R.id.button5), "5");
        buttons.put(findViewById(R.id.button6), "6");
        buttons.put(findViewById(R.id.button7), "7");
        buttons.put(findViewById(R.id.button8), "8");
        buttons.put(findViewById(R.id.button9), "9");
        buttons.put(findViewById(R.id.buttondiv), "/");
        buttons.put(findViewById(R.id.buttonplus), "+");
        buttons.put(findViewById(R.id.buttonminus), "-");
        buttons.put(findViewById(R.id.buttonmul), "x");
        buttons.put(findViewById(R.id.buttonAC), "c");
        buttons.put(findViewById(R.id.buttondot), ".");
        buttons.put(findViewById(R.id.buttoneq), "=");

        for(Button b : buttons.keySet() )
            b.setOnClickListener(this);





    }



    @Override
    public void onClick(View v) {


        String c = buttons.get(findViewById(v.getId()));


            String s = Model.enterValue(c.charAt(0));
            textAns.setText(s);



        }


    }
