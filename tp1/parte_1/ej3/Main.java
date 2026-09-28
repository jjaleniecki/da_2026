package tp1.parte_1.ej3;

public class Main {
    public static void main(String[] args) {
        Trie arbolin = new Trie();

        arbolin.agregarPalabra("valulu");
        arbolin.agregarPalabra("vals");
        arbolin.agregarPalabra("vino");
        arbolin.agregarPalabra("val");

        //arbolin.imprimirEstructura();

        arbolin.agregarSinonimo("valulu", "linda");
        arbolin.agregarSinonimo("vino", "rico");
        arbolin.imprimirEstructura();

        arbolin.listarPalabras();
    }
}
