package biblioteka;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Wypozyczalnia {
    private static List<Uzytkownik> uzytkownicy = new ArrayList<>();
    private static List<Ksiazka> ksiazki = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Uzytkownik zalogowanyUzytkownik = null;
    private static RodzajUzytkownika rodzajUzytkownika;

    public static void main(String[] args) {
        inicjujDane();

        while (true) {
            if (rodzajUzytkownika == RodzajUzytkownika.ADMIN) {
                menuWypozyczalni();
            } else if  (rodzajUzytkownika == RodzajUzytkownika.KLIENT){
                menuUzytkownika();
            } else {
            	zaloguj();
            }
        }
    }

    private static void inicjujDane() {
    	uzytkownicy.add(new Uzytkownik("Admin" , RodzajUzytkownika.ADMIN));
    	
        ksiazki.add(new Ksiazka("Wiedzmin"));
        ksiazki.add(new Ksiazka("Harry Potter"));
        ksiazki.add(new Ksiazka("Hobbit"));
        
        rodzajUzytkownika = RodzajUzytkownika.NULL;
    }

    private static void menuWypozyczalni() {
        System.out.println("\n1. Zaloguj się na konto");
        System.out.println("2. Dodaj użytkownika");
        System.out.println("3. Usuń użytkownika");
        System.out.println("4. Wyświetl listę użytkowników");
        System.out.println("5. Wyświetl wszystkie książki");
        System.out.println("6. Wyświetl wypożyczone książki");
        System.out.println("7. Wyświetl użytkowników z wypożyczonymi książkami");
        System.out.println("0. Zamknij aplikacje");

        int wybor = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (wybor) {
            case 1:
                zaloguj();
                break;
            case 2:
                dodajUzytkownika();
                break;
            case 3:
                usunUzytkownika();
                break;
            case 4:
                wyswietlUzytkownikow();
                break;
            case 5:
                wyswietlWszystkieKsiazki();
                break;
            case 6:
                wyswietlWypozyczoneKsiazki();
                break;
            case 7:
                wyswietlUzytkownikowZWypozyczonymiKsiazkami();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Niepoprawny wybór, spróbuj ponownie.");
        }
    }

    private static void zaloguj() {
        System.out.print("Podaj imię użytkownika: ");
        String imie = scanner.nextLine();
        for (Uzytkownik u : uzytkownicy) {
            if (u.getImie().equalsIgnoreCase(imie)) {
                zalogowanyUzytkownik = u;
                rodzajUzytkownika = u.rodzaj;
                System.out.println("Zalogowano jako " + imie);
                return;
            }
        }
        System.out.println("Użytkownik nie znaleziony.");
    }

    private static void dodajUzytkownika() {
        System.out.print("Podaj imię nowego użytkownika: ");
        String imie = scanner.nextLine();
        
        System.out.print("Jaki rodzaj uprawnień ma mieć nowy użytkownik?:\n 1. Admin\n2. Klient");
        String typ = scanner.nextLine();
        
        if(typ.equals("1") || typ.equalsIgnoreCase("admin")) {
        	uzytkownicy.add(new Uzytkownik(imie, RodzajUzytkownika.ADMIN));
        } else {
        	uzytkownicy.add(new Uzytkownik(imie, RodzajUzytkownika.KLIENT));
        }        
        
        System.out.println("Dodano użytkownika " + imie);
    }

    private static void usunUzytkownika() {
        System.out.print("Podaj imię użytkownika do usunięcia: ");
        String imie = scanner.nextLine();
        uzytkownicy.removeIf(u -> u.getImie().equalsIgnoreCase(imie));
        System.out.println("Usunięto użytkownika " + imie);
    }

    private static void wyswietlUzytkownikow() {
        System.out.println("Lista użytkowników:");
        for (Uzytkownik u : uzytkownicy) {
            System.out.println("- " + u.getImie());
        }
    }

    private static void wyswietlWszystkieKsiazki() {
        System.out.println("Lista książek:");
        for (Ksiazka k : ksiazki) {
            System.out.println("- " + k.getTytul() + (k.isWypozyczona() ? " (wypożyczona)" : ""));
        }
    }

    private static void wyswietlWypozyczoneKsiazki() {
        System.out.println("Wypożyczone książki:");
        for (Ksiazka k : ksiazki) {
            if (k.isWypozyczona()) {
                System.out.println("- " + k.getTytul());
            }
        }
    }

    private static void wyswietlUzytkownikowZWypozyczonymiKsiazkami() {
        System.out.println("Użytkownicy z wypożyczonymi książkami:");
        for (Uzytkownik u : uzytkownicy) {
            if (!u.getWypozyczoneKsiazki().isEmpty()) {
                System.out.println("- " + u.getImie() + ":");
                for (Ksiazka k : u.getWypozyczoneKsiazki()) {
                    System.out.println("  * " + k.getTytul());
                }
            }
        }
    }

    private static void menuUzytkownika() {
        System.out.println("\n1. Wyświetl listę dostępnych książek");
        System.out.println("2. Wypożycz książkę");
        System.out.println("3. Oddaj książkę");
        System.out.println("4. Wyświetl wypożyczone książki");
        System.out.println("0. Wyloguj");

        int wybor = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (wybor) {
            case 1:
                wyswietlDostepneKsiazki();
                break;
            case 2:
                wypozyczKsiazke();
                break;
            case 3:
                oddajKsiazke();
                break;
            case 4:
                wyswietlWypozyczoneKsiazki();
                break;
            case 0:
                zalogowanyUzytkownik = null;
                rodzajUzytkownika = RodzajUzytkownika.NULL;
                break;
            default:
                System.out.println("Niepoprawny wybór, spróbuj ponownie.");
        }
    }

    private static void wyswietlDostepneKsiazki() {
        System.out.println("Dostępne książki:");
        for (Ksiazka k : ksiazki) {
            if (!k.isWypozyczona()) {
                System.out.println("- " + k.getTytul());
            }
        }
    }

    private static void wypozyczKsiazke() {
        System.out.print("Podaj tytuł książki do wypożyczenia: ");
        String tytul = scanner.nextLine();
        for (Ksiazka k : ksiazki) {
            if (k.getTytul().equalsIgnoreCase(tytul) && !k.isWypozyczona()) {
                zalogowanyUzytkownik.wypozyczKsiazke(k);
                System.out.println("Wypożyczono książkę: " + tytul);
                return;
            }
        }
        System.out.println("Książka niedostępna.");
    }

    private static void oddajKsiazke() {
        System.out.print("Podaj tytuł książki do oddania: ");
        String tytul = scanner.nextLine();
        for (Ksiazka k : zalogowanyUzytkownik.getWypozyczoneKsiazki()) {
            if (k.getTytul().equalsIgnoreCase(tytul)) {
                zalogowanyUzytkownik.oddajKsiazke(k);
                System.out.println("Oddano książkę: " + tytul);
                return;
            }
        }
        System.out.println("Nie znaleziono takiej książki w Twoich wypożyczeniach.");
    }
}