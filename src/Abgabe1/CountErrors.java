package Abgabe1;

public class CountErrors {
    //test
    public static int countErrors(int[] solution, int[][] graph1, int[][] graph2) {
        // O(n^2)
        int count = 0;
        for (int i = 0; i < solution.length; i++) {
            if (solution[i] != -1) {
                for (int j = 0; j < solution.length; j++) {
                    if (i != j && solution[j] != -1) {                              //check
                            if (graph1[i][j] != graph2[solution[i]][solution[j]]) {
                                count++;
                            }
                        }
                        // Vergleiche ein Feld im Graph1 mit dem dazugehörigen
                        // Feld in Graph2 (dazugehörig heißt, dass solution diese
                        // Felder verbindet))
                        // Wenn es nicht übereinstimmt, zähle count hoch

                    }
            }
        }
        return count / 2;
    }
}
