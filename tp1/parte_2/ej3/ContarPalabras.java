package tp1.parte_2.ej3;
import java.util.HashMap;
import java.util.Map;

public class ContarPalabras {

    public static Map<String, Integer> contar(String texto) {
        //mapeo string - cant. apariciones
        Map<String, Integer> frecuencias = new HashMap<>();

        // iteras sobre cada palabra normalizada (sin mayus) 
        // y usando como separador cualquier cosa que no sean caracteres
        for (String palabra : texto.toLowerCase().split("[^\\p{L}]+")) {
            //el if descarta el caso de que el texto arranque con algo distinto a un caracter 
            if (!palabra.isEmpty()) {
                //falopa el metodo este pero si "palabra" no estaba en frecuencias, la agrega 
                //con valor 1, y si ya estaba le agrega 1 al valor
                frecuencias.merge(palabra, 1, Integer::sum);
            }
        }
        return frecuencias;
    }
}