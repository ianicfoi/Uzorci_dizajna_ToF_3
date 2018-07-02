/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.chainofresp;

import uzdiz.ivaanic2.zadaca3.InicijalizacijaSustava;
import uzdiz.ivaanic2.zadaca3.PrikazPrograma;

/**
 *
 * @author ivaanic2
 */
public class ProsjecnaIspravnost extends CommandHandler {

    PrikazPrograma prikaz = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.contains("PI ")) {
            int prosjIspravnost = Integer.parseInt(komanda.substring(komanda.indexOf(" ") + 1, komanda.length()));
            prikaz.ispisi("POSTAVLJANJE ISPRAVNOSTI");
            if (prosjIspravnost < 0 || prosjIspravnost > 100) {
                prikaz.ispisi("Prosjecna ispravnost mora biti izmedu 0 i 100. Pokusajte ponovno.");
            } else {
                InicijalizacijaSustava.prosjecnaIspravnost = prosjIspravnost;
                prikaz.ispisi("Prosjecna ispravnost: " + InicijalizacijaSustava.prosjecnaIspravnost);
            }
        } else {
            if (successor != null) {
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }
}
