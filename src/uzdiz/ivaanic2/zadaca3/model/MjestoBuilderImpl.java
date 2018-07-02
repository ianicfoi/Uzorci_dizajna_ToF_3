/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.model;

import java.util.List;

/**
 *
 * @author ivaanic2
 */
public class MjestoBuilderImpl implements MjestoBuilder{
    
    private Mjesto mjesto;

    public MjestoBuilderImpl(){
        mjesto = new Mjesto();
    }
    
    @Override
    public Mjesto build() {
        return mjesto;
    }

    @Override
    public MjestoBuilder setNazivMjesta(String nazivMjesta) {
        mjesto.setNaziv(nazivMjesta);
        return this;
    }

    @Override
    public MjestoBuilder setTipMjesta(Integer tipMjesta) {
        mjesto.setTip(tipMjesta);
        return this;
    }

    @Override
    public MjestoBuilder setBrojSenzoraMjesta(Integer brojSenzoraMjesta) {
        mjesto.setBrojSenzora(brojSenzoraMjesta);
        return this;
    }

    @Override
    public MjestoBuilder setBrojAktuatoraMjesta(Integer brojAktuatoraMjesta) {
        mjesto.setBrojAktuatora(brojAktuatoraMjesta);
        return this;
    }

    @Override
    public MjestoBuilder setSenzori(List<Senzor> senzori) {
        mjesto.setListaSenzoraZaMjesto(senzori);
        return this;
    }

    @Override
    public MjestoBuilder setAktuatori(List<Aktuator> aktuatori) {
        mjesto.setListaAktuatoraZaMjesto(aktuatori);
        return this;
    }

    @Override
    public MjestoBuilder setIdMjesta(Integer idMjesta) {
        mjesto.setId(idMjesta);
        return this;
    }
    

}
