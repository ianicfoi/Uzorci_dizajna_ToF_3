/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3;

import uzdiz.ivaanic2.zadaca3.singleton.GeneratorSlucajnogBrojaSingleton;
import uzdiz.ivaanic2.zadaca3.singleton.StatistikaSingleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uzdiz.ivaanic2.zadaca3.model.Aktuator;
import uzdiz.ivaanic2.zadaca3.model.Mjesto;
import uzdiz.ivaanic2.zadaca3.model.Senzor;
import uzdiz.ivaanic2.zadaca3.iterator.Container;
import uzdiz.ivaanic2.zadaca3.iterator.Iterator;

/**
 *
 * @author ivaanic2
 */
public class ProvjeraMjesta extends Thread implements Container {

    private List<Senzor> listaSenzora = new ArrayList<>();
    private List<Aktuator> listaAktuatora = new ArrayList<>();
    public static int trajanjeCiklusaDretve = 0;
    private int brojCiklusaDretve = 0;
    private static long trajanjeObrade = 0;
    long vrijemePocetka = 0;
    long vrijemeZavrsetka = 0;
    private static List<Integer> listaIdUredaja = new ArrayList<>();

    GeneratorSlucajnogBrojaSingleton generatorBrojeva = GeneratorSlucajnogBrojaSingleton.getInstance();
    InicijalizacijaSustava inicijalizacijaSustava = new InicijalizacijaSustava();
    StatistikaSingleton statistika = StatistikaSingleton.getInstance();
    PrikazPrograma prikaz = PrikazPrograma.getInstance();

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        int broj = 1;
        while (brojCiklusaDretve >= 1) {
            vrijemePocetka = System.currentTimeMillis();
            prikaz.ispisi("Provjera mjesta " + broj);
            for (Iterator iter = getIterator(); iter.hasNext();) {
                Mjesto mjesto = (Mjesto) iter.next();
                prikaz.ispisi("");
                prikaz.ispisi(String.format("|%-110s|", "--------------------------------------------------------------------------------------------------------------"));
                prikaz.ispisi(String.format("|%-110s|", "MJESTO: " + mjesto.getNaziv()));
                provjeriStatus(mjesto);
                ocitajVrijednostiSenzora(mjesto);
                ocitajVrijednostiAktuatora(mjesto);
            }
            try {
                prikaz.ispisi("");
                prikaz.ispisi("");
                vrijemeZavrsetka = System.currentTimeMillis();
                trajanjeObrade = vrijemeZavrsetka - vrijemePocetka;
                long vrijeme = trajanjeCiklusaDretve * 1000 - trajanjeObrade;
                if (vrijeme < 0) {
                    vrijeme = 0;
                }
                sleep(vrijeme);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProvjeraMjesta.class.getName()).log(Level.SEVERE, null, ex);
            }
            brojCiklusaDretve--;
            broj++;
        }
    }

    public void provjeriStatus(Mjesto mjesto) {
        
        prikaz.ispisi(String.format("|%-110s|", "========================================= PROVJERA STATUSA UREDAJA ==========================================="));
        prikaz.ispisi(String.format("|%-40s|%-15s|%-15s|%-15s|%-20s|", "Naziv uredaja", "ID uredaja", "Status uredaja", "Broj greski", "Napomena"));
        prikaz.ispisi(String.format("|%-110s|", "--------------------------------------------------------------------------------------------------------------"));
        
        
        for (int i = mjesto.getListaSenzoraZaMjesto().size() - 1; i >= 0; i--) {
            Senzor s = mjesto.getListaSenzoraZaMjesto().get(i);
            if (s.getBrojGreski() != 3) {
                int greske = 0;
                int status = inicijalizacijaSustava.vracenaPoruka();
                if (status == 1) {
                    s.setStanje(true);
                }
                s.setStatus(status);
                if (status == 0) {
                    greske = s.getBrojGreski() + 1;
                    s.setBrojGreski(greske);
                } else {
                    s.setBrojGreski(0);
                }
                prikaz.ispisi(String.format("|%-40s|%15s|%15s|%15s|%-20s|", "Sen: " + s.getNaziv(), s.getId(), s.getStatus(), s.getBrojGreski(), "AKTIVAN"));
                if (greske == 3) {
                    prikaz.ispisi("");
                    prikaz.ispisi(" ----------------------- ZAMJENA SENZORA ---------------------");
                    //novi senzor
                    Senzor senz = (Senzor) s.clone();
                    senz.setVrijednost(0.0f);
                    senz.setBrojGreski(0);
                    Collections.sort(listaIdUredaja, Collections.reverseOrder());
                    senz.setId(listaIdUredaja.get(0) + 1);
                    for(Aktuator akt : mjesto.getListaAktuatoraZaMjesto()){
                        if(akt.getPopisSenzora().contains(s)){
                            akt.getPopisSenzora().remove(s);
                            akt.getPopisSenzora().add(senz);                           
                        }
                    }                  
                    listaIdUredaja.add(listaIdUredaja.get(0) + 1);
                    inicijalizacijaSustava.inicijalizirajSenzor(senz);
                    mjesto.getListaSenzoraZaMjesto().add(new Senzor(senz)); 
                    statistika.setZamjenaSenzora(statistika.getZamjenaSenzora()+1);
                    prikaz.ispisi(String.format("|%-60s|", "Senzor ID: " + s.getId() + " - 3 greske - novi ID: " + senz.getId()));
                    prikaz.ispisi(" -------------------------------------------------------------");
                    prikaz.ispisi("");
                    prikaz.ispisi(String.format("|%-40s|%15s|%15s|%15s|%-20s|", "Senz: " + senz.getNaziv(), senz.getId(), senz.getStatus(), senz.getBrojGreski(), "Ispravan"));
                }
            } else {
                prikaz.ispisi(String.format("|%-40s|%15s|%15s|%15s|%-20s|", "Senz: " + s.getNaziv(), s.getId(), s.getStatus(), s.getBrojGreski(), "Neispravan"));
            }
        }

        for (int i = mjesto.getListaAktuatoraZaMjesto().size() - 1; i >= 0; i--) {
            Aktuator a = mjesto.getListaAktuatoraZaMjesto().get(i);
            if (a.getBrojGreski() != 3) {
                int greske = 0;
                int status = inicijalizacijaSustava.vracenaPoruka();
                a.setStatus(status);
                if (status == 0) {
                    greske = a.getBrojGreski() + 1;
                    a.setBrojGreski(greske);
                } else {
                    a.setBrojGreski(0);
                }
                prikaz.ispisi(String.format("|%-40s|%15s|%15s|%15s|%-20s|", "Akt: " + a.getNaziv(), a.getId(), a.getStatus(), a.getBrojGreski(), "Ispravan"));
                if (greske == 3) {
                    prikaz.ispisi("");
                    prikaz.ispisi(" ----------------------- ZAMJENA AKTUATORA ------------------- ");
                    Aktuator noviAktuator = (Aktuator) a.clone();
                    noviAktuator.setBrojGreski(0);
                    noviAktuator.setVrijednost(0.0f);
                    Collections.sort(listaIdUredaja, Collections.reverseOrder());
                    noviAktuator.setId(listaIdUredaja.get(0) + 1);
                    listaIdUredaja.add(listaIdUredaja.get(0) + 1);
                    inicijalizacijaSustava.inicijalizirajAktuator(noviAktuator);
                    mjesto.getListaAktuatoraZaMjesto().add(new Aktuator(noviAktuator));
                    statistika.setZamjenaAktuatora(statistika.getZamjenaAktuatora()+1);
                    prikaz.ispisi(String.format("|%-60s|", "Aktuator ID: " + a.getId() + " - 3 greske - novi ID: " + noviAktuator.getId()));
                    prikaz.ispisi(" ------------------------------------------------------------- ");
                    prikaz.ispisi("");
                    prikaz.ispisi(String.format("|%-40s|%15s|%15s|%15s|%-20s|", "Akt: " + noviAktuator.getNaziv(), noviAktuator.getId(), noviAktuator.getStatus(), noviAktuator.getBrojGreski(), "Ispravan"));
                }
            } else {
                prikaz.ispisi(String.format("|%-40s|%15s|%15s|%15s|%-20s|", "Akt: " + a.getNaziv(), a.getId(), a.getStatus(), a.getBrojGreski(), "Neispravan"));
            }
        }
        prikaz.ispisi(String.format("|%-90s|", "==============================================================================================================="));
    }

    public void ocitajVrijednostiSenzora(Mjesto mjesto) {
        prikaz.ispisi(String.format("|%-110s|", "============================================= OCITANJE SENZORA ==============================================="));
        prikaz.ispisi(String.format("|%-40s|%-15s|%-15s|%-10s|%-10s|%-15s|", "Naziv senzora", "ID senzora", "Status senzora", "MIN", "MAX", "Vrijednost"));
        prikaz.ispisi(String.format("|%-110s|", "--------------------------------------------------------------------------------------------------------------"));
        for (Senzor s : mjesto.getListaSenzoraZaMjesto()) {
            if (s.getStatus() == 1) {
                if (s.getVrsta() == 0) {
                    //0=od-do cjelobrojno
                    int min = s.getMinVrijednost().intValue();
                    int max = s.getMaxVrijednost().intValue();
                    int vrijednost = generatorBrojeva.dajSlucajniBroj(min, max);
                    float pom = (float) vrijednost;
                    s.setVrijednost(pom);
                } else if (s.getVrsta() == 1) {
                    //1=od-do razlomljeno 1 decimala
                    Float min = s.getMinVrijednost();
                    Float max = s.getMaxVrijednost();
                    float vrijednost = generatorBrojeva.dajSlucajniBroj(min, max);
                    s.setVrijednost(Float.parseFloat(String.format("%.1f", vrijednost).replace(",", ".")));
                } else if (s.getVrsta() == 2) {
                    //2=od-do razlomljeno 5 decimala
                    Float min = s.getMinVrijednost();
                    Float max = s.getMaxVrijednost();
                    float vrijednost = generatorBrojeva.dajSlucajniBroj(min, max);
                    s.setVrijednost(Float.parseFloat(String.format("%.5f", vrijednost).replace(",", ".")));
                } else if (s.getVrsta() == 3) {
                    //3=0(ne) ili 1(da)
                    int min = s.getMinVrijednost().intValue();
                    int max = s.getMaxVrijednost().intValue();
                    int vrijednost = generatorBrojeva.dajSlucajniBroj(min, max);
                    float pom = (float) vrijednost;
                    s.setVrijednost(pom);
                }
                if (s.getVrsta() == 0 || s.getVrsta() == 3) {
                    prikaz.ispisi(String.format("|%-40s|%15s|%15s|%10s|%10s|%15s|", s.getNaziv(), s.getId(), s.getStatus(), s.getMinVrijednost(), s.getMaxVrijednost(), s.getVrijednost().intValue()));
                } else {
                    prikaz.ispisi(String.format("|%-40s|%15s|%15s|%10s|%10s|%15s|", s.getNaziv(), s.getId(), s.getStatus(), s.getMinVrijednost(), s.getMaxVrijednost(), s.getVrijednost()));
                }
                statistika.setVrijednostiSenzora(statistika.getVrijednostiSenzora()+1);
            }
        }
        prikaz.ispisi(String.format("|%-110s|", "=============================================================================================================="));
    }

    public void ocitajVrijednostiAktuatora(Mjesto mjesto) {
        prikaz.ispisi(String.format("|%-110s|", "============================================================ OCITANJE AKTUATORA ============================================================="));
        prikaz.ispisi(String.format("|%-40s|%-15s|%-15s|%-10s|%-10s|%-15s|%-30s|", "Naziv aktuatora", "ID aktuatora", "Status aktuatora", "MIN", "MAX", "Vrijednost", "ID dodjeljenih senzora"));
        prikaz.ispisi(String.format("|%-110s|", "---------------------------------------------------------------------------------------------------------------------------------------------"));
        for (Aktuator a : mjesto.getListaAktuatoraZaMjesto()) {
            String listaDodjeljenihSenzora = "";
            for (Senzor s : a.getPopisSenzora()) {
                listaDodjeljenihSenzora += s.getId() + ", ";
            }
            if (listaDodjeljenihSenzora != "") {
                listaDodjeljenihSenzora = listaDodjeljenihSenzora.substring(0, listaDodjeljenihSenzora.lastIndexOf(","));
            }
            if (a.getStatus() == 1) {
                if (a.getStanje()) {
                    if (a.getVrsta() == 0 || a.getVrsta() == 3) {
                        prikaz.ispisi(String.format("|%-40s|%15s|%20s|%10s|%10s|%15s|%30s|", a.getNaziv(), a.getId(), a.getStatus(), a.getMinVrijednost(), a.getMaxVrijednost(), a.getVrijednost().intValue(), listaDodjeljenihSenzora));
                    } else {
                        prikaz.ispisi(String.format("|%-40s|%15s|%20s|%10s|%10s|%15s|%30s|", a.getNaziv(), a.getId(), a.getStatus(), a.getMinVrijednost(), a.getMaxVrijednost(), a.getVrijednost(), listaDodjeljenihSenzora));
                    }
                    Float staraVrijednost = a.getVrijednost();
                    Boolean smjer = a.getSmjer();
                    int vrijednostInt;
                    float vrijednostFloat;
                    if (a.getVrsta() == 0) {
                        int min = a.getMinVrijednost().intValue();
                        int max = a.getMaxVrijednost().intValue();
                        if (smjer) {
                            vrijednostInt = generatorBrojeva.dajSlucajniBroj(staraVrijednost.intValue() + 1, max);
                            if (vrijednostInt == max) {
                                a.setSmjer(false);
                            }
                        } else {
                            vrijednostInt = generatorBrojeva.dajSlucajniBroj(min, staraVrijednost.intValue());
                            if (vrijednostInt == min) {
                                a.setSmjer(true);
                            }
                        }
                        float pom = (float) vrijednostInt;
                        a.setVrijednost(pom);

                    } else if (a.getVrsta() == 1) {
                        float min = a.getMinVrijednost();
                        float max = a.getMaxVrijednost();
                        if (smjer) {
                            vrijednostFloat = generatorBrojeva.dajSlucajniBroj(staraVrijednost, max);
                            if (vrijednostFloat == max) {
                                a.setSmjer(false);
                            }
                        } else {
                            vrijednostFloat = generatorBrojeva.dajSlucajniBroj(min, staraVrijednost);
                            if (vrijednostFloat == min) {
                                a.setSmjer(true);
                            }
                        }
                        a.setVrijednost(Float.parseFloat(String.format("%.1f", vrijednostFloat).replace(",", ".")));

                    } else if (a.getVrsta() == 2) {
                        float min = a.getMinVrijednost();
                        float max = a.getMaxVrijednost();
                        if (smjer) {
                            vrijednostFloat = generatorBrojeva.dajSlucajniBroj(staraVrijednost, max);
                            if (vrijednostFloat == max) {
                                a.setSmjer(false);
                            }
                        } else {
                            vrijednostFloat = generatorBrojeva.dajSlucajniBroj(min, staraVrijednost);
                            if (vrijednostFloat == min) {
                                a.setSmjer(true);
                            }
                        }
                        a.setVrijednost(Float.parseFloat(String.format("%.5f", vrijednostFloat).replace(",", ".")));

                    } else if (a.getVrsta() == 3) {
                        if (staraVrijednost.intValue() == 0) {
                            vrijednostInt = 1;
                        } else {
                            vrijednostInt = 0;
                        }
                        float pom = (float) vrijednostInt;
                        a.setVrijednost(pom);
                    }
                } else {
                    if (a.getPopisSenzora().size() == 0) {
                        if (a.getVrsta() == 0 || a.getVrsta() == 3) {
                            prikaz.ispisi(String.format("|%-40s|%15s|%20s|%10s|%10s|%15s|%30s|", a.getNaziv(), a.getId(), a.getStatus(), a.getMinVrijednost(), a.getMaxVrijednost(), a.getVrijednost().intValue(), ""));
                        } else {
                            prikaz.ispisi(String.format("|%-40s|%15s|%20s|%10s|%10s|%15s|%30s|", a.getNaziv(), a.getId(), a.getStatus(), a.getMinVrijednost(), a.getMaxVrijednost(), a.getVrijednost(), ""));
                        }
                    } else {
                        prikaz.ispisi(String.format("|%-140s|", "AKTUATOR : " + a.getNaziv() + " S ID: " + a.getId() + " nije prikazan jer su senzori nepromijenjeni (" + listaDodjeljenihSenzora.toString() + ")"));
                    }
                }
            }
        }
        statistika.setVrijednostiAktuatora(statistika.getVrijednostiAktuatora()+1);
        for (Senzor s : mjesto.getListaSenzoraZaMjesto()) {
            s.setStanje(false);
        }
        prikaz.ispisi(String.format("|%-110s|", "============================================================================================================================================="));

    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Senzor> getListaSenzora() {
        return listaSenzora;
    }

    public void setListaSenzora(List<Senzor> listaSenzora) {
        this.listaSenzora = listaSenzora;
    }

    public List<Aktuator> getListaAktuatora() {
        return listaAktuatora;
    }

    public void setListaAktuatora(List<Aktuator> listaAktuatora) {
        this.listaAktuatora = listaAktuatora;
    }
    
    public int getBrojCiklusaDretve() {
        return brojCiklusaDretve;
    }

    public void setBrojCiklusaDretve(int brojCiklusaDretve) {
        this.brojCiklusaDretve = brojCiklusaDretve;
    }

    public int getTrajanjeCiklusaDretve() {
        return trajanjeCiklusaDretve;
    }

    public void setTrajanjeCiklusaDretve(int trajanjeCiklusaDretve) {
        this.trajanjeCiklusaDretve = trajanjeCiklusaDretve;
    }

    

    public List<Integer> getListaIdUredaja() {
        return listaIdUredaja;
    }

    public void setListaIdUredaja(List<Integer> listaIdUredaja) {
        this.listaIdUredaja = listaIdUredaja;
    }

    @Override
    public Iterator getIterator() {
        return new IteratorMjesta();
    }

    private class IteratorMjesta implements Iterator {

        int indeks;

        @Override
        public boolean hasNext() {
            if (indeks < KomandePrograma.listaMjesta.size()) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return KomandePrograma.listaMjesta.get(indeks++);
            }
            return null;
        }

    }

}
