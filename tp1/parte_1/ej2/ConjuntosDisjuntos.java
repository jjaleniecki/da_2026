package tp1.parte_1.ej2;

public class ConjuntosDisjuntos {
    private int[] arr;

    public ConjuntosDisjuntos(int n){
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
    }

    public int find(int x){
        boolean encontrao = false;
        
        int i = 0, num = this.arr[i];

        while(!encontrao && num != x){
            if (num == x){
                encontrao = true;
            } else{
                i++;
                num = arr[i];
            }
        }
        return num;
    }

    public boolean union(int x, int y){
        boolean unioneo = false;
        int rootX = find(x);
        int rootY = find(y);
        

        if (rootX != rootY){
            unioneo = true;
            arr[rootX] = rootY;
        }
        return unioneo;
    }

}
