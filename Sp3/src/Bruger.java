import java.util.ArrayList;

// Bruger holder styr på login og brugerens watchlist
// Har brugernavn, adgangskode og en liste med gemte medier
public class Bruger {
    private String brugernavn;
    private String adgangskode;
    private ArrayList<Medie> watchlist = new ArrayList<>();

    // Opretter en ny bruger med navn og kode
    // Gemmer data i variabler til senere brug
    public Bruger(String brugernavn, String adgangskode) {
        this.brugernavn = brugernavn;
        this.adgangskode = adgangskode;
    }

    // Returnerer brugerens navn
    // Simpel getter-metode
    public String getBrugernavn() {
        return brugernavn;
    }

    // Checker om den indtastede adgangskode er korrekt
    // Bruger equals til sammenligning
    public boolean checkPassword(String kode) {
        return adgangskode.equals(kode);
    }

    // Returnerer listen over brugerens gemte film
    // Giver adgang til ArrayList med watchlist
    public ArrayList<Medie> getWatchlist() {
        return watchlist;
    }
}
