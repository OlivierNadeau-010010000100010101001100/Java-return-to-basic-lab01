package Laboratoire1.Classes;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private final String code;
    private final String title;
    private final String description;
    private List<Course> Prerequis;

    public Course(String code, String title, String description) {

        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("\n-----\nLe code du course ne peut pas être null ou vide.\n-----");
        } else {
            this.code = code;
        }
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("\n-----\nLe titre du course ne peut pas être null ou vide.\n-----");
        } else {
            this.title = title;
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException(
                    "\n-----\nLa description du course ne peut pas être null ou vide.\n-----");
        } else {
            this.description = description;
        }

        this.Prerequis = new ArrayList<>();
    }

    // Getters/Setters

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Course> getPrerequis() {
        return new ArrayList<>(Prerequis);
    }

    public void addPrerequis(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("\n-----\nLe prérequis ne peut pas être null.\n-----");
        }

        // Regarde si la liste présente déja un prérequis du meme object
        if (!Prerequis.contains(course)) {
            // si non, il l'ajoute
            Prerequis.add(course);
        }
    }

    // Functions Demander

    // opération ternaire pour le prérequis dans la méthode toString()
    @Override
    public String toString() {
        return "Code: " + code + ", Title: " + title + ", Description: " + description + ", Prerequis: "
                + (Prerequis != null ? Prerequis : "None");
    }
}
