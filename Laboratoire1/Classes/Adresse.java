package Laboratoire1.Classes;

public class Adresse {
    private String numero_de_porte;
    private String rue;
    private String ville;
    private String adresse_postale;

    // Constructeurs (pour mon aprrentissage et ma remémoraisation des constructeurs)
    // Constructeur par défaut
    public Adresse() {
        this.numero_de_porte = "dft";
        this.rue = "dft";
        this.ville = "dft";
        this.adresse_postale = "dft";
    }

    // Constructeur avec paramètres
    public Adresse(String numero_de_porte, String rue, String ville, String adresse_postale) {
        if (numero_de_porte == null || numero_de_porte.isEmpty()) {
            throw new IllegalArgumentException("\n-----\nLe numéro de porte ne peut pas être null ou vide.\n-----");
        }
        if (rue == null || rue.isEmpty()) {
            throw new IllegalArgumentException("\n-----\nLa rue ne peut pas être null ou vide.\n-----");
        }
        if (ville == null || ville.isEmpty()) {
            throw new IllegalArgumentException("\n-----\nLa ville ne peut pas être null ou vide.\n-----");
        }
        if (adresse_postale == null || adresse_postale.isEmpty()) {
            throw new IllegalArgumentException("\n-----\nL'adresse postale ne peut pas être null ou vide.\n-----");
        }

        this.numero_de_porte = numero_de_porte;
        this.rue = rue;
        this.ville = ville;
        this.adresse_postale = adresse_postale;
    }

    // Getter et Setter

    public String GetNumeroPorte() {
        return numero_de_porte;
    }

    public String GetRue() {
        return rue;
    }

    public String getVille() {
        return ville;
    }

    public String getAdressePostale() {
        return adresse_postale;
    }

    // Functions Demander

    // le toString() pour afficher les informations de l'objet Adresse voulue
    @Override
    public String toString() {
        return numero_de_porte + " " + rue + ", " + ville + ", " + adresse_postale + " !!! ";
    }
}
