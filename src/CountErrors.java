public class CountErrors {
    //test
    public static int countErrors(int[] solution, int[][] graph1, int[][] graph2) {
        int count = 0;
        for (int i = 0; i < solution.length; i++) {
            if(solution[i]!=-1){
                for (int j = 0; j < solution.length; j++) {
                    if (i != j && (solution[i] > -1 || solution[j] > -1)) {
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
