/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.chainofresp;

/**
 *
 * @author ivaanic2
 */
public abstract class DfHandler {
    
    DfHandler successor;

    public void setSuccessor(DfHandler successor) {
        this.successor = successor;
    }
    
    public abstract void handleRequest(int id, String naziv);
    
}
