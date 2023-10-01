package com.example.trabalho3Bi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btCliente;
    private Button btItem;
    private Button btPedidoVenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btCliente = findViewById(R.id.btCliente);
        btItem= findViewById(R.id.btItem);
        btPedidoVenda= findViewById(R.id.btPedidoVenda);

        btCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { cadastroCliente();} });

        btItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { cadastroListaItens();} });

        btPedidoVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { pedidoVenda();} });
    }

    public void cadastroCliente(){
        Intent intent = new Intent(MainActivity.this, CadastrarCliente.class);
        startActivity(intent);
    }
    public void cadastroListaItens(){
        Intent intent = new Intent(MainActivity.this, ItemVenda.class);
        startActivity(intent);
    }
    public void pedidoVenda(){
        Intent intent = new Intent(MainActivity.this, PedidoVenda.class);
        startActivity(intent);
    }
}