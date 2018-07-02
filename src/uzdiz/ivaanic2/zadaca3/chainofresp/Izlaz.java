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
public class Izlaz extends CommandHandler {

    PrikazPrograma prikaz = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.equals("I")) {
            prikaz.ispisi("IZLAZ IZ PROGRAMA");
            System.exit(1);
        } else {
            if (successor != null) {
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }
}
