/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.singleton;

import java.util.Random;

/**
 *
 * @author ivaanic2
 */
public class GeneratorSlucajnogBrojaSingleton {
    private static GeneratorSlucajnogBrojaSingleton instance = new GeneratorSlucajnogBrojaSingleton();
    public static int sjeme;
    Random rand = new Random(sjeme);
    StatistikaSingleton statistika = StatistikaSingleton.getInstance();

    private GeneratorSlucajnogBrojaSingleton() {
    }
    
    public static GeneratorSlucajnogBrojaSingleton getInstance(){
        if (instance == null) {
            instance = new GeneratorSlucajnogBrojaSingleton();
        }
        return instance;
    }
    
    public int dajSlucajniBroj(int odBroja, int doBroja){
        int slucajniBroj = odBroja + rand.nextInt((doBroja - odBroja) + 1);
        statistika.setKoristenjaGeneratora(statistika.getKoristenjaGeneratora()+1);
        return slucajniBroj;
    }

    public float dajSlucajniBroj(float odBroja, float doBroja) {
        float slucajniBroj = odBroja + rand.nextFloat() * (doBroja - odBroja);
        statistika.setKoristenjaGeneratora(statistika.getKoristenjaGeneratora()+1);
        return slucajniBroj;
    }
}
