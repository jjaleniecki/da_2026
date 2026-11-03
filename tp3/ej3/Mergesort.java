package tp3.ej3;

import java.util.Arrays;
import java.util.Random;

public class Mergesort {

    // Ordena el arreglo en el lugar usando un arreglo auxiliar único
    public static void mergeSort(int[] a) {
        if (a == null || a.length < 2) {
            return;
        }
        int[] aux = new int[a.length];
        mergeSort(a, aux, 0, a.length - 1);
    }

    // Divide: ordena a[izq..der] recursivamente
    private static void mergeSort(int[] a, int[] aux, int izq, int der) {
        if (izq >= der) {
            return; // caso base: 0 o 1 elemento
        }
        int medio = izq + (der - izq) / 2;
        mergeSort(a, aux, izq, medio);
        mergeSort(a, aux, medio + 1, der);
        merge(a, aux, izq, medio, der);
    }

    // Combina las mitades ordenadas a[izq..medio] y a[medio+1..der]
    private static void merge(int[] a, int[] aux, int izq, int medio, int der) {
        int i = izq; // índice de la mitad izquierda
        int j = medio + 1; // índice de la mitad derecha
        int k = izq; // índice en el auxiliar

        while (i <= medio && j <= der) {
            if (a[i] <= a[j]) { // <= mantiene la estabilidad
                aux[k++] = a[i++];
            } else {
                aux[k++] = a[j++];
            }
        }
        while (i <= medio) {
            aux[k++] = a[i++];
        }
        while (j <= der) {
            aux[k++] = a[j++];
        }

        // Copiar el tramo combinado de vuelta al arreglo original
        for (k = izq; k <= der; k++) {
            a[k] = aux[k];
        }
    }

    public static void main(String[] args) {
        int[] datos = { 38, 27, 43, 3, 9, 82, 10, -5, 27, 0 };
        System.out.println("Antes:   " + Arrays.toString(datos));
        mergeSort(datos);
        System.out.println("Después: " + Arrays.toString(datos));

        // Verificación contra Arrays.sort con arreglos aleatorios
        Random rnd = new Random(42);
        for (int t = 0; t < 1000; t++) {
            int[] x = new int[rnd.nextInt(50)];
            for (int i = 0; i < x.length; i++) {
                x[i] = rnd.nextInt(41) - 20;
            }
            int[] esperado = x.clone();
            Arrays.sort(esperado);
            mergeSort(x);
            if (!Arrays.equals(x, esperado)) {
                System.out.println("ERROR con: " + Arrays.toString(esperado));
                return;
            }
        }
        System.out.println("1000 pruebas aleatorias OK");
    }
}
