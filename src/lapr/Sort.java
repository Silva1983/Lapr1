package lapr;

public class Sort {
 
 public static int[][] aplicaSelectionSort(int[][] matrizImagem) {
        long timeNow = System.nanoTime();
        int[][] matrizImageTransposta = fazMatrizTranposta(matrizImagem);
        int[] linha = new int[matrizImagem.length];
        for(int d=0; d<matrizImageTransposta.length; d++){
            for(int a =0; a<matrizImageTransposta[0].length; a++){
            }
        }
        for(int k=0; k<matrizImagem.length; k++) {
            for (int i = 0; i < matrizImagem[0].length; i++) {
                    linha[i] = matrizImageTransposta[k][i];
                    if (i == matrizImagem.length - 1) {
                        for (int f = 0; f < linha.length; f++) {
                        }
                        int [] temp = doSelectionSort(linha);
                        System.arraycopy(temp, 0,matrizImageTransposta[k],0, temp.length);
                    }
                }
        }
        matrizImageTransposta=fazMatrizTranposta(matrizImageTransposta);
        long timeAfter = System.nanoTime();
        long totalTime= timeAfter-timeNow;
        System.out.println("tempo decorrido para efetuar SelectionSort "  + totalTime + " ns");
        return matrizImageTransposta;
    }
  public static int[][] aplicaBubbleSort(int[][] matrizImagem) {
      long timeNow = System.nanoTime();
      int[][] matrizImageTransposta = fazMatrizTranposta(matrizImagem);
        int[] linha = new int[matrizImagem.length];
        for(int d=0; d<matrizImageTransposta.length; d++){
            for(int a =0; a<matrizImageTransposta[0].length; a++){
            }
        }
        for(int k=0; k<matrizImagem.length; k++) {
            for (int i = 0; i < matrizImagem[0].length; i++) {
                    linha[i] = matrizImageTransposta[k][i];
                    if (i == matrizImagem.length - 1) {
                        for (int f = 0; f < linha.length; f++) {
                        }
                        int [] temp = doBubbleSort(linha);
                        System.arraycopy(temp, 0,matrizImageTransposta[k],0, temp.length);
                    }
                }
        }
        matrizImageTransposta=fazMatrizTranposta(matrizImageTransposta);
        long timeAfter = System.nanoTime();
        long totalTime= timeAfter-timeNow;
        System.out.println("tempo decorrido para efetuar BubbleSort "  + totalTime + " ns");
       
        return matrizImageTransposta;
    }
  public static int[][] aplicaInsertionSort(int[][] matrizImagem) {
      long timeNow = System.nanoTime();
      int[][] matrizImageTransposta = fazMatrizTranposta(matrizImagem);
        int[] linha = new int[matrizImagem.length];
        for(int d=0; d<matrizImageTransposta.length; d++){
            for(int a =0; a<matrizImageTransposta[0].length; a++){
            }
        }
        for(int k=0; k<matrizImagem.length; k++) {
            for (int i = 0; i < matrizImagem[0].length; i++) {
                    linha[i] = matrizImageTransposta[k][i];
                    if (i == matrizImagem.length - 1) {
                        for (int f = 0; f < linha.length; f++) {
                        }
                        int [] temp = doInsertionSort(linha);
                        System.arraycopy(temp, 0,matrizImageTransposta[k],0, temp.length);
                    }
                }
        }
        matrizImageTransposta=fazMatrizTranposta(matrizImageTransposta);
        long timeAfter = System.nanoTime();
        long totalTime = timeAfter-timeNow;
        System.out.println("tempo decorrido para efetuar InsertionSort "  + totalTime + " ns");
       
        return matrizImageTransposta;
    }

    public static int[][] fazMatrizTranposta(int[][] matriz) {
        int[][] matrizTransposta = new int[matriz[0].length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz[0].length; k++) {
                matrizTransposta[k][i] = matriz[i][k];
            }
        }
        return matrizTransposta;
    }
    public static int[] doBubbleSort(int[] vetorLinha){
        int n = vetorLinha.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
        for (int j = 1; j < (n - i); j++) {
            if (vetorLinha[j - 1] < vetorLinha[j]) {
                temp = vetorLinha[j - 1];
                vetorLinha[j - 1] = vetorLinha[j];
                vetorLinha[j] = temp;
            }
        }
    }
        return vetorLinha;
    }
    public static int[] doSelectionSort(int[] vetorLinha){
        int max;
        for (int i = 0; i < vetorLinha.length; i++) {
            max = i;
            for (int j = i + 1; j < vetorLinha.length; j++) {
                if (vetorLinha[j] > vetorLinha[max]) {
                    max = j;
                }
            }
            if (max != i) {
                final int temp = vetorLinha[i];
                vetorLinha[i] = vetorLinha[max];
                vetorLinha[max] = temp;
            }
        }
        return vetorLinha;
    }
    public static int[] doInsertionSort(int[] vetorLinha)
    {
        int n = vetorLinha.length;
        for (int i=1; i<n; ++i)
        {
            int key = vetorLinha[i];
            int j = i-1;
 
      
            while (j>=0 && vetorLinha[j] < key)
            {
                vetorLinha[j+1] = vetorLinha[j];
                j = j-1;
            }
            vetorLinha[j+1] = key;
        }
        return vetorLinha;
    }
}

