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

/**
 *
 * @author ivaanic2
 */
public class PreimenujAktuator extends DfHandler{

    PrikazPrograma prikaz = PrikazPrograma.getInstance();
    
    @Override
    public void handleRequest(int id, String naziv) {
        Boolean postoji = false;
        for (Mjesto m : KomandePrograma.listaMjesta) {
            for (Aktuator a : m.getListaAktuatoraZaMjesto()) {
                if (a.getId().equals(id)) {
                    prikaz.ispisi("AKTUATOR: '" + a.getNaziv() + "'");
                    a.setNaziv(naziv);
                    prikaz.ispisi("Novi naziv aktuatora: '" + a.getNaziv());
                    postoji = true;
                }
            }
        }
        if(!postoji){
            if(successor != null){
                successor.handleRequest(id, naziv);
            } else {
                prikaz.ispisi("Ne postoji uredaj/mjesto za taj id");
            }
        }
    }
}
