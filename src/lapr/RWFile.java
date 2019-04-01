package lapr;

import java.io.*;
import java.util.Scanner;

public class RWFile {
    
    public static boolean returnaTipoImagem(String pathFicheiro) throws FileNotFoundException{
        //verificar se o ficheiro é um ficheiro de imagem comprimida ou um ficheiro normal
        boolean comprimida = true;
        Scanner scannerFile = new Scanner(new File(pathFicheiro));
        scannerFile.nextLine();
        scannerFile.nextLine();
        String[] linha3 = scannerFile.nextLine().split(",");
        if(linha3.length!=1){
            comprimida=false;
        }
        return comprimida;
    }
    
    public static String getNomeMatriz(String pathFicheiro) throws FileNotFoundException{
        //retorna nome da imagem dentro do ficheiro 
        Scanner scannerFile = new Scanner(new File(pathFicheiro));
        String[] linha1;
        linha1 = scannerFile.nextLine().split("  ");
        String titulo = linha1[0];
        return titulo;
    }

    public static int[][] lerFicheiroMatrizDescomprimido(String pathFicheiro) throws FileNotFoundException {
        int dimensaoImagem = 0;
        boolean parsable = true;
        int[][] matriz = new int[0][0];
        String[] linha1;        
        File f = new File(pathFicheiro);
        //verificar se ficheiro existe antes de o ler
        if(!f.exists()) { 
            System.out.println("Ficheiro inexistente ou não encontrado");
            return null;
        }        
        Scanner scannerFile = new Scanner(new File(pathFicheiro));
        linha1 = scannerFile.nextLine().split("  ");
        //verificar se primeira linha so contem duas linhas
        if(linha1.length!=2){
            System.out.println("Formato incorreto de titulo");
            return null;
        }
        //apanhar titulo da matriz para posterior escrita de ficheiro
        getNovoNomeFicheiro("", linha1[0]);
        //verificar dimensao imagem
        try{
            dimensaoImagem=Integer.parseInt(linha1[1]);
        }catch(NumberFormatException e){
            parsable = false;
        }
        if(parsable==false){
            System.out.println("Impossivel ler dimensão de Imagem");
            return null;
        }
        //incialização matriz de string para aramzenamento temporario informação e matriz fibnal para retornar
        String[][] matriztemp = new String[dimensaoImagem][dimensaoImagem];
        int[][] matrizImagem = new int[dimensaoImagem][dimensaoImagem];
        int contadorMatriz = 0;
        scannerFile.nextLine();
        //colocar valores do ficheiro em matriz
        while (scannerFile.hasNextLine()) {
            String[] linhaMatriz = scannerFile.nextLine().trim().split(",");
            if(linhaMatriz.length!=dimensaoImagem){
                System.out.println("matriz com erros");
                return matriz;
            }
            for (int i = 0; i < dimensaoImagem; i++) {
                matriztemp[contadorMatriz][i] = linhaMatriz[i].trim();
            }
            contadorMatriz++;
        }
        //passar informação matriz de strings para matriz de int
        for (int a = 0; a < matrizImagem.length; a++) {
            for (int b = 0; b < matrizImagem.length; b++) {
                matrizImagem[a][b] = Integer.parseInt(matriztemp[a][b]);
            }
        }
        return matrizImagem;
    }
    
    public static int calculaDimensaoMatriz(String pathname) throws FileNotFoundException{
        int dimensaoImagem = 0;
        boolean parsable = true;
        int[][] matriz = new int[0][0];
        String[] linha1;        
        File f = new File(pathname);
        if(!f.exists()){ 
            System.out.println("Ficheiro inexistente ou não encontrado");
            return 0;
        }                
        Scanner scannerFile = new Scanner(new File(pathname));
        linha1 = scannerFile.nextLine().split("  ");
        if(linha1.length!=2){
            System.out.println("Formato incorreto de titulo");
            return 0;
        }
        try{
            dimensaoImagem=Integer.parseInt(linha1[1]);
        }catch(NumberFormatException e){
            parsable = false;
        }
        if(parsable==false){
            System.out.println("Impossivel ler dimensão de Imagem");       
        }
        return dimensaoImagem;
    }
    
    public static int totalElementos(int[][] array){
        //calcular dimensao imagem para posterior escrita(metodo escreverFile)
        int elem = 0;
        for (int i = 0; i < array.length; i++) {
          elem++;
        }
        return elem;               
    }
    
