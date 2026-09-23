package tp1.parte_1.ej1;

public class Main {
    public static void main(String[] args) {
        HeapBinomial heap = new HeapBinomial();

        // Caso 1: insertar 7 elementos, fuerza fusiones en cascada (111 en binario)
        int[] valores = { 5, 3, 8, 1, 9, 2, 7 };
        for (int v : valores) {
            heap.insert(v);
        }
        heap.imprimir();

        System.out.println("---");

        // Caso 2: union de dos heaps con árboles de mismo grado
        HeapBinomial heapA = new HeapBinomial();
        heapA.insert(10);
        heapA.insert(20);
        heapA.insert(30);

        HeapBinomial heapB = new HeapBinomial();
        heapB.insert(15);
        heapB.insert(25);
        heapB.insert(35);

        NodoBinomial resultado = heapA.union(heapB);
        heapA.setHead(resultado); // o como corresponda según tu union()
        heapA.imprimir();
    }
}
