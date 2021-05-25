package com.barnese.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textAns;
    Map<Button,String> buttons = new HashMap<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textAns = findViewById(R.id.textAns);
        textAns.setText("0");


        buttons.put((Button) findViewById(R.id.button0), "0");
        buttons.put((Button) findViewById(R.id.button1), "1");
        buttons.put((Button) findViewById(R.id.button2), "2");
        buttons.put((Button) findViewById(R.id.button3), "3");
        buttons.put((Button) findViewById(R.id.button4), "4");
        buttons.put((Button) findViewById(R.id.button5), "5");
        buttons.put((Button) findViewById(R.id.button6), "6");
        buttons.put((Button) findViewById(R.id.button7), "7");
        buttons.put((Button) findViewById(R.id.button8), "8");
        buttons.put((Button) findViewById(R.id.button9), "9");
        buttons.put((Button) findViewById(R.id.buttondiv), "/");
        buttons.put((Button) findViewById(R.id.buttonplus), "+");
        buttons.put((Button) findViewById(R.id.buttonminus), "-");
        buttons.put((Button) findViewById(R.id.buttonmul), "x");
        buttons.put((Button) findViewById(R.id.buttonAC), "c");
        buttons.put((Button) findViewById(R.id.buttondot), ".");
        buttons.put((Button) findViewById(R.id.buttoneq), "=");

        for(Button b : buttons.keySet() )
            b.setOnClickListener(this);





    }

    private String client(String s){
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI());


        Response val = null;
        try {
            System.out.println("TEST");

            System.out.println(s);
            val = target.path("rest").path("hello/{info}").resolveTemplate("info", s).request(MediaType.TEXT_PLAIN_TYPE).get();


            System.out.println(val);
        }
        catch(Exception ex){
            System.out.println("ERROR!!!");
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause().toString());
            ex.printStackTrace();
        }
        return val.readEntity(String.class);
    }



    @Override
    public void onClick(View v) {

        CompletableFuture<String> cf = new CompletableFuture<>();

        cf.supplyAsync(() -> {return buttons.get(findViewById(v.getId()));}).
                thenApply(t -> client(t)).thenAccept(s -> textAns.setText(s));
        }

    private static URI getBaseURI() {
        //here server is running on 4444 port number and project name is restfuljersey
        return UriBuilder.fromUri("http://10.0.2.2:8080/CalculatorBackEndII/").build();
    }

    }
