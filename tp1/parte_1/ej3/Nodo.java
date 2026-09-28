package tp1.parte_1.ej3;

import java.util.LinkedList;

public class Nodo {
    private boolean esFinal;
    private char valor;
    private LinkedList<Nodo> hijos;
    //decidi hacerlo cada nodo pueda tener sinonimos si es terminal, podria hacerse con 
    //una lista de nodos en vez de string pero tendrias que poder referenciar al padre de c/u
    private LinkedList<String> sinonimos;

    public Nodo(char c){
        esFinal = false;
        hijos = new LinkedList<>();
        valor = c;
        sinonimos = null;
    }

    public void agregarHijo(char c){
        Nodo nuevo = new Nodo(c);
        hijos.add(nuevo);
    }

    public LinkedList<Nodo> getHijos(){
        return this.hijos;
    }

    public char getValor(){
        return this.valor;
    }

    public boolean esFinal(){
        return this.esFinal;
    }

    public void setFinal(){
        if(sinonimos == null){
            sinonimos = new LinkedList<String>();
        }
        this.esFinal = true;
    }

    public void addSinonimo(String s){
        this.sinonimos.add(s);
    }

    public LinkedList<String> getSinonimos(){
        return this.sinonimos;
    }
}
