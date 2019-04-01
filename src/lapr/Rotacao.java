package lapr;

public class Rotacao {

    //Este método percorre os valores da 1ª linha de uma matriz (nxn) e passa-os para a última coluna;
    /*à medida que percorre as linhas da matrixRodada lê os valores das colunas da matrizVelha
    ex:
    (1,2,3}  (7,4,1}
    (4,5,6}  (8,5,2}
    (7,8,9}  (9,6,3}
     */
    public static int[][] rotacaoMatrizDireita(int[][] matrizVelha) {
        int n = matrizVelha.length;
        int[][] matrizRodada = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizRodada[i][j] = matrizVelha[n - 1 - j][i];
            }
        }
        RWFile.getNovoNomeFicheiro("rotacaoDireita.", Main.nomeFicheiro);
        return matrizRodada;
    }

    /*à medida que percorre as linhas da matrixRodada lê os valores das colunas da matrizVelha
    ex:
    (7,4,1}  (1,2,3}  
    (8,5,2}  (4,5,6}  
    (9,6,3}  (7,8,9}  
     */

    public static int[][] rotacaoMatrizEsquerda(int[][] matrizOriginal) {
        int n = matrizOriginal.length;
        int[][] matrizRodada = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizRodada[i][j] = matrizOriginal[j][n - 1 - i];
            }
        }
        RWFile.getNovoNomeFicheiro("rotacaoEsquerda.", Main.nomeFicheiro);
        return matrizRodada;
    }

    public static void printIntMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
/*
    public static void printIntMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }*/
