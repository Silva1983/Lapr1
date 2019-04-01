package lapr;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;
import org.la4j.Matrix;

public class Menu {
    //este entrada serve para o programa nao ler a matriz do ficheiro de novo sempre que se chama o menu
       public static int entrada=0;
    
       public static void menu() throws FileNotFoundException, IOException {
        int option = 0;
        Scanner read = new Scanner(System.in);
        //verifica se o ficheiro que vai ler está ou não comprimido e se ja leu alguma vez
        if(RWFile.returnaTipoImagem(Main.getPathName()) && entrada==0){
            Main.dimensaoMatriz = RWFile.calculaDimensaoMatriz(Main.getPathName());
            Main.matrizImagem=RWFile.lerFicheiroMatrizComprimida(Main.getPathName());
            entrada=1;
        }else{
            if(entrada==0){
                Main.dimensaoMatriz = RWFile.calculaDimensaoMatriz(Main.getPathName());
                Main.matrizImagem=RWFile.lerFicheiroMatrizDescomprimido(Main.getPathName());
                entrada=1;
            }    
        }
        do {
            System.out.println("***************************** Escolha uma opção *****************************\n"
                    + "\n\t 1 - Obter caracterização e aplicar filtros"
                    + "\n\t 2 - Criar ficheiro com nova imagem"
                    + "\n\t 3 - Comprimir Imagem"
                    + "\n\t 4 - Mostrar matriz"
                    + "\n\t 0 - Saír");
             
            String temp = read.nextLine();
            //if valida se a opção introduzida é valida, ou seja, se nao for uma das opçoes do menu pede ao utilizador novo valor
            if(temp.length()!=1){
                System.out.println("O valor que introduziu não é válido, por favor introduza um número válido");
                menu();
                break;
            }else{
                if(!temp.equals("0") && !temp.equals("1") && !temp.equals("2") && !temp.equals("3")  && !temp.equals("4")){
                    System.out.println("O valor que introduziu não é válido, por favor introduza um número válido!!");
                    menu();
                    break;
                }else{
                    option = Integer.parseInt(temp);
                }
            }

            switch (option) {

                case 1:
                    subMenu();
                    break;
                case 2:
                    RWFile.escreverFile(Main.nomeFicheiro);
                    subMenu();
                    break;
                case 3: 
                    System.out.println("Qual o grau de compressão que deseja?");
                    int grau=read.nextInt();
                    if(grau>Main.dimensaoMatriz){
                        System.out.println("grau de compresão maior que dimensao imagem, impossivel efetuar");
                        menu();
                        break;
                    }
                    Matrix[] matrizSVD= Compressao.decomposeSVD(Main.matrizImagem);
                    int espacoGanho=Compressao.calcularGanhosEspaco(grau);
                    if(espacoGanho<=0){
                        System.out.println("Este grau de compressao leva a perda de espaço");
                        System.out.println("para parar digite n, para continuar qualquer outro input");
                        String prosseguir=read.next();
                        if(prosseguir.equals("n")){
                            menu();
                            break;
                        }else{
                            System.out.println("Espaço perdido é " + espacoGanho*-1);
                            double[][] matrizComprimida = new double[Main.matrizImagem.length][Main.matrizImagem.length];
                            matrizComprimida=Compressao.descomprimirSVD(grau, matrizSVD);
                            double erro=Compressao.calcularPerdaInformacao(matrizComprimida);
                            System.out.println("A informacao perdida foi" + erro);                        
                        }
                        }else{
                            System.out.println("Espaço ganho é " + espacoGanho);
                            double[][] matrizComprimida = new double[Main.matrizImagem.length][Main.matrizImagem.length];
                            matrizComprimida=Compressao.descomprimirSVD(grau, matrizSVD);
                        double erro=Compressao.calcularPerdaInformacao(matrizComprimida);
                        System.out.println("A informacao perdida foi " + erro);
                        } 
                    Gnuplot.gnuPlot();
                    System.out.println("Deseja gravar a imagem comprimida num ficheiro? para prosseguir digite y, "
                            + "caso contrario qualquer outro input");
                    String opcaoGravar = read.next();
                    if(opcaoGravar.equals("y")){
                        String nomeMatriz=Compressao.escreverMatrizComprimidaFile(matrizSVD, grau);
                        System.out.println("Escrita efetuada com sucesso, com o nome " + nomeMatriz);
                        menu();
                        break;
                    }else{
                        menu();
                        break;
                    }
                case 4:
                    Filtros3x3.mostrarMatriz(Main.matrizImagem);
                    Gnuplot.gnuPlot();
                    menu();
                    break;
                case 0: System.out.println("Deseja gravar antes de sair? Para gravar digite y, qualquer outra tecla para sair sem gravar!");
                String input = read.nextLine();   
                if(input.equals("y")){
                    RWFile.escreverFile(Main.nomeFicheiro);             
                }
                break;
            }
        } 
        while (option < 0 || option > 4);
    }

