package com.example.trabalho3Bi.Controlers;

import com.example.trabalho3Bi.classes.Cliente;

import java.util.ArrayList;

public class ControllerCliente {
    private static ControllerCliente instanciaCliente;
    private ArrayList<Cliente> listaCliente;

    public static ControllerCliente getInstanciaCliente(){
        if(instanciaCliente == null){
            return instanciaCliente = new ControllerCliente();
        }else{
            return instanciaCliente;
        }
    }
    private ControllerCliente(){
        listaCliente = new ArrayList<>();
    }
    public void salvarCliente(Cliente cliente){
        listaCliente.add(cliente);
    }
    public ArrayList<Cliente> retornarCliente(){
        return listaCliente;
    }
}

