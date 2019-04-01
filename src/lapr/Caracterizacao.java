package lapr;
import org.la4j.Matrix;
import org.la4j.decomposition.EigenDecompositor;
import org.la4j.matrix.dense.Basic2DMatrix;

public class Caracterizacao {
    //imprimir soma colunas
    public static void sumColumn(int[][] matrixImage) {
        System.out.println(" ");
        double soma;
        double n;
        //for para realização de somatório de toda a coluna, iterando coluna a coluna
        for (int j = 0; j < matrixImage.length; j++) {
            soma = 0;
            n = 0;
            for (int i = 0; i < matrixImage[j].length; i++) {
                soma = soma + matrixImage[i][j];
                n++;
            }
            System.out.println("A Soma da coluna " + j + " é " + soma);
            double media = average(soma, n);
            System.out.printf("A média da coluna é: %.2f%n", media);
        }
    }
     
    //calcular média para ser usado em outras funções
    public static double average(double soma, double n) {
        double media = soma / n;
        return media;
    }
    
    //imprime soma linhas
    public static void sumLine(int[][] matrixImage) {
        System.out.println(" ");
        double soma;
        int n;
        //for para realização de somatório de toda a linha, iterando linha a linha
        for (int j = 0; j < matrixImage.length; j++) {
            soma = 0;
            n=0;
            for (int i = 0; i < matrixImage[j].length; i++) {
                soma = soma + matrixImage[j][i];
                n++;
            }            
            System.out.println("A soma da coluna " + j+ " é " + soma);
            double media= average(soma, n);
            System.out.printf("A média da linha é: %.2f%n",  media);   
        }
    }
    //somar todos os elementos da matriz num unico numero
    public static int sumMatriz(int [][] matrixImage){
        int soma =0;        
        for (int i=0; i< matrixImage.length; i++){
            for (int j=0; j< matrixImage[i].length; j++){
               soma = soma + matrixImage[i][j];
            } 
        }
        return soma;
    }
    //calcula media das linhas de uma matriz
    public static double mediaMatriz(int [][] matrixImage) {
        double media=0;
        double soma =0;
        int x=0;
        //Cálculo da soma e da média
        for (int i=0; i< matrixImage.length; i++){
            for (int j=0; j< matrixImage[i].length; j++){
               soma = soma + matrixImage[i][j];
               x++;
               media = soma/x;
            }
        }
         return media;
    }
    
    /*metodo para calcular os valores e vetores proprios de uma matriz atraves da biblioteca la4j
    O resultado da decomposição são 2 matrizes, uma que representa os vetores próprios e 
    outra que representa os valores próprios*/ 
    public static Matrix[] calcOwnValueAndOwnVector (int [][] matrixImage) { 
        //inicializção matriz de doubles para ser usada com a library pois esa funciona com matrizes de doubles
        double[][] matrizDouble = new double[matrixImage.length][matrixImage.length];
        //copiar matriz imagem para matriz de doubles
        for(int i = 0; i<matrixImage.length; i++){
            for(int j = 0; j<matrixImage.length; j++){
                matrizDouble[i][j]=matrixImage[i][j];
            }
        }
        //aplicar função da library que cria um objeto Matrix que contem as duas matriz la dentro
        Matrix a = new Basic2DMatrix(matrizDouble); 
        //criar eigenDecompositer que vai pegar no objeto Matrix e decompo-lo no objeto Matrix mais facil de usar
        EigenDecompositor eigenm=new EigenDecompositor(a);
        //criar objeto Matrix para 
        Matrix[] mattD = eigenm.decompose();
        return mattD;   
    }
       
    //impressão da matriz com valores proprios, diagonal
    public static void ownValuePrint (int [][] mattD) { 
        Matrix[] num = calcOwnValueAndOwnVector(mattD);
        System.out.println("Os valores próprios são: \n" +num[1]);
    }
       
    //impressão da matriz com vetores proprios
    public static void printOwnVector (int [][] mattD) { 
        Matrix[] num = calcOwnValueAndOwnVector(mattD);
        System.out.println("Os vetores próprios são: \n" +num[0]);
    }
    //impressao das integrais númericas de cada linha
    public static void printTrapezio(int[][] trapezio) {
        double soma;
        double somaFinal;
        double div=0;
        double[] valorTrapezios= new double[trapezio.length];
        //carregar matriz
        for (int i=0; i<trapezio.length; i++){
            somaFinal=0;
            for (int j = 0; j<trapezio[0].length-1; j++){
                //formula dos trapézios (T( f ) = I1( f ) = ( f(a) + f(b) ) ( b - a ) / 2)
                soma = trapezio[i][j]+trapezio[i][j+1];
                somaFinal=somaFinal+soma;
                div = somaFinal /2;
            }
            valorTrapezios[i]=div;
            System.out.print("Integral numérico linha" + i+1 + " é: " + valorTrapezios[i]+ " ");
            System.out.println(" ");
        }
    }
}
