package tp1.parte_1.ej3;
import java.util.LinkedList;

public class Trie {
    private Nodo root;

    public Trie(){
        root = new Nodo('*');
    }

    public Nodo agregarPalabra(String word){
        //analisis de eficiencia: O(n)
        //posibilidades (el tamaño del alfabeto)
        Nodo actual = root;
        
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            LinkedList<Nodo> hijos = actual.getHijos();
            boolean esHijo = false;
            for(Nodo hijo : hijos){
                if(hijo.getValor() == curr){
                    //el caracter está en los hijos
                    esHijo = true;
                    actual = hijo;
                }
            }
            if(!esHijo){
                //si no era hijo, lo agrego y me meto en el
                Nodo nuevoHijo = new Nodo(curr);
                hijos.add(nuevoHijo);
                actual = nuevoHijo;
            }
        }
        actual.setFinal();
        return actual;
    }

    public void agregarSinonimo(String palabra, String sinonimo){
        palabra = palabra.toLowerCase();
        sinonimo = sinonimo.toLowerCase();

        //si la palabra no está, la agrego y si está la obtengo
        Nodo nPalabra = agregarPalabra(palabra);

        if(!nPalabra.getSinonimos().contains(sinonimo)){
            Nodo nSinonimo = agregarPalabra(sinonimo);
            
            nPalabra.addSinonimo(sinonimo);
            //System.out.println(nPalabra.getSinonimos());
            nSinonimo.addSinonimo(palabra);
        }
    }

    public void imprimirEstructura() {
        for (Nodo hijo : root.getHijos()) {
            imprimirEstructura(hijo, 0);
        }
    }

    private void imprimirEstructura(Nodo n, int profundidad) {
        String indent = "  ".repeat(profundidad);
        System.out.println(indent + n.getValor() + (n.esFinal() ? " *" + n.getSinonimos().toString() : ""));

        for (Nodo hijo : n.getHijos()) {
            imprimirEstructura(hijo, profundidad + 1);
        }
    }

    public LinkedList<String> obtenerSinonimos(String palabra){
        //obtengo el nodo de la palabra
        Nodo nPalabra = agregarPalabra(palabra);
        return nPalabra.getSinonimos();
    }

    public void listarPalabras(){
        //analisis de eficiencia: O(m*n) porque se recorre por cada rama su longitud max
        listarPalabrasAux("", root);
    }

    private void listarPalabrasAux(String prefix, Nodo actual){
        if(actual.esFinal()){
            System.out.println(prefix + actual.getValor());
        }
        LinkedList<Nodo> hijos = actual.getHijos();
        for (int i = 0; i < hijos.size(); i++) {
            Nodo hijo = hijos.get(i);
            String pref = prefix+actual.getValor();
            listarPalabrasAux(pref, hijo);
        }
    }

}
