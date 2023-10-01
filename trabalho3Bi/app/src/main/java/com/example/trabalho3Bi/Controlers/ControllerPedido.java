package com.example.trabalho3Bi.Controlers;

import com.example.trabalho3Bi.classes.Pedidos;

import java.util.ArrayList;

public class ControllerPedido {
    private static ControllerPedido instanciaPedidos;
    private ArrayList<Pedidos> listaPedidos;

    public static ControllerPedido getControlerPedidos(){
        if(instanciaPedidos == null){
            return instanciaPedidos = new ControllerPedido();
        }else{
            return instanciaPedidos;
        }
    }
    private ControllerPedido(){
        listaPedidos = new ArrayList<>();
    }
    public void salvarPedidos(Pedidos pedidos){
        listaPedidos.add(pedidos);
    }
    public ArrayList<Pedidos> retornarPedidos(){
        return listaPedidos;
    }
}
