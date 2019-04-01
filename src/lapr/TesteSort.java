package lapr;
import java.io.FileNotFoundException;

public class TesteSort {
    
    public static void testeTempoSortMatriz4() throws FileNotFoundException {
        System.out.println("tempos test matriz 4x4");
        String pathname = "./src/testImage4.csv";
        int[][] matriz = new int[4][4];
        matriz = RWFile.lerFicheiroMatrizDescomprimido(pathname);
        Sort.aplicaSelectionSort(matriz);
        Sort.aplicaBubbleSort(matriz);
        Sort.aplicaInsertionSort(matriz);
    }

    public static void testeTempoSortMatriz40() throws FileNotFoundException {
        System.out.println("tempos test matriz 40x40");
        String pathname = "./src/testImage40.csv";
        int[][] matriz = new int[40][40];
        matriz = RWFile.lerFicheiroMatrizDescomprimido(pathname);
        Sort.aplicaSelectionSort(matriz);
        Sort.aplicaBubbleSort(matriz);
        Sort.aplicaInsertionSort(matriz);

    }

    public static void testeTempoSortMatriz80() throws FileNotFoundException {
        System.out.println("tempos test matriz 80x80");
        String pathname = "./src/testImage80.csv";
        int[][] matriz = new int[80][80];
        matriz = RWFile.lerFicheiroMatrizDescomprimido(pathname);
        Sort.aplicaSelectionSort(matriz);
        Sort.aplicaBubbleSort(matriz);
        Sort.aplicaInsertionSort(matriz);

    }

    public static void testeTempoSortMatriz120() throws FileNotFoundException {
        System.out.println("tempos test matriz 120x120");
        String pathname = "./src/testImage120.csv";
        int[][] matriz = new int[4][4];
        matriz = RWFile.lerFicheiroMatrizDescomprimido(pathname);
        Sort.aplicaSelectionSort(matriz);
        Sort.aplicaBubbleSort(matriz);
        Sort.aplicaInsertionSort(matriz);

    }

    public static void testeTempoSortMatriz160() throws FileNotFoundException {
        System.out.println("tempos test matriz 160x160");
        String pathname = "./src/testImage160.csv";
        int[][] matriz = new int[160][160];
        matriz = RWFile.lerFicheiroMatrizDescomprimido(pathname);
        Sort.aplicaSelectionSort(matriz);
        Sort.aplicaBubbleSort(matriz);
        Sort.aplicaInsertionSort(matriz);

    }

    public static void testeTempoSortMatriz200() throws FileNotFoundException {
        System.out.println("tempos test matriz 200x200");
        String pathname = "./src/testImage200.csv";
        int[][] matriz = new int[4][4];
        matriz = RWFile.lerFicheiroMatrizDescomprimido(pathname);
        Sort.aplicaSelectionSort(matriz);
        Sort.aplicaBubbleSort(matriz);
        Sort.aplicaInsertionSort(matriz);

    }
}
