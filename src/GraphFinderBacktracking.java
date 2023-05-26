public class GraphFinderBacktracking {


    public static int countErrors(int[] solution, int[][] graph1, int[][] graph2) {
        int count = 0;
        for (int i = 0; i < solution.length; i++) {
            if (solution[i] != -1) {
                for (int j = 0; j < solution.length; j++) {
                    if (i != j && solution[j] != -1 ) {                              //check
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
        int[] best = res;
        int[] result = backtracking(best,res,graph1,graph2);
        for(int a : result){
            System.out.print(a + " ");
        }
        return result;
    }

    private static int[] backtracking(int[] best, int[] zstd, int[][] g1, int[][] g2) {
        int[][] zustaende = new int[g2.length * g1.length][zstd.length]; // Menge ALLER möglichen Zustände, deshalb n1 * n2

        for(int zstdIndex = 0; zstdIndex < zstd.length; zstdIndex++){ // der Index, der guckt, wie weit wir mit der Lösung sind(wenn diese Loop durch ist, gibt es kein Feld mehr mit -1)

            int innerIndex = 0; // zustaende[innerIndex] wird mit den möglichen Zahlen beschrieben

            whichNumber:for(int number = 0; number < g2.length; number++){ //Die Zahl, die in zustaende[innerIndex] reingeschrieben wird

                for(int j = 0; j < zstdIndex; j++){ // ob der Knoten schonmal in diesem Array benutzt wurde

                    if(zustaende[innerIndex][j] == number){ // Wenn ja, dann geh zur nächsten Zahl, aber ändere nicht den Ort, wo die nächste Zahl reingeschrieben wird
                        continue whichNumber;
                    }
                }

                for(int k = 0; k < g1.length; k++){ // Wie oft die Zahl in zustaende[innerIndex] reingeschrieben wird

                    zustaende[innerIndex++][zstdIndex] = number; 
                }
            }
        }
        best = zustaende[0];
        for(int i = 1; i< zustaende.length; i++){                                                           // geh durch die Menge der Zustände 
            int e;  
            if((e = countErrors(zustaende[i], g1, g2)) < countErrors(best, g1, g2)){  // check ob der Zustand 0 fehler hat oder besser ist als der bisher beste
                best = zustaende[i];                                                                       // mache diesen zum besten
                if(e == 0){                                                                             //wenn das der beste ist, hör auf zu durchsuchen
                    break;
                }
            }
        }
        zstd = best; // der bisher beste wird zum besten
        return best;
    }


    public static int[] getStartArr(int x){
        int[] res = new int[x];
        for (int n = 0; n < res.length; n++) {              //alle werte mit -1 belegen
            res[n] = -1;
        }
        return res;
    }

}

