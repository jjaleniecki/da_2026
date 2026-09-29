package tp1.parte_1.ej2;

public class ConjuntosDisjuntos {
    private int[] padre;
    private int[] rank;

    public ConjuntosDisjuntos(int n) {
        padre = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = 0;
            padre[i] = i;
        }
    }

    public int find(int x) {
        //complejidad: O(n), sube por todos los padres hasta la raiz, osea que tarda la altura del arbol
        boolean encontrao = false;

        while (!encontrao) {
            if (padre[x] == x) {
                encontrao = true;
            } else {
                x = padre[x];
            }
        }
        return x;
    }

    public int findOptimizado(int x) {
        //optimizado con path compression. 
        //orden: O(log n) por culpa del union by rank (no por el path compression)
        //si sacas el union by rank y solo haces path compression entonces es O(n) peor caso
        //pero amortizado mejora
        
        if (padre[x] != x) {
            //cada nodo termina apuntando a la raiz en vez de a su padre
            //por lo que cada busqueda a partir de la segunda se hace en un solo paso

            padre[x] = findOptimizado(padre[x]);
        }
        return padre[x];
    }

    public boolean union(int x, int y) {
        //complejidad: son 2 find, asi que es O(2n) = O(n)
        boolean unioneo = false;
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            unioneo = true;
            padre[rootX] = rootY;
        }
        return unioneo;
    }

    public boolean unionOptimizado(int x, int y) {
        //optimizado con union by rank.
        //complejidad: O(log n) porque la altura nunca supera el log n
        boolean unioneo = false;
        int rootX = findOptimizado(x);
        int rootY = findOptimizado(y);

        if (rootX != rootY) {
            //la raiz menor se ubica debajo de la mayor, si son iguales 
            //el arbol crece
            unioneo = true;
            if (rank[rootX] < rank[rootY]) {
                padre[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                padre[rootY] = rootX;
            } else {
                padre[rootY] = rootX;
                rank[rootX]++;
            }
        }
        return unioneo;
    }
}