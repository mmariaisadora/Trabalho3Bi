package com.example.trabalho3Bi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalho3Bi.Controlers.ControllerCliente;
import com.example.trabalho3Bi.Controlers.ControllerItem;
import com.example.trabalho3Bi.Controlers.ControllerPedido;
import com.example.trabalho3Bi.classes.Cliente;
import com.example.trabalho3Bi.classes.Item;
import com.example.trabalho3Bi.classes.Pedidos;

import java.util.ArrayList;

public class PedidoVenda extends AppCompatActivity {

    private AutoCompleteTextView tvCliente;
    private Spinner spItem;
    private EditText edQuantidade;
    private EditText edValorUnitario;
    private Button btAdicionarItem;
    private TextView tvListaItensPedidos;
    private TextView tvValorTotalPedidos;
    private Button btSalvarParcelas;
    private Button btConcluirPedidos;
    private TextView tvErroCliente;
    private int posicaoSelecionado = 0;
    private ArrayList<Cliente> clientes;
    private RadioGroup rgSistema;
    private RadioButton rbPagamentoPrazo;
    private RadioButton rbPagamentoVista;
    private Spinner spParcelas;
    private ArrayList<Item> itensAdd;
    private ArrayList<Item> listaItens;
    private ArrayList<Cliente> listaCliente;
    private ArrayList<Pedidos> listaQuantidade;
    private ArrayList<Pedidos> listaValores;
    private int quantidadeTotal;
    private int valoresTotais;
    private TextView tvNomeCliente;
    Pedidos pedidos = new Pedidos();
    private String[]vetPrazo = new String[]{"1x","2x","3x","4x","5x","6x","7x","8x","9x","10x","11x","12x",};
    private String[]vetAVista = new String[]{"A vista"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_venda);
        tvCliente= findViewById(R.id.tvCliente);
        spItem= findViewById(R.id.spItem);
        edQuantidade= findViewById(R.id.edQuantidade);
        edValorUnitario= findViewById(R.id.edValorUnitario);
        btAdicionarItem= findViewById(R.id.btAdicionarItem);
        tvListaItensPedidos= findViewById(R.id.tvListaItemPedido);
        rgSistema= findViewById(R.id.rgSistema);
        rbPagamentoPrazo= findViewById(R.id.rbPagamentoPrazo);
        rbPagamentoVista= findViewById(R.id.rbPagamentoVista);
        tvValorTotalPedidos= findViewById(R.id.tvValorTotalPedidos);
        btConcluirPedidos= findViewById(R.id.btConcluirPedidos);
        tvErroCliente= findViewById(R.id.tvErroClientes);
        spParcelas= findViewById(R.id.spParcelas);
        tvNomeCliente= findViewById(R.id.tvNomeCliente);
        btSalvarParcelas= findViewById(R.id.btSalvarParcelas);

        popularListaClientes();
        carregarItens();

        btAdicionarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarItem();
                informarListaItensAdicionados();
                }
        });

        rgSistema.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                carregarPagamento();
            }
        });

        btSalvarParcelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                informarListaPedidos();
            }
        });

        btConcluirPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PedidoVenda.this,"Pedido salvo!",Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }


    public void popularListaClientes(){
        clientes = ControllerCliente.getInstanciaCliente().retornarCliente();
        String[]vetorClientes = new String[clientes.size()+1];
        vetorClientes[0] = "";
        for (int i = 0; i < clientes.size(); i++) {
            Cliente clientes1 = clientes.get(i);
            vetorClientes[i+1] = clientes1.getNome();
        }

        ArrayAdapter adapter = new ArrayAdapter(
                PedidoVenda.this,
                android.R.layout.simple_dropdown_item_1line, vetorClientes);

        tvCliente.setAdapter(adapter);
    }

    private void carregarItens(){
    itensAdd = ControllerItem.getInstanciaItem().retornarItens();
    String[]vetorItens = new String[itensAdd.size()];

        for (int i = 0; i < itensAdd.size(); i++) {
        Item itens1 = itensAdd.get(i);
        vetorItens[i] = itens1.getNome();
    }
    ArrayAdapter adapter = new ArrayAdapter(PedidoVenda.this, android.R.layout.simple_dropdown_item_1line, vetorItens);

        spItem.setAdapter(adapter);
}

    private void adicionarItem(){
        String itemSelecionado = spItem.getSelectedItem().toString();
        int clienteSelecionado = spItem.getSelectedItemPosition();
        if (clienteSelecionado < 0) {
            Toast.makeText(this, "Item deve ser informado.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edQuantidade.getText().toString().isEmpty()) {
            edQuantidade.setError("Quantidade deve ser informada.");
            return;
        }
        if (edValorUnitario.getText().toString().isEmpty()) {
            edValorUnitario.setError("Valor deve ser informado.");
            return;
        }

        pedidos.setNomeItem(itemSelecionado);
        pedidos.setQuantidade(Integer.parseInt(edQuantidade.getText().toString()));
        pedidos.setValor(Double.parseDouble(edValorUnitario.getText().toString()));
        ControllerPedido.getControlerPedidos().salvarPedidos(pedidos);

        Toast.makeText(PedidoVenda.this,"Item" +
                " adicionado.",Toast.LENGTH_LONG).show();

        guardarClientesSelecionados();
        guardarItensSelecionados();
        guardarQuantidadeTotalPedido();
        guardarValorTotalPedido();
        informarCliente();
        }

    private void informarCliente(){
        String texto = "";
        for (Cliente clientes : ControllerCliente.getInstanciaCliente().retornarCliente()) {
            texto += "Cliente: "+clientes.getNome()+"\n"+
                    "*******************************-\n";
        }
        tvNomeCliente.setText(texto);
    }

    private void guardarClientesSelecionados(){
        listaCliente = ControllerCliente.getInstanciaCliente().retornarCliente();
        String[]vetCliente = new String[listaCliente.size()];

        for (int i = 0; i < listaCliente.size(); i++) {
            Cliente clientes1 = listaCliente.get(i);
            vetCliente[i] = clientes1.getNome();
        }
        ArrayAdapter adapter = new ArrayAdapter(PedidoVenda.this, android.R.layout.simple_dropdown_item_1line, vetCliente);

        tvCliente.setAdapter(adapter);
    }

    private void guardarItensSelecionados(){
        listaItens = ControllerItem.getInstanciaItem().retornarItens();
        String[]vetItens = new String[listaItens.size()];

        for (int i = 0; i < listaItens.size(); i++) {
            Item itens = listaItens.get(i);
            vetItens[i] = itens.getNome();
        }
        ArrayAdapter adapter = new ArrayAdapter(PedidoVenda.this, android.R.layout.simple_dropdown_item_1line, vetItens);

        spItem.setAdapter(adapter);
    }

    private void guardarQuantidadeTotalPedido(){
        listaQuantidade = ControllerPedido.getControlerPedidos().retornarPedidos();
        int[]vetQuantidade = new int[listaQuantidade.size()];

        for (int i = 0; i < listaQuantidade.size(); i++) {
            Pedidos pedidos = listaQuantidade.get(i);
            vetQuantidade[i] = pedidos.getQuantidade();
            quantidadeTotal = vetQuantidade[i] + pedidos.getQuantidade();
        }
    }

    private void guardarValorTotalPedido(){
        listaValores = ControllerPedido.getControlerPedidos().retornarPedidos();
        Double[]vetValores = new Double[listaValores.size()];

        for (int i = 0; i < listaValores.size(); i++) {
            Pedidos pedidos = listaValores.get(i);
            vetValores[i] = pedidos.getValor();
            valoresTotais = (int) (vetValores[i] + pedidos.getValor());
        }
    }

    private void informarListaItensAdicionados(){
        String texto = "";
        ArrayList<Pedidos> lista = ControllerPedido.getControlerPedidos().retornarPedidos();
        for (Pedidos pedidos: lista) {
            texto += "Item: "+pedidos.getNomeItem()+" - Quantidade: "+pedidos.getQuantidade()+" - Valor total: "+pedidos.getQuantidade() * pedidos.getValor()+"\n";
        }
        tvListaItensPedidos.setText(texto);
    }

    private void carregarPagamento(){
        ArrayAdapter adapter = null;
        if (rbPagamentoPrazo.isChecked()){
            adapter = new ArrayAdapter<>(PedidoVenda.this, android.R.layout.simple_dropdown_item_1line, vetPrazo);
        }
        if(rbPagamentoVista.isChecked()){
            adapter = new ArrayAdapter<>(PedidoVenda.this, android.R.layout.simple_dropdown_item_1line, vetAVista);
        }
        spParcelas.setAdapter(adapter);
    }

    private void informarListaPedidos(){
        int parcelaSelecionada = 1 + spParcelas.getSelectedItemPosition();
        if(rbPagamentoPrazo.isChecked()){
            tvValorTotalPedidos.setText("Quantidade de parcelas: "+parcelaSelecionada+"\n"+
                    "Valor das parcelas: "+((quantidadeTotal * valoresTotais)*1.05)/parcelaSelecionada+"\n"+
                    "Valor total do pedido: "+(quantidadeTotal * valoresTotais)*1.05+"\n");
        }else {
            tvValorTotalPedidos.setText("Quantidade de parcelas: "+parcelaSelecionada+"\n"+
                    "Valor das parcelas: "+(quantidadeTotal*valoresTotais)*0.95+"\n"+
                    "Valor total do pedido: "+(quantidadeTotal * valoresTotais)*0.95+"\n");

        }
    }
}