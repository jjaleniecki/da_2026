###  ¿Cómo se comportaría el algoritmo de Ordenamiento por Conteo si en el arreglo original se permiten elementos repetidos?
El funcionamiento fundamentalmente no se modificaría, ya que los elementos que ingresan siguen ubicandose según cuantos elementos menores tengan, y si hay duplicados se igualmente se ubican bien. Ejemplo:

El algoritmo encuentra dos elementos A[i] y A[j] iguales (i < j)
La condicion A[i] < A[j] es falsa y se entra en el bloque SINO
contador[i] <- contador[i]+1
Es decir, el elemento que apareció primero (A[i]) recibe un contador más alto
Coloquialmente, esto significa que, en caso de que 2 elementos sean iguales, el elemento que aparece antes se coloca posterior al que aparece después, por lo que el duplicado se coloca en una posición anterior al "original". Aunque ambos quedan en posiciones consecutivas, y no rompen el ordenamiento, se pierde el orden relativo.

### Modificar el algoritmo de Ordenamiento por Conteo para poder ordenar un arreglo de caracteres, de tal manera, que sean indistintas las letras mayúsculas y minúsculas, es decir que siempre se cumpla que:
* a<b,
* a<B,
* A<b,
* A<B
### En el arreglo resultante debe figurar cada letra en el mismo modo que en el arreglo original

```java
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

```