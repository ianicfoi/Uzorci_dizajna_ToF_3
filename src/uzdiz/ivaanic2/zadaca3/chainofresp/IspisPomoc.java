/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.chainofresp;

import uzdiz.ivaanic2.zadaca3.PrikazPrograma;

/**
 *
 * @author ivaanic2
 */
public class IspisPomoc extends CommandHandler {

    PrikazPrograma prikaz = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.equals("H")) {
            prikaz.ispisi("POMOC ZA PROGRAM");
            prikaz.ispisi("M x - ispis podataka mjesta x");
            prikaz.ispisi("S x - ispis podataka senzora x");
            prikaz.ispisi("A x - ispis podataka aktuatora x");
            prikaz.ispisi("S - ispis statistike");
            prikaz.ispisi("SP - spremi podatke (mjesta, uredaja)");
            prikaz.ispisi("VP - vrati spremljene podatke (mjesta, uredaja)");
            prikaz.ispisi("C n - izvrsavanje n ciklusa dretve (1-100)");
            prikaz.ispisi("VF x y - vlastita funkcionalnost, x je id, y ime mjesta ili uredaja");
            prikaz.ispisi("PI n - prosjecni % ispravnosti uredaja (0-100)");
            prikaz.ispisi("H - pomoc, ispis dopustenih komandi i njihov opis");
            prikaz.ispisi("I - izlaz.");
        } else {
            if(successor != null){
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }

}
