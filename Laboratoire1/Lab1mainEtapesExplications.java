/*
    Fait par : Olivier Nadeau
    Composé le : 13 septembre 2026
    Finaliser le : 15 septembre 2026

    Note : Je suis attitré au Groupe 8, cependant aucun fichier nous a été 
    transférer pour connaitre qui sont nos coéquipier aléatoires
*/

package Laboratoire1;

import Laboratoire1.Classes.*;
import java.util.*;

public class Lab1mainEtapesExplications {
    public static void main(String[] args) {

        /*
         * 
         * 
         * Étape # 1 - Modéliser le domaine
         * Livrable : diagramme UML initial.
         **
         * 
         * Diagramme UML Fournis dans le fichier png : DiagrammeUML.png
         * Utilisation du programme en ligne : draw.io
         **
         * 
         * Q1: Où doit vivre la note ?
         * R1: La Note doit vivre dans la classe Inscription, puisque l'inscription
         * comptient un champ "note eventuelle",
         * il appartient spécifiquement à cette classe et non à Student ou à Course.
         * 
         * Q2: Quel objet connaît les inscriptions ?
         * R2: Seulement l'objet Student connait les inscriptions, puisque uniquement un
         * Student à la possibilité de s'inscrire a plusieurs cours. Et non non l'objet
         * Professor
         * 
         * Q3: Un prérequis est-il un String ou un Course ?
         * R3: Un prérequis est un Course, pusiqu'il permet à un Cour de
         * savoir/connaitre ses cours prérequis,
         * et non pas seulement le nom du cours précédent. Un prérequis est une Classe
         * Course qui point vers elle même,
         * d'où sont : (Course) dans le diagramme UML.
         * 
         * Q4: Quelles multiplicités UML utiliser ?
         * R4: Selon le diagramme UML, voici les relations importantes du plus haut au
         * plus bas.
         * 
         * -> Un Student peut avoir 0 ou multiples inscriptions, d'où le : 0..n .
         * Et une Inscription ne peut seuelemnt avoir qu'un seul Student, d'où le : 1..1
         * .
         * 
         * -> Une Inscription ne concerne que 1 cours, d'où le : 1..1 .
         * Et un Course peut avoir 0 ou multiples inscriptions, d'où le : 0..n .
         * 
         * -> Un Prérequis d'un Course peut avoir 0 ou multiples Course, étant donner
         * que un Course peut avoir des prérequis d'elle même,
         * d'où la relation : 0..n qui pointe vers elle-mêmes.
         */

        System.out.println("\n****************** Étape 1 - Modéliser le domaine ******************\n");
        System.out.println("Pour le diagramme, voir le fichier png : DiagrammeUML.png");

        /*
         * 
         * 
         * Étape # 2 - Classes simples et invariants
         * Livrable : Address et Course testées.
         * 
         * Q1: essayez de créer un cours avec un code vide. Quel comportement
         * attendez-vous ?
         * R1: Selon la qualité de mon programme, je souhaite qu'il me retourne une
         * exception, étant donner que mon code gère les exceptions pour les champs
         * vides et/ou nulles.
         */
        System.out.println("\n****************** Étape 2 - Classes simples et invariants ******************\n");

        try {
            Adresse adresse_default = new Adresse();
            System.out.println("Adresse par défaut : " + adresse_default);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Adresse adresse_invalide = new Adresse("", "Rue vide", "St-loin-loin-des-meuh-meuh", "J0K 1M0");
            System.out.println("Adresse avec ville vide : " + adresse_invalide);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Course courseBad = new Course("", "Bad", "Un mauvais course");
            System.out.println("Course avec code vide : " + courseBad);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /*
         * 
         * 
         * Étape #3 - Composition et collections
         * Livrable : prérequis et inscriptions.
         * 
         * Défi : empêcher deux inscriptions au même cours pour un même étudiant.
         * Status : Compléter, tester et fonctionnel.
         */
        System.out.println("\n****************** Étape 3 - Composition et collections ******************\n");

        // Cours principal et ses prérequis
        Course course_main = new Course("M001", "POO_main", "test");
        Course course_prerequis_main01 = new Course("DB02", "Database2", "prerequis POO_main");
        Course course_prerequis_main02 = new Course("PG03", "Programmation", "prerequis POO_main");
        Course course_prerequis_database = new Course("DB01", "Database", "prerequis database 01");

        course_prerequis_main01.addPrerequis(course_prerequis_database);
        course_main.addPrerequis(course_prerequis_main01);
        course_main.addPrerequis(course_prerequis_main02);

        // Adresses réelles pour les étudiants et professeurs (composition réelle,
        // pas un simple String)
        Adresse adresse_etudiant_1 = new Adresse("123", "Rue", "Ville générique", "J0K 1A0");
        Adresse adresse_etudiant_2 = new Adresse("456", "Avenue", "Ville générique", "J0K 1A0");
        Adresse adresse_etudiant_3 = new Adresse("789", "Boulevard", "Ville générique", "J0K 1A0");
        Adresse adresse_prof_1 = new Adresse("1111", "Rue", "Ville générique", "J0K 1A0");
        Adresse adresse_prof_2 = new Adresse("2222", "Quartier", "Ville générique", "J0K 1A0");

        Student student_1 = new Student("Étudiant_1", "etu1", "01-01-2001", "Endroit", adresse_etudiant_1,
                "ETU001");
        Student student_not_legit = new Student("Étudiant_not_legit", "etu2", "01-01-2001", "Somewhere",
                adresse_etudiant_2, "ETU002");
        Student student_2 = new Student("Étudiant_2", "etu3", "01-01-2001", "quelquepart", adresse_etudiant_3,
                "ETU003");

        /* Happy Path - Student_1 */
        student_1.registerForCourse(course_prerequis_database, "essais date 1", 80.0);
        student_1.registerForCourse(course_prerequis_main01, "test date 2", 85.0);
        student_1.registerForCourse(course_prerequis_main02, "date 3", 90.0);
        student_1.registerForCourse(course_main, "date 4", 95.0);

        studentShow(student_1);

        /* Unhappy Path - Student_not_legit */
        /*
         * Ici, il devrait avoir deux erreurs, la première étant que l'étudiant n'a
         * pas réussi les prérequis pour le cours principal (M001), et la deuxième
         * étant que l'étudiant n'a pas réussit le cours prérequis à la database, il
         * est refusé l'accès au prérequis main.
         */
        student_not_legit.registerForCourse(course_prerequis_database, "essais date 1", 50.0);
        student_not_legit.registerForCourse(course_prerequis_main01, "test date 2", 80.0);
        student_not_legit.registerForCourse(course_main, "date 5", null);

        studentShow(student_not_legit);

        /* Unhappy Path - Student_2 */
        /*
         * L'étudiant essaye de rejoindre le même cours deux fois, il devrait avoir
         * une erreur.
         */
        student_2.registerForCourse(course_prerequis_database, "essais date 1", 80.0);
        student_2.registerForCourse(course_prerequis_database, "essais date 1", null);

        studentShow(student_2);

        /*
         * 
         * 
         * Étape #4 - Héritage et comportement commun
         * Livrable : hiérarchie Person.
         * 
         * La classe Etape4main.java contien deux essais, une pour la classe toString()
         * et l'autre pour displayname()
         */
        System.out.println("\n****************** Étape 4 - Héritage et comportement commun ******************\n");

        Professor professor_1 = new Professor("Prof", "01", "01-01-2001", "Ville générique de prof", adresse_prof_1,
                "PROF001");
        Professor professor_2 = new Professor("Prof", "02", "01-01-2001", "Ville générique de prof", adresse_prof_2,
                "PROF002");

        System.out.println(student_1);
        System.out.println(professor_1);
        System.out.println(student_1.displayName());
        System.out.println(professor_1.displayName());
        System.out.println(professor_1.enseigner());

        /*
         * 
         * 
         * Étape #5 - Polymorphisme
         * Livrable : une liste hétérogène de Person.
         * 
         * La classe Etape5main.java contient le nécessaire pour répondre à l'exercise
         * de polymorphisme
         * 
         * Q1: Expliquez quelle version est appelée et pourquoi.
         * R1: La version de displayname() appelé dépendant de la classe extended de
         * l'objet créer (par exmeple : Student à le display name du Override
         * displayname() comprenannt inscriptions et code permanent, tandis que
         * professor contient le displayname() avec le numero d'employ).
         * 
         * Pourquoi ? puisque Java viens utiliser la fonction qui a été Override par la
         * classe, s'il n'y a pas d'override, la fonctione displayname() prend le dessus
         * 
         * 
         * Q2: Identifiez type déclaré et type réel pour chaque appel.
         * R2: Elles ont été identifier dans le main lors de leurs ajout, mais les voici
         * quand même :
         * 
         * Nom | Type Réel | Type déclarer |
         * student_01 | Student | Personne |
         * student_02 | Student | Personne |
         * professor_01 | Professor | Personne |
         * professor_02 | Professor | Personne |
         */

        System.out.println("\n****************** Étape 5 - Polymorphisme ******************\n");

        List<Personne> list_personnes = new ArrayList<>();
        list_personnes.add(student_1); // Type réel : Student
        list_personnes.add(student_2); // Type réel : Student
        list_personnes.add(professor_1); // Type réel : Professor
        list_personnes.add(professor_2); // Type réel : Professor

        for (Personne p : list_personnes) {
            System.out.println(p.displayName());
        }

        /*
         * 
         * 
         * Étape #6 - Égalité logique
         * Livrable : equals() et hashCode() de Student.
         */
        System.out.println("\n****************** Étape 6 - Égalité logique ******************\n");

        Student student_meme_cp_a = new Student("student_dup", "01", "01-01-2001", "Ville générique",
                adresse_etudiant_1, "CPDUPLIQUER01");
        Student student_meme_cp_b = new Student("student_dup", "02", "01-01-2001", "Ville générique",
                adresse_etudiant_2,
                "CPDUPLIQUER01");

        System.out.println(student_meme_cp_a == student_meme_cp_b); // false : objets différents
        System.out.println(student_meme_cp_a.equals(student_meme_cp_b)); // true : même code_permanent

        HashSet<Student> hashset_student = new HashSet<>();
        hashset_student.add(student_meme_cp_a);
        hashset_student.add(student_meme_cp_b);
        System.out.println("Taille Hashset (attendu 1) : " + hashset_student.size());

        /*
         *
         *
         * Étape #7 - Tri : Comparable et Comparator
         * Livrable : deux stratégies de tri.
         * 
         * Q1: Expliquez pourquoi Comparator est préférable lorsqu'on veut plusieurs
         * ordres de tri.
         * R1: Il est préférrable d'utiliser des comparator puisque dans une classe,
         * nous ne pouvons qu'avoir une seule function compareto(), par exemple pour la
         * classe
         * Personne pusique nom et prenom devaient être vérifier ensemble, cependent ce
         * n'Est pas du tout idéal lorsque l'ont recherhce plusieurs functions
         * individuelles sans toucher au compareto(), donc pas de conflits /
         * duplications inutile de la classe
         */
        System.out.println("\n****************** Étape 7 - Tri : Comparable et Comparator ******************\n");

        List<Personne> personnes_triees = new ArrayList<>(list_personnes);

        // Comparable<Personne> : par nom puis prénom
        // Collections.sort trie une liste données en se basant sur le compareto() de la
        // classe donnée, par exemple avoir entré personne signifie qu'il se base sur le
        // override créer
        Collections.sort(personnes_triees);

        // affichage
        System.out.println("Personnes triées par nom (Comparable) :");
        for (Personne p : personnes_triees) {
            System.out.println(p.displayName());
        }

        // Création d'une nouvelle liste pour les tests de trie pour average (ne pas
        // changer l'ordre naturel de student)
        List<Student> students_duped = new ArrayList<>();
        students_duped.add(student_1);
        students_duped.add(student_2);
        students_duped.add(student_meme_cp_a);

        // Trie des students en utilisant le comparator byAverage (selon les notes de
        // ceux-ci)
        students_duped.sort(Student.byAverage);
        int simplecounter = 1;
        System.out.println("\nÉtudiants triés par moyenne décroissante (Comparator BY_AVERAGE) :");
        for (Student s : students_duped) {
            System.out.println(simplecounter + ". " + s.getCodePermanent() + " : " + s.average());
            simplecounter++;
        }

        // Trie des students en utilisant le comparator byCodePermanent (Selon le code
        // permanent du student)
        students_duped.sort(Student.byCodePermanent);
        System.out.println("\nÉtudiants triés par code permanent (Comparator BY_CP) :");
        simplecounter = 1;
        for (Student s : students_duped) {
            System.out.println(simplecounter + ". " + s.getCodePermanent());
            simplecounter++;
        }

        /*
         *
         *
         * Étape 8 - Scénario d'intégration
         * Livrable : main démontrant tout le modèle.
         * 
         * Note : Les données brutes pour les classes précédements créer ont été
         * générées par l'IA pour
         * éviter de perdre du temps avec la saisie manuelle de données et pouvoir me
         * concentrer sur le développement et les tests du code.
         */

        System.out.println("\n****************** Étape 8 - Scénario d'intégration ******************\n");

        // Créer au moins 4 cours, dont 2 avec prérequis.
        Course algo = new Course("INF1010", "Algorithmique", "Introduction aux algorithmes");
        Course poo = new Course("INF1035", "Concepts avancés en objet", "Programmation orientée objet");
        Course bd = new Course("INF2120", "Bases de données", "Conception de bases de données");
        Course reseaux = new Course("INF3000", "Réseaux", "Introduction aux réseaux informatiques");

        // Préreuqis
        // Nécéssite le cour : INF1010 et INF2120 pour INF1035
        poo.addPrerequis(algo);
        poo.addPrerequis(bd);
        // Nécessite le cour : INF2120 pour INF3000
        reseaux.addPrerequis(bd);

        // Création de 5 addresses totales
        Adresse student_adresse_1 = new Adresse("10", "Rue des Ormes", "Trois-Rivières", "G8Z 1A1");
        Adresse student_adresse_2 = new Adresse("20", "Rue des Pins", "Shawinigan", "G9N 2B2");
        Adresse student_adresse_3 = new Adresse("30", "Rue des Érables", "Québec", "G1K 3C3");
        Adresse prof_adresse_4 = new Adresse("40", "Rue du Parc", "Trois-Rivières", "G8Z 4D4");
        Adresse prof_adresse_5 = new Adresse("50", "Rue Notre-Dame", "Québec", "G1K 5E5");

        // Créer 3 étudiants et 2 professeurs avec adresses
        Student etudiant_1 = new Student("Amélie", "Girard", "2002-04-12", "Trois-Rivières", student_adresse_1,
                "GIRA01");
        Student etudiant_2 = new Student("Nicolas", "Bélanger", "2001-09-23", "Shawinigan", student_adresse_2,
                "BELA02");
        Student etudiant_3 = new Student("Camille", "Fortin", "2003-01-15", "Québec", student_adresse_3,
                "FORT03");

        Professor professeur_1 = new Professor("Hélène", "Côté", "1970-03-08", "Trois-Rivières",
                prof_adresse_4, "PROF101");
        Professor professeur_2 = new Professor("David", "Mercier", "1965-12-01", "Québec", prof_adresse_5,
                "PROF102");

        // Créer plusieurs inscriptions et notes
        etudiant_1.registerForCourse(algo, "2024-09-05", 88.0);
        etudiant_1.registerForCourse(bd, "2024-09-05", 76.0);
        etudiant_1.registerForCourse(poo, "2025-01-10", 91.0);
        etudiant_1.registerForCourse(algo, "2025-01-10", 99.0);

        etudiant_2.registerForCourse(bd, "2024-09-05", 82.0);
        etudiant_2.registerForCourse(reseaux, "2025-01-10", 70.0);
        etudiant_2.registerForCourse(algo, "2024-09-05", 65.0);

        etudiant_3.registerForCourse(algo, "2024-09-05", 55.0);
        etudiant_3.registerForCourse(poo, "2025-01-10", 80.0);

        // Ajout des nouveaux .tudiants dans une liste
        List<Student> etudiants = new ArrayList<>();
        etudiants.add(etudiant_1);
        etudiants.add(etudiant_2);
        etudiants.add(etudiant_3);

        // Afficher la moyenne de chaque étudiant
        System.out.println("\nMoyenne de chaque étudiant :");
        for (Student s : etudiants) {
            System.out.println("-> " + s.getCodePermanent() + " : " + s.average());
        }

        // Afficher les personnes triées par nom
        List<Personne> personnes = new ArrayList<>();
        personnes.add(etudiant_1);
        personnes.add(etudiant_2);
        personnes.add(etudiant_3);
        personnes.add(professeur_1);
        personnes.add(professeur_2);

        Collections.sort(personnes);
        System.out.println("\nPersonnes triées par nom (Comparable, appel polymorphe de displayName()) :");
        for (Personne p : personnes) {
            System.out.println("-> " + p.displayName());
        }

        // Afficher les étudiants triés par moyenne décroissante.
        etudiants.sort(Student.byAverage);
        System.out.println("\nÉtudiants triés par average :");
        for (Student etu : etudiants) {
            System.out.println("-> " + etu.getCodePermanent() + " : " + etu.average());
        }

        // Tester égalité/HashSet avec deux Student de même CP. (code_permanent dans mon
        // code)
        Student doublon_a = new Student("Test", "Doublon", "2000-01-01", "Trois-Rivières", student_adresse_1,
                "DOUBLON01");
        Student doublon_b = new Student("Test2", "Doublon", "2000-06-06", "Québec", student_adresse_3,
                "DOUBLON01");

        System.out.println("\nÉgalité/HashSet :");
        System.out.println("doublon_a == doublon_b : " + (doublon_a == doublon_b));
        System.out.println("doublon_a.equals(doublon_b) : " + doublon_a.equals(doublon_b));

        HashSet<Student> hashset = new HashSet<>();
        hashset.add(doublon_a);
        hashset.add(doublon_b);
        System.out.println("Taille du HashSet (attendu 1, car même code permanent) : " + hashset.size());

        // Montrer au moins un appel polymorphe via Person.
        // Création d'un objet Personne pour les deux appels 
        System.out.println("\nAppel polymorphe via Personne :");
        // type déclaré : Personne, type réel : Student
        Personne personne_duped = etudiant_1;
        System.out.println("Student -> " + personne_duped.displayName());

        // type déclaré : Personne, type réel : Professor
        personne_duped = professeur_1;
        System.out.println("Professor -> " + personne_duped.displayName());

        System.out.println("\nFin du programme");

        /*
         *
         *
         * Étape 9 - Questions de justification
         * À remettre avec le code : réponses courtes (2-4 phrases chacune).
         * 
         * 
         * Q1: Pourquoi Registration est-elle une classe plutôt qu'une simple
         * List<Course> dans Student ?
         * R1: Parce que la classe Inscription contient des attributs proprent a leurs
         * objets. Si celle-ci devait être une simple List, alors nous perdons toutes
         * relations/attributs importants qui relie les inscriptions aux course et qui
         * donne les informations nécessaires.
         * 
         * Q2. Pourquoi Address est-elle en composition avec Person plutôt qu'en
         * héritage ?
         * R2. La classe Adresse n'est pas un héritage de Personne puisque celle-ci est
         * une classe entièrement autonomme comparer à Student et Professor qui font des
         * appels à l'aide de super pour se compléter. De plus Addresse à moyen de
         * pouvoir s'adapter dynamiquement a des nouvelles entrée/champs sans avoir a
         * restructurer la classe Personne ainsi que ses héritages.
         * 
         * Q3: Quelle différence observez-vous entre == et equals() ?
         * R3 : les opérandes : "==" nous permettent de comparer si les deux objets ont
         * été créer séparéement ou ils découlent de la même instance. d'une autre part,
         * la function equals nous permet de personnaliser nos comparaisons, par exemple
         * dans ce lab nous avons override le equals pour qu'il regadre le nom ainsi que
         * le prénom.
         * 
         * Q4: Pourquoi hashCode() doit-il être cohérent avec equals() ?
         * R4: Pusique HAshcode() calcule directement dans quel emplacement
         * (compartement) l'objet doit analyser doit se trouver,
         * il doit donc se baser sur la function equals() pour comparer les objets déja
         * dans cette emplacement (compartement).
         * 
         * Q5: Pourquoi List<Person> peut contenir Student et Professor ?
         * R5: La list de Personne peut contenir Student et Professor, puisque les
         * objets héritent des propriété de la classe parent, ils sont donc considérer
         * comme fesant partie de la classe Personne.
         * 
         * Q6: Pourquoi Comparator<Student> est-il préférable à une redéfinition
         * différente de compareTo() dans Student ?
         * R6: Il est préférable d'utiliser des Comparator plustot que compareto()
         * puisque il ne peut y avoir que 1 instance de compareto() par classes, ce qui
         * rend le code peut modulaire , comparer à des comparator qui peuvent être
         * implémenter sans avoir a toucher au code original (Open/Close principle de la
         * méthode SOLID) à plusieurs reprises.
         * 
         * -> Si jamais SOLID sonne louche, j'ai un un cour Lundi (14 septembre) dessus.
         * 
         * Q7: Donnez un exemple d'invariant protégé par votre encapsulation.
         * R7: Il existe plusieurs type d'invariants, par contre dans mon code les
         * invariants sont principalement les attributs d'un objet qui ne peuvent être
         * changer suite à leurs construction, à l'aide de l'appel du constructeur. Par
         * exemple, dans la classe Personne.java, nous avons les attributs de la date de
         * naissance ainsi que du lieu de naissance qui ne peuvent être changer suite à
         * leurs création, princiaplement parce que des méthodes n'existent pas mais
         * aussi que leur valeur est final et ne peut être modifier, elles restent donc
         * tout le temps les même suite à leur création.
         */
    }

    // fonction pour afficher les infos des inscriptions à propos d'un student
    public static void studentShow(Student student) {
        System.out.println("\nInformations de l'étudiant : " + student);
        System.out.println("Inscriptions de l'étudiant :");
        for (Inscription inscription : student.getInscriptions()) {
            System.out.println("-> " + inscription);
        }
        System.out.println("\n***************************************\n");
    }
}
