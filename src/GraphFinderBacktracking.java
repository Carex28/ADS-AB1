public class GraphFinderBacktracking {


    public static int countErrors(int[] solution, int[][] graph1, int[][] graph2) {
        int count = 0;
        for (int i = 0; i < solution.length; i++) {
            if (solution[i] != -1) {
                for (int j = 0; j < solution.length; j++) {
                    if (i != j && (solution[i] > -1 || solution[j] > -1)) {
                        if (solution[j] != -1) {
                            if (graph1[i][j] != graph2[solution[i]][solution[j]]) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count / 2;
    }

    
    public static int[] findBacktracking(int[][] graph1, int[][] graph2) {
        int[] res = getStartArr(graph1.length);
        int[] best = res;
        int lowesError = Integer.MAX_VALUE;
        backtracking(best,res,graph1,graph2);
        return best;
    }

    private static void backtracking(int[] best, int[] zstd, int[][] g1, int[][] g2) {
        if(countErrors(zstd,g1,g2)<countErrors(best,g1,g2)){
            best = zstd;
        }

    }


    public static int[] getStartArr(int x){
        int[] res = new int[x];
        res[0] = 0;
        for (int n = 1; n < res.length; n++) {              //alle werte ausser erstem mit -1 belegen
            res[n] = -1;
        }
        return res;
    }

}

