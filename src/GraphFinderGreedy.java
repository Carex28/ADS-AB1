import java.util.Arrays;

public class GraphFinderGreedy {


    public static int countErrors(int[] solution, int[][] graph1, int[][] graph2) {
        int count = 0;
        for (int i = 0; i < solution.length; i++) {
            if(solution[i]!=-1){                                                //check
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
        // /2 zwei, weil der Fehler doppelt vorkommt
        // (z.B. einmal bei 1 3 und bei 3 1)
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
                int LowesError = Integer.MAX_VALUE-1;       //vergleichswert für errors
                int bestMatch = -1;                         //best passender knoten mit -1 init
                for(int n =0; n<graph2.length;n++){         //für alle konten des graph2 abgleichen
                    if(used[n]==-1){                        //wenn knoten bereits verwendet wurde diesen überspringen
                        continue;
                    }
                    res[x] = n;                             //in res an stelle x den aktuell projizierten knoten schreiben
                    errors = countErrors(res,graph1,graph2);    //fehler abfragen
                    if(errors < LowesError){                //wenn fehler niedriger
                        used[n]=-1;
                        LowesError = errors;                //vergleichswert aktualisieren
                        bestMatch = n;                      //aktuellen konten als bestes match festlegen
                    }
                }
                res[x] = bestMatch;                         //res den besten knoten hinzufügen
        }
        //System.out.println(Arrays.toString(res));
        return res;
    }
}

