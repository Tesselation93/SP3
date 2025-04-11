// Medie er den generelle klasse for film og serier
// Holder styr på titel, kategori, år og rating
public class Medie {
    private String titel;
    private String kategori;
    private int udgivelsesAar;
    private double rating;

    // Opretter medie objekt med titel, kategori, udgivelse, rating
    // Constructor som giver værdier til hvert felt
    public Medie(String titel, String kategori, int udgivelsesAar, double rating) {
        this.titel = titel;
        this.kategori = kategori;
        this.udgivelsesAar = udgivelsesAar;
        this.rating = rating;
    }

    // Returnerer mediets titel
    // Simpel getter-metode
    public String getTitel() {
        return titel;
    }

    // Returnerer hvilken kategori filmen hører til
    // Simpel getter-metode
    public String getKategori() {
        return kategori;
    }

    // Returnerer hvilket år filmen udkom
    // Simpel getter-metode
    public int getUdgivelsesAar() {
        return udgivelsesAar;
    }

    // Returnerer filmens rating
    // Simpel getter-metode
    public double getRating() {
        return rating;
    }

    // Afspiller filmen (eller skriver at den afspilles)
    // Simulerer afspilning med et print
    public void play() {
        System.out.println(titel + " afspilles nu...");
    }
}
