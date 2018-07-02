/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3;

import uzdiz.ivaanic2.zadaca3.singleton.GeneratorSlucajnogBrojaSingleton;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import uzdiz.ivaanic2.zadaca3.model.Aktuator;
import uzdiz.ivaanic2.zadaca3.model.Mjesto;
import uzdiz.ivaanic2.zadaca3.model.Senzor;

/**
 *
 * @author ivaanic2
 */
public class Ivaanic2_zadaca_3 {

    public static void main(String[] args) {
        
        GeneratorSlucajnogBrojaSingleton generatorBrojeva = GeneratorSlucajnogBrojaSingleton.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date trenutnoVrijeme = new Date();
        

        int brojRedaka = -1;
        int brojStupaca = -1;
        int brojRedakaUpis = -1;
        int prosjecnaIspravnost = -1;
        int sjemeGenerator = -1;
        int trajanjeCiklusa = -1;

        String datotekaMjesta = null;
        String datotekaSenzora = null;
        String datotekaAktuatora = null;
        String datotekaRasporeda = null;

        try {
            if (args.length == 1) {
                if (args[0].equals("--help")) {
                    help();
                } else {
                    System.out.println("Pogresno uneseni argumenti");
                }
            } else {
                for (int i = 0; i < args.length; i++) {
                    if (args[i].equals("-br")) {
                        brojRedaka = Integer.parseInt(args[i + 1]);
                    } else if (args[i].equals("-bs")) {
                        brojStupaca = Integer.parseInt(args[i + 1]);
                    } else if (args[i].equals("-brk")) {
                        brojRedakaUpis = Integer.parseInt(args[i + 1]);
                    } else if (args[i].equals("-pi")) {
                        prosjecnaIspravnost = Integer.parseInt(args[i + 1]);
                    } else if (args[i].equals("-g")) {
                        sjemeGenerator = Integer.parseInt(args[i + 1]);
                    } else if (args[i].equals("-m")) {
                        datotekaMjesta = args[i + 1];
                    } else if (args[i].equals("-s")) {
                        datotekaSenzora = args[i + 1];
                    } else if (args[i].equals("-a")) {
                        datotekaAktuatora = args[i + 1];
                    } else if (args[i].equals("-r")) {
                        datotekaRasporeda = args[i + 1];
                    } else if (args[i].equals("-tcd")) {
                        trajanjeCiklusa = Integer.parseInt(args[i + 1]);
                    }
                }
                if (datotekaMjesta == null || datotekaSenzora == null || datotekaAktuatora == null || datotekaRasporeda == null) {
                    System.out.println("Argumenti za datoteku mjesta, senzora, aktuatora i rasporeda su obavezni.");
                    System.exit(0);
                } else {
                    if (sjemeGenerator == 0 || sjemeGenerator < 100 || sjemeGenerator > 65535) {
                        Calendar cal = Calendar.getInstance();
                        int sec = cal.get(Calendar.SECOND);
                        int millis = cal.get(Calendar.MILLISECOND);
                        sjemeGenerator = sec * 1000 + millis;
                    }
                    GeneratorSlucajnogBrojaSingleton.sjeme = sjemeGenerator;

                    if (brojRedaka == -1) {
                        brojRedaka = 24;
                    } else if (brojRedaka < 24 || brojRedaka > 40) {
                        brojRedaka = generatorBrojeva.dajSlucajniBroj(24, 40);
                    }

                    if (brojStupaca == -1) {
                        brojStupaca = 80;
                    } else if (brojStupaca < 80 || brojStupaca > 160) {
                        brojStupaca = generatorBrojeva.dajSlucajniBroj(80, 160);
                    }

                    if (brojRedakaUpis == -1) {
                        brojRedakaUpis = 2;
                    } else if (brojRedakaUpis < 2 || brojRedakaUpis > 5) {
                        brojRedakaUpis = generatorBrojeva.dajSlucajniBroj(2, 5);
                    }

                    if (prosjecnaIspravnost == -1) {
                        prosjecnaIspravnost = 50;
                    } else if (prosjecnaIspravnost < 0 || prosjecnaIspravnost > 100) {
                        prosjecnaIspravnost = generatorBrojeva.dajSlucajniBroj(0, 100);
                    }
                    InicijalizacijaSustava.prosjecnaIspravnost = prosjecnaIspravnost;

                    if (trajanjeCiklusa < 0) {
                        trajanjeCiklusa = generatorBrojeva.dajSlucajniBroj(1, 17);
                    }
                    ProvjeraMjesta.trajanjeCiklusaDretve = trajanjeCiklusa;

                    PrikazPrograma prikaz = PrikazPrograma.getInstance();
                    prikaz.postavi(brojRedaka, brojStupaca, brojRedakaUpis);

                    prikaz.ispisi("Retci: " + brojRedaka);
                    prikaz.ispisi("Stupci: " + brojStupaca);
                    prikaz.ispisi("Retci za upis: " + brojRedakaUpis);
                    prikaz.ispisi("Prosjecna ispravnost: " + prosjecnaIspravnost);
                    prikaz.ispisi("Sjeme: " + sjemeGenerator);
                    prikaz.ispisi("Datoteka mjesta: " + datotekaMjesta);
                    prikaz.ispisi("Datoteka senzora: " + datotekaSenzora);
                    prikaz.ispisi("Datoteka aktuatora: " + datotekaAktuatora);
                    prikaz.ispisi("Datoteka rasporeda: " + datotekaRasporeda);
                    prikaz.ispisi("Trajanje ciklusa: " + trajanjeCiklusa);


                    KonfiguracijaSustava konf = new KonfiguracijaSustava();
                    List<Mjesto> listaMjesta = konf.napuniMjesta(datotekaMjesta);
                    List<Senzor> listaSenzora = konf.napuniSenzore(datotekaSenzora);

                    List<Aktuator> listaAktuatora = konf.napuniAktuatore(datotekaAktuatora);
                    konf.izvrsiRaspored(datotekaRasporeda);
                    konf.napuniSenzoreZaAktuatore(datotekaRasporeda);
                    KomandePrograma komande = new KomandePrograma(listaMjesta);
                    InicijalizacijaSustava inicijalizacija = new InicijalizacijaSustava();
                    inicijalizacija.inicijalizirajUredajePoMjestima(KomandePrograma.listaMjesta);

                    PrikazPrograma pp = PrikazPrograma.getInstance();

                    Scanner scan = new Scanner(System.in);
                    String komanda = "";
                    while (true) {
                        pp.postaviNaKomande();
                        System.out.print("Unesite komandu: ");
                        komanda = scan.nextLine();
                        komande.izvrsiKomande(komanda);
                    }

                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Pogreska kod unosa argumenata.");
        }
    }


    private static void help() {
        System.out.println("Pomoc programa");
        System.out.println("-br     broj redaka na ekranu (24-40)");
        System.out.println("-bs     broj stupaca na ekranu (80-160)");
        System.out.println("-brk    broj redaka na ekranu za unos komandi (2-5)");
        System.out.println("-pi     prosjecni % ispravnosti uredaja (0-100)");
        System.out.println("-g      sjeme za generator slucajnog broja (100-65535)");
        System.out.println("-m      naziv datoteke mjesta");
        System.out.println("-s      naziv datoteke senzora");
        System.out.println("-a      naziv datoteke aktuatora");
        System.out.println("-r      naziv datoteke rasporeda");
        System.out.println("-tcd    trajanje ciklusa dretve u sek (1-17)");
    }

}
