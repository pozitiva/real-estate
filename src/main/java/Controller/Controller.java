package Controller;

import DataBase.DatabaseBroker;
import domain.object.entities.Adresa;
import domain.object.entities.Agencija_Osnovno;
import domain.object.entities.Agencija_Pogled;
import domain.object.entities.Grad;
import domain.object.entities.JavniBeleznik;
import domain.object.entities.KatastarskaParcela;
import domain.object.entities.LicnaKarta;
import domain.object.entities.Nekretnina;
import domain.object.entities.NekretninaProdavca;
import domain.object.entities.Osoba;
import domain.object.entities.Potvrda;
import domain.object.entities.Rezervacija;
import domain.object.entities.StavkaRezervacije;
import domain.object.entities.UgovorOKupoprodaji;
import domain.object.entities.UgovorOPosredovanju;
import domain.object.entities.Zapisnik;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Controller {

    DatabaseBroker db = new DatabaseBroker();
    private static Controller instance;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    // LICNA KARTA
    public void insertLicnaKarta(LicnaKarta lk) throws Exception {
        try {
            db.connect();
            db.insert(lk);

            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<LicnaKarta> loadSveLicneKarte() throws Exception {
        List<LicnaKarta> licneKarte = new LinkedList<>();
        try {
            db.connect();
            licneKarte = (List<LicnaKarta>) (Object) db.getAll(new LicnaKarta());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return licneKarte;
    }

    public List<LicnaKarta> searchPozicije(String whereClause) throws Exception {
        List<LicnaKarta> licneKarte = new LinkedList<>();
        try {
            db.connect();
            licneKarte = (List<LicnaKarta>) (Object) db.getAllWithWhere(new LicnaKarta(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return licneKarte;
    }

    // GRAD
    public void insertGrad(Grad grad) throws Exception {
        try {
            db.connect();
            db.insert(grad);

            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Grad> loadSveGradove() throws Exception {
        List<Grad> gradovi = new LinkedList<>();
        try {
            db.connect();
            gradovi = (List<Grad>) (Object) db.getAll(new Grad());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return gradovi;
    }

    public List<Grad> searchGradovi(String whereClause) throws Exception {
        List<Grad> gradovi = new LinkedList<>();
        try {
            db.connect();
            gradovi = (List<Grad>) (Object) db.getAllWithWhere(new Grad(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return gradovi;
    }
    
    
    public void updateGrad(Grad g) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite nalog?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.update(g);
            JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // AGENCIJA POGLED
    public void insertAgencijaPogled(Agencija_Pogled ap) throws Exception {
        try {
            db.connect();
            db.insert(ap);
            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Agencija_Pogled> loadSveAgencije() throws Exception {
        List<Agencija_Pogled> agencije = new LinkedList<>();
        try {
            db.connect();
            agencije = (List<Agencija_Pogled>) (Object) db.getAll(new Agencija_Pogled());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return agencije;
    }

    public void deleteAgencija(Agencija_Pogled ap) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zaista zelite da obrisete objekat?", "Brisanje", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.delete(ap);

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Agencija_Pogled> searchAgencije(String whereClause) throws Exception {
        List<Agencija_Pogled> agencije = new LinkedList<>();
        try {
            db.connect();
            agencije = (List<Agencija_Pogled>) (Object) db.getAllWithWhere(new Agencija_Pogled(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return agencije;
    }

    public void updateAgencija(Agencija_Pogled ap) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite agenciju?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.update(ap);
            JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // AGENCIJA OSNOVNO
    public List<Agencija_Osnovno> loadSveAgencijeOsnovno() throws Exception {
        List<Agencija_Osnovno> agencije = new LinkedList<>();
        try {
            db.connect();
            agencije = (List<Agencija_Osnovno>) (Object) db.getAll(new Agencija_Osnovno());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return agencije;
    }

    // UGOVOR O KUPOPRODAJI
    public List<UgovorOKupoprodaji> loadSveUgovoreOKupoprodaji() throws Exception {
        List<UgovorOKupoprodaji> ugovoriOKupoprodaji = new LinkedList<>();
        try {
            db.connect();
            ugovoriOKupoprodaji = (List<UgovorOKupoprodaji>) (Object) db.getAll(new UgovorOKupoprodaji());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ugovoriOKupoprodaji;
    }

    public List<UgovorOKupoprodaji> searchUgovoriOKupoprodaji(String whereClause) throws Exception {
        List<UgovorOKupoprodaji> ugovoriOKupoprodaji = new LinkedList<>();
        try {
            db.connect();
            ugovoriOKupoprodaji = (List<UgovorOKupoprodaji>) (Object) db.getAllWithWhere(new UgovorOKupoprodaji(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ugovoriOKupoprodaji;
    }

    public void insertUgovorOKupoprodaji(UgovorOKupoprodaji uk) throws Exception {
        try {
            db.connect();
            db.insert(uk);
            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateUgovorOKupoprodaji(UgovorOKupoprodaji uk, String setClause) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite ugovor o kupoprodaji?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                db.updatePartial(uk, setClause);
                JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<UgovorOKupoprodaji> getParticijeUgovorOKupoprodaji(String string) throws Exception {
        List<UgovorOKupoprodaji> uk = new LinkedList<>();
        try {
            db.connect();
            uk = (List<UgovorOKupoprodaji>) (Object) db.getPartition(new UgovorOKupoprodaji(), string);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return uk;
    }

     // UGOVOR O POSREDOVANJU
    public List<UgovorOPosredovanju> loadSveUgovoreOPosredovanju() throws Exception {
        List<UgovorOPosredovanju> ugovoriOPosredovanju = new LinkedList<>();
        try {
            db.connect();
            ugovoriOPosredovanju = (List<UgovorOPosredovanju>) (Object) db.getAll(new UgovorOPosredovanju());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ugovoriOPosredovanju;
    }

    public List<UgovorOPosredovanju> searchUgovoriOPosredovanju(String whereClause) throws Exception {
        List<UgovorOPosredovanju> ugovoriOPosredovanju = new LinkedList<>();
        try {
            db.connect();
            ugovoriOPosredovanju = (List<UgovorOPosredovanju>) (Object) db.getAllWithWhere(new UgovorOPosredovanju(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ugovoriOPosredovanju;
    }

    public void insertUgovorOPosredovanju(UgovorOPosredovanju up) throws Exception {
        try {
            db.connect();
            db.insert(up);
            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateUgovorOPosredovanju(UgovorOPosredovanju up) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite ugovor o posredovanju?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                db.update(up);
                JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteUgovorOPosredovanju(UgovorOPosredovanju u) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zaista zelite da obrisete objekat?", "Brisanje", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.delete(u);

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // ADRESA
    public List<Adresa> loadSveAdrese() throws Exception {
        List<Adresa> banke = new LinkedList<>();
        try {
            db.connect();
            banke = (List<Adresa>) (Object) db.getAll(new Adresa());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return banke;
    }

    public List<Adresa> searchAdrese(String whereClause) throws Exception {
        List<Adresa> adrese = new LinkedList<>();
        try {
            db.connect();
            adrese = (List<Adresa>) (Object) db.getAllWithWhere(new Adresa(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return adrese;
    }

    public void insertAdresa(Adresa a) throws Exception {
        try {
            db.connect();
            db.insert(a);
            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteAdresa(Adresa a) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zaista zelite da obrisete objekat?", "Brisanje", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.delete(a);

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public void updateAdresa(Adresa a, String setClause) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite nalog?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                db.updatePartial(a, setClause);
                JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // ZAPOSLENI
    public List<Osoba> loadSveOsobe() throws Exception {
        List<Osoba> osobe = new LinkedList<>();
        try {
            db.connect();
            osobe = (List<Osoba>) (Object) db.getAll(new Osoba());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return osobe;
    }

    public List<Osoba> searchOsobe(String whereClause) throws Exception {
        List<Osoba> osobe = new LinkedList<>();
        try {
            db.connect();
            osobe = (List<Osoba>) (Object) db.getAllWithWhere(new Osoba(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return osobe;
    }

    public void insertOsobe(Osoba o) throws Exception {
        try {
            db.connect();
            db.insert(o);
            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateOsoba(Osoba o) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite osobu?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.update(o);
            JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // REZERVACIJA
    public List<Rezervacija> loadSveRezervacije() throws Exception {
        List<Rezervacija> rezervacije = new LinkedList<>();
        try {
            db.connect();
            rezervacije = (List<Rezervacija>) (Object) db.getAll(new Rezervacija());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rezervacije;
    }

    public List<Rezervacija> searchRezervacije(String whereClause) throws Exception {
        List<Rezervacija> rezervacije = new LinkedList<>();
        try {
            db.connect();
            rezervacije = (List<Rezervacija>) (Object) db.getAllWithWhere(new Rezervacija(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rezervacije;
    }

    public void updateRezervacija(Rezervacija r, String setClause) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite rezervaciju?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                db.updatePartial(r, setClause);
                JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");
            }
        } catch (Exception ex) {
           throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // STAVKA REZERVACIJE
    public List<StavkaRezervacije> searchStavkeRezervacije(String whereClause) throws Exception {
        List<StavkaRezervacije> stavkeRezervacije = new LinkedList<>();
        try {
            db.connect();
            stavkeRezervacije = (List<StavkaRezervacije>) (Object) db.getAllWithWhere(new StavkaRezervacije(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stavkeRezervacije;
    }

    public void insertStavkaRezervacije(StavkaRezervacije s) throws Exception {
        try {
            db.connect();
            db.insert(s);
            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteStavkaRezervacije(StavkaRezervacije s) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zaista zelite da obrisete objekat?", "Brisanje", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.delete(s);

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateStavkaRezervacije(StavkaRezervacije s) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite nalog?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.update(s);
            JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // JAVNI BELEZNIK
    public List<JavniBeleznik> loadSveJavneBeleznike() throws Exception {
        List<JavniBeleznik> javniBeleznici = new LinkedList<>();
        try {
            db.connect();
            javniBeleznici = (List<JavniBeleznik>) (Object) db.getAll(new JavniBeleznik());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return javniBeleznici;
    }

    public List<JavniBeleznik> searchJavniBeleznici(String whereClause) throws Exception {
        List<JavniBeleznik> javniBeleznici = new LinkedList<>();
        try {
            db.connect();
            javniBeleznici = (List<JavniBeleznik>) (Object) db.getAllWithWhere(new JavniBeleznik(), whereClause);
        } catch (Exception ex) {
           throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return javniBeleznici;
    }

    public void updateJavniBeleznik(JavniBeleznik jb) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite nalog?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.update(jb);
            JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // KATASTARSKA PARCELA
    public List<KatastarskaParcela> loadSveKatastarskeParcele() throws Exception {
        List<KatastarskaParcela> katastarskeParcele = new LinkedList<>();
        try {
            db.connect();
            katastarskeParcele = (List<KatastarskaParcela>) (Object) db.getAll(new KatastarskaParcela());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return katastarskeParcele;
    }

    public List<KatastarskaParcela> searchKatastarskeParcele(String whereClause) throws Exception {
        List<KatastarskaParcela> katastarskeParcele = new LinkedList<>();
        try {
            db.connect();
            katastarskeParcele = (List<KatastarskaParcela>) (Object) db.getAllWithWhere(new KatastarskaParcela(), whereClause);
        } catch (Exception ex) {
           throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return katastarskeParcele;
    }

    public void updateKatastarskaParcela(KatastarskaParcela kp) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite nalog?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.update(kp);
            JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // NEKRETNINA
    public List<Nekretnina> loadSveNekretnine() throws Exception {
        List<Nekretnina> nekretnine = new LinkedList<Nekretnina>();
        try {
            db.connect();
            nekretnine = (List<Nekretnina>) (Object) db.getAll(new Nekretnina());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return nekretnine;
    }

    public List<Nekretnina> searchNekretnine(String whereClause) throws Exception {
        List<Nekretnina> nekretnine = new LinkedList<Nekretnina>();
        try {
            db.connect();
            nekretnine = (List<Nekretnina>) (Object) db.getAllWithWhere(new Nekretnina(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return nekretnine;
    }

    public void insertNekretnina(Nekretnina n) throws Exception {
        try {
            db.connect();
            db.insert(n);
            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteNekretnina(Nekretnina n) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zaista zelite da obrisete objekat?", "Brisanje", JOptionPane.YES_NO_OPTION);
            if (answer == 1) {
                return;
            }
            db.delete(n);

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateNekretnina(Nekretnina n) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite nekretninu?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                db.update(n);
                JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // NEKRETNINA PRODAVCA
    public List<NekretninaProdavca> loadSveNekretnineProdavca() throws Exception {
        List<NekretninaProdavca> nekretnineProdavca = new LinkedList<>();
        try {
            db.connect();
            nekretnineProdavca = (List<NekretninaProdavca>) (Object) db.getAll(new NekretninaProdavca());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return nekretnineProdavca;
    }

    public List<NekretninaProdavca> searchNekretnineProdavca(String whereClause) throws Exception {
        List<NekretninaProdavca> nekretnineProdavca = new LinkedList<>();
        try {
            db.connect();
            nekretnineProdavca = (List<NekretninaProdavca>) (Object) db.getAllWithWhere(new NekretninaProdavca(), whereClause);
        } catch (Exception ex) {
           throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return nekretnineProdavca;
    }
    
    // POTVRDA
    public List<Potvrda> loadSvePotvrde() throws Exception {
        List<Potvrda> potvrde = new LinkedList<>();
        try {
            db.connect();
            potvrde = (List<Potvrda>) (Object) db.getAll(new Potvrda());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return potvrde;
    }

    public List<Potvrda> searchPotvrde(String whereClause) throws Exception {
        List<Potvrda> potvrde = new LinkedList<>();
        try {
            db.connect();
            potvrde = (List<Potvrda>) (Object) db.getAllWithWhere(new Potvrda(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return potvrde;
    }

        public void insertPotvrda (Potvrda p) throws Exception {
        try {
            db.connect();
            db.insert(p);
            JOptionPane.showMessageDialog(null, "Uspesno ste uneli");

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void updatePotvrda(Potvrda p, String setClause) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite potvrdu?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                db.updatePartial(p, setClause);
                JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");
            }
        } catch (Exception ex) {
           throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // ZAPISNIK
    public List<Zapisnik> loadSveZapisnike() throws Exception {
        List<Zapisnik> zapisnici = new LinkedList<>();
        try {
            db.connect();
            zapisnici = (List<Zapisnik>) (Object) db.getAll(new Zapisnik());
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return zapisnici;
    }

    public List<Zapisnik> searchZapisnici(String whereClause) throws Exception {
        List<Zapisnik> zapisnici = new LinkedList<>();
        try {
            db.connect();
            zapisnici = (List<Zapisnik>) (Object) db.getAllWithWhere(new Zapisnik(), whereClause);
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return zapisnici;
    }

    public void updateZapisnik(Zapisnik z, String setClause) throws Exception {
        try {
            db.connect();
            int answer = JOptionPane.showConfirmDialog(null, "Zelite li da izmenite zapisnik?", "Izmena", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                db.updatePartial(z, setClause);
                JOptionPane.showMessageDialog(null, "Uspesno ste izmenili");
            }
        } catch (Exception ex) {
           throw ex;
        } finally {
            try {
                db.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
