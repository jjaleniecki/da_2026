package tp1.parte_3.ej1;

import java.util.*;


public class GrafoDirigido {

    private final int n;
    private final List<List<Integer>> adyacencia;

    public GrafoDirigido(int n) {
        this.n = n;
        this.adyacencia = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adyacencia.add(new ArrayList<>());
        }
    }

    public void agregarArista(int origen, int destino) {
        validar(origen);
        validar(destino);
        adyacencia.get(origen).add(destino);
    }

    private void validar(int v) {
        if (v < 0 || v >= n) {
            throw new IllegalArgumentException("Vértice inválido: " + v);
        }
    }

    // BFS (recorrido en anchura)

    public List<Integer> bfs(int inicio) {
        validar(inicio);
        List<Integer> orden = new ArrayList<>();
        boolean[] visitado = new boolean[n];
        Queue<Integer> cola = new LinkedList<>();

        visitado[inicio] = true;
        cola.add(inicio);

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            orden.add(actual);
            for (int vecino : adyacencia.get(actual)) {
                if (!visitado[vecino]) {
                    visitado[vecino] = true; // se marca al poner en la cola para evitar duplicados
                    cola.add(vecino);
                }
            }
        }
        return orden;
    }


    // DFS (recorrido en prfundidad)

    public List<Integer> dfs(int inicio) {
        validar(inicio);
        List<Integer> orden = new ArrayList<>();
        boolean[] visitado = new boolean[n];
        dfsRecursivo(inicio, visitado, orden);
        return orden;
    }

    private void dfsRecursivo(int v, boolean[] visitado, List<Integer> orden) {
        visitado[v] = true;
        orden.add(v);
        for (int vecino : adyacencia.get(v)) {
            if (!visitado[vecino]) {
                dfsRecursivo(vecino, visitado, orden);
            }
        }
    }

   
    // Recorridos que cubren todo el grafo contemplando que desde un nodo inicial no se llegue a todo el resto de nodos
    public List<Integer> bfsCompleto() {
        List<Integer> orden = new ArrayList<>();
        boolean[] visitado = new boolean[n];
        for (int s = 0; s < n; s++) {
            if (visitado[s]) continue;
            Queue<Integer> cola = new LinkedList<>();
            visitado[s] = true;
            cola.add(s);
            while (!cola.isEmpty()) {
                int actual = cola.poll();
                orden.add(actual);
                for (int vecino : adyacencia.get(actual)) {
                    if (!visitado[vecino]) {
                        visitado[vecino] = true;
                        cola.add(vecino);
                    }
                }
            }
        }
        return orden;
    }

    public List<Integer> dfsCompleto() {
        List<Integer> orden = new ArrayList<>();
        boolean[] visitado = new boolean[n];
        for (int s = 0; s < n; s++) {
            if (!visitado[s]) {
                dfsRecursivo(s, visitado, orden);
            }
        }
        return orden;
    }

}
