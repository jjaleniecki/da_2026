package  tp1.parte_1.ej1;
public class NodoBinomial {
    private NodoBinomial padre;
    private int clave;
    private int grado;
    private NodoBinomial hijoIzq;
    private NodoBinomial hermanoDer;

    public NodoBinomial(int val){
        padre = null;
        clave = val;
        grado = 0;
        hijoIzq = null;
        hermanoDer = null;
    }
}
