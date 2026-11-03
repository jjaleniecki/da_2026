import java.util.*;

public class GaleShapleyProhibidos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[][] forbidden = new boolean[n][n];
        for (int h = 0; h < n; h++)
            for (int m = 0; m < n; m++)
                forbidden[h][m] = sc.nextInt() == 1;
        sc.nextLine();

        int[][] prefH = leerPrefs(sc, n);   // lista de mujeres por hombre
        int[][] prefM = leerPrefs(sc, n);   // lista de hombres por mujer

        // rank inverso; INF = inaceptable (prohibido o no listado)
        int[][] rankM = new int[n][n];
        for (int[] row : rankM) Arrays.fill(row, n);
        for (int m = 0; m < n; m++)
            for (int r = 0; r < prefM[m].length; r++) {
                int h = prefM[m][r];
                if (!forbidden[h][m]) rankM[m][h] = r;
            }

        int[] husband = new int[n];  Arrays.fill(husband, -1);
        int[] wife    = new int[n];  Arrays.fill(wife, -1);
        int[] next    = new int[n];
        ArrayDeque<Integer> free = new ArrayDeque<>();
        for (int h = 0; h < n; h++) free.add(h);

        while (!free.isEmpty()) {
            int h = free.poll();
            // buscar la proxima m permitida a la que h todavia no propuso
            while (next[h] < prefH[h].length) {
                int m = prefH[h][next[h]++];
                if (forbidden[h][m] || rankM[m][h] == n) continue; // no mutuo
                if (husband[m] == -1) {                 // m libre: acepta
                    husband[m] = h; wife[h] = m; break;
                } else if (rankM[m][h] < rankM[m][husband[m]]) { // prefiere a h
                    int old = husband[m];
                    wife[old] = -1; free.add(old);      // el anterior vuelve a libre
                    husband[m] = h; wife[h] = m; break;
                }
                // si no, m rechaza y h sigue con la proxima
            }
            // si se agoto la lista sin pareja, h queda soltero (no vuelve a la cola)
        }

        StringBuilder sb = new StringBuilder("Pares:\n");
        for (int h = 0; h < n; h++)
            if (wife[h] != -1) sb.append(h).append(' ').append(wife[h]).append('\n');
        sb.append("Solteros H:");
        for (int h = 0; h < n; h++) if (wife[h] == -1) sb.append(' ').append(h);
        sb.append("\nSolteras M:");
        for (int m = 0; m < n; m++) if (husband[m] == -1) sb.append(' ').append(m);
        System.out.println(sb);
    }

    static int[][] leerPrefs(Scanner sc, int n) {
        int[][] p = new int[n][];
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) { p[i] = new int[0]; continue; }
            String[] t = line.split("\\s+");
            p[i] = new int[t.length];
            for (int j = 0; j < t.length; j++) p[i][j] = Integer.parseInt(t[j]);
        }
        return p;
    }
}