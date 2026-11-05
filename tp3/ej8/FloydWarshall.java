package tp3.ej8;

public class FloydWarshall {
    public static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        
        int[][] distancias = {
            //   1   2   3   4
            { 0,  3, INF, 7 },  // Desde 1
            { 8,  0,  2, INF }, // Desde 2
            { 5, INF, 0,  1 },  // Desde 3
            { 2, INF, INF, 0 }   // Desde 4
        };

        System.out.println("Grafo inicial (INF=" + INF + "):");
        imprimirMatriz(distancias);

        // N = numero de vertices (filas/columnas)
        int N = distancias.length;

        int[][] matrizMinimas = calcularFloydWarshall(distancias, N);
        imprimirMatriz(matrizMinimas);
    }
    
    public static int[][] calcularFloydWarshall(int[][] distancias, int N) {
        
        // Crear una copia de la matriz inicial para las actualizaciones
        int[][] D = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(distancias[i], 0, D[i], 0, N);
        }

        // Bucle k: Vertice intermedio
        for (int k = 0; k < N; k++) {
            
            // Bucle i: Vertice de inicio
            for (int i = 0; i < N; i++) {
                
                // Bucle j: Vertice de destino
                for (int j = 0; j < N; j++) {
                    
                    // Condicion para evitar el overflow al sumar INF
                    if (D[i][k] != INF && D[k][j] != INF) {
                        
                        // Formula clave: D[i][j] = min( D[i][j], D[i][k] + D[k][j] )
                        if (D[i][j] > D[i][k] + D[k][j]) {
                            D[i][j] = D[i][k] + D[k][j];
                        }
                    }
                }
            }
            
            // Imprimir la matriz en cada paso k (para k=0, 1, 2, 3)
            System.out.println("\n--- 1. Matriz Intermedia D(k=" + (k + 1) + ") ---");
            imprimirMatriz(D);
        }

        return D;
    }

    public static void imprimirMatriz(int[][] m){
        System.out.println("-----Imprimiendo matriz-----");
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println("");
        }
    }
}
