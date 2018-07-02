/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uzdiz.ivaanic2.zadaca3;

import uzdiz.ivaanic2.zadaca3.singleton.StatistikaSingleton;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import uzdiz.ivaanic2.zadaca3.model.Aktuator;
import uzdiz.ivaanic2.zadaca3.model.AktuatorBuilder;
import uzdiz.ivaanic2.zadaca3.model.AktuatorBuilderImpl;
import uzdiz.ivaanic2.zadaca3.model.Mjesto;
import uzdiz.ivaanic2.zadaca3.model.MjestoBuilder;
import uzdiz.ivaanic2.zadaca3.model.MjestoBuilderImpl;
import uzdiz.ivaanic2.zadaca3.model.Senzor;
import uzdiz.ivaanic2.zadaca3.model.SenzorBuilder;
import uzdiz.ivaanic2.zadaca3.model.SenzorBuilderImpl;

/**
 *
 * @author ivaanic2
 */
public class KonfiguracijaSustava {

    List<Mjesto> listaMjesta = new ArrayList<>();
    List<Senzor> listaSenzora = new ArrayList<>();
    List<Aktuator> listaAktuatora = new ArrayList<>();
    List<Aktuator> listaAktuatoraDodatna = new ArrayList<>();
    ProvjeraMjesta provjeraMjesta = new ProvjeraMjesta();
    
    StatistikaSingleton statistika = StatistikaSingleton.getInstance();
    PrikazPrograma prikaz = PrikazPrograma.getInstance();
    
    
    String redak = "";

    public List<Mjesto> napuniMjesta(String datotekaMjesta) {
        try (BufferedReader br = new BufferedReader(new FileReader(datotekaMjesta))) {
            redak = br.readLine();
            List<Integer> listaIdMjesta = new ArrayList<>();
            while ((redak = br.readLine()) != null) {
                if (redak.split(";").length == 5) {
                    String[] dio = redak.split(";");
                    if (Integer.parseInt(dio[2]) != 1 && Integer.parseInt(dio[2]) != 0) {
                        prikaz.ispisi("Mjesto u datoteci nije ispravno: ne odgovara tip 0 ili 1");
                        statistika.setNeispravnaMjesta(statistika.getNeispravnaMjesta()+1);
                    } else if (listaIdMjesta.contains(Integer.parseInt(dio[0]))) {
                        prikaz.ispisi("Mjesto s id: '" + dio[0] + "' vec postoji");
                        statistika.setNeispravnaMjesta(statistika.getNeispravnaMjesta()+1);
                    } else {
                        MjestoBuilder mjBuilder = new MjestoBuilderImpl();
                        mjBuilder.setIdMjesta(Integer.parseInt(dio[0]));
                        mjBuilder.setNazivMjesta(dio[1]);
                        mjBuilder.setTipMjesta(Integer.parseInt(dio[2]));
                        mjBuilder.setBrojSenzoraMjesta(Integer.parseInt(dio[3]));
                        mjBuilder.setBrojAktuatoraMjesta(Integer.parseInt(dio[4]));
                        listaMjesta.add(mjBuilder.build());
                        listaIdMjesta.add(Integer.parseInt(dio[0]));
                        statistika.setIspravnaMjesta(statistika.getIspravnaMjesta()+1);
                    }
                } else {
                    prikaz.ispisi("Mjesto u datoteci nije ispravno");
                    statistika.setNeispravnaMjesta(statistika.getNeispravnaMjesta()+1);
                }
            }
        } catch (IOException ex) {
            prikaz.ispisi("Greska s obradom datoteke mjesta.");
        }
        return listaMjesta;
    }
    
    
    

