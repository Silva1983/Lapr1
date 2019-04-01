package lapr;

import java.io.FileNotFoundException;
import java.io.IOException;
import static lapr.TEST.testeAplicaSelectionSort;

public class TEST {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        TEST.runTestes();

    }

    public static void runTestes() throws FileNotFoundException, IOException {
     
        printTestResult(testeSumMatriz(), "SumMatriz");
        printTestResult(testMediaMatriz(), "MediaMatriz");
        printTestResult(testeRotacao(), "Rotação");
        printTestResult(testeAplicaSelectionSort(), "SelectionSort");
        printTestResult(testeDoSelectionSort(), "DoSelectionSort");
        printTestResult(testeAplicaBubbleSort(), "BubbleSort");
        printTestResult(testeDoBubbleSort(), "DoBubbleSort");
        printTestResult(testeAplicaInsertionSort(), "InsertionSort");
        printTestResult(testeDoInsertionSort(), "DoInsertionSort");
        printTestResult(testeFazMatrizTransposta(), "Transposta");
        printTestResult(testeLerFicheiroMatriz(), "LerFicheiroMatriz");
        printTestResult(testeFiltroMinimo(), "FiltroMinimo");
        printTestResult(testeFiltroMaximo(), "FiltroMaximo");
        printTestResult(testeFiltroMediana(), "FiltroMediana");
        printTestResult(testeFiltroMedia(), "FiltroMedia");
        printTestResult(testeFiltroVariancia(), "FiltroVariancia");
        printTestResult(testeFiltroConvolução(), "FiltroConvolução");
        printTestResult(testeArredondar(), "arredondar");
        printTestResult(testeTrapezio(),"Trapézio");
    }

    public static void printTestResult(boolean teste, String nomeMetodo) {
        if (teste == true) {
            System.out.println(nomeMetodo + " ok");
        }else {
            System.out.println(nomeMetodo + " not ok");
        }
    }

    public static boolean testeSumMatriz() {
        boolean ok = true;
        int[][] matrizDefinida = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},};
        int expectedResult = 45;
        int result = Caracterizacao.sumMatriz(matrizDefinida);
        if (result != expectedResult) {
            ok = false;
        }
        return ok;

    }

    public static boolean testMediaMatriz() {
        boolean ok = true;
        int[][] matrizDefinida = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},};
        double expectedResult = 5;
        double result = Caracterizacao.mediaMatriz(matrizDefinida);
        if (result != expectedResult) {
            ok = false;
        }
        return ok;
    }

    public static boolean testeRotacao() {
        boolean ok = true;
        int[][] matrizDefinida = {
            {1, 1, 1, 1},
            {2, 2, 2, 2},
            {3, 3, 3, 3},
            {4, 4, 4, 4}
        };
        int[][] expectedMatriz = {
            {4, 3, 2, 1},
            {4, 3, 2, 1},
            {4, 3, 2, 1},
            {4, 3, 2, 1}
        };

        int[][] matrizTeste = Filtros3x3.rotacaoMatriz(matrizDefinida);
        for (int i = 0; i < matrizDefinida.length; i++) {
            for (int k = 0; k < matrizDefinida.length; k++) {
                if (matrizTeste[i][k] != expectedMatriz[i][k]) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static boolean testeAplicaSelectionSort() {
        boolean ok = true;
        int[][] matriz = {{7, 8, 9}, {3, 2, 1}, {6, 4, 5}};
        int[][] matrizModificada = {{7, 8, 9}, {6, 4, 5}, {3, 2, 1}};
        int[][] matrizNova = new int[matriz.length][matriz.length];
        boolean igual = true;
        matrizNova = Sort.aplicaSelectionSort(matriz);
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz.length; k++) {
                if (matrizNova[i][k] != matrizModificada[i][k]) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static boolean testeDoSelectionSort() {
        boolean ok = true;
        int[] vetor = {5, 3, 2, 4, 1};
        int[] vetorOrdenado = new int[vetor.length];
        vetorOrdenado = Sort.doSelectionSort(vetor);
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] != vetorOrdenado[i]) {
                ok = false;
            }
        }
        return ok;
    }

    public static boolean testeAplicaBubbleSort() {
        boolean ok = true;
        int[][] matriz = {{7, 8, 9}, {3, 2, 1}, {6, 4, 5}};
        int[][] matrizModificada = {{7, 8, 9}, {6, 4, 5}, {3, 2, 1}};
        int[][] matrizNova = new int[matriz.length][matriz.length];
        matrizNova = Sort.aplicaBubbleSort(matriz);
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz.length; k++) {
                if (matrizNova[i][k] != matrizModificada[i][k]) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static boolean testeDoBubbleSort() {
        boolean ok = true;
        int[] vetor = {5, 3, 2, 4, 1};
        int[] vetorOrdenado = new int[vetor.length];
        vetorOrdenado = Sort.doBubbleSort(vetor);
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] != vetorOrdenado[i]) {
                ok = false;
            }
        }
        return ok;
    }

    public static boolean testeAplicaInsertionSort() {
        boolean ok = true;
        int[][] matriz = {{7, 8, 9}, {3, 2, 1}, {6, 4, 5}};
        int[][] matrizModificada = {{7, 8, 9}, {6, 4, 5}, {3, 2, 1}};
        int[][] matrizNova = new int[matriz.length][matriz.length];
        matrizNova = Sort.aplicaInsertionSort(matriz);
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz.length; k++) {
                if (matrizNova[i][k] != matrizModificada[i][k]) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static boolean testeDoInsertionSort() {
        boolean ok = true;
        int[] vetor = {5, 3, 2, 4, 1};
        int[] vetorOrdenado = new int[vetor.length];
        vetorOrdenado = Sort.doInsertionSort(vetor);
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] != vetorOrdenado[i]) {
                ok = false;
            }
        }
        return ok;
    }

    public static boolean testeFazMatrizTransposta() {
        boolean ok = true;
        int[][] matriz = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrizTransformada = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] matrizTransposta = new int[matriz.length][matriz.length];
        matrizTransposta = Sort.fazMatrizTranposta(matriz);
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz.length; k++) {
                if (matrizTransformada[i][k] != matrizTransposta[i][k]) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static boolean testeLerFicheiroMatriz() throws FileNotFoundException {
        String pathname = "./src/matrizTeste.txt";
        int[][] matrizDefinida = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] matrizTeste = new int[4][4];
        matrizTeste = RWFile.lerFicheiroMatrizDescomprimido(pathname);
        boolean ok = true;
        for (int i = 0; i < matrizDefinida.length; i++) {
            for (int k = 0; k < matrizDefinida[0].length; k++) {
                if (matrizDefinida[i][k] != matrizTeste[i][k]) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static boolean testeFiltroMinimo() throws IOException {
        int pos = 0;
        int[][] matriz = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrizModificada = {{0, 1, 0}, {1, 1, 2}, {0, 4, 0}};
        int[][] matrizNova = new int[matriz.length][matriz.length];
        boolean ok = true;
        matrizNova = Filtros3x3.filtroMinimo(matriz);
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz.length; k++) {
                if (matrizNova[i][k] != matrizModificada[i][k]) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static boolean testeFiltroMaximo() throws IOException {
        int pos = 8;
        int[][] matriz = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrizModificada = {{5, 6, 6}, {8, 9, 9}, {8, 9, 9}};
        int[][] matrizNova = new int[matriz.length][matriz.length];
        boolean ok = true;
        matrizNova = Filtros3x3.filtroMaximo(matriz);
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz.length; k++) {
                if (matrizNova[i][k] != matrizModificada[i][k]) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static boolean testeFiltroMediana() throws IOException {
        int pos = 4;
        int[][] matriz = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrizModificada = {{2, 3, 3}, {4, 5, 6}, {7, 7, 8}};
        int[][] matrizNova = new int[matriz.length][matriz.length];
        boolean ok = true;
        matrizNova = Filtros3x3.filtroMediana(matriz);
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz.length; k++) {
                if (matrizNova[i][k] != matrizModificada[i][k]) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static boolean testeFiltroMedia() throws IOException {
        int[][] matriz = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] matrizModificada = {{3, 3, 4, 5}, {5, 6, 7, 8}, {9, 10, 11, 12}, {11, 13, 14, 13}};
        int[][] matrizNova = new int[matriz.length][matriz.length];
        boolean ok = true;
        matrizNova = Filtros3x3.filtroMedia(matriz);
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz.length; k++) {
                if (matrizNova[i][k] != matrizModificada[i][k]) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static boolean testeFiltroVariancia() throws IOException {
        int[][] matriz = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrizModificada = {{3, 3, 4}, {6, 7, 6}, {6, 3, 8}};
        int[][] matrizNova = new int[matriz.length][matriz.length];
        boolean ok = true;
        matrizNova = Filtros3x3.filtroVariancia(matriz);
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz.length; k++) {
                if (matrizNova[i][k] != matrizModificada[i][k]) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    public static boolean testeFiltroConvolução() {
        int[][] matriz = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] matrizModificada = {{4, 4, 5, 6}, {7, 8, 9, 9}, {11, 12, 13, 13}, {11, 14, 15, 12}};
        int[][] matrizNova = new int[matriz.length][matriz.length];
        boolean ok = true;
        matrizNova = Filtros3x3.filtroConvuluçaotest(matriz);
        for (int i = 0; i < matriz.length; i++) {
            for (int k = 0; k < matriz.length; k++) {
                if (matrizNova[i][k] != matrizModificada[i][k]) {
                    ok = false;
                }
            }
        }
        return ok;
    }
    
    public static boolean testeArredondar(){
        double numero=3.2444;
        double numeroArredondado = 3.24;
        boolean teste=true;
       
        if(Compressao.arredondar(numero,2)==numeroArredondado){}
        else{
            teste=false;
        }
        return teste;
    }
    
    
    public static boolean testeTrapezio(){
        boolean ok = true;
        double soma;
        double div=0;
        double[][] matrizDefinida = {
            {1, 1, 2},
            {4, 4, 5},
            {7, 7, 8},};
        double[] valorTrapezios= new double[matrizDefinida.length];
        double [] expectedResult = {2.5, 8.5, 14.5};
        for (int i=0; i<matrizDefinida.length; i++){
            double somaFinal=0;
            for(int j=0; j<matrizDefinida[0].length-1; j++){
                soma = matrizDefinida[i][j]+matrizDefinida[i][j+1];
                somaFinal=somaFinal+soma;
                div = somaFinal /2;
            }
            valorTrapezios[i] = div;
            if (valorTrapezios[i] !=  expectedResult[i]){
                ok = false;
            }
        }
        return ok;
    }
}
    
