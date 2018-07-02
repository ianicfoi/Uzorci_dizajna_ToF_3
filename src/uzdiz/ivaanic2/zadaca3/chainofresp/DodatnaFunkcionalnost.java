/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.chainofresp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import uzdiz.ivaanic2.zadaca3.PrikazPrograma;

/**
 *
 * @author ivaanic2
 */
public class DodatnaFunkcionalnost extends CommandHandler {

    PrikazPrograma prikaz = PrikazPrograma.getInstance();
    
    @Override
    public void handleRequest(String komanda) {
        Pattern p = Pattern.compile("^VF [0-9]{1,4} [a-zA-Z1-9]{1,}$");
        Matcher m = p.matcher(komanda);
        if (m.matches()) {
            prikaz.ispisi("DODATNA FUNKCIONALNOST");
            String[] zapis = komanda.split(" ");
            int id  = Integer.parseInt(zapis[1]);
            String naziv = zapis[2];
            DfHandler dfh = postaviLanac();
            dfh.handleRequest(id,naziv);

        } else {
            if (successor != null) {
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }
    
    public static DfHandler postaviLanac(){
        DfHandler preimenujMjesto = new PreimenujMjesto();
        DfHandler preimenujSenzor = new PreimenujSenzor();
        DfHandler preimenujAktuator = new PreimenujAktuator();

        preimenujMjesto.setSuccessor(preimenujSenzor);
        preimenujSenzor.setSuccessor(preimenujAktuator);
        
        return preimenujMjesto;
    }
}
