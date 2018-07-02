/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.observer;

/**
 *
 * @author ivaanic2
 */
public interface Subject {
    public void addObserver(Observer o);
    public Boolean getStanje();
    public void setStanje(Boolean state);
}
