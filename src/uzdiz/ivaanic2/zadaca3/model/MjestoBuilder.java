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
public interface MjestoBuilder {
    Mjesto build();
    MjestoBuilder setNazivMjesta(final String nazivMjesta);
    MjestoBuilder setTipMjesta(final Integer tipMjesta);
    MjestoBuilder setBrojSenzoraMjesta(final Integer brojSenzoraMjesta);
    MjestoBuilder setBrojAktuatoraMjesta(final Integer brojAktuatoraMjesta);
    MjestoBuilder setSenzori(final List<Senzor> senzori);
    MjestoBuilder setAktuatori(final List<Aktuator> aktuatori);
    MjestoBuilder setIdMjesta(final Integer idMjesta);
}
