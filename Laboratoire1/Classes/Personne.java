package Laboratoire1.Classes;

public class Personne implements Comparable<Personne> {
    private final String prenom;
    private final String nom;
    private final String date_de_naissance;
    private final String lieu_de_naissance;
    private Adresse adresse;

    // Constructeur paramétrer
    public Personne(String prenom, String nom, String date_de_naissance, String lieu_de_naissance, Adresse adresse) {
        this.prenom = prenom;
        this.nom = nom;
        this.date_de_naissance = date_de_naissance;
        this.lieu_de_naissance = lieu_de_naissance;
        this.adresse = adresse;
    }

    public String displayName() {
        return "Voici la méthode displayname() : " + nom + " " + prenom;
    }

    // Comparable : ordre naturel des Personne par nom puis prénom
    @Override
    public int compareTo(Personne other_personne) {

        int comparer_nom = this.nom.compareTo(other_personne.nom);

        if (comparer_nom != 0) {
            return comparer_nom;
        }

        return this.prenom.compareTo(other_personne.prenom);

    }

    // Functions Demander

    @Override
    public String toString() {
        return "prenom : " + prenom + ", nom : " + nom + ", date naissance : " + date_de_naissance
                + ", lieu naissance : " + lieu_de_naissance + ", adresse : " + adresse;
    }
}
