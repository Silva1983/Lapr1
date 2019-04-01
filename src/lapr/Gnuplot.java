/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr;

import java.io.IOException;

/**
 *
 * @author Shaker
 */
public class Gnuplot {

    public static void gnuPlot() throws IOException {
        /*
        Sempre que se usa o gnuplot é impresso um ficheiro temporário,
        uma vez que o GNU tem de ler sempre de um ficheiro.txt        
        */
        RWFile.escreverFile("temp");
        Runtime rt = Runtime.getRuntime();
        //Process prcs = rt.exec("gnuplot  -e \" filename = 'temp.txt'\"  Gnuplot.gp");
        Process prcs = rt.exec("cmd /c script.bat " + "temp.txt");
    }

}
