/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3;

import uzdiz.ivaanic2.zadaca3.singleton.GeneratorSlucajnogBrojaSingleton;
import uzdiz.ivaanic2.zadaca3.singleton.StatistikaSingleton;
import java.util.List;
import uzdiz.ivaanic2.zadaca3.model.Aktuator;
import uzdiz.ivaanic2.zadaca3.model.Mjesto;
import uzdiz.ivaanic2.zadaca3.model.Senzor;

/**
 *
 * @author ivaanic2
 */
public class InicijalizacijaSustava {

    GeneratorSlucajnogBrojaSingleton generatorBrojeva = GeneratorSlucajnogBrojaSingleton.getInstance();
    public static int prosjecnaIspravnost;
    StatistikaSingleton statistika = StatistikaSingleton.getInstance();
    PrikazPrograma prikaz = PrikazPrograma.getInstance();
    
    
    public void inicijalizacijskaPoruka(String uredaj) {
        prikaz.ispisi(String.format("|%-65s|", "Init poruka za '" + uredaj + "'"));
        statistika.setInicijalizacijskePoruke(statistika.getInicijalizacijskePoruke()+1);
    }

    public int vracenaPoruka() {
        int slucajniBroj = generatorBrojeva.dajSlucajniBroj(1, 100);
        if (slucajniBroj <= prosjecnaIspravnost) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public void inicijalizirajUredajePoMjestima(List<Mjesto> listaMjesta) {
        int statusSenzora = 0;
        int statusAktuatora = 0;
        prikaz.ispisi("");
        prikaz.ispisi(String.format("%50s","INICIJALIZACIJA SUSTAVA"));
        for (Mjesto mj : listaMjesta) {
            prikaz.ispisi(String.format("|%-65s|", "================================================================="));
            prikaz.ispisi(String.format("|%-65s|", "MJESTO: " + mj.getNaziv()));
            prikaz.ispisi(String.format("|%-65s|", "-----------------------------------------------------------------"));
            prikaz.ispisi(String.format("|%-42s|%-22s|", "Naziv uredaja", "Status uredaja"));
            prikaz.ispisi(String.format("|%-65s|", "-----------------------------------------------------------------"));
            for (int i = 0; i < mj.getListaSenzoraZaMjesto().size(); i++) {
                Senzor senzor = mj.getListaSenzoraZaMjesto().get(i);
                inicijalizacijskaPoruka(senzor.getNaziv());
                statusSenzora = vracenaPoruka();              
                if (statusSenzora == 0) {
                    senzor.setBrojGreski(3);
                }
                senzor.setStatus(statusSenzora);
                prikaz.ispisi(String.format("|%-42s|%22d|", "Senz: " + senzor.getNaziv(), senzor.getStatus()));
            }
            for (int i = 0; i < mj.getListaAktuatoraZaMjesto().size(); i++) {
                Aktuator aktuator = mj.getListaAktuatoraZaMjesto().get(i);
                inicijalizacijskaPoruka(aktuator.getNaziv());
                statusAktuatora = vracenaPoruka();
                if (statusAktuatora == 0) {
                    aktuator.setBrojGreski(3);
                }
                aktuator.setStatus(statusAktuatora);
                prikaz.ispisi(String.format("|%-42s|%22d|", "Akt: " + aktuator.getNaziv(), aktuator.getStatus()));
            }
        }
        prikaz.ispisi(String.format("|%-65s|", "================================================================="));
        prikaz.ispisi("");
    }

    public void inicijalizirajSenzor(Senzor senzor) {
        inicijalizacijskaPoruka(senzor.getNaziv());
        int statusSenzora = vracenaPoruka();
        senzor.setStatus(statusSenzora);
        prikaz.ispisi(String.format("|%-65s|", "-----------------------------------------------------------------"));
        prikaz.ispisi(String.format("|%-42s|%-22s|", "Naziv uredaja", "Status uredaja"));
        prikaz.ispisi(String.format("|%-65s|", "-----------------------------------------------------------------"));
        prikaz.ispisi(String.format("|%-42s|%22d|", "Senz: " + senzor.getNaziv(), senzor.getStatus()));
    }

    public void inicijalizirajAktuator(Aktuator aktuator) {
        inicijalizacijskaPoruka(aktuator.getNaziv());
        int statusAktuatora = vracenaPoruka();
        aktuator.setStatus(statusAktuatora);
        prikaz.ispisi(String.format("|%-65s|", "-----------------------------------------------------------------"));
        prikaz.ispisi(String.format("|%-42s|%-22s|", "Naziv uredaja", "Status uredaja"));
        prikaz.ispisi(String.format("|%-65s|", "-----------------------------------------------------------------"));
        prikaz.ispisi(String.format("|%-42s|%22d|", "Akt: " + aktuator.getNaziv(), aktuator.getStatus()));
    }

    
}
