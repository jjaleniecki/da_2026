package tp1.parte_1.ej3;

public class Main {
    public static void main(String[] args) {
        Trie arbolin = new Trie();

        arbolin.agregarPalabra("juego");
        arbolin.agregarPalabra("jugo");
        arbolin.agregarPalabra("vino");
        arbolin.agregarPalabra("vintage");

        //arbolin.imprimirEstructura();

        arbolin.agregarSinonimo("juego", "jogo");
        arbolin.agregarSinonimo("vino", "rico");
        arbolin.imprimirEstructura();

        arbolin.listarPalabras();
    }
}
