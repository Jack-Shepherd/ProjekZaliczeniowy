package biblioteka;

import java.util.ArrayList;
import java.util.List;

class Uzytkownik {
    private String imie;
    private List<Ksiazka> wypozyczoneKsiazki;
    public RodzajUzytkownika rodzaj;

    public Uzytkownik(String imie, RodzajUzytkownika rodzaj) {
        this.imie = imie;
        this.wypozyczoneKsiazki = new ArrayList<>();
        this.rodzaj = rodzaj;
    }

    public String getImie() {
        return imie;
    }

    public List<Ksiazka> getWypozyczoneKsiazki() {
        return wypozyczoneKsiazki;
    }

    public void wypozyczKsiazke(Ksiazka ksiazka) {
        ksiazka.wypozycz();
        wypozyczoneKsiazki.add(ksiazka);
    }

    public void oddajKsiazke(Ksiazka ksiazka) {
        ksiazka.oddaj();
        wypozyczoneKsiazki.remove(ksiazka);
    }
}