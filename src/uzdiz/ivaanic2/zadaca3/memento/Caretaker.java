/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.memento;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivaanic2
 */
public class Caretaker {

    private List<Object> spremljeniPodaci = new ArrayList<Object>();

    public void addMemento(Object m) {
        spremljeniPodaci.add(m);
    }

    public Object getMemento() {
        return spremljeniPodaci.get(spremljeniPodaci.size()-1);
    }
}
