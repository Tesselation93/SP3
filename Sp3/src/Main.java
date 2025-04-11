import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

// Main styrer hele programmet og brugeroplevelsen
// Indeholder start, login, søgning og watchlist
public class Main {

    // Her gemmer jeg brugere og film
    // Bruger ArrayList til at holde data
    static ArrayList<Bruger> brugere = new ArrayList<>();
    static ArrayList<Medie> filmListe = new ArrayList<>();
    static Bruger currentUser = null;
    static Scanner scanner = new Scanner(System.in);

    // Starter programmet og kalder menuen frem
    // Kalder først seedData for at hente testfilm og testbruger
    public static void main(String[] args) {
        seedData();
        visStartMenu();
    }

    // Indlæser film fra fil og opretter en testbruger
    // Scanner og split bruges til at hente og dele fil-data
    public static void seedData() {
        try {
            File file = new File("src/filmdata.txt");
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String linje = fileScanner.nextLine();
                String[] dele = linje.split(";");

                if (dele.length == 4) {
                    String titel = dele[0].trim();
                    String kategori = dele[1].trim();
                    int aar = Integer.parseInt(dele[2].trim());
                    double rating = Double.parseDouble(dele[3].trim());

                    Film film = new Film(titel, kategori, aar, rating);
                    filmListe.add(film);
                }
            }

            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Fejl ved indlæsning af filmdata: " + e.getMessage());
        }

        brugere.add(new Bruger("test", "1234"));
    }

    // Viser første menu hvor brugeren kan logge ind eller afslutte
    // Bruger while-løkke og if-else til at holde programmet kørende
    public static void visStartMenu() {
        while (true) {
            System.out.println("\nVelkommen til Tesselation Din Fortrukne Nørde Streamingtjeneste");
            System.out.println("1. Log ind");
            System.out.println("2. Afslut");
            String valg = scanner.nextLine();

            if (valg.equals("1")) {
                logInd();
            } else if (valg.equals("2")) {
                System.out.println("Ses Nørd!");
                return;
            } else {
                System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    // Her logger brugeren ind med navn og kode
    // For-løkke tjekker om oplysninger matcher en eksisterende bruger
    public static void logInd() {
        System.out.print("Brugernavn: ");
        String brugernavn = scanner.nextLine();
        System.out.print("Adgangskode: ");
        String kode = scanner.nextLine();

        for (Bruger b : brugere) {
            if (b.getBrugernavn().equals(brugernavn) && b.checkPassword(kode)) {
                currentUser = b;
                System.out.println("\nLogin succesfuldt. Velkommen " + brugernavn);
                visBrugerMenu();
                return;
            }
        }
        System.out.println("Forkert brugernavn eller adgangskode.");
    }

    // Viser brugerens hovedmenu efter login
    // Bruger while-løkke og valg med if-else
    public static void visBrugerMenu() {
        while (true) {
            System.out.println("\n1. Søg film");
            System.out.println("2. Se NerdList");
            System.out.println("3. Log ud");
            String valg = scanner.nextLine();

            if (valg.equals("1")) {
                søgFilm();
            } else if (valg.equals("2")) {
                visWatchlist();
            } else if (valg.equals("3")) {
                currentUser = null;
                return;
            } else {
                System.out.println("Ugyldigt valg.");
            }
        }
    }

    // Søger efter film brugeren skriver ind
    // For-løkke og if-contains tjekker om titlen matcher søgning
    public static void søgFilm() {
        System.out.print("Indtast NerdTitel eller nøgleord: ");
        String søgning = scanner.nextLine().toLowerCase();
        ArrayList<Medie> resultater = new ArrayList<>();

        for (Medie m : filmListe) {
            if (m.getTitel().toLowerCase().contains(søgning)) {
                resultater.add(m);
            }
        }

        if (resultater.isEmpty()) {
            System.out.println("Ingen resultater fundet.");
            return;
        }

        for (int i = 0; i < resultater.size(); i++) {
            Medie m = resultater.get(i);
            System.out.println((i + 1) + ". " + m.getTitel() + " (" + m.getKategori() + ", " + m.getUdgivelsesAar() + ", Rating: " + m.getRating() + ")");
        }

        System.out.print("Vælg et nummer for at afspille eller gemme, Nørd: ");
        try {
            int valg = Integer.parseInt(scanner.nextLine()) - 1;
            if (valg >= 0 && valg < resultater.size()) {
                Medie valgt = resultater.get(valg);
                System.out.println("1. Afspil");
                System.out.println("2. Gem til NerdList");
                String handling = scanner.nextLine();
                if (handling.equals("1")) {
                    valgt.play();
                } else if (handling.equals("2")) {
                    currentUser.getWatchlist().add(valgt);
                    System.out.println("Tilføjet til NerdList.");
                } else {
                    System.out.println("Ugyldigt valg.");
                }
            } else {
                System.out.println("Nummer ugyldigt.");
            }
        } catch (Exception e) {
            System.out.println("Ugyldigt input.");
        }
    }

    // Viser brugerens gemte film
    // Hvis den er tom, print besked ellers loop og print titlerne
    public static void visWatchlist() {
        ArrayList<Medie> liste = currentUser.getWatchlist();
        if (liste.isEmpty()) {
            System.out.println("Din NerdList er tom.");
        } else {
            System.out.println("Din NerdListt:");
            for (Medie m : liste) {
                System.out.println("- " + m.getTitel());
            }
        }
    }
}
