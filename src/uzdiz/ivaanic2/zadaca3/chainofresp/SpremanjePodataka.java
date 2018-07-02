/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.chainofresp;

import java.util.ArrayList;
import java.util.List;
import uzdiz.ivaanic2.zadaca3.KomandePrograma;
import uzdiz.ivaanic2.zadaca3.PrikazPrograma;
import uzdiz.ivaanic2.zadaca3.model.Aktuator;
import uzdiz.ivaanic2.zadaca3.model.Mjesto;
import uzdiz.ivaanic2.zadaca3.model.Senzor;

/**
 *
 * @author ivaanic2
 */
public class SpremanjePodataka extends CommandHandler {

    PrikazPrograma prikaz = PrikazPrograma.getInstance();

    @Override
    public void handleRequest(String komanda) {
        if (komanda.equals("SP")) {
            prikaz.ispisi("SPREMANJE PODATAKA");
            List<Mjesto> lista = new ArrayList<>();
            for (Mjesto mjesto : KomandePrograma.listaMjesta) {//orig lista mjesta
                Mjesto mj = new Mjesto(mjesto);//kopija liste mjesta
                mj.getListaSenzoraZaMjesto().clear();
                mj.getListaAktuatoraZaMjesto().clear();//prazne liste
                for (Senzor senzor : mjesto.getListaSenzoraZaMjesto()) {
                    Senzor sen = new Senzor(senzor);//kopija liste senzora
                    sen.getObservers().clear();
                    mj.getListaSenzoraZaMjesto().add(sen);
                }
                for (Aktuator aktuator : mjesto.getListaAktuatoraZaMjesto()) {
                    Aktuator akt = new Aktuator(aktuator);
                    akt.getPopisSenzora().clear();
                    mj.getListaAktuatoraZaMjesto().add(akt);
                }

                for (Senzor senzor2 : mj.getListaSenzoraZaMjesto()) {//senzori kopije
                    for (Senzor senzor3 : mjesto.getListaSenzoraZaMjesto()) {//senzori orig
                        if (senzor3.getId().equals(senzor2.getId())) {//ako se id iz liste senzora iz orig i kopije poklapaju
                            for (Object o : senzor3.getObservers()) {//observeri originala
                                Aktuator akt = (Aktuator) o;
                                for (Aktuator ak : mj.getListaAktuatoraZaMjesto()) {//aktuatori kopije
                                    if (akt.getId().equals(ak.getId())) {//ako se id aktuatora mjesta poklapa s id akt kopije
                                        senzor2.addObserver(ak);//kopiji damo observer
                                        ak.getPopisSenzora().add(senzor2);
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
                lista.add(mj);
            }
            sp.setLista(lista);
            caretaker.addMemento(sp.saveToMemento());
            prikaz.ispisi("Podaci o mjestima i uredajima uspjesno spremljeni");
        } else {
            if (successor != null) {
                successor.setCaretaker(caretaker);
                successor.setSp(sp);
                successor.handleRequest(komanda);
            }
        }
    }
}
