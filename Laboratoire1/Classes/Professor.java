package Laboratoire1.Classes;

public class Professor extends Personne {

    private String numero_employee;

    public Professor(String prenom, String nom, String date_de_naissance, String lieu_de_naissance, Adresse adresse,
            String numero_employee) {
        super(prenom, nom, date_de_naissance, lieu_de_naissance, adresse);

        this.numero_employee = numero_employee;
    }

    // Getter/Setters
    public String getNumeroEmployee() {
        return numero_employee;
    }

    // Functions Demander

    public String enseigner() {
        return "Test function de la classe enseigner";
    }

    @Override
    public String displayName() {
        return super.displayName() + ", numero_employee: " + numero_employee;
    }

    @Override
    public String toString() {
        return super.toString() + " + numero d'employee : " + numero_employee;
    }
}
