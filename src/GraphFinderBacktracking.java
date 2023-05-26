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
        int[] zstd = getStartArr(graph1.length);
        int[] best = findGreedy(graph1,graph2);
        int lowesError = countErrors(best,graph1,graph2);
        int[]used = new int[graph2.length];
        used[0]=1;
        backtracking(best,zstd,graph1,graph2,lowesError,1,used);
        return best;
    }

    private static void backtracking(int[] best, int[] zstd, int[][] g1, int[][] g2, int lowestError, int pos, int[] unsed) {
        if(pos > zstd.length-1){
            return;
        }
        int index =0;
        int errors;
        int [][]temp = new int[g2.length][zstd.length];
        for(int n = pos ; n<zstd.length;n++){
            for(int x = 0;x<g2.length;x++){
                int[] neu = new int[zstd.length];
                if(unsed[x]==0){
                    zstd[n]=x;
                }
                neu = zstd;
                temp[index]= neu;
                index++;
            }
        }

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

    public static int[] findGreedy(int[][] graph1, int[][] graph2) {
        int[] res = new int[graph1.length];
        res[0] = 0;
        for (int n = 1; n < res.length; n++) {              //alle werte ausser erstem mit -1 belegen
            res[n] = -1;
        }
        int[] used = new int [graph2.length];               //array um benutzte knoten zu speichern
        for(int x = 1; x<res.length; x++){
            int errors = Integer.MAX_VALUE;             //error startwert
            int lowesError = Integer.MAX_VALUE-1;       //vergleichswert für errors
            int bestMatch = -1;                         //best passender knoten mit -1 init
            for(int n =0; n<graph2.length;n++){         //für alle konten des graph2 abgleichen
                if(used[n]==-1){                        //wenn knoten bereits verwendet wurde diesen überspringen
                    continue;
                }
                res[x] = n;                             //in res an stelle x den aktuell projizierten knoten schreiben
                errors = countErrors(res,graph1,graph2);    //fehler abfragen
                if(errors < lowesError){                //wenn fehler niedriger
                    used[n]=-1;
                    lowesError = errors;                //vergleichswert aktualisieren
                    bestMatch = n;                      //aktuellen konten als bestes match festlegen
                }
            }
            res[x] = bestMatch;                         //res den besten knoten hinzufügen
        }
        //System.out.println(Arrays.toString(res));
        return res;
    }

}

