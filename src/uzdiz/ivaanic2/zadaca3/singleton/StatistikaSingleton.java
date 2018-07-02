/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.singleton;

/**
 *
 * @author ivaanic2
 */
public class StatistikaSingleton {
    
    private static StatistikaSingleton instance = new StatistikaSingleton();
    
    private int ispravniSenzori = 0;
    private int neispravniSenzori = 0;
    private int dodijeljeniSenzori = 0;
    private int nedodijeljeniSenzori = 0;
    private int senzoriDodijeljeniAktuatorima = 0;
    private int nedodijeljeniSenzoriAktuatorima = 0;       
    private int zamjenaSenzora = 0;
    private int vrijednostiSenzora = 0;
    private int vrijednostiAktuatora = 0;
    
    private int ispravniAktuatori = 0;
    private int neispravniAktuatori = 0;
    private int dodijeljeniAktuatori = 0;
    private int nedodijeljeniAktuatori = 0;
    private int zamjenaAktuatora = 0;
    
    private int ispravnaMjesta = 0;
    private int neispravnaMjesta = 0;
    private int inicijalizacijskePoruke = 0;
    private int koristenjaGeneratora = 0;
    private int koristeneKomande = 0;
    
    
    private StatistikaSingleton(){
    }
    
    public static StatistikaSingleton getInstance(){
        if (instance == null) {
            instance = new StatistikaSingleton();
        }
        return instance;
    }

    public int getZamjenaSenzora() {
        return zamjenaSenzora;
    }

    public void setZamjenaSenzora(int zamjenaSenzora) {
        this.zamjenaSenzora = zamjenaSenzora;
    }

    public int getZamjenaAktuatora() {
        return zamjenaAktuatora;
    }

    public void setZamjenaAktuatora(int zamjenaAktuatora) {
        this.zamjenaAktuatora = zamjenaAktuatora;
    }

    public int getVrijednostiSenzora() {
        return vrijednostiSenzora;
    }

    public void setVrijednostiSenzora(int vrijednostiSenzora) {
        this.vrijednostiSenzora = vrijednostiSenzora;
    }

    public int getVrijednostiAktuatora() {
        return vrijednostiAktuatora;
    }

    public void setVrijednostiAktuatora(int vrijednostiAktuatora) {
        this.vrijednostiAktuatora = vrijednostiAktuatora;
    }

    public int getInicijalizacijskePoruke() {
        return inicijalizacijskePoruke;
    }

    public void setInicijalizacijskePoruke(int inicijalizacijskePoruke) {
        this.inicijalizacijskePoruke = inicijalizacijskePoruke;
    }

    public int getKoristenjaGeneratora() {
        return koristenjaGeneratora;
    }

    public void setKoristenjaGeneratora(int koristenjaGeneratora) {
        this.koristenjaGeneratora = koristenjaGeneratora;
    }

    public int getDodijeljeniSenzori() {
        return dodijeljeniSenzori;
    }

    public void setDodijeljeniSenzori(int dodijeljeniSenzori) {
        this.dodijeljeniSenzori = dodijeljeniSenzori;
    }

    public int getNeispravniSenzori() {
        return neispravniSenzori;
    }

    public void setNeispravniSenzori(int neispravniSenzori) {
        this.neispravniSenzori = neispravniSenzori;
    }

    public int getNeispravniAktuatori() {
        return neispravniAktuatori;
    }

    public void setNeispravniAktuatori(int neispravniAktuatori) {
        this.neispravniAktuatori = neispravniAktuatori;
    }

    public int getIspravniSenzori() {
        return ispravniSenzori;
    }

    public void setIspravniSenzori(int ispravniSenzori) {
        this.ispravniSenzori = ispravniSenzori;
    }

    public int getIspravniAktuatori() {
        return ispravniAktuatori;
    }

    public void setIspravniAktuatori(int ispravniAktuatori) {
        this.ispravniAktuatori = ispravniAktuatori;
    }

    public int getIspravnaMjesta() {
        return ispravnaMjesta;
    }

    public void setIspravnaMjesta(int ispravnaMjesta) {
        this.ispravnaMjesta = ispravnaMjesta;
    }

    public int getNeispravnaMjesta() {
        return neispravnaMjesta;
    }

    public void setNeispravnaMjesta(int neispravnaMjesta) {
        this.neispravnaMjesta = neispravnaMjesta;
    }

    public int getDodijeljeniAktuatori() {
        return dodijeljeniAktuatori;
    }

    public void setDodijeljeniAktuatori(int dodijeljeniAktuatori) {
        this.dodijeljeniAktuatori = dodijeljeniAktuatori;
    }

    public int getNedodijeljeniSenzori() {
        return nedodijeljeniSenzori;
    }

    public void setNedodijeljeniSenzori(int nedodijeljeniSenzori) {
        this.nedodijeljeniSenzori = nedodijeljeniSenzori;
    }

    public int getNedodijeljeniAktuatori() {
        return nedodijeljeniAktuatori;
    }

    public void setNedodijeljeniAktuatori(int nedodijeljeniAktuatori) {
        this.nedodijeljeniAktuatori = nedodijeljeniAktuatori;
    }

    public int getSenzoriDodijeljeniAktuatorima() {
        return senzoriDodijeljeniAktuatorima;
    }

    public void setSenzoriDodijeljeniAktuatorima(int senzoriDodijeljeniAktuatorima) {
        this.senzoriDodijeljeniAktuatorima = senzoriDodijeljeniAktuatorima;
    }

    public int getNedodijeljeniSenzoriAktuatorima() {
        return nedodijeljeniSenzoriAktuatorima;
    }

    public void setNedodijeljeniSenzoriAktuatorima(int nedodijeljeniSenzoriAktuatorima) {
        this.nedodijeljeniSenzoriAktuatorima = nedodijeljeniSenzoriAktuatorima;
    }

    public int getKoristeneKomande() {
        return koristeneKomande;
    }

    public void setKoristeneKomande(int koristeneKomande) {
        this.koristeneKomande = koristeneKomande;
    }

   
    
    
}
