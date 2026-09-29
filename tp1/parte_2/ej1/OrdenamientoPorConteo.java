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
        /*
        ej: input 'C' 'a' 'B'
        1: C <= a = false => cont[0]++ osea el contador asociado a C
        2: C <= B = false => cont[0]++ 
        3: a < B = true => cont[2]++ osea el contador asociado a B

        el for final acomoda
        ord[cont[0]] = arr[0] osea ord[2] = C
        ord[cont[1]] = arr[1] osea ord[0] = a
        ord[cont[2]] = arr[2] osea ord[1] = B
        
        ord final = {a, B, C}
        
        */
        return ord;
    }
}