    public List<Senzor> napuniSenzore(String datotekaSenzora) {
        try (BufferedReader br = new BufferedReader(new FileReader(datotekaSenzora))) {
            redak = br.readLine();
            List<Integer> listaIdModelaSenzora = new ArrayList<>();
            while ((redak = br.readLine()) != null) {
                if ((redak.substring(redak.length() - 1)).equals(";")) {
                    redak = redak + " ";
                }
                if (redak.split(";").length == 7) {
                    String[] dio = redak.split(";");
                    if (listaIdModelaSenzora.contains(Integer.parseInt(dio[0]))) {
                        prikaz.ispisi("Senzor s id: '" + dio[0] + "' vec postoji.");
                        statistika.setNeispravniSenzori(statistika.getNeispravniSenzori()+1);
                    } else if (Integer.parseInt(dio[2]) != 0 && Integer.parseInt(dio[2]) != 1 && Integer.parseInt(dio[2]) != 2 || Integer.parseInt(dio[3]) != 0 && Integer.parseInt(dio[3]) != 1 && Integer.parseInt(dio[3]) != 2 && Integer.parseInt(dio[3]) != 3) {
                        prikaz.ispisi("Senzor u datoteci nije ispravan.");
                        statistika.setNeispravniSenzori(statistika.getNeispravniSenzori()+1);
                    } else {
                        SenzorBuilder senzBuilder = new SenzorBuilderImpl();
                        senzBuilder.setIdModelaSenzora(Integer.parseInt(dio[0]));
                        senzBuilder.setNazivSenzora(dio[1]);
                        senzBuilder.setTipSenzora(Integer.parseInt(dio[2]));
                        senzBuilder.setVrstaSenzora(Integer.parseInt(dio[3]));
                        senzBuilder.setMinSenzora(Float.parseFloat(dio[4]));
                        senzBuilder.setMaxSenzora(Float.parseFloat(dio[5]));
                        senzBuilder.setKomentarSenzora(dio[6]);
                        listaIdModelaSenzora.add(Integer.parseInt(dio[0]));
                        Senzor s = senzBuilder.build();
                        listaSenzora.add(new Senzor(s));
                        statistika.setIspravniSenzori(statistika.getIspravniSenzori()+1);
                    }
                } else {
                    String[] zapis = redak.split(";");
                    prikaz.ispisi("Senzor u datoteci '" + zapis[1] + "' s id: " + zapis[0] + " nije ispravan pa se eliminira.");
                    statistika.setNeispravniSenzori(statistika.getNeispravniSenzori()+1);
                }
            }
        } catch (IOException ex) {
            prikaz.ispisi("Greska s obradom datoteke senzora");
        }
        return listaSenzora;
    }

    
    
    
    public List<Aktuator> napuniAktuatore(String datotekaAktuatora) {
        try (BufferedReader br = new BufferedReader(new FileReader(datotekaAktuatora))) {
            redak = br.readLine();
            List<Integer> listaIdModelaAktuatora = new ArrayList<>();
            while ((redak = br.readLine()) != null) {
                if ((redak.substring(redak.length() - 1)).equals(";")) {
                    redak += " ";
                }
                if (redak.split(";").length == 7) {
                    String[] dio = redak.split(";");
                    if (listaIdModelaAktuatora.contains(Integer.parseInt(dio[0]))) {
                        prikaz.ispisi("Aktuator s id: '" + dio[0] + "' vec postoji.");
                        statistika.setNeispravniAktuatori(statistika.getNeispravniAktuatori()+1);
                    } else if (Integer.parseInt(dio[2]) != 0 && Integer.parseInt(dio[2]) != 1 && Integer.parseInt(dio[2]) != 2 || Integer.parseInt(dio[3]) != 0 && Integer.parseInt(dio[3]) != 1 && Integer.parseInt(dio[3]) != 2 && Integer.parseInt(dio[3]) != 3) {
                        prikaz.ispisi("Aktuator u datoteci nije ispravan");
                        statistika.setNeispravniAktuatori(statistika.getNeispravniAktuatori()+1);
                    } else {
                        AktuatorBuilder aktBuilder = new AktuatorBuilderImpl();
                        aktBuilder.setIdModelaAktuatora(Integer.parseInt(dio[0]));
                        aktBuilder.setNazivAktuatora(dio[1]);
                        aktBuilder.setTipAktuatora(Integer.parseInt(dio[2]));
                        aktBuilder.setVrstaAktuatora(Integer.parseInt(dio[3]));
                        aktBuilder.setMinAktuatora(Float.parseFloat(dio[4]));
                        aktBuilder.setMaxAktuatora(Float.parseFloat(dio[5]));
                        aktBuilder.setKomentarAktuatora(dio[6]);
                        listaIdModelaAktuatora.add(Integer.parseInt(dio[0]));
                        Aktuator a = aktBuilder.build();
                        listaAktuatora.add(new Aktuator(a));
                        statistika.setIspravniAktuatori(statistika.getIspravniAktuatori()+1);
                    }
                } else {
                    String[] zapis = redak.split(";");
                    prikaz.ispisi("Aktuator u datoteci '" + zapis[1] + "' s id: " + zapis[0] + " nije ispravan pa se eliminira.");
                    statistika.setNeispravniAktuatori(statistika.getNeispravniAktuatori()+1);
                }
            }
        } catch (IOException ex) {
            prikaz.ispisi("Greska s obradom datoteke aktuatora.");
        }
        return listaAktuatora;
    }

    
    
    
    void izvrsiRaspored(String datotekaRasporeda) {
        try (BufferedReader br = new BufferedReader(new FileReader(datotekaRasporeda))) {
            redak = br.readLine();
            redak = br.readLine();
            redak = br.readLine();
            while ((redak = br.readLine()) != null) {
                if (redak.split(";").length == 5) {
                    String[] dio = redak.split(";");
                    int ind = 0;
                    for (int i = 0; i < listaMjesta.size(); i++) {//lista napunjenih mjesta
                        if (listaMjesta.get(i).getId().equals(Integer.parseInt(dio[1]))) {//id liste mjesta se poklapa s id u retku
                            ind = i;
                            break;
                        }
                    }
                    if (dio[0].equals("0") && dio[2].equals("0")) {//raspored uredaja po mjestima (umjesto senz po akt) && senzor
                        if (listaMjesta.get(ind).getListaSenzoraZaMjesto().size() < listaMjesta.get(ind).getBrojSenzora()) {
                            Boolean postoji = false;
                            for (Senzor senzor : listaSenzora) {
                                if (senzor.getIdModela().equals(Integer.parseInt(dio[3]))) {//id modela uredaja su isti
                                    postoji = true;
                                    if (senzor.getTip().equals(listaMjesta.get(ind).getTip()) || senzor.getTip().equals(2)) {//tipovi su isti
                                        Senzor senz = new Senzor(senzor);
                                        senz.setId(Integer.parseInt(dio[4]));
                                        List<Senzor> listaSen = new ArrayList<>(listaMjesta.get(ind).getListaSenzoraZaMjesto());
                                        listaSen.add(senz);
                                        listaMjesta.get(ind).setListaSenzoraZaMjesto(listaSen);
                                        provjeraMjesta.getListaIdUredaja().add(Integer.parseInt(dio[4]));
                                        statistika.setDodijeljeniSenzori(statistika.getDodijeljeniSenzori()+1);                                        
                                        break;
                                    } else {
                                        prikaz.ispisi("Senzor: '" + senzor.getNaziv() + "' tipa: " + senzor.getTip() + " ne odgovara mjestu '" + listaMjesta.get(ind).getNaziv() + "' tipa: " + listaMjesta.get(ind).getTip());
                                        statistika.setNedodijeljeniSenzori(statistika.getNedodijeljeniSenzori()+1);
                                    }
                                }
                            }
                            if (!postoji) {
                               prikaz.ispisi("Senzor s id modela: " + dio[3] + " ne postoji te nije dodan mjestu.");
                                statistika.setNedodijeljeniSenzori(statistika.getNedodijeljeniSenzori()+1);
                            }
                        } else {
                            prikaz.ispisi("Senzor s id modela: " + dio[3] + " i id: " + dio[4] + " nije dodan mjestu zbog popunjenosti mjesta");
                            statistika.setNedodijeljeniSenzori(statistika.getNedodijeljeniSenzori()+1);
                        }
                    } else if (dio[0].equals("0") && dio[2].equals("1")) {
                        if (listaMjesta.get(ind).getListaAktuatoraZaMjesto().size() < listaMjesta.get(ind).getBrojAktuatora()) {
                            Boolean postoji = false;
                            for (Aktuator aktuator : listaAktuatora) {
                                if (aktuator.getIdModela().equals(Integer.parseInt(dio[3]))) {
                                    postoji = true;
                                    if (aktuator.getTip().equals(listaMjesta.get(ind).getTip()) || aktuator.getTip().equals(2)) {
                                        Aktuator akt = new Aktuator(aktuator);
                                        akt.setId(Integer.parseInt(dio[4]));
                                        provjeraMjesta.getListaIdUredaja().add(Integer.parseInt(dio[4]));
                                        List<Aktuator> novaLista = new ArrayList<>(listaMjesta.get(ind).getListaAktuatoraZaMjesto());
                                        novaLista.add(akt);
                                        listaMjesta.get(ind).setListaAktuatoraZaMjesto(novaLista);
                                        statistika.setDodijeljeniAktuatori(statistika.getDodijeljeniAktuatori()+1);
                                        break;
                                    } else {
                                        prikaz.ispisi("Aktuator: '" + aktuator.getNaziv() + "' tipa " + aktuator.getTip() + " ne odgovara mjestu: '" + listaMjesta.get(ind).getNaziv() + "' tipa: " + listaMjesta.get(ind).getTip());
                                        statistika.setNedodijeljeniAktuatori(statistika.getNedodijeljeniAktuatori()+1);
                                    }
                                }
                            }
                            if (!postoji) {
                                prikaz.ispisi("Aktuator s id modela: " + dio[3] + " ne postoji te nije dodan mjestu.");
                                statistika.setNedodijeljeniAktuatori(statistika.getNedodijeljeniAktuatori()+1);
                            }
                        } else {
                            prikaz.ispisi("Aktuator s id modela: " + dio[3] + " i id: " + dio[4] + " nije dodan mjestu zbog popunjenosti mjesta.");
                            statistika.setNedodijeljeniAktuatori(statistika.getNedodijeljeniAktuatori()+1);
                        }
                    }
                }
            }
        } catch (IOException ex) {
            prikaz.ispisi("Greska s obradom datoteke rasporeda.");
        }
    }
    
    
    

