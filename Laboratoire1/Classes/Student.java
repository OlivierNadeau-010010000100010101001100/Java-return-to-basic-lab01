package Laboratoire1.Classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Student extends Personne {

    // Variables
    private final String code_permanent;
    private List<Inscription> inscriptions;

    // Deux Comparators nécessaire au Livrable de l'étape 7
    /*
     * 1. Premier comparator qui trie By_average, donc qui prend les résulatts de la
     * fonction average et qui trie les résultats obtenus, du plus haut au plus
     * bas résultat
     */
    public static final Comparator<Student> byAverage = Comparator.comparingDouble(Student::average).reversed();

    /*
     * 2. Deuxième comprator qui compare les strings des code permanent des Students
     * (a l'aide de getCodePermanent) et qui les trie de A à Z
     */
    public static final Comparator<Student> byCodePermanent = Comparator.comparing(Student::getCodePermanent);


    // Construcetur paramétré - appel de super (qui est Personne)
    public Student(String prenom, String nom, String date_de_naissance, String lieu_de_naissance, Adresse adresse,
            String code_permanent) {
        super(prenom, nom, date_de_naissance, lieu_de_naissance, adresse);

        this.code_permanent = code_permanent;
        this.inscriptions = new ArrayList<>();
    }

    // Getter/Setter
    public String getCodePermanent() {
        return code_permanent;
    }

    // duped de la liste pour éviter de toucher à l'originale
    public List<Inscription> getInscriptions() {
        return new ArrayList<>(inscriptions);
    }

    // Functions Demander

    public void registerForCourse(Course course, String date, Double note_eventuelle) {
        Inscription inscription = new Inscription(date, course, note_eventuelle == null ? 0.0 : note_eventuelle);
        if (!inscription.passerPrerequis(this.inscriptions)) {
            System.out.println("Erreur : L'étudiant : " + code_permanent +", n'a pas réussi les prérequis pour le cours : " + course.getCode());
            return;
        }
        if (!inscription.DeuxinscriptionsNonIdentiques(this.inscriptions)) {
            System.out.println("Erreur : L'étudiant : "+code_permanent+", est déjà inscrit à ce cours : " + course.getCode());
            return;
        }
        inscriptions.add(inscription);
    }

    // Moyenne des notes éventuelles de toutes les inscriptions de l'étudiant
    public double average() {
        if (inscriptions.isEmpty()) {
            return 0.0;
        }

        double somme = 0.0;
        for (Inscription inscription : inscriptions) {
            somme += inscription.getNote_eventuelle();
        }

        return somme / inscriptions.size();
    }

    @Override
    public String displayName() {
        return super.displayName() + ", code_permanent: " + code_permanent + ", moyenne: " + average();
    }

    // Ont override la classe equals, alors quand ont utilise le equals de student,
    // il regarde si preminèrement ils sont des objets,
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Student)) {
            return false;
        }

        Student other_student;
        try {
            other_student = (Student) object;
        } catch (Exception e) {
            System.out.println("L'objet fournis n'est pas compatible à la classe Student");
            return false;
        }

        return this.code_permanent.equals(other_student.code_permanent);
    }

    @Override
    public int hashCode() {
        return code_permanent.hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + " + code_permanent: " + code_permanent + ", Inscriptions:" + inscriptions;
    }
}
