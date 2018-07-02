/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3;

import java.util.Scanner;

/**
 *
 * @author ivaanic2
 */
/*Program kod pokretanja provjerava upisane argumente, priprema ekran za rad tako da
dijeli ekran u dva logička dijela (prozora) od kojih je gonji za prikaz podataka
(broj redaka odgovara ukupnom broju redaka (opcija -br) umanjeno za broj redaka za
unos komandi (opcija -brk)), a donji je za unos komandi (broj redaka odgovara opciji -brk).  

Početna pozicija za ispis je gornja lijeva koordinata prozora. Svaki novi ispis prikazuje se u retku
ispod prethodnog retka dok ne popuni cijeli gornji dio. U tom slučaju u donjem dijelu se ispiše da
se za nastavak treba pritisnuti tipka n/N. Kada se n/N pritisne, obriše se gornji prozor te se
nastavlja s ispisom kao što je prethodno opisano. 
 */
public class PrikazPrograma {

    public static final String ANSI_ESC = "\033[";

    private static PrikazPrograma instance = new PrikazPrograma();
    
    int trenutnaPozicija = 1;
    int pozicijaZaKomandu = 1;
    
    int brojRedaka = 0;
    int brojStupaca = 0;
    int brojRedakaKomanda = 0;
    int ekstraVar = 0;

    private PrikazPrograma() {
    }

    public static PrikazPrograma getInstance() {
        if (instance == null) {
            instance = new PrikazPrograma();
        }
        return instance;
    }

    public void postavi(int retci, int stupci, int retciUpis) {
        System.out.print(ANSI_ESC + "2J");//erase screen
        brojRedaka = retci;
        brojStupaca = stupci;
        brojRedakaKomanda = retciUpis;
        pozicijaZaKomandu = retci - retciUpis;
        ekstraVar = retci - retciUpis;
    }

    //kod metoda prikaza i brisanja komandi
    public void postaviKurs(int x, int y) {
        System.out.print(ANSI_ESC + x + ";" + y + "f");//Force Cursor Position	<ESC>[{ROW};{COLUMN}f
    }

    //prije poruke da se unese komanda
    public void postaviNaKomande() {
        System.out.print(ANSI_ESC + pozicijaZaKomandu + ";" + 0 + "f");//Force Cursor Position	<ESC>[{ROW};{COLUMN}f
        pozicijaZaKomandu++;
        if (pozicijaZaKomandu == (brojRedaka + 2)) {
            pozicijaZaKomandu = ekstraVar;
            obrisiKomande();
        }
    }

    //u metodi postaviKurs na komande
    public void obrisiKomande() {
        int broj = brojRedakaKomanda;
        int x = ekstraVar;
        while (broj >= 0) {
            postaviKurs(x, 0);
            System.out.print(ANSI_ESC + "37m");//bijela
            System.out.print("                                                   ");
            x++;
            broj--;
        }
        postaviKurs(ekstraVar, 0);
        pozicijaZaKomandu = ekstraVar;
    }

    public void ispisi(String tekst) {
        if (trenutnaPozicija >= ekstraVar) {//if ekran popunjen
            String komanda = "";
            while (!komanda.equalsIgnoreCase("n")) {//dok komanda nije n/N
                System.out.print("Unesite n/N za nastavak");
                Scanner userInput = new Scanner(System.in);
                komanda = userInput.nextLine();
            }
            System.out.print("\033[H\033[2J");//kursor na home, erase screen
            System.out.flush();
            trenutnaPozicija = 1;
            pozicijaZaKomandu = ekstraVar;
        }
        
        postaviKurs(trenutnaPozicija, 1);
        System.out.print(ANSI_ESC + "37m");
        if (tekst.length() > brojStupaca) {
            tekst = tekst.substring(0, brojStupaca - 1);
        }
        
        System.out.print(tekst);
        trenutnaPozicija++;
    }
}
