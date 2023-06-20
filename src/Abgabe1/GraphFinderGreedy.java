package Abgabe1;

import java.util.Arrays;

public class GraphFinderGreedy {


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
                    }
            }
        }
        return count / 2;
    }


    public static int[] findGreedy(int[][] graph1, int[][] graph2) {
        // O(n^3)
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

                        lowesError = errors;                //vergleichswert aktualisieren
                        bestMatch = n;                      //aktuellen konten als bestes match festlegen
                    }
                }
                used[bestMatch]=-1;
                res[x] = bestMatch;                         //res den besten knoten hinzufügen
        }
        //System.out.println(Arrays.toString(res));
        return res;
    }
}

