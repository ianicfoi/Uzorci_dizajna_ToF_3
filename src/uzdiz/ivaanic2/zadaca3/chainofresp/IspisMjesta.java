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
public class IspisMjesta extends CommandHandler {

    PrikazPrograma prikaz = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.contains("M ")) {
            int id  = Integer.parseInt(komanda.substring(komanda.indexOf(" ") + 1, komanda.length()));
            prikaz.ispisi("ISPIS MJESTA");
            Boolean postoji = false;
            for (Mjesto mjesto : KomandePrograma.listaMjesta) {
                if (mjesto.getId().equals(id)) {
                    postoji = true;
                    prikaz.ispisi("");
                    prikaz.ispisi(String.format("|%-109s|", "================================================ ISPIS MJESTA ===================================================="));
                    prikaz.ispisi(String.format("|%-50s|%-15s|%-15s|%-15s|%-15s|", "Naziv mjesta", "ID mjesta", "Tip mjesta", "Broj senzora", "Broj aktuatora"));
                    prikaz.ispisi(String.format("|%-109s|", "------------------------------------------------------------------------------------------------------------------"));
                    prikaz.ispisi(String.format("|%-50s|%15s|%15s|%15s|%15s|", mjesto.getNaziv(), mjesto.getId(), mjesto.getTip(), mjesto.getBrojSenzora(), mjesto.getBrojAktuatora()));
                    prikaz.ispisi(String.format("|%-150s|", "================================================================= ISPIS SENZORA ====================================================================="));
                    prikaz.ispisi(String.format("|%-40s|%-15s|%-15s|%10s|%-10s|%-15s|%-15s|%-20s|", "Naziv senzora", "ID senzora", "Status senzora", "MIN", "MAX", "Vrijednost", "Broj greski", "Napomena"));
                    prikaz.ispisi(String.format("|%-150s|", "------------------------------------------------------------------------------------------------------------------------------------------------------"));
                    for (Senzor s : mjesto.getListaSenzoraZaMjesto()) {
                        if (s.getBrojGreski() == 3) {
                            prikaz.ispisi(String.format("|%-40s|%15s|%15s|%10s|%10s|%15s|%15s|%-20s|", s.getNaziv(), s.getId(), s.getStatus(), s.getMinVrijednost(), s.getMaxVrijednost(), s.getVrijednost(), s.getBrojGreski(), "Neispravan"));
                        } else {
                            prikaz.ispisi(String.format("|%-40s|%15s|%15s|%10s|%10s|%15s|%15s|%-20s|", s.getNaziv(), s.getId(), s.getStatus(), s.getMinVrijednost(), s.getMaxVrijednost(), s.getVrijednost(), s.getBrojGreski(), "Radi"));
                        }
                        prikaz.ispisi(String.format("|%-150s|", "------------------------------------------------------------------------------------------------------------------------------------------------------"));
                    }
                    prikaz.ispisi(String.format("|%1s|", "================================================================= ISPIS AKTUATORA ======================================================================"));
                    prikaz.ispisi(String.format("|%-40s|%-10s|%-10s|%-10s|%-10s|%-15s|%-30s|%15s|%-15s|", "Naziv aktuatora", "ID", "Status", "MIN", "MAX", "Vrijednost", "ID dodjeljenih senzora", "Broj greski", "Napomena"));
                    prikaz.ispisi(String.format("|%-150s|", "--------------------------------------------------------------------------------------------------------------------------------------------------------"));
                    for (Aktuator a : mjesto.getListaAktuatoraZaMjesto()) {
                        String popisSenzora = "";
                        for (Senzor s : a.getPopisSenzora()) {
                            popisSenzora += (s.getId() + ", ");
                        }
                        popisSenzora = popisSenzora.substring(0, popisSenzora.lastIndexOf(","));
                        if (a.getBrojGreski() == 3) {
                            prikaz.ispisi(String.format("|%-40s|%10s|%10s|%10s|%10s|%15s|%30s|%15s|%-15s|", a.getNaziv(), a.getId(), a.getStatus(), a.getMinVrijednost(), a.getMaxVrijednost(), a.getVrijednost().intValue(), popisSenzora, a.getBrojGreski(), "Neispravan"));
                        } else {
                            prikaz.ispisi(String.format("|%-40s|%10s|%10s|%10s|%10s|%15s|%30s|%15s|%-15s|", a.getNaziv(), a.getId(), a.getStatus(), a.getMinVrijednost(), a.getMaxVrijednost(), a.getVrijednost().intValue(), popisSenzora, a.getBrojGreski(), "Radi"));
                        }
                        prikaz.ispisi(String.format("|%-150s|", "--------------------------------------------------------------------------------------------------------------------------------------------------------"));
                    }
                }
            }
            if (!postoji) {
                prikaz.ispisi("Ne postoji mjesto za taj id");
            }
            prikaz.ispisi("");
        } else {
            if(successor != null){
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }

}
