/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3.model;

/**
 *
 * @author ivaanic2
 */
public class SenzorBuilderImpl implements SenzorBuilder {

    private Senzor senzor;

    public SenzorBuilderImpl() {
        senzor = new Senzor();
    }

    @Override
    public Senzor build() {
        return senzor;
    }

    @Override
    public SenzorBuilder setIdSenzora(int id) {
        senzor.setId(id);
        return this;
    }

    @Override
    public SenzorBuilder setNazivSenzora(String naziv) {
        senzor.setNaziv(naziv);
        return this;
    }

    @Override
    public SenzorBuilder setTipSenzora(int tip) {
        senzor.setTip(tip);
        return this;
    }

    @Override
    public SenzorBuilder setVrstaSenzora(int vrsta) {
        senzor.setVrsta(vrsta);
        return this;
    }

    @Override
    public SenzorBuilder setMinSenzora(float minVrijednost) {
        senzor.setMinVrijednost(minVrijednost);
        return this;
    }

    @Override
    public SenzorBuilder setMaxSenzora(float maxVrijednost) {
        senzor.setMaxVrijednost(maxVrijednost);
        return this;
    }

    @Override
    public SenzorBuilder setKomentarSenzora(String komentar) {
        senzor.setKomentar(komentar);
        return this;
    }

    @Override
    public SenzorBuilder setVrijednostSenzora(float vrijednost) {
        senzor.setVrijednost(vrijednost);
        return this;
    }

    @Override
    public SenzorBuilder setStatusSenzora(int status) {
        senzor.setStatus(status);
        return this;
    }

    @Override
    public SenzorBuilder setBrojGreskiSenzora(int brojGreski) {
        senzor.setBrojGreski(brojGreski);
        return this;
    }

    @Override
    public SenzorBuilder setIdModelaSenzora(int idModela) {
        senzor.setIdModela(idModela);
        return this;
    }

}