    void napuniSenzoreZaAktuatore(String datotekaRasporeda) {
        try (BufferedReader br = new BufferedReader(new FileReader(datotekaRasporeda))) {
            redak = br.readLine();
            redak = br.readLine();
            redak = br.readLine();
            while ((redak = br.readLine()) != null) {
                if (redak.split(";").length == 3) {
                    String[] dio = redak.split(";");
                    String[] senzori = dio[2].split(",");
                    Boolean postojiAktuator = false;
                    Boolean postojiSenzor = false;
                    for (Mjesto mjesto : listaMjesta) {
                        for (Aktuator aktuator : mjesto.getListaAktuatoraZaMjesto()) {
                            if (aktuator.getId() == Integer.parseInt(dio[1])) {
                                postojiAktuator = true;
                                for (String idSenzora : senzori) {
                                    for (Senzor senzor : mjesto.getListaSenzoraZaMjesto()) {
                                        if (senzor.getId() == Integer.parseInt(idSenzora)) {
                                            postojiSenzor = true;
                                            List<Senzor> senz = new ArrayList<>(aktuator.getPopisSenzora());
                                            senzor.addObserver(aktuator);
                                            senz.add(senzor);                                           
                                            aktuator.setPopisSenzora(senz);
                                            statistika.setSenzoriDodijeljeniAktuatorima(statistika.getSenzoriDodijeljeniAktuatorima()+1);
                                            break;
                                        } else {
                                            postojiSenzor = false;
                                        }
                                    }
                                    if (!postojiSenzor) {
                                        prikaz.ispisi("Ne moze se dodijeliti senzor koji ima id: " + idSenzora + " aktuatoru. Senzor se ne nalazi u mjestu.");
                                        statistika.setNedodijeljeniSenzoriAktuatorima(statistika.getNedodijeljeniSenzoriAktuatorima()+1);
                                    }
                                }
                            }
                        }
                    }
                    if (!postojiAktuator) {
                        prikaz.ispisi("Ne moze se dodijeliti senzore aktuatoru koji ima id: " + dio[1] + ". Aktuator se ne nalazi u mjestu.");
                        statistika.setNedodijeljeniSenzoriAktuatorima(statistika.getNedodijeljeniSenzoriAktuatorima()+1);
                    }
                }
            }
        } catch (IOException ex) {
            prikaz.ispisi("Pogreska s obradom datoteke rasporeda.");
        }
    }
}
