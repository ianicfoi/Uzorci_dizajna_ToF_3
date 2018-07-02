/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3;

import uzdiz.ivaanic2.zadaca3.singleton.StatistikaSingleton;
import java.util.ArrayList;
import java.util.List;
import uzdiz.ivaanic2.zadaca3.chainofresp.CommandHandler;
import uzdiz.ivaanic2.zadaca3.chainofresp.IspisAktuatora;
import uzdiz.ivaanic2.zadaca3.chainofresp.IspisPomoc;
import uzdiz.ivaanic2.zadaca3.chainofresp.IspisMjesta;
import uzdiz.ivaanic2.zadaca3.chainofresp.IspisSenzora;
import uzdiz.ivaanic2.zadaca3.chainofresp.IspisStatistike;
import uzdiz.ivaanic2.zadaca3.chainofresp.Izlaz;
import uzdiz.ivaanic2.zadaca3.chainofresp.IzvrsavanjeDretve;
import uzdiz.ivaanic2.zadaca3.chainofresp.ProsjecnaIspravnost;
import uzdiz.ivaanic2.zadaca3.chainofresp.SpremanjePodataka;
import uzdiz.ivaanic2.zadaca3.chainofresp.DodatnaFunkcionalnost;
import uzdiz.ivaanic2.zadaca3.chainofresp.VracanjePodataka;
import uzdiz.ivaanic2.zadaca3.memento.Caretaker;
import uzdiz.ivaanic2.zadaca3.memento.SpremistePodataka;
import uzdiz.ivaanic2.zadaca3.model.Mjesto;

/**
 *
 * @author ivaanic2
 */
public class KomandePrograma {

    public static List<Mjesto> listaMjesta = new ArrayList<>();
    StatistikaSingleton statistika = StatistikaSingleton.getInstance();
    Caretaker caretaker = new Caretaker();
    SpremistePodataka sp = new SpremistePodataka();
    PrikazPrograma prikaz = PrikazPrograma.getInstance();


    public KomandePrograma(List<Mjesto> listaMjesta) {
        this.listaMjesta = listaMjesta;
    }
    
    public static CommandHandler postaviLanac(){
        CommandHandler ispisMjesta = new IspisMjesta();
        CommandHandler ispisSenzora = new IspisSenzora();
        CommandHandler ispisAktuatora = new IspisAktuatora();
        CommandHandler ispisStatistika = new IspisStatistike();
        CommandHandler spremanjePodataka = new SpremanjePodataka();
        CommandHandler vracanjePodataka = new VracanjePodataka();
        CommandHandler izvrsiDretvu = new IzvrsavanjeDretve();
        CommandHandler vlastitaFunkcionalnost = new DodatnaFunkcionalnost();
        CommandHandler postavljanjeIspravnosti = new ProsjecnaIspravnost();
        CommandHandler ispisHelp = new IspisPomoc();
        CommandHandler izlaz = new Izlaz();

        ispisMjesta.setSuccessor(ispisSenzora);
        ispisSenzora.setSuccessor(ispisAktuatora);
        ispisAktuatora.setSuccessor(ispisStatistika);
        ispisStatistika.setSuccessor(spremanjePodataka);
        spremanjePodataka.setSuccessor(vracanjePodataka);
        vracanjePodataka.setSuccessor(izvrsiDretvu);
        izvrsiDretvu.setSuccessor(vlastitaFunkcionalnost);
        vlastitaFunkcionalnost.setSuccessor(postavljanjeIspravnosti);
        postavljanjeIspravnosti.setSuccessor(ispisHelp);
        ispisHelp.setSuccessor(izlaz);      
        return ispisMjesta;
    }

    public void izvrsiKomande(String komanda) {     
        CommandHandler ch = postaviLanac();
        ch.setCaretaker(caretaker);
        ch.setSp(sp);
        ch.handleRequest(komanda);
        
        statistika.setKoristeneKomande(statistika.getKoristeneKomande() + 1);
    }
}
