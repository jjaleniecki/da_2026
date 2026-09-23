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

    public int getGrado(){
        return this.grado;
    }

    public int getClave(){
        return this.clave;
    }

    public NodoBinomial getHermanoDer(){
        return this.hermanoDer;
    }

    public NodoBinomial getHijoIzq(){
        return this.hijoIzq;
    }

    public void setHermanoDer(NodoBinomial nuevo){
        this.hermanoDer = nuevo;
    }

    public void setPadre(NodoBinomial nuevo){
        this.padre = nuevo;
    }

    public void setHijoIzq(NodoBinomial nuevo) {
        this.hijoIzq = nuevo;
    }

    public void incrementarGrado(){
        this.grado++;
    }
}
