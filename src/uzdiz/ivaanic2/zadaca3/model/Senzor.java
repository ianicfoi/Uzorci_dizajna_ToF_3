/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import uzdiz.ivaanic2.zadaca3.observer.Observer;
import uzdiz.ivaanic2.zadaca3.observer.Subject;

/**
 *
 * @author ivaanic2
 */
public class Senzor implements Cloneable, Subject {

    private Integer id;
    private Integer idModela;
    private String naziv;
    private Integer tip;
    private Integer vrsta;
    private Float minVrijednost;
    private Float maxVrijednost;
    private String komentar;
    private Float vrijednost;
    private Integer brojGreski = 0;
    private Integer status;
    
    private List observers = new ArrayList<>();
    
    
    private Boolean stanje = false;

    public Senzor(String naziv, Integer tip, Integer vrsta, Float minVrijednost, Float maxVrijednost, String komentar) {
        this.naziv = naziv;
        this.tip = tip;
        this.vrsta = vrsta;
        this.minVrijednost = minVrijednost;
        this.maxVrijednost = maxVrijednost;
        this.komentar = komentar;
    }

    public Senzor() {
    }

    public Senzor(Senzor senzor) {
        this.id = senzor.getId();
        this.idModela = senzor.getIdModela();
        this.naziv = senzor.getNaziv();
        this.tip = senzor.getTip();
        this.vrsta = senzor.getVrsta();
        this.minVrijednost = senzor.getMinVrijednost();
        this.maxVrijednost = senzor.getMaxVrijednost();
        this.komentar = senzor.getKomentar();
        this.vrijednost = senzor.getVrijednost();
        this.status = senzor.getStatus();
        this.observers = new ArrayList<>(senzor.getObservers());
        this.stanje = senzor.getStanje();
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

    public Integer getVrsta() {
        return vrsta;
    }

    public void setVrsta(Integer vrsta) {
        this.vrsta = vrsta;
    }

    public Float getMinVrijednost() {
        return minVrijednost;
    }

    public void setMinVrijednost(Float minVrijednost) {
        this.minVrijednost = minVrijednost;
    }

    public Float getMaxVrijednost() {
        return maxVrijednost;
    }

    public void setMaxVrijednost(Float maxVrijednost) {
        this.maxVrijednost = maxVrijednost;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Float getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(Float vrijednost) {
        this.vrijednost = vrijednost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
     public Integer getIdModela() {
        return idModela;
    }

    public void setIdModela(Integer idModela) {
        this.idModela = idModela;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBrojGreski() {
        return brojGreski;
    }

    public void setBrojGreski(Integer brojGreski) {
        this.brojGreski = brojGreski;
    }

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }

   
    
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public Boolean getStanje() {
       return stanje;
    }

    @Override
    public void setStanje(Boolean stanje) {
        this.stanje = stanje;
        notifyObservers();
    }
    
    public void notifyObservers(){
        Iterator i = observers.iterator();
        while(i.hasNext()){
            Observer o = (Observer) i.next();
            o.update(this);
        }
    }

    public List getObservers() {
        return observers;
    }

}
