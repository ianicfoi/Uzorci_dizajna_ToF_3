/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivaanic2
 */
public class Mjesto implements Comparable<Mjesto> {

    private Integer id;
    private String naziv;
    private Integer tip;
    private Integer brojSenzora;
    private Integer brojAktuatora;
    
    private List<Senzor> listaSenzoraZaMjesto = new ArrayList<>();
    private List<Aktuator> listaAktuatoraZaMjesto = new ArrayList<>();
    
    public Mjesto(){
    }
    
    public Mjesto(Mjesto mjesto){
        this.id = mjesto.getId();
        this.naziv = mjesto.getNaziv();
        this.brojSenzora = mjesto.getBrojSenzora();
        this.brojAktuatora = mjesto.getBrojAktuatora();
        this.listaSenzoraZaMjesto = new ArrayList<>(mjesto.getListaSenzoraZaMjesto());
        this.listaAktuatoraZaMjesto = new ArrayList<>(mjesto.getListaAktuatoraZaMjesto());
    }
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getTip() {
        return tip;
    }

    public void setTip(Integer tip) {
        this.tip = tip;
    }

    public Integer getBrojSenzora() {
        return brojSenzora;
    }

    public void setBrojSenzora(Integer brojSenzora) {
        this.brojSenzora = brojSenzora;
    }

    public Integer getBrojAktuatora() {
        return brojAktuatora;
    }

    public void setBrojAktuatora(Integer brojAktuatora) {
        this.brojAktuatora = brojAktuatora;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Senzor> getListaSenzoraZaMjesto() {
        return listaSenzoraZaMjesto;
    }

    public void setListaSenzoraZaMjesto(List<Senzor> listaSenzoraZaMjesto) {
        this.listaSenzoraZaMjesto = listaSenzoraZaMjesto;
    }

    public List<Aktuator> getListaAktuatoraZaMjesto() {
        return listaAktuatoraZaMjesto;
    }

    public void setListaAktuatoraZaMjesto(List<Aktuator> listaAktuatoraZaMjesto) {
        this.listaAktuatoraZaMjesto = listaAktuatoraZaMjesto;
    }

    

    @Override
    public int compareTo(Mjesto o) {
        return Integer.valueOf(id).compareTo(o.id);
    }
}
