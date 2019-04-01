package lapr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;




public class Main {
    public static int dimensaoMatriz = 0;
    public static int[][] matrizImagem = new int[dimensaoMatriz][dimensaoMatriz];
    public static String pathname = null;
    public static String nomeFicheiro;
    

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        //Continua ciclo que valida o pathname caso este seja vazio(Enter sem info) ou null(não foi fornecido nenhum parametro na linha de comandos)
        do {
            if (args.length > 0) {
                Main.pathname = args[0];
                System.out.println(pathname);
            } else {
                System.out.println("Por favor insira o path correcto");
                pathname = in.nextLine();
            }
        } while (pathname == null || pathname.length() == 0);
        Menu.menu();
    }

    public static String getPathName() {
        return Main.pathname;
    }
}
