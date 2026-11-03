import java.util.*;


public class EmpresasEstudiantes {

  
    // Devuelve match[p] = r. Imprime la traza si trace == true.
    static int[] galeShapley(int[][] prefP, int[][] prefR, String[] nameP, String[] nameR, boolean trace) {
        int n = prefP.length;
        int[][] rank = new int[n][n];               // rank[r][p]: posicion de p en la lista de r
        for (int r = 0; r < n; r++)
            for (int i = 0; i < n; i++) rank[r][prefR[r][i]] = i;

        int[] holder = new int[n];  Arrays.fill(holder, -1);   // holder[r] = p retenido por r
        int[] next = new int[n];                               // proxima posicion a proponer
        ArrayDeque<Integer> free = new ArrayDeque<>();
        for (int p = 0; p < n; p++) free.add(p);

        int it = 0;
        while (!free.isEmpty()) {
            int p = free.poll();
            int r = prefP[p][next[p]++];
            it++;
            String msg;
            if (holder[r] == -1) {
                holder[r] = p;
                msg = nameR[r] + " esta libre: acepta (provisorio)";
            } else if (rank[r][p] < rank[r][holder[r]]) {
                int old = holder[r];
                holder[r] = p;
                free.add(old);
                msg = nameR[r] + " prefiere a " + nameP[p] + " sobre " + nameP[old]
                        + ": acepta y rechaza a " + nameP[old];
            } else {
                free.add(p);
                msg = nameR[r] + " prefiere a " + nameP[holder[r]] + ": rechaza a " + nameP[p];
            }
            if (trace) System.out.printf("  %2d. %s propone a %s -> %s%n", it, nameP[p], nameR[r], msg);
        }
        if (trace) System.out.println("  Total de propuestas: " + it);

        int[] match = new int[n];
        for (int r = 0; r < n; r++) match[holder[r]] = r;
        return match;
    }

    // Comprueba que no exista par bloqueante. matchC[c] = e
    static boolean esEstable(int[] matchC, int[][] prefC, int[][] prefE) {
        int n = matchC.length;
        int[] matchE = new int[n];
        for (int c = 0; c < n; c++) matchE[matchC[c]] = c;
        int[][] rc = rank(prefC), re = rank(prefE);
        for (int c = 0; c < n; c++)
            for (int e = 0; e < n; e++)
                if (rc[c][e] < rc[c][matchC[c]] && re[e][c] < re[e][matchE[e]]) return false;
        return true;
    }

    static int[][] rank(int[][] pref) {
        int n = pref.length;
        int[][] r = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) r[i][pref[i][j]] = j;
        return r;
    }

    // Enumera todos los pareos perfectos (n! posibles) y devuelve los estables
    static List<int[]> todosLosEstables(int[][] prefC, int[][] prefE) {
        int n = prefC.length;
        List<int[]> res = new ArrayList<>();
        permutar(new int[n], new boolean[n], 0, prefC, prefE, res);
        return res;
    }

    static void permutar(int[] cur, boolean[] used, int k, int[][] pc, int[][] pe, List<int[]> out) {
        int n = cur.length;
        if (k == n) { if (esEstable(cur, pc, pe)) out.add(cur.clone()); return; }
        for (int e = 0; e < n; e++)
            if (!used[e]) { used[e] = true; cur[k] = e; permutar(cur, used, k + 1, pc, pe, out); used[e] = false; }
    }

    static String fmt(int[] matchC, String[] nc, String[] ne) {
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < matchC.length; c++)
            sb.append(nc[c]).append("-").append(ne[matchC[c]]).append(c + 1 < matchC.length ? "  " : "");
        return sb.toString();
    }

    // Suma de posiciones (1 = primera opcion) que obtiene cada lado
    static int[] costos(int[] matchC, int[][] prefC, int[][] prefE) {
        int[][] rc = rank(prefC), re = rank(prefE);
        int sc = 0, se = 0;
        for (int c = 0; c < matchC.length; c++) { sc += rc[c][matchC[c]] + 1; se += re[matchC[c]][c] + 1; }
        return new int[]{sc, se};
    }

    public static void main(String[] args) throws Exception {
        int n;
        int[][] prefC, prefE;
        if (args.length > 0) {
            Scanner sc = new Scanner(new java.io.File(args[0]));
            n = sc.nextInt();
            prefC = new int[n][n]; prefE = new int[n][n];
            for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) prefC[i][j] = sc.nextInt();
            for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) prefE[i][j] = sc.nextInt();
        } else {
            n = 4;
            // Indices 0..3 = C1..C4 / E1..E4
            prefC = new int[][]{{3, 0, 1, 2}, {0, 2, 3, 1}, {0, 2, 3, 1}, {0, 2, 1, 3}};
            prefE = new int[][]{{0, 1, 3, 2}, {0, 1, 2, 3}, {0, 1, 2, 3}, {3, 0, 1, 2}};
        }
        String[] nc = new String[n], ne = new String[n];
        for (int i = 0; i < n; i++) { nc[i] = "C" + (i + 1); ne[i] = "E" + (i + 1); }

        System.out.println("== Preferencias de las empresas ==");
        for (int c = 0; c < n; c++) System.out.println("  " + nc[c] + ": " + lista(prefC[c], ne));
        System.out.println("== Preferencias de los estudiantes ==");
        for (int e = 0; e < n; e++) System.out.println("  " + ne[e] + ": " + lista(prefE[e], nc));

        System.out.println("\n== Version A: EMPRESAS PROPONEN ==");
        int[] mA = galeShapley(prefC, prefE, nc, ne, true);
        System.out.println("  Pareo final: " + fmt(mA, nc, ne));

        System.out.println("\n== Version B: ESTUDIANTES PROPONEN ==");
        int[] mE = galeShapley(prefE, prefC, ne, nc, true);   // mE[e] = c
        int[] mB = new int[n];
        for (int e = 0; e < n; e++) mB[mE[e]] = e;           // a formato matchC[c] = e
        System.out.println("  Pareo final: " + fmt(mB, nc, ne));

        System.out.println("\n== Verificacion ==");
        System.out.println("  A estable: " + esEstable(mA, prefC, prefE));
        System.out.println("  B estable: " + esEstable(mB, prefC, prefE));
        int[] cA = costos(mA, prefC, prefE), cB = costos(mB, prefC, prefE);
        System.out.println("  Suma de posiciones (menor = mejor)  [empresas, estudiantes]");
        System.out.println("  A (empresas proponen):     " + Arrays.toString(cA));
        System.out.println("  B (estudiantes proponen):  " + Arrays.toString(cB));

        System.out.println("\n== Todos los pareos estables (fuerza bruta, n! casos) ==");
        for (int[] m : todosLosEstables(prefC, prefE)) {
            int[] c = costos(m, prefC, prefE);
            System.out.println("  " + fmt(m, nc, ne) + "   [empresas=" + c[0] + ", estudiantes=" + c[1] + "]");
        }
    }

    static String lista(int[] pref, String[] names) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pref.length; i++) sb.append(names[pref[i]]).append(i + 1 < pref.length ? " > " : "");
        return sb.toString();
    }
}
