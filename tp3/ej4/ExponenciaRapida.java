package tp3.ej4;

import java.math.BigInteger;

public class ExponenciaRapida {
    public static long potencia(long a, int n) {
        if (n < 0) {
            System.out.println("Error");
            return 0;
        }
        if (n == 0) {
            return 1; // caso base
        }
        long mitad = potencia(a, n / 2); // una sola llamada recursiva
        if (n % 2 == 0) {
            return mitad * mitad;
        } else {
            return a * mitad * mitad;
        }
    }

    public static void main(String[] args) {
        System.out.println("3^13 recursiva:  " + potencia(3, 13));

        // Verificación contra BigInteger.pow (rangos que no desbordan long)
        for (int a = -5; a <= 5; a++) {
            for (int n = 0; n <= 20; n++) {
                long esperado = BigInteger.valueOf(a).pow(n).longValueExact();
                if (potencia(a, n) != esperado){
                    System.out.println("ERROR con a=" + a + ", n=" + n);
                    return;
                }
            }
        }
        System.out.println("Todas las pruebas OK");
    }

}
