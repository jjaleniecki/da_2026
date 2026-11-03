package tp3.ej2;

public class Main {

    static final char[] LETRAS = { 'A', 'B', 'C', 'D', 'E' };
    static int total = 0;
    static int validas = 0;

    static boolean esVocal(char c) {
        return c == 'A' || c == 'E';
    }

    // Se valida recién con la permutación completa (fuerza bruta: sin poda)
    static boolean sinVocalesSeguidas(char[] perm) {
        for (int i = 0; i < perm.length - 1; i++) {
            if (esVocal(perm[i]) && esVocal(perm[i + 1])) {
                return false;
            }
        }
        return true;
    }

    static void generar(char[] actual, boolean[] usada, int pos) {
        if (pos == LETRAS.length) {
            total++;
            if (sinVocalesSeguidas(actual)) {
                validas++;
                System.out.println(new String(actual));
            }
            return;
        }

        for (int i = 0; i < LETRAS.length; i++) {
            if (!usada[i]) {
                usada[i] = true;
                actual[pos] = LETRAS[i];
                generar(actual, usada, pos + 1);
                usada[i] = false; // backtrack
            }
        }
    }

    public static void main(String[] args) {
        generar(new char[LETRAS.length], new boolean[LETRAS.length], 0);
        System.out.println("Permutaciones totales: " + total);
        System.out.println("Sin vocales seguidas: " + validas);
    }
}