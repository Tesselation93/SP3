// Film arver fra Medie og bruges til at lave film-objekter
// Genbruger constructoren fra Medie med super()
public class Film extends Medie {

    // Laver en film med titel, kategori, udgivelse og rating
    // Sender værdierne videre til superklassen Medie
    public Film(String titel, String kategori, int udgivelsesAar, double rating) {
        super(titel, kategori, udgivelsesAar, rating);
    }
}
