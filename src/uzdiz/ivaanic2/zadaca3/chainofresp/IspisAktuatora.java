/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.chainofresp;

import uzdiz.ivaanic2.zadaca3.KomandePrograma;
import uzdiz.ivaanic2.zadaca3.PrikazPrograma;
import uzdiz.ivaanic2.zadaca3.model.Aktuator;
import uzdiz.ivaanic2.zadaca3.model.Mjesto;
import uzdiz.ivaanic2.zadaca3.model.Senzor;

/**
 *
 * @author ivaanic2
 */
public class IspisAktuatora extends CommandHandler {

    PrikazPrograma prikaz = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.contains("A ")) {
            int id = Integer.parseInt(komanda.substring(komanda.indexOf(" ") + 1, komanda.length()));
            prikaz.ispisi("ISPIS AKTUATORA");
            Boolean postoji = false;
            
            for (Mjesto mjesto : KomandePrograma.listaMjesta) {
                for (Aktuator a : mjesto.getListaAktuatoraZaMjesto()) {
                    if (a.getId().equals(id)) {
                        postoji = true;
                        String popisSenzora = "";
                        for (Senzor s : a.getPopisSenzora()) {
                            popisSenzora += (s.getId() + ", ");
                        }
                        popisSenzora = popisSenzora.substring(0, popisSenzora.lastIndexOf(","));
                        prikaz.ispisi("");
                        prikaz.ispisi(String.format("|%1s|", "-------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
                        prikaz.ispisi(String.format("|%-152s|", "Mjesto: " + mjesto.getNaziv()));
                        prikaz.ispisi(String.format("|%1s|", "====================================================================== ISPIS AKTUATORA ==========================================================================="));
                        prikaz.ispisi(String.format("|%-40s|%-10s|%-10s|%-10s|%-10s|%-15s|%-30s|%15s|%-15s|", "Naziv aktuatora", "ID", "Status", "MIN", "MAX", "Vrijednost", "ID dodjeljenih senzora", "Broj greski", "Napomena"));
                        prikaz.ispisi(String.format("|%-161s|", "-------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
                        if (a.getBrojGreski() == 3) {
                            prikaz.ispisi(String.format("|%-40s|%-10s|%-10s|%-10s|%-10s|%-15s|%-30s|%15s|%-15s|", a.getNaziv(), a.getId(), a.getStatus(), a.getMinVrijednost(), a.getMaxVrijednost(), a.getVrijednost().intValue(), popisSenzora, a.getBrojGreski(), "Radi"));
                        } else {
                            prikaz.ispisi(String.format("|%-40s|%-10s|%-10s|%-10s|%-10s|%-15s|%-30s|%15s|%-15s|", a.getNaziv(), a.getId(), a.getStatus(), a.getMinVrijednost(), a.getMaxVrijednost(), a.getVrijednost().intValue(), popisSenzora, a.getBrojGreski(), "Neispravan"));
                        }
                        prikaz.ispisi(String.format("|%-161s|", "-------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
                        break;
                    }
                }
            }
            if (!postoji) {
                prikaz.ispisi("Ne postoji aktuator za taj id");
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
