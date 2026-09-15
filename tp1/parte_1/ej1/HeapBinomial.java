package tp1.parte_1.ej1;

public class HeapBinomial {
    private NodoBinomial raiz;
    private int count;

    public HeapBinomial(NodoBinomial raiz){
        this.raiz = raiz;
        count = 0;
    }

    public boolean is_empty(){
        return raiz==null;
    }

    public void insert(int val){
        NodoBinomial nuevo = new NodoBinomial(val);
        HeapBinomial heapTemp = new HeapBinomial(nuevo);
        raiz = union(heapTemp);
    }

    public NodoBinomial union(HeapBinomial heap){
        NodoBinomial raizNueva = null;
        return raizNueva;
    }
}
