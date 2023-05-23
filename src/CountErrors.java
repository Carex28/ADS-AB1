public class CountErrors {

    public static int countErrors(int[] solution, int[][] graph1, int[][] graph2) {

        for (int i = 0; i < graph1.length - 1; i++) {
            for (int s = 0; s < solution.length - 1; s++) {

            }
        }

        for (int i = 0; i < solution.length - 1; i++) {
            for (int j = 0; j < solution.length - 1; j++) {
                for (int s = 0; s < solution.length - 1; s++) {
                    if (i != j) {
                        // graph1[i][j] == graph2[solution[i]][solution[j]];
                        // Vergleiche ein Feld im Graph1 mit dem dazugehörigen
                        // Feld in Graph2 (dazugehörig heißt, dass solution diese
                        // Felder verbindet))
                    }
                }
            }
        }

        return 0;
    }

}
