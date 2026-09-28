package tp1.parte_2.ej1;

public class OrdenamientoPorConteo{

    public static char[] ordenarConteo(char[] arr) {
        int length = arr.length;
        char[] ord = new char[length];
        int[] cont = new int[length];

        int i, j;
        for (i = 0; i < length; i++)
            cont[i] = 0;

        for (i = 0; i < length - 1; i++) {
            for(j = i+1; j < length; j++){
                if(Character.toLowerCase(arr[i])<=Character.toLowerCase(arr[j])){
                    cont[j] = cont[j]+1;
                } else{
                    cont[i] = cont[i]+1;
                }
            }
        }

        for(i = 0; i < length; i++){
            ord[cont[i]] = arr[i];
        }
        return ord;
    }
}

