/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.chainofresp;

import uzdiz.ivaanic2.zadaca3.PrikazPrograma;
import uzdiz.ivaanic2.zadaca3.singleton.StatistikaSingleton;

/**
 *
 * @author ivaanic2
 */
public class IspisStatistike extends CommandHandler {

    PrikazPrograma prikaz = PrikazPrograma.getInstance();
    StatistikaSingleton statistika = StatistikaSingleton.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.equals("S")) {
            prikaz.ispisi("ISPIS STATISTIKE");
            prikaz.ispisi("Broj ispravnih mjesta u datoteci: " + statistika.getIspravnaMjesta());
            prikaz.ispisi("Broj neispravnih mjesta u datoteci: " + statistika.getNeispravnaMjesta());
            prikaz.ispisi("Broj ispravnih senzora u datoteci: " + statistika.getIspravniSenzori());
            prikaz.ispisi("Broj neispravnih senzora u datoteci: " + statistika.getNeispravniSenzori());
            prikaz.ispisi("Broj ispravnih aktuatora u datoteci: " + statistika.getIspravniAktuatori());
            prikaz.ispisi("Broj neispravnih aktuatora u datoteci: " + statistika.getNeispravniAktuatori());
            prikaz.ispisi("Broj ispravnih pridruzivanja senzora mjestima: " + statistika.getDodijeljeniSenzori());
            prikaz.ispisi("Broj neispravnih pridruzivanja senzora mjestima: " + statistika.getNedodijeljeniSenzori());
            prikaz.ispisi("Broj ispravnih pridruzivanja aktuatora mjestima: " + statistika.getDodijeljeniAktuatori());
            prikaz.ispisi("Broj neispravnih pridruzivanja aktuatora mjestima: " + statistika.getNedodijeljeniAktuatori());
            prikaz.ispisi("Broj ispravnih pridruzivanja senzora aktuatorima: " + statistika.getSenzoriDodijeljeniAktuatorima());
            prikaz.ispisi("Broj neispravnih pridruzivanja senzora aktuatorima: " + statistika.getNedodijeljeniSenzoriAktuatorima());
            prikaz.ispisi("Broj inicijalizacijskih poruka: " + statistika.getInicijalizacijskePoruke());
            prikaz.ispisi("Broj koristenja generatora: " + statistika.getKoristenjaGeneratora());
            prikaz.ispisi("Broj zamjena senzora: " + statistika.getZamjenaSenzora());
            prikaz.ispisi("Broj zamjena aktuatora: " + statistika.getZamjenaAktuatora());
            prikaz.ispisi("Broj ucitanih vrijednosti senzora: " + statistika.getVrijednostiSenzora());
            prikaz.ispisi("Broj ucitanih vrijednosti aktuatora: " + statistika.getVrijednostiAktuatora());
            prikaz.ispisi("Broj izvrsenih komandi: " + statistika.getKoristeneKomande());
        } else {
            if(successor != null){
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }

}
