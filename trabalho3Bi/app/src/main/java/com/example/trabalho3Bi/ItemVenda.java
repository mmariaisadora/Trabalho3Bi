package com.example.trabalho3Bi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalho3Bi.Controlers.ControllerItem;
import com.example.trabalho3Bi.classes.Item;

public class ItemVenda extends AppCompatActivity {

    private EditText edCodigoItem;
    private EditText edDescricaoItem;
    private EditText edValorItem;
    private Button btCadastrarItem;
    private EditText edNomeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_de_venda);

        edCodigoItem = findViewById(R.id.edCodigoItem);
        edDescricaoItem = findViewById(R.id.edDescricaoItem);
        edValorItem = findViewById(R.id.edValorItem);
        btCadastrarItem = findViewById(R.id.btCadastrarItem);
        edNomeItem = findViewById(R.id.edNomeItem);

        btCadastrarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastroItem();
                onRestart();
            }
        });
    }

    private void cadastroItem() {
            if(edNomeItem.getText().toString().isEmpty()){
                edNomeItem.setError("O nome deve ser informado.");
                return;
            }
            if(edCodigoItem.getText().toString().isEmpty()){
                edCodigoItem.setError("Código deve ser informado.");
                return;
            }
            if(edDescricaoItem.getText().toString().isEmpty()){
                edDescricaoItem.setError("Descrição deve ser informada.");
                return;
            }
            if(edValorItem.getText().toString().isEmpty()){
                edValorItem.setError("Valor deve ser informado.");
                return;
            }

            Item itens = new Item();
            itens.setCodigo(Integer.parseInt(edCodigoItem.getText().toString()));
            itens.setValor(Double.parseDouble(edValorItem.getText().toString()));
            itens.setNome(edNomeItem.getText().toString());
            itens.setDescricao(edDescricaoItem.getText().toString());

            ControllerItem.getInstanciaItem().salvarItens(itens);
            finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(ItemVenda.this,"Item" +
                " cadastrado.",Toast.LENGTH_LONG).show();
    }
}