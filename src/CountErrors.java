public class CountErrors {

    public static int countErrors(int[] solution, int[][] graph1, int[][] graph2) {
        int count = 0;
        for (int i = 0; i < solution.length - 1; i++) {
            for (int j = 0; j < solution.length - 1; j++) {
                for (int s = 0; s < solution.length - 1; s++) {
                    if (i != j) {
                        if (graph1[i][j] != graph2[solution[i]][solution[j]]) {
                            count++;
                        }
                        // Vergleiche ein Feld im Graph1 mit dem dazugehörigen
                        // Feld in Graph2 (dazugehörig heißt, dass solution diese
                        // Felder verbindet))
                        // Wenn es nicht übereinstimmt, zähle count hoch
                    }
                }
            }
        }
        return count / 2;
        // /2 zwei, weil der Fehler doppelt vorkommt
        // (z.B. einmal bei 1 3 und bei 3 1)
    }

}
