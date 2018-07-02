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
public class AktuatorBuilderImpl implements AktuatorBuilder{
    
    private Aktuator aktuator;

    public AktuatorBuilderImpl() {
        aktuator = new Aktuator();
    }
    
    @Override
    public Aktuator build() {
        return aktuator;
    }

    @Override
    public AktuatorBuilder setIdAktuatora(int id) {
        aktuator.setId(id);
        return this;
    }

    @Override
    public AktuatorBuilder setNazivAktuatora(String naziv) {
        aktuator.setNaziv(naziv);
        return this;
    }

    @Override
    public AktuatorBuilder setTipAktuatora(int tip) {
        aktuator.setTip(tip);
        return this;
    }

    @Override
    public AktuatorBuilder setVrstaAktuatora(int vrsta) {
        aktuator.setVrsta(vrsta);
        return this;
    }

    @Override
    public AktuatorBuilder setMinAktuatora(float minVrijednost) {
        aktuator.setMinVrijednost(minVrijednost);
        return this;
    }

    @Override
    public AktuatorBuilder setMaxAktuatora(float maxVrijednost) {
        aktuator.setMaxVrijednost(maxVrijednost);
        return this;
    }

    @Override
    public AktuatorBuilder setKomentarAktuatora(String komentar) {
        aktuator.setKomentar(komentar);
        return this;
    }

    @Override
    public AktuatorBuilder setVrijednostAktuatora(float vrijednost) {
        aktuator.setVrijednost(vrijednost);
        return this;
    }

    @Override
    public AktuatorBuilder setStatusAktuatora(int status) {
        aktuator.setStatus(status);
        return this;
    }

    @Override
    public AktuatorBuilder setBrojGreskiAktuatora(int brojGreski) {
        aktuator.setBrojGreski(brojGreski);
        return this;
    }

    @Override
    public AktuatorBuilder setIdModelaAktuatora(int idModela) {
        aktuator.setIdModela(idModela);
        return this;
    }
    
}
