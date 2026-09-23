package tp1.parte_1.ej1;

public class HeapBinomial {
    private NodoBinomial head;
    private int count;

    public HeapBinomial(){
        this.head = null;
        count = 0;
    }

    public HeapBinomial(NodoBinomial head){
        this.head = head;
        count = 0;
    }

    public void setHead(NodoBinomial head){
        this.head = head;
    }

    public boolean is_empty(){
        return head==null;
    }

    public void insert(int val){
        NodoBinomial nuevo = new NodoBinomial(val);
        HeapBinomial heapTemp = new HeapBinomial(nuevo);
        head = union(heapTemp);
    }

    public NodoBinomial union(HeapBinomial heap) {
        NodoBinomial nuevaCabeza = merge(this, heap);

        // Vaciar los heaps originales
        this.head = null;
        heap.head = null;

        if (nuevaCabeza == null)
            return null;

        NodoBinomial prev = null;
        NodoBinomial curr = nuevaCabeza;
        NodoBinomial next = curr.getHermanoDer();

        while (next != null) {
            boolean tresConsecutivos = (next.getHermanoDer() != null
                    && next.getHermanoDer().getGrado() == curr.getGrado());

            if (curr.getGrado() != next.getGrado() || tresConsecutivos) {
                prev = curr;
                curr = next;
            } else if (curr.getClave() <= next.getClave()) {
                curr.setHermanoDer(next.getHermanoDer());
                linkTree(curr, next);
            } else {
                if (prev == null) {
                    nuevaCabeza = next;
                } else {
                    prev.setHermanoDer(next);
                }
                linkTree(next, curr);
                curr = next;
            }
            next = curr.getHermanoDer();
        }

        return nuevaCabeza;
    }

    private static NodoBinomial merge(HeapBinomial heap1, HeapBinomial heap2) {
        //ponele que unir(H1,H2)
        if (heap1.head == null) return heap2.head;
        if (heap2.head == null) return heap1.head;
        
        NodoBinomial head;
        NodoBinomial heap1Next = heap1.head;
        NodoBinomial heap2Next = heap2.head;
        
        if (heap1.head.getGrado() <= heap2.head.getGrado()) {
            head = heap1.head;
            heap1Next = heap1Next.getHermanoDer();
        } else {
            head = heap2.head;
            heap2Next = heap2Next.getHermanoDer();
        }
        
        NodoBinomial tail = head;
        while (heap1Next != null && heap2Next != null) {
            if (heap1Next.getGrado() <= heap2Next.getGrado()) {
                tail.setHermanoDer(heap1Next);
                heap1Next = heap1Next.getHermanoDer();
            } else {
                tail.setHermanoDer(heap2Next);
                heap2Next = heap2Next.getHermanoDer();
            }
            tail = tail.getHermanoDer();
        }
        
        tail.setHermanoDer((heap1Next != null) ? heap1Next : heap2Next);
        return head;
    }

    private void linkTree(NodoBinomial raizMenor, NodoBinomial otro) {
        otro.setPadre(raizMenor);
        otro.setHermanoDer(raizMenor.getHijoIzq());
        raizMenor.setHijoIzq(otro);
        raizMenor.incrementarGrado();
    }

    public void imprimir() {
        System.out.println("Binomial heap:");
        if (head != null) {
            imprimirNodo(head, 0);
        }
    }

    private void imprimirNodo(NodoBinomial nodo, int nivel) {
        NodoBinomial curr = nodo;
        while (curr != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < nivel; i++)
                sb.append("  ");
            sb.append(curr.getClave()).append(" (grado ").append(curr.getGrado()).append(")");
            System.out.println(sb);
            if (curr.getHijoIzq() != null) {
                imprimirNodo(curr.getHijoIzq(), nivel + 1);
            }
            curr = curr.getHermanoDer();
        }
    }

    public NodoBinomial buscarMin() {
        //busca solo en las raices pq siempre son mínimos de su arbol
        //es de orden O(log n) pq hay a lo sumo O(log n) arboles en la lista de raices
        if (head == null) {
            return null;
        }

        NodoBinomial min = head;
        NodoBinomial actual = head.getHermanoDer();

        while (actual != null) {
            if (actual.getClave() < min.getClave()) {
                min = actual;
            }
            actual = actual.getHermanoDer();
        }

        return min;
    }
}
