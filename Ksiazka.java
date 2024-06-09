package biblioteka;


public class Ksiazka {
    private String tytul;
    private boolean wypozyczona;

    public Ksiazka(String tytul) {
        this.tytul = tytul;
        this.wypozyczona = false;
    }

    public String getTytul() {
        return tytul;
    }

    public boolean isWypozyczona() {
        return wypozyczona;
    }

    public void wypozycz() {
        this.wypozyczona = true;
    }

    public void oddaj() {
        this.wypozyczona = false;
    }
}