    public static void subMenu() throws FileNotFoundException, IOException {
        int option;
        Scanner read = new Scanner(System.in);
       
        do {
            System.out.println("\n\n***************************** Escolha uma opção *****************************\n"
                    + "\n\t 1 - Caracterização da imagem"
                    + "\n\t 2 - Aplicar filtros"
                    + "\n\t 3 - Mostrar matriz"
                    + "\n\t 0 - Voltar");
            String temp = read.nextLine();
            //o if valida se a opção introduzida é valida, ou seja, se nao for uma das opçoes do menu pede ao utilizador novo valor            
            if(temp.length()!=1){
                System.out.println("O valor que introduziu não é válido, por favor introduza um número válido");
                subMenu();
                break;
            }else{
                if(!temp.equals("0") && !temp.equals("1") && !temp.equals("2") && !temp.equals("3")){
                    System.out.println("O valor que introduziu não é válido, por favor introduza um número válido!");
                    subMenu();
                    break;
                }else{
                    option = Integer.parseInt(temp);
                }
            }
            switch (option) {
                case 1:
                    menuCaracterizaçao();
                    break;
                case 2:
                    menuFiltros();
                    break;
                case 3:
                    Filtros3x3.mostrarMatriz(Main.matrizImagem);
                    Gnuplot.gnuPlot();
                    subMenu();
                    break;
                case 0:
                    menu();
                    break;
              
            }

        } 
        while (option < 0 || option > 3);
    }

    public static void menuCaracterizaçao() throws FileNotFoundException, IOException {
        int option;
        Scanner read = new Scanner(System.in);
        do {
            System.out.println("\n\n***************************** Escolha uma opção *****************************\n"
                    + "\n\t 1 - Soma e média de cada linha"
                    + "\n\t 2 - Soma e média de cada coluna"
                    + "\n\t 3 - Soma e média da matriz"
                    + "\n\t 4 - Valores próprios"
                    + "\n\t 5 - Vetores próprios"
                    + "\n\t 6 - Trapézios"
                    + "\n\t 7 - Mostrar matriz"
                    + "\n\t 0 - voltar");
            String temp = read.nextLine();
            //o if valida se a opção introduzida é valida, ou seja, se nao for uma das opçoes do menu pede ao utilizador novo valor            
            if(temp.length()!=1){
                System.out.println("O valor que introduziu não é válido, por favor introduza um número válido");
                menuCaracterizaçao();
                break;
            }else{
                if(!temp.equals("0") && !temp.equals("1") && !temp.equals("2") && !temp.equals("3") && !temp.equals("4") && !temp.equals("5")&& !temp.equals("6")&& !temp.equals("7")){
                    System.out.println("O valor que introduziu não é válido, por favor introduza um número válido!");
                    menuCaracterizaçao();
                    break;
                }else{
                    option = Integer.parseInt(temp);
                }
            }
            switch (option) {
                case 1:
                    Caracterizacao.sumLine(Main.matrizImagem);
                    menuCaracterizaçao();
                    break;

                case 2:
                    Caracterizacao.sumColumn(Main.matrizImagem);
                    menuCaracterizaçao();
                    break;

                case 3:
                    int soma = Caracterizacao.sumMatriz(Main.matrizImagem);
                    double media= Caracterizacao.mediaMatriz(Main.matrizImagem);
                    System.out.println("Soma da matriz é " + soma);
                    System.out.println("Média da matriz é " + media);
                    menuCaracterizaçao();
                    break;

                case 4:
                    Caracterizacao.ownValuePrint(Main.matrizImagem);
                    menuCaracterizaçao();
                    break;

                case 5:
                    Caracterizacao.printOwnVector(Main.matrizImagem);                    
                    menuCaracterizaçao();
                    break;
                
                case 6:
                    Caracterizacao.printTrapezio(Main.matrizImagem);                
                    menuCaracterizaçao();
                    break;
                case 7:
                    Filtros3x3.mostrarMatriz(Main.matrizImagem);
                    Gnuplot.gnuPlot();
                    menuCaracterizaçao();
                    break;
                case 0:
                    subMenu();
                    break;
            }
        } 
        while (option < 0 || option > 7);
    }

