/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.model;

import java.util.ArrayList;
import java.util.List;
import uzdiz.ivaanic2.zadaca3.observer.Observer;
import uzdiz.ivaanic2.zadaca3.observer.Subject;

/**
 *
 * @author ivaanic2
 */
public class Aktuator implements Cloneable, Observer{
    private Integer id;
    private Integer idModela;
    private String naziv;
    private Integer tip;
    private Integer vrsta;
    private Float minVrijednost;
    private Float maxVrijednost;
    private String komentar;
    private Float vrijednost = 0.0f;
    private Integer brojGreski = 0;
    private Integer status;
    
    
    private List<Senzor> popisSenzora = new ArrayList<>();
    
    private Boolean smjer = true;
    private Boolean stanje = false;

    public Aktuator(String naziv, Integer tip, Integer vrsta, Float minVrijednost, Float maxVrijednost, String komentar) {
        this.naziv = naziv;
        this.tip = tip;
        this.vrsta = vrsta;
        this.minVrijednost = minVrijednost;
        this.maxVrijednost = maxVrijednost;
        this.komentar = komentar;
    }

    public Aktuator() {
    }

    public Aktuator(Aktuator aktuator) {
        this.id = aktuator.getId();
        this.idModela = aktuator.getIdModela();
        this.naziv = aktuator.getNaziv();
        this.tip = aktuator.getTip();
        this.vrsta = aktuator.getVrsta();
        this.minVrijednost = aktuator.getMinVrijednost();
        this.maxVrijednost = aktuator.getMaxVrijednost();
        this.komentar = aktuator.getKomentar();
        this.vrijednost = aktuator.getVrijednost();
        this.status = aktuator.getStatus();
        this.popisSenzora = new ArrayList<>(aktuator.getPopisSenzora());
        this.stanje = aktuator.getStanje();
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

    public void setTip(Integer tipSAktuatora) {
        this.tip = tipSAktuatora;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Boolean getSmjer() {
        return smjer;
    }

    public void setSmjer(Boolean smjer) {
        this.smjer = smjer;
    }

    public List<Senzor> getPopisSenzora() {
        return popisSenzora;
    }

    public void setPopisSenzora(List<Senzor> popisSenzora) {
        this.popisSenzora = popisSenzora;
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

    public Integer getIdModela() {
        return idModela;
    }

    public void setIdModela(Integer idModela) {
        this.idModela = idModela;
    }

    @Override
    public void update(Subject o) {
        stanje = o.getStanje();
    }

    public Boolean getStanje() {
        return stanje;
    }

}
