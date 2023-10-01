package com.example.trabalho3Bi.Controlers;

import com.example.trabalho3Bi.classes.Item;

import java.util.ArrayList;

public class ControllerItem {
    private static ControllerItem instanciaItem;
    private ArrayList<Item> listaItens;

    public static ControllerItem getInstanciaItem(){
        if(instanciaItem == null){
            return instanciaItem = new ControllerItem();
        }else{
            return instanciaItem;
        }
    }
    private ControllerItem(){
        listaItens = new ArrayList<>();
    }
    public void salvarItens(Item itens){
        listaItens.add(itens);
    }
    public ArrayList<Item> retornarItens(){
        return listaItens;
    }
}
