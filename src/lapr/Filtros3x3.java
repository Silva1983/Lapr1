package lapr;
import java.io.IOException;
import java.util.Scanner;
import static lapr.Filtros3x3.cloneMatriz;
import static lapr.Filtros3x3.matrixNormalizada;
import static lapr.Filtros3x3.matrixpedida;

public class Filtros3x3 {
   
    //calcula a média de cada pixel e dos 8 pixeis vizinhos e guarda valor numa nova matriz 
    public static int[][] filtroMedia(int[][] matrix) throws IOException {
        int matriz[][] = cloneMatriz(matrix);
        double media = 0;
        int soma = 0;
        int[][] med = new int[matrix.length][matrix.length];
        for (int l = 1; l < matriz.length-1; l++) {
            for (int c = 1; c < matriz[l].length-1; c++) {
                soma = (matriz[l - 1][c - 1] + matriz[l - 1][c] + matriz[l - 1][c + 1] + matriz[l][c - 1] + matriz[l][c] + matriz[l][c + 1] + matriz[l + 1][c - 1] + matriz[l + 1][c] + matriz[l + 1][c + 1]);
                media = calculaMedia(soma);
             double temp = Math.round((double)media);
             med [l-1][c-1]=(int) temp;
            }
        }
        //retorna alteração feita para novo noe ficheiro
        RWFile.getNovoNomeFicheiro("media.", Main.nomeFicheiro);
        Gnuplot.gnuPlot();
        return med;
    }
    // calcula da média de cada pixel e da vizinhança, é a soma de todos os valores a dividir por 9, por filtro né 3 x 3
    public static double calculaMedia(int soma) {
        double media = (double) soma / 9;
        return media;
    }
    //criar uma matriz igual á matriz imagem com maior dimensao para aplicação de filtros
    public static int[][] cloneMatriz(int[][] matrix) {
        //nova matriz tem +2 de lenght para podermos aplicar filtros nas bordas
        int[][] cloneMatrix = new int[matrix.length + 2][matrix[0].length + 2];
         //zera os elementos da matriz;
        for (int i = 0; i < cloneMatrix.length; i++) {
            for (int j = 0; j < cloneMatrix[0].length; j++) {
                cloneMatrix[i][j] = 0;
            }
        }
        //substitui alguns elementos por elementos da matriz inicial;
        for (int k = 0; k < matrix.length; k++) {
            for (int l = 0; l < matrix[k].length; l++) {
                cloneMatrix[k + 1][l + 1] = matrix[k][l];
                cloneMatrix[0][l + 1] = matrix[0][l];
                cloneMatrix[k + 1][0] = matrix[k][0];
                cloneMatrix[k + 1][cloneMatrix.length - 1] = matrix[k][l];
                cloneMatrix[cloneMatrix.length - 1][l + 1] = matrix[k][l];
            }
        }  
        return cloneMatrix;
    }
    /*aplica um filtro de valor minimo á matriz, sendo o valor do novo pixel, 
    o mais baixo dos 8 valores da vizinhaça*/
    public static int [][] filtroMinimo(int[][] matrix) throws IOException {
        int matriz[][] = cloneMatriz(matrix);
        int pos = 0;
        int[][] medi = new int[matrix.length][matrix.length];
        for (int l = 1; l < matriz.length - 1; l++) {
            for (int c = 1; c < matriz.length - 1; c++) {
                int vetor[] = {matriz[l - 1][c - 1], matriz[l - 1][c], matriz[l - 1][c + 1], 
                    matriz[l][c - 1], matriz[l][c], matriz[l][c + 1], matriz[l + 1][c - 1],
                    matriz[l + 1][c], matriz[l + 1][c + 1]};
                int temp = calcula(vetor, matriz, pos);
                medi[l - 1][c - 1] = temp;
            }          
        }  
        //retorna alteração feita para escrever nome de novo ficheiro
        RWFile.getNovoNomeFicheiro("minimo.", Main.nomeFicheiro); 
        Gnuplot.gnuPlot();
        return medi;
    }
    
    /*aplica um filtro de mediana á matriz, sendo o valor do novo pixel, 
    o valor do meio dos 8 valores da vizinhaça, após ordenação*/
    public static int [][] filtroMediana(int[][] matrix) throws IOException {
        int matriz[][] = cloneMatriz(matrix);
        int pos = 4;
        int[][] medi = new int[matrix.length][matrix.length];
        for (int l = 1; l < matriz.length - 1; l++) {
            for (int c = 1; c < matriz.length - 1; c++) {
                int vetor[] = {matriz[l - 1][c - 1], matriz[l - 1][c], matriz[l - 1][c + 1],
                    matriz[l][c - 1], matriz[l][c], matriz[l][c + 1], matriz[l + 1][c - 1],
                    matriz[l + 1][c], matriz[l + 1][c + 1]};
                int temp = calcula(vetor, matriz, pos);
                medi[l - 1][c - 1] = temp;
            }           
        }  
        //retorna alteração feita para escrever nome de novo ficheiro
        RWFile.getNovoNomeFicheiro("mediana.", Main.nomeFicheiro);
        Gnuplot.gnuPlot();
        return medi;
    }
    
