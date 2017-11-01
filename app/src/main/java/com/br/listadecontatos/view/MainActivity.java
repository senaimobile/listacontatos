package com.br.listadecontatos.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.br.listadecontatos.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onContatosClick(View view) {
        Intent it = new Intent(getBaseContext(), ContatoActivity.class);
        startActivity(it);

    }
}