    public static void escreverFile(String nomeFicheiro) throws IOException{
        //esccrever novo ficheiro com a matriz e as modificações que lhe foram feitas
        String novoFicheiro=nomeFicheiro+".txt";
        FileWriter fich = new FileWriter(new File(novoFicheiro));
        PrintWriter write = new PrintWriter(fich);
        int ordem = totalElementos(Main.matrizImagem);
        int cont=0;
        //escrever titulo e dimensao
        write.printf("%s  %d\n", novoFicheiro, ordem);
        //avançar duas linhas para chegar à 3 linha e escrever a matriz
        write.println("");
        write.println("");
        //escrever a matriz
        for (int i = 0; i < Main.matrizImagem.length; i++){
            for (int j = 0; j < Main.matrizImagem[i].length; j++) {
                //quando contador igual a ordem(dimensao da imagem) avançar de linha
                if (cont == ordem ){
                    write.println("");
                    cont = 0;
                }
                write.printf("%d", Main.matrizImagem[i][j]);
                //quando contador nao igual a ordem introduzir virgula a separar valores
                if (cont < ordem-1){                    
                    write.printf(", ");      
                }
                cont++;
            }
        }
        fich.close();
        System.out.printf("\nNovo ficheiro gravado com sucesso, com o nome " + nomeFicheiro+ ".txt");
        System.out.println("");
    }
    public static int[][] lerFicheiroMatrizComprimida(String pathFicheiro) throws IOException, FileNotFoundException{
        boolean parsable = true;
        String[] linha1;
        File f = new File(pathFicheiro);
        
        //verificações existencia de ficheiro
        if(!f.exists()) { 
            System.out.println("Ficheiro inexistente ou não encontrado");
        }
        Scanner scannerFile = new Scanner(new File(pathFicheiro));
        
        //obtenção titulo da matriz e dimensao imgaem
        linha1 = scannerFile.nextLine().split("  ");
        //validaçao formato titulo
        if(linha1.length!=2){
            System.out.println("Formato incorreto de titulo");
            return null;
        }
        getNovoNomeFicheiro("", linha1[0]);
        //validação se dimensão imagem é um inteiro
        try{
            Main.dimensaoMatriz=Integer.parseInt(linha1[1]);
        }catch(NumberFormatException e){
            parsable = false;
        }
        if(parsable==false){
            System.out.println("Impossivel ler dimensão de Imagem");
            return null;
        }
       
        //inicialização matriz final e matriz temporaria para fazer sumatorio com matriz final
        double[][] matrizImagem = new double[Main.dimensaoMatriz][Main.dimensaoMatriz];
        double[][] matrizImagemTemp = new double[Main.dimensaoMatriz][Main.dimensaoMatriz];
        
        //contador matriz para saber qual o valor que está a ler, se o SVD, o U ou o V.
        int contadorMatriz = 0;
        double[] vetorUdouble = new double[Main.dimensaoMatriz];
        double[] vetorVdouble = new double[Main.dimensaoMatriz];
        double singleValueDouble = 0;
        
        //saltar a linha a branco
        scannerFile.nextLine();
        
        
        //while vai iterar sobre ficheiro para cada linha e obter uma matriz temp por cada iteração
        while(scannerFile.hasNextLine()){
            if(contadorMatriz==0){    
        
            //obtenção e validação do single value    
            String singleValueString = scannerFile.nextLine();
            try{
                singleValueDouble=Double.parseDouble(singleValueString);
            }catch(NumberFormatException e){
                parsable=false;
            }
            if(parsable==false){
                System.out.println("SVD não é número");
                return null;
            }
            contadorMatriz++;//avançar para leitura vetor U
            }
        
            if(contadorMatriz==1){
                //obtenção e validação do vetor U
                String[] vetorUstring= scannerFile.nextLine().split(",");
                for (int i=0; i<Main.dimensaoMatriz; i++){
                    try{
                        vetorUdouble[i]= Double.parseDouble(vetorUstring[i]);
                    }catch(NumberFormatException e){
                       parsable=false;
                    }
                    if(parsable==false){
                        System.out.println("erro no vetor U da imagem comprimida");
                        return null;
                    }
                }
                contadorMatriz++; //saltar para vetor V
            }
        
            if(contadorMatriz==2){
                //validação e obtenção vetor  V
                String[] vetorVstring= scannerFile.nextLine().split(",");
                for (int i=0; i<Main.dimensaoMatriz; i++){
                    try{
                        vetorVdouble[i]= Double.parseDouble(vetorVstring[i]);
                    }catch(NumberFormatException e){
                        parsable=false;
                    }if(parsable==false){
                        System.out.println("erro no vetor V da imagem comprimida");
                        return null;
                    }   
                }
                contadorMatriz=0;
                for(int j=0; j<Main.dimensaoMatriz; j++){
                    for(int s=0; s<Main.dimensaoMatriz; s++){
                        matrizImagemTemp[j][s]=singleValueDouble*vetorUdouble[j]*vetorVdouble[s];
                    }
                }
                for(int d=0; d<Main.dimensaoMatriz; d++){
                    for(int u=0; u<Main.dimensaoMatriz; u++){
                    matrizImagem[d][u]= matrizImagem[d][u]+matrizImagemTemp[d][u];
                    }
                }   
            }
        }
        int[][] matrizImagemFinal= new int[matrizImagem.length][matrizImagem[0].length];
        for(int i=0; i<Main.dimensaoMatriz; i++){
            for(int j=0; j<Main.dimensaoMatriz; j++){
             matrizImagemFinal[i][j]=(int) Compressao.arredondar(matrizImagem[i][j], 0);
             
            }
        }
        return matrizImagemFinal;
    }
    public static void getNovoNomeFicheiro(String alteracao, String nomeAtual){
        String novoNome;
        novoNome = alteracao + nomeAtual;
        Main.nomeFicheiro= novoNome;
    }
}