    public static void menuFiltros() throws FileNotFoundException, IOException {
        int option;
        Scanner read = new Scanner(System.in);
        do {

            System.out.println("\n\n***************************** Escolha uma opção *****************************\n"
                    + "\n\t 1 - Filtro da média"
                    + "\n\t 2 - Filtro da mediana"
                    + "\n\t 3 - Filtro máximo"
                    + "\n\t 4 - Filtro mínimo"
                    + "\n\t 5 - Filtro variância"
                    + "\n\t 6 - Filtro da convolução"
                    + "\n\t 7 - Rotação 90º Graus Direita"
                    + "\n\t 8 - Rotação 90º Graus Esquerda"
                    + "\n\t 9 - Mostrar a matriz"
                    + "\n\t 0 - voltar");

            String temp = read.nextLine();
            //o if valida se a opção introduzida é valida, ou seja, se nao for uma das opçoes do menu pede ao utilizador novo valor            
            if(temp.length()!=1){
                System.out.println("O valor que introduziu não é válido, por favor introduza um número válido");
                menuFiltros();
                break;
            }else{
                if(!temp.equals("0") && !temp.equals("1") && !temp.equals("2") && !temp.equals("3") && !temp.equals("4") && !temp.equals("5")&& !temp.equals("6")&& !temp.equals("7")&& !temp.equals("8")&& !temp.equals("9")){
                    System.out.println("O valor que introduziu não é válido, por favor introduza um número válido!");
                    menuFiltros();
                    break;
                }else{
                    option = Integer.parseInt(temp);
                }
            }

            switch (option) {

                case 1:
                    Main.matrizImagem=Filtros3x3.filtroMedia(Main.matrizImagem);
                    Gnuplot.gnuPlot();
                    menuFiltros();
                    break;

                case 2:
                    Main.matrizImagem=Filtros3x3.filtroMediana(Main.matrizImagem);
                    Gnuplot.gnuPlot();
                    menuFiltros();
                    break;

                case 3:
                    Main.matrizImagem=Filtros3x3.filtroMaximo(Main.matrizImagem);
                    Gnuplot.gnuPlot();
                    menuFiltros();
                    break;

                case 4:                 
                    Main.matrizImagem=Filtros3x3.filtroMinimo(Main.matrizImagem);
                    menuFiltros();
                    break;
                    
                case 5:                  
                    Main.matrizImagem=Filtros3x3.filtroVariancia(Main.matrizImagem);
                    Gnuplot.gnuPlot();
                    menuFiltros();
                    break;

                case 6:
                    Main.matrizImagem=Filtros3x3.filtroConvuluçao(Main.matrizImagem);
                    Gnuplot.gnuPlot();
                    menuFiltros();
                    break;
                    
                case 7:
                    Main.matrizImagem=Rotacao.rotacaoMatrizDireita(Main.matrizImagem);
                    Gnuplot.gnuPlot();
                    menuFiltros();
                    break;

                case 8:
                    Main.matrizImagem=Rotacao.rotacaoMatrizEsquerda(Main.matrizImagem);
                    Gnuplot.gnuPlot();
                    menuFiltros();
                    break;
                
                case 9:
                    Filtros3x3.mostrarMatriz(Main.matrizImagem);
                    Gnuplot.gnuPlot();
                    menuFiltros();
                    break;
                  
                case 0:
                    subMenu();
                    break;
            }
        } 
        while (option < 0 || option > 7);
    }
}
