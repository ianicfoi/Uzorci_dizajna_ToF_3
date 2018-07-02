/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.chainofresp;

import uzdiz.ivaanic2.zadaca3.KomandePrograma;
import uzdiz.ivaanic2.zadaca3.PrikazPrograma;
import uzdiz.ivaanic2.zadaca3.model.Mjesto;

/**
 *
 * @author ivaanic2
 */
public class PreimenujMjesto extends DfHandler{

    PrikazPrograma prikaz = PrikazPrograma.getInstance();
    
    @Override
    public void handleRequest(int id, String naziv) {
        Boolean postoji = false;
        for(Mjesto m : KomandePrograma.listaMjesta){
            if (m.getId().equals(id)){
                prikaz.ispisi("MJESTO: '" + m.getNaziv() + "'");
                m.setNaziv(naziv);
                prikaz.ispisi("Novi naziv mjesta: '" + m.getNaziv() + "'");
                postoji = true;
                break;
            }
        }
        if(!postoji){
            if(successor != null){
                successor.handleRequest(id, naziv);
            }
        }
    }
    
}
