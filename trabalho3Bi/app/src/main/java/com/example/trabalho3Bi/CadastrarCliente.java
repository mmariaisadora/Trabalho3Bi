package com.example.trabalho3Bi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalho3Bi.Controlers.ControllerCliente;
import com.example.trabalho3Bi.classes.Cliente;

public class CadastrarCliente extends AppCompatActivity {

    private EditText edNomeCliente;
    private EditText edCpfCliente;
    private TextView tvListaCliente;
    private Button btCadastrarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);
        edNomeCliente = findViewById(R.id.edNomeCliente);
        edCpfCliente = findViewById(R.id.edCpfCliente);
        tvListaCliente = findViewById(R.id.tvListaCliente);
        btCadastrarCliente = findViewById(R.id.btCadastrarCliente);

        atualizarListaCliente();

        btCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarCliente();
            }
        });
    }

    private void salvarCliente() {
        int cpf;
        if (edCpfCliente.getText().toString().isEmpty()) {
            edCpfCliente.setError("CPF deve ser informado.");
            return;
        } else {
            try {
                cpf = Integer.parseInt(edCpfCliente.getText().toString());
            } catch (Exception ex) {
                edCpfCliente.setError("Informe somente números.");
                return;
            }
        }
        if (edNomeCliente.getText().toString().isEmpty()) {
            edNomeCliente.setError("O nome do Cliente deve ser informado.");
            return;
        }

        Cliente clientes = new Cliente();
        clientes.setNome(edNomeCliente.getText().toString());
        clientes.setCpf(edCpfCliente.getText().toString());

        ControllerCliente.getInstanciaCliente().salvarCliente(clientes);

        Toast.makeText(CadastrarCliente.this,"Cliente" +
                " cadastrado.",Toast.LENGTH_LONG).show();

        finish();
    }

    private void atualizarListaCliente() {
        String texto = "";
        for (Cliente cliente : ControllerCliente.getInstanciaCliente().retornarCliente()) {
            texto += "Nome: " + cliente.getNome() + "\n" +
                    "CPF: " + cliente.getCpf() + "\n" +
                    "***********************************\n";
        }
        tvListaCliente.setText(texto);

    }
}