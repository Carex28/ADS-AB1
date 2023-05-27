public class GraphFinderBacktrackingRec {
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
        int[] best = fillBest(graph1.length);
        int[] result = backtracking(best,res,graph1,graph2, 0);
        show(result);
        return result;
    }

    private static int[] backtracking(int[] best, int[] zustand, int[][] g1, int[][] g2, int pos){
        int bestError;
        if(pos == 0){
            bestError = Integer.MAX_VALUE;
        }
        else{
            bestError = countErrors(best, g1, g2);
        }
        if(isFull(zustand, pos)){
            return zustand;
        }
        if(!isValid(zustand) || countErrors(zustand, g1, g2) > bestError){
            // System.out.println("ist nicht Valide "+pos);
            // show(zustand);
            return null;
        }
        //Folgezustände:
        int[][] zustaende = new int[g2.length][zustand.length];
        {int i = 0;
        while(i < zustaende.length){
            zustaende[i] = zustand.clone();
            // show(zustand);
            zustaende[i][pos] = i; 
            // showln(zustaende[i]);
            i++;
        }}
        for(int i = 0; i < zustaende.length; i++){
            System.out.print("[pos: "+pos+"] ");
            showln(zustaende[i]);
        }
        for(int z = 0; z < zustaende.length; z++){
            
            int[] loesung = backtracking(best, zustaende[z], g1, g2, pos+1);
            if(loesung != null && countErrors(loesung, g1, g2) < bestError){
                best = loesung;
                bestError = countErrors(best,g1, g2);
                System.out.println("loesung: ");
                showln(best);
            }
        }
        return best;
    }

    private static int[] fillBest(int n){
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            res[i] = i;
        }
        return res;
    }
    
    private static boolean isFull(int[] zustand, int pos){
        if(pos == zustand.length || zustand[zustand.length - 1] > -1){
            return true;
        }
        return false;
    }

    private static boolean isValid(int[] zustand){
        for(int i = 0; i < zustand.length; i++){
            int current;
            if((current = zustand[i]) == -1){
                continue;
            }
            int count = 0;
            for(int j = 0; j < zustand.length; j++){
                if(zustand[j] == current){
                    count++;
                }
            }
            if(count >= 2){
                return false;
            }
        }
        return true;
    }

    public static int[] getStartArr(int x){
        int[] res = new int[x];
        for (int n = 0; n < res.length; n++) {              //alle werte mit -1 belegen
            res[n] = -1;
        }
        return res;
    }

    private static void showln(int[] result){
        for(int a : result){
            System.out.print(a + " ");
        }
        System.out.println();
    }
    private static void show(int[] result){
        for(int a : result){
            System.out.print(a + " ");
        }
    }
}
