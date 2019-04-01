package lapr;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.la4j.Matrix;
import org.la4j.decomposition.SingularValueDecompositor;
import org.la4j.matrix.dense.Basic2DMatrix;

public class Compressao {
    
    public static Matrix[] decomposeSVD(int[][] matriz){
        double[][] matrizDouble = new double[matriz.length][matriz.length];
        //converter matriz de int para matriz de double para poder aplicar library
        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz[0].length; j++){
                matrizDouble[i][j]=matriz[i][j];
            }
        }
        Matrix matriz2D = new Basic2DMatrix(matrizDouble);           
        SingularValueDecompositor SVD = new SingularValueDecompositor(matriz2D);
        boolean teste = SVD.applicableTo(matriz2D);
        // testar se matriz pode ser decomposta
        if(teste){
            //decompor matriz em SVD
            Matrix[] matrizSVD = SVD.decompose();
            return matrizSVD;
        }else{
            return null;
        }
    }
    
    public static double[][] descomprimirSVD(int grau, Matrix[] matrizSVD){
        //transformar Matriz[] em trez matrizes[][]
        double matU [][]= matrizSVD[0].toDenseMatrix().toArray();
        double matD [][]= matrizSVD[1].toDenseMatrix().toArray();
        double matV [][]= matrizSVD[2].toDenseMatrix().toArray();
        double[][] matrizComprimida= new double[Main.matrizImagem.length][Main.matrizImagem.length];
        //transformar as tres matrizes numa so matriz
        for(int i=0; i<grau; i++){
            for(int j=0; j<matU.length; j++){
                for(int k=0; k<matV.length; k++){
                    matrizComprimida[j][k]= matrizComprimida[j][k]+matU[i][k]*matV[k][i]*matD[i][i];                    
                }
            }
        }
        return matrizComprimida;
    }
          
    public static int calcularGanhosEspaco(int grau) {
        int espacoTotal=Main.matrizImagem.length*Main.matrizImagem.length;
        int espacoComprimido = grau*Main.matrizImagem.length*2+grau;
        int espacoGanho = espacoTotal-espacoComprimido;
        return espacoGanho;
    }

    public static double calcularPerdaInformacao(double[][] matrizComprimida){
        double soma=0;
        double somaTemp=0;
        int[][] matrizInt=new int[Main.matrizImagem.length][Main.matrizImagem.length];
        //fazer subtraçao matriz inicial menos matriz comprimida
        for(int i=0; i<Main.matrizImagem.length; i++){
            for(int j=0; j<Main.matrizImagem[0].length; j++){
                matrizInt[i][j]=(int)matrizComprimida[i][j];
            }
        }
        for(int i=0; i<Main.matrizImagem.length; i++){
            for(int j=0; j<Main.matrizImagem.length; j++){
                somaTemp=Math.abs(matrizInt[i][j]-matrizComprimida[i][j]);
                soma = soma+somaTemp;
            }
        }
        //aplicar formula erro
        double erroAbsolutoMedio = (1/((double)Main.matrizImagem.length*(double)Main.matrizImagem[0].length))*soma;
        return erroAbsolutoMedio;         
    }

    public static String escreverMatrizComprimidaFile(Matrix[] matrizSVD, int grau) throws IOException{
        String titulo = RWFile.getNomeMatriz(Main.getPathName());
        //converter MATRIZ[] para SVD
        double matU [][]= matrizSVD[0].toDenseMatrix().toArray();
        double matD [][]= matrizSVD[1].toDenseMatrix().toArray();
        double matV [][]= matrizSVD[2].toDenseMatrix().toArray();
        String nomeMatriz=titulo+"Compressed."+grau+"singleValue"+".txt";
        //inicilizar escritores de ficheiro
        FileWriter fich = new FileWriter(new File(nomeMatriz));
        PrintWriter write = new PrintWriter(fich);
          
        //escrita titulo matriz comprimida
        write.printf("#");
        write.printf("%s", titulo);
        write.print("  ");
        write.println(Main.dimensaoMatriz);
        write.println("");
        for(int i=0; i<grau; i++){
            int cont=0;
            int cont1=0;       
        
            // escrever single value
            write.print(arredondar(matD[i][i], 3));
            write.println("");
        
            // escrever vetor U
            for(int j=0; j<matU.length-1; j++){
                write.print(arredondar(matU[j][i], 3));
                write.printf(",");
                cont++;
            }        
            //escrever ultimo elemento vetor U
            write.println(arredondar(matU[cont][i], 3));
        
            //escrever vetor V
            for(int j=0; j<matU.length-1; j++){               
                write.print(arredondar(matV[j][i], 3));
                write.printf(",");
                cont1++;
            }
            //escrever ultimo elemento vetor V
            write.print(arredondar(matV[cont1][i], 3));
            write.println("");               
        }
    fich.close();
    return nomeMatriz;
    }

    public static double arredondar(double value, int casasDecimais) {
        //arredondar valores double
        if (casasDecimais < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, casasDecimais);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }    
}

