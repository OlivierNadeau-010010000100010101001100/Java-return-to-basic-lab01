package Laboratoire1.Classes;

import java.util.List;

public class Inscription {
    private String date;
    private Course course;
    private double note_eventuelle;

    // Constructeur par défaut
    public Inscription() {
        this.date = null;
        this.course = null;
        this.note_eventuelle = 0.0;
    }

    // Constructeur avec paramètres
    public Inscription(String date, Course course, double note_eventuelle) {
        this.date = date;
        this.course = course;
        this.note_eventuelle = note_eventuelle;
    }

    public Inscription(String date, Course course) {
        this.date = date;
        this.course = course;
        this.note_eventuelle = 0.0;
    }

    // Getters/Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        //
        this.course = course;
    }

    public double getNote_eventuelle() {
        return note_eventuelle;
    }

    public void setNote_eventuelle(double note_eventuelle) {
        this.note_eventuelle = note_eventuelle;
    }

    // Functions Demander

    // Si la note est inférieure à 60, elle est considérée comme un échec (passed ==
    // false)
    public boolean passedCourse() {
        return note_eventuelle >= 60.0;
    }

    @Override
    public String toString() {
        return "Inscription" + "Date: " + date + ", Course: " + course.getCode() + ", Note éventuelle: "
                + note_eventuelle;
    }

    // Vérifier si l'étudiant n'a pas déjà 2 fois le même cours dans ses
    // inscriptions (a seulement besoin de fonctionner lorsque ont ajouter une
    // nouvelle inscription)
    // Avant je fesait toute la liste, mais des les doublons ne fonctionnaient pas
    // correctement
    public boolean DeuxinscriptionsNonIdentiques(List<Inscription> inscriptions) {

        // une bonne vielle boucle for
        for (Inscription inscription : inscriptions) {

            if (inscription.getCourse().getCode().equals(this.course.getCode())) {
                return false;
            }
        }

        return true;
    }

    /*
     * Malgrès qu'il ne soit pas demander, j'ai été le "extra mile" pour créer une
     * méthode qui check si un étudiant a passé
     * les prérequis d'un cours avant de pouvoir s'inscrire à celui-ci, il est peut
     * être demander plus loins, mais c'Est entièrement personnel
     */
    public boolean passerPrerequis(List<Inscription> inscriptions) {
        if (course.getPrerequis().isEmpty()) {
            return true;
        } else {
            for (Course prerequis : course.getPrerequis()) {

                boolean prerequisReussit = false;
                // Aller chercher les inscriptions de l'étudiant, puisque les prérequis sont des
                // cours,
                // de plus, il faut vérifier si l'étudiant a passé les cours prérequis
                for (Inscription inscription : inscriptions) {
                    if (inscription.getCourse() == prerequis && inscription.passedCourse()) {
                        prerequisReussit = true;
                        break;
                    }
                }

                if (prerequisReussit == false) {
                    return false;
                }
            }
        }
        return true;
    }
}
