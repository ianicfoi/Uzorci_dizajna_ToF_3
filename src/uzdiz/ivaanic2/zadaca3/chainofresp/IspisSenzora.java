/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.chainofresp;

import uzdiz.ivaanic2.zadaca3.KomandePrograma;
import uzdiz.ivaanic2.zadaca3.PrikazPrograma;
import uzdiz.ivaanic2.zadaca3.model.Mjesto;
import uzdiz.ivaanic2.zadaca3.model.Senzor;

/**
 *
 * @author ivaanic2
 */
public class IspisSenzora extends CommandHandler {

    PrikazPrograma prikaz = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.contains("S ")) {
            int id = Integer.parseInt(komanda.substring(komanda.indexOf(" ") + 1, komanda.length()));
            prikaz.ispisi("ISPIS SENZORA");
            Boolean postoji = false;
            for (Mjesto mjesto : KomandePrograma.listaMjesta) {
                for (Senzor s : mjesto.getListaSenzoraZaMjesto()) {
                    if (s.getId().equals(id)) {
                        postoji = true;
                        prikaz.ispisi("");
                        prikaz.ispisi(String.format("|%-150s|", "------------------------------------------------------------------------------------------------------------------------------------------------------"));
                        prikaz.ispisi(String.format("|%-150s|", "MJESTO: " + mjesto.getNaziv()));
                        prikaz.ispisi(String.format("|%-150s|", "================================================================== ISPIS SENZORA ====================================================================="));
                        prikaz.ispisi(String.format("|%-40s|%-15s|%-15s|%-10s|%-10s|%-15s|%15s|%-20s|", "Naziv senzora", "ID senzora", "Status senzora", "MIN", "MAX", "Vrijednost", "Broj greski", "Napomena"));
                        prikaz.ispisi(String.format("|%-150s|", "------------------------------------------------------------------------------------------------------------------------------------------------------"));
                        if (s.getBrojGreski() == 3) {
                            prikaz.ispisi(String.format("|%-40s|%15s|%15s|%10s|%10s|%15s|%15s|%-20s|", s.getNaziv(), s.getId(), s.getStatus(), s.getMinVrijednost(), s.getMaxVrijednost(), s.getVrijednost(), s.getBrojGreski(), "Neispravan"));
                        } else {
                            prikaz.ispisi(String.format("|%-40s|%15s|%15s|%10s|%10s|%15s|%15s|%-20s|", s.getNaziv(), s.getId(), s.getStatus(), s.getMinVrijednost(), s.getMaxVrijednost(), s.getVrijednost(), s.getBrojGreski(), "Radi"));
                        }
                        prikaz.ispisi(String.format("|%-150s|", "------------------------------------------------------------------------------------------------------------------------------------------------------"));
                        break;
                    }
                }
            }
            if (!postoji) {
                prikaz.ispisi("Ne postoji senzor za taj id");
            }
            prikaz.ispisi("");
        } else {
            if (successor != null) {
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }

}
