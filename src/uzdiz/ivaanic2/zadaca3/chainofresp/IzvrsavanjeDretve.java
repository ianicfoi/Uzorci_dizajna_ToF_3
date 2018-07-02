/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.chainofresp;

import java.util.logging.Level;
import java.util.logging.Logger;
import uzdiz.ivaanic2.zadaca3.KomandePrograma;
import uzdiz.ivaanic2.zadaca3.PrikazPrograma;
import uzdiz.ivaanic2.zadaca3.ProvjeraMjesta;

/**
 *
 * @author ivaanic2
 */
public class IzvrsavanjeDretve extends CommandHandler {

    PrikazPrograma prikaz = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.contains("C")) {
            int brojCiklusa = Integer.parseInt(komanda.substring(komanda.indexOf(" ") + 1, komanda.length()));
            prikaz.ispisi("IZVRSAVANJE DRETVE");
            if (brojCiklusa < 1 || brojCiklusa > 100) {
                prikaz.ispisi("Broj ciklusa dretve mora biti izmedu 1 i 100. Pokusajte ponovno.");
            } else {
                ProvjeraMjesta provjeraMjesta = new ProvjeraMjesta();
                provjeraMjesta.setBrojCiklusaDretve(brojCiklusa);
                provjeraMjesta.start();
                try {
                    provjeraMjesta.join();

                } catch (InterruptedException ex) {
                    Logger.getLogger(KomandePrograma.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            if(successor != null){
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }

}