    /*aplica um filtro de valor maximo á matriz, sendo o valor do novo pixel, 
    o mais alto dos 8 valores da vizinhaça*/
    public static int [][] filtroMaximo(int[][] matrix) throws IOException {
        int matriz[][] = cloneMatriz(matrix);
        int pos = 8;
        int[][] medi = new int[matrix.length][matrix.length];
        for (int l = 1; l < matriz.length - 1; l++) {
            for (int c = 1; c < matriz.length - 1; c++) {
                int vetor[] = {matriz[l - 1][c - 1], matriz[l - 1][c], matriz[l - 1][c + 1],
                    matriz[l][c - 1], matriz[l][c], matriz[l][c + 1], matriz[l + 1][c - 1],
                    matriz[l + 1][c], matriz[l + 1][c + 1]};
                int temp = calcula(vetor, matriz, pos);
                medi[l - 1][c - 1] = temp;
            }            
        } 
        //retorna alteração feita para escrever nome de novo ficheiro
        RWFile.getNovoNomeFicheiro("maximo.", Main.nomeFicheiro);
        Gnuplot.gnuPlot();
        return medi;
     }
    
    //aplica um filtro de variancia á matriz
    public static int [] [] filtroVariancia(int[][] matrix) throws IOException {
        //clona matriz
        int[][] matriz = cloneMatriz(matrix);
        // nova matriz com o filtro;
        int novaMatriz[][] = new int[matrix.length][matrix.length];
        double num, variancia, result = 0;
        int soma; 
        for (int l = 1; l < matriz.length - 1; l++) {
            for (int c = 1; c < matriz.length - 1; c++) {
                double somatorio = 0;
                     //soma os elementos e depois calcula a média
                
                     soma = (matriz[l - 1][c - 1] + matriz[l - 1][c] + matriz[l - 1][c + 1] + matriz[l][c - 1]
                        + matriz[l][c] + matriz[l][c + 1] + matriz[l + 1][c - 1] + matriz[l + 1][c]
                        + matriz[l + 1][c + 1]);
                
                double media=calculaMedia(soma);
                //vetor da matriz 3x3
                int vetor[] = {matriz[l - 1][c - 1], matriz[l - 1][c], matriz[l - 1][c + 1], matriz[l][c - 1],
                    matriz[l][c], matriz[l][c + 1], matriz[l + 1][c - 1], matriz[l + 1][c],
                    matriz[l + 1][c + 1]};
                
                for (int i = 0; i < vetor.length; i++) {
                      //variância. Realiza o somatório de (cada elemento - media)^2/9 (nºs de elementos);
                   num = (double) vetor[i] - media;
                    result = (num * num);
                    somatorio = somatorio + result;                    
                } //realiza a divisão do somatório dos elementos.
                variancia = somatorio/9;
                if (variancia>255) {
                     variancia=255;
                }
            novaMatriz[l - 1][c - 1] = (int) Math.round ((double) variancia);
            }            
        }
        //retorna altração feita para escrita de nome de novo ficheiro
        RWFile.getNovoNomeFicheiro("variancia.", Main.nomeFicheiro);
        Gnuplot.gnuPlot();
        return novaMatriz;
    }
 
    //ordena os vetores da matriz para depois serem transferidos para os filtros;
    public static int[] ordena(int vetor[], int matrix[][]) {
        int aux;
        for (int i = 0; i < vetor.length; i++) {
            for (int j = 0; j < vetor.length; j++) {
                if (vetor[i] < vetor[j]) {
                    aux = vetor[i];
                    vetor[i] = vetor[j];
                    vetor[j] = aux;
                }
            }
        }
        return vetor;
    }

    //método calcula o filtro escolhido - busca a posicao no vetor;
    public static int calcula(int vetor[], int matriz[][], int posicao) {
        ordena(vetor, matriz);
        //percorre o vetor 
        for (int i = 0; i < vetor.length; i++) {
        }
        int medi = vetor[posicao];
        return medi;
    }


    
    /**
     * Método percorre e imprime a matriz.
     * @param matrix 
     */
        
