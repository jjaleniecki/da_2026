package tp3.ej5;

import java.util.Arrays;

public class MonedasGreedy {

    // Devuelve cuántas monedas de cada denominación se usan (mismo orden que
    // 'monedas')
    public static int[] cambio(int[] monedas, int monto) {
        int[] ordenadas = monedas.clone();
        Arrays.sort(ordenadas); // ascendente; se recorre de atrás para adelante

        int[] cantidades = new int[ordenadas.length];
        int restante = monto;

        // Elección voraz: en cada paso, la moneda más grande que entre en lo que falta
        for (int i = ordenadas.length - 1; i >= 0 && restante > 0; i--) {
            cantidades[i] = restante / ordenadas[i];
            restante -= cantidades[i] * ordenadas[i];
        }

        if (restante != 0) {
            throw new IllegalStateException("No se puede formar el monto con estas monedas");
        }
        return cantidades;
    }

    public static void main(String[] args) {
        int[] monedas = { 1, 2, 5, 10 };
        int monto = 18;

        int[] ordenadas = monedas.clone();
        Arrays.sort(ordenadas);
        int[] cantidades = cambio(monedas, monto);

        System.out.println("Monto: " + monto);
        int total = 0;
        for (int i = ordenadas.length - 1; i >= 0; i--) {
            if (cantidades[i] > 0) {
                System.out.println(cantidades[i] + " x moneda de " + ordenadas[i]);
                total += cantidades[i];
            }
        }
        System.out.println("Total de monedas: " + total);
    }
}
