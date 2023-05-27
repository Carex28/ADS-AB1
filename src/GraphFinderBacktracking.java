public class GraphFinderBacktracking {


    public static int countErrors(int[] solution, int[][] graph1, int[][] graph2) {
        int count = 0;
        for (int i = 0; i < solution.length; i++) {
            if (solution[i] != -1) {
                for (int j = 0; j < solution.length; j++) {
                    if (i != j && solution[j] != -1) { // check
                        if (graph1[i][j] != graph2[solution[i]][solution[j]]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count / 2;
    }

    public static int[] findBacktracking(int[][] graph1, int[][] graph2) {
        int[] res = getStartArr(graph1.length);
        int[] best = fillBest(graph1.length); // Am Anfang 0, 1, 2, 3...,
        int[] result = backtracking(best, res, graph1, graph2, 0);
        showln(result); // hilfsfunktion, umd as ergebnis anzuzeigen
        return result;
    }

    private static int[] backtracking(int[] best, int[] zustand, int[][] g1, int[][] g2, int pos) {
        int bestError;
        if (pos == 0) {
            bestError = Integer.MAX_VALUE; // Beim ersten durchlauf max_value fehler
        } else {
            bestError = countErrors(best, g1, g2); // sonst countErrors
        }


        if (!isValid(zustand) || countErrors(zustand, g1, g2) > bestError) { //zuerst gucken, ob Zustand Valide ist (Zahlen mehrmals vorkommen)
            return null;         //bzw ob verbesserung überhaupt möglich ist -> null
        }

        if (isFull(zustand, pos)) { //Wenn alle Zahlen Voll sind (oder pos die letzte Position erreicht hat),
            return zustand;         //wird der Zustand zurückgegeben
        }


        // Folgezustände:
        int[][] zustaende = new int[g2.length][zustand.length];
        for(int i = 0; i < zustaende.length; i++) {
            zustaende[i] = zustand.clone(); // Man muss zustand clonen, bevor man ihn assigned, weil er sonst den zustand selbst verändert (Zustand wird als Pointer behandelt)
            zustaende[i][pos] = i; // An Position i wird i assigned, somit entsteht eine Matrix mit allen möglichen Zuständen (inlusive den invaliden)            
        }

        // Für jeden zustand tue:
        for (int z = 0; z < zustaende.length; z++) {
            // Rekursiver einstieg:
            int[] loesung = backtracking(best, zustaende[z], g1, g2, pos + 1);
            if (loesung != null && countErrors(loesung, g1, g2) < bestError) {// ignoriere die Lösung, wenn sie null ist (invalide oder nicht zu verbessern) oder
                best = loesung;                                               // schlechter ist, als die bisher beste Lösung
                bestError = countErrors(best, g1, g2);
            }
        }
        return best;
    }

    private static int[] fillBest(int n) { // Zahlen werden dem Index her aufgefüllt
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = i;
        }
        return res;
    }

    private static boolean isFull(int[] zustand, int pos) {
        if (pos == zustand.length || zustand[zustand.length - 1] > -1) { // ob pos die Letzte Position erreicht hat oder das letzte Element nicht mehr -1 ist
            return true;
        }
        return false;
    }

    private static boolean isValid(int[] zustand) {
        for (int i = 0; i < zustand.length; i++) {
            int current;
            if ((current = zustand[i]) == -1) {
                continue;
            }
            int count = 0;
            for (int j = 0; j < zustand.length; j++) {
                if (zustand[j] == current) { // Zählt wie oft ein Element vorkommt
                    count++;
                }
            }
            if (count >= 2) { // Wenn ein Element 2 mal oder mehr vorkommt, zählt es als invalid
                return false;
            }
        }
        return true;
    }

    public static int[] getStartArr(int x) {
        int[] res = new int[x];
        for (int n = 0; n < res.length; n++) { // alle werte mit -1 belegen
            res[n] = -1;
        }
        return res;
    }

    /**
     * Hilfsfunktion für das Zeigen von Arrays
     */
    private static void showln(int[] result) {
        for (int a : result) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

}