    public static void  mostrarMatriz (int matrix [][]){
        for (int i = 0; i <  matrix.length; i++){
            for (int j = 0; j <  matrix.length; j++){                 
                System.out.printf(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        
    }
    
    //pergunta ao utilizador quais os valores quer utilizar como nova mascara para matri
    public static double[][] matrixpedida() {
        Scanner input=new Scanner(System.in); 
            double ler;
            double[][] position = new double[3][3];
            for(int i = 0; i < position.length; i++) {
                for(int j=0; j<position.length; j++){            
                    System.out.println("Insira o valor da posição (" + i+ "," +j+")");
                    String entrada= input.nextLine();
                    if(!entrada.matches( "^-?\\d*\\.?\\d+$[0-9]*$") ){
                        System.out.println("Insira apenas números!");
                        j=j-1;
                    }else{
                        ler=Double.parseDouble(entrada);
                        position[i][j]=ler;
                    }
                }
            }
     return position;
    }
       
    // metodo que valida o valor introduzido, se nao for numero pede numero valido    
    public static int returnIndiceMatriz(String input){
        int posicao;
        try{
            posicao= Integer.parseInt(input);
            return posicao; 
        }catch(Exception e){
            return -1000000;
        }
    }
    
//metodo que calcula a soma da matriz mascara
    public static double calculaSoma(double [][]position) {
    double soma=0;
        for (int i = 0; i <position.length ; i++) {
            for (int j=0; j<position.length; j++){ 
                soma = soma + position[i][j];
            }
        }
    return soma;
    }
    
    //metodo que calcula a matriz normalizada    
    public static double[][] matrixNormalizada(double [][]position){
        /*Para obter a matriz normalizada é dividido o valor de cada número da matriz pela 
        soma de todos os números*/    
        double soma= calculaSoma(position);
        double [][] matrixnorma= new double [3][3];
        for (int l = 0; l < position.length; l++) {
            for (int c = 0; c < position.length; c++) {
                matrixnorma [l][c] = position [l][c]/soma;
            }    
        }
        return matrixnorma;
    }

    //aplica filtro de convulação
    public static int [][]  filtroConvuluçao (int [][] matrix) throws IOException {
        /*para o calculo do filtro convolução é feito de somatório dos 9 valores resultado 
        da multiplicação do elemento da matriz normalizada pelo mesmo elemento da matrix 3 x 3 de 
        cada número com a vizinhança é feita a mesma operação para todos as posiçoes da matriz*/
        double[][] position = matrixpedida();
        double valor=0;
        int matriz[][] = cloneMatriz(matrix);
        double [][] matrixnorma = matrixNormalizada (position);
        int[][] conv = new int[matrix.length][matrix.length];
        for (int l = 1; l < matriz.length-1; l++) {
            for (int c = 1; c < matriz.length-1; c++){
                valor = matriz[l - 1][c - 1]*matrixnorma[0][0] + matriz[l - 1][c]*matrixnorma[0][1] + matriz[l - 1][c + 1]*matrixnorma[0][2] + matriz[l][c - 1]*matrixnorma[1][0] + matriz[l][c]*matrixnorma[1][1] + matriz[l][c + 1]*matrixnorma[1][2] + matriz[l + 1][c - 1]*matrixnorma[2][0] + matriz[l + 1][c]*matrixnorma[2][1] + matriz[l + 1][c + 1]*matrixnorma[2][2];    
                double temp = Math.round((double)valor);
                if (temp>255) {
                    temp=255;
                }else {
                if (temp<0){
                    temp=0;}}
        conv [l-1][c-1]=(int) temp;
            }
        }
        RWFile.getNovoNomeFicheiro("convulacao.", Main.nomeFicheiro);
        Gnuplot.gnuPlot();
       return conv;
    }
    
    public static int [][]  filtroConvuluçaotest (int [][] matrix) {
        double [][]position = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double valor=0;
        int matriz[][] = cloneMatriz(matrix);
        double [][] matrixnorma = matrixNormalizada (position);
        int[][] conv = new int[matrix.length][matrix.length];
        for (int l = 1; l < matriz.length-1; l++) {
            for (int c = 1; c < matriz.length-1; c++) {
                valor = matriz[l - 1][c - 1]*matrixnorma[0][0] + matriz[l - 1][c]*matrixnorma[0][1] + matriz[l - 1][c + 1]*matrixnorma[0][2] + matriz[l][c - 1]*matrixnorma[1][0] + matriz[l][c]*matrixnorma[1][1] + matriz[l][c + 1]*matrixnorma[1][2] + matriz[l + 1][c - 1]*matrixnorma[2][0] + matriz[l + 1][c]*matrixnorma[2][1] + matriz[l + 1][c + 1]*matrixnorma[2][2];    
                double temp = Math.round((double)valor);
                if (temp>255) {
                     temp=255;
                } else {
                if (temp<0){
                    temp=0;}}
        conv [l-1][c-1]=(int) temp;
        }
    }
       return conv;
    }
    public static int[][] rotacaoMatriz(int[][] matrizVelha) {
        int n = matrizVelha.length;
        int[][] matrizRodada = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizRodada[i][j] = matrizVelha[n - 1 - j][i];
            }
        }
        RWFile.getNovoNomeFicheiro("rotacao.", Main.nomeFicheiro);
        return matrizRodada;
    }
}

