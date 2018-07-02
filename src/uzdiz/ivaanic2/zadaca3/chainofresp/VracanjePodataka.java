/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.chainofresp;

import java.util.ArrayList;
import uzdiz.ivaanic2.zadaca3.KomandePrograma;
import uzdiz.ivaanic2.zadaca3.PrikazPrograma;

/**
 *
 * @author ivaanic2
 */
public class VracanjePodataka extends CommandHandler {

    PrikazPrograma prikaz = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.equals("VP")) {
            prikaz.ispisi("VRACANJE PODATAKA");
            KomandePrograma.listaMjesta.clear();
            KomandePrograma.listaMjesta = new ArrayList<>(sp.restoreFromMemento(caretaker.getMemento()));
            prikaz.ispisi("Podaci o mjestima i uredajima uspjesno vraceni");
        } else {
            if (successor != null) {
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }
}
