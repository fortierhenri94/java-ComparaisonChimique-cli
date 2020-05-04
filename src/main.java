import java.util.*;

public class main {

    // Déclaration de variables

    // ArrayList contenant la séquence d'acides aminés contenus dans l'ARN <code> a </code>
    public static ArrayList<String> s = new ArrayList<String>();

    // ArrayList contenant la séquence d'acides aminés contenus dans l'ARN <code> b </code>
    public static ArrayList<String> t = new ArrayList<String>();

    // HashMap contenant l'index des acides aminés dans <code> s </code> ET dans <code> t </code>
    // comme KEY et la déviation minimal associé comme valeur à partir de <code> s </code>
    public static HashMap<Integer, Integer> mapS = new HashMap<Integer, Integer>();

    // HashMap contenant l'index des acides aminés dans <code> t </code> ET dans <code> s </code>
    // comme KEY et la déviation minimal associé comme valeur à partir de <code> t </code>
    public static HashMap<Integer, Integer> mapT = new HashMap<Integer, Integer>();

    // Variable contenant la déviation total (lorsque considéré comme infini) dans <code> s </code>
    public static int deviationInfiniS;

    // Variable contenant la déviation total (lorsque considéré comme infini) dans <code> t </code>
    public static int deviationInfiniT;

    // Variable contenant la distance maximum utilisée pour le calcul de la métrique
    public static int d;

    // Désigne le nombre d'acides aminés dans l'ARN <code> t </code>
    public static int m;

    // Désigne le nombre d'acides aminés dans l'ARN <code> s </code>
    public static int n;

    // Exécute le code
    public static void main(String[] args) {

        lectureARN1();

        lectureARN2();

        distanceMaximumMetriqueInput();

        comparaisonAcidesIdentiquesEntreS_T();

        comparaisonAcidesIdentiquesEntreT_S();

        calculMetrique();

    }

    // Enregistre dans une variable la première chaîne de caractère entrée par l'utilisateur
    // et test sa validité selon les conditions du TP
    public static String lectureARN1() {

        // La variable <code> a </code> représente l'ARN1.
        String a;

        Scanner ARN1 = new Scanner(System.in);

        System.out.println("Veuillez entrer une chaîne de caractères représentant le premier ARN ...");
        a = ARN1.nextLine();

        if (a.matches("[ACGU]*") && !a.isEmpty() && a.length() % 3 == 0) {
            System.out.println();
        }
        else if (!a.matches("[ACGU]*")) {

            if (a.length() % 3 != 0) {
                System.err.println("La taille de la chaîne de caractères entrée n'est pas divisible par 3... Fin du programme.");
                System.err.println("La chaîne de caractères entrée n'est pas conforme... Elle ne doit contenir que les lettres 'A', 'C', 'G' et/ou 'U'. Fin du programme.");
                System.exit(-1);
            }
            else {
                System.err.println("La chaîne de caractères entrée n'est pas conforme... Elle ne doit contenir que les lettres 'A', 'C', 'G' et/ou 'U'. Fin du programme.");
                System.exit(-1);
            }
        }
        else if (a.isEmpty()) {
            System.err.println("Vous n'avez pas entrés aucune chaîne de caractères... Fin du programme.");
            System.exit(-1);
        }
        else if (a.length() % 3 != 0) {
            System.err.println("La taille de la chaîne de caractères entrée n'est pas divisible par 3... Fin du programme.");
            System.exit(-1);
        }
        else {
            System.err.println("Fail !");
            System.exit(-1);
        }

        // Variable pour divisé l'ARN en groupe de 3 (en codons)
        int limiteSize3 = 3;

        // Boucle afin de diviser le String input en groupe de 3 et les mettre dans un ArrayList
        for (int i = 0; i < a.length(); i += limiteSize3) {
            s.add(a.substring(i, Math.min(a.length(), i + limiteSize3)));
        }

        //Transformation Codons en acides aminés
        for (int i = 0; i < s.size(); i++) {

            // Ala (Alanine)
            if (s.get(i).equals("GCU")) {
                s.set(i, "Ala");
            }
            else if (s.get(i).equals("GCC")) {
                s.set(i, "Ala");
            }
            else if (s.get(i).equals("GCA")) {
                s.set(i, "Ala");
            }
            else if (s.get(i).equals("GCG")) {
                s.set(i, "Ala");
            }
            // Arg (Arginine)
            else if (s.get(i).equals("CGU")) {
                s.set(i, "Arg");
            }
            else if (s.get(i).equals("CGC")) {
                s.set(i, "Arg");
            }
            else if (s.get(i).equals("CGA")) {
                s.set(i, "Arg");
            }
            else if (s.get(i).equals("CGG")) {
                s.set(i, "Arg");
            }
            else if (s.get(i).equals("AGA")) {
                s.set(i, "Arg");
            }
            else if (s.get(i).equals("AGG")) {
                s.set(i, "Arg");
            }
            // Asn (Asparagine)
            else if (s.get(i).equals("AAU")) {
                s.set(i, "Asn");
            }
            else if (s.get(i).equals("AAC")) {
                s.set(i, "Asn");
            }
            // Asp (Aspartate)
            else if (s.get(i).equals("GAU")) {
                s.set(i, "Asp");
            }
            else if (s.get(i).equals("GAC")) {
                s.set(i, "Asp");
            }
            // Cys (Cystéine)
            else if (s.get(i).equals("UGU")) {
                s.set(i, "Cys");
            }
            else if (s.get(i).equals("UGC")) {
                s.set(i, "Cys");
            }
            // Glu (Glutamate)
            else if (s.get(i).equals("GAA")) {
                s.set(i, "Glu");
            }
            else if (s.get(i).equals("GAG")) {
                s.set(i, "Glu");
            }
            // Gln (Glutamine)
            else if (s.get(i).equals("CAA")) {
                s.set(i, "Gln");
            }
            else if (s.get(i).equals("CAG")) {
                s.set(i, "Gln");
            }
            // Gly (Glycine)
            else if (s.get(i).equals("GGU")) {
                s.set(i, "Gly");
            }
            else if (s.get(i).equals("GGC")) {
                s.set(i, "Gly");
            }
            else if (s.get(i).equals("GGA")) {
                s.set(i, "Gly");
            }
            else if (s.get(i).equals("GGG")) {
                s.set(i, "Gly");
            }
            // His (Histidine)
            else if (s.get(i).equals("CAU")) {
                s.set(i, "His");
            }
            else if (s.get(i).equals("CAC")) {
                s.set(i, "His");
            }
            // Ile (Isoleucine)
            else if (s.get(i).equals("AUU")) {
                s.set(i, "Ile");
            }
            else if (s.get(i).equals("AUC")) {
                s.set(i, "Ile");
            }
            else if (s.get(i).equals("AUA")) {
                s.set(i, "Ile");
            }
            // Leu (Leucine)
            else if (s.get(i).equals("UUA")) {
                s.set(i, "Leu");
            }
            else if (s.get(i).equals("UUG")) {
                s.set(i, "Leu");
            }
            else if (s.get(i).equals("CUU")) {
                s.set(i, "Leu");
            }
            else if (s.get(i).equals("CUC")) {
                s.set(i, "Leu");
            }
            else if (s.get(i).equals("CUA")) {
                s.set(i, "Leu");
            }
            else if (s.get(i).equals("CUG")) {
                s.set(i, "Leu");
            }
            // Lys (Lysine)
            else if (s.get(i).equals("AAA")) {
                s.set(i, "Lys");
            }
            else if (s.get(i).equals("AAG")) {
                s.set(i, "Lys");
            }
            // Met (Méthionine)
            else if (s.get(i).equals("AUG")) {
                s.set(i, "Met");
            }
            // Phe (Phénylalanine)
            else if (s.get(i).equals("UUU")) {
                s.set(i, "Phe");
            }
            else if (s.get(i).equals("UUC")) {
                s.set(i, "Phe");
            }
            // Pro (Proline)
            else if (s.get(i).equals("CCU")) {
                s.set(i, "Pro");
            }
            else if (s.get(i).equals("CCC")) {
                s.set(i, "Pro");
            }
            else if (s.get(i).equals("CCA")) {
                s.set(i, "Pro");
            }
            else if (s.get(i).equals("CCG")) {
                s.set(i, "Pro");
            }
            // Pyl (Pyrrolysine)
            else if (s.get(i).equals("UAG")) {
                s.set(i, "Pyl");
            }
            // Sec (Sélénocystéine)
            else if (s.get(i).equals("UGA")) {
                s.set(i, "Sec");
            }
            // Ser (Sérine)
            else if (s.get(i).equals("UCU")) {
                s.set(i, "Ser");
            }
            else if (s.get(i).equals("UCC")) {
                s.set(i, "Ser");
            }
            else if (s.get(i).equals("UCA")) {
                s.set(i, "Ser");
            }
            else if (s.get(i).equals("UCG")) {
                s.set(i, "Ser");
            }
            else if (s.get(i).equals("AGU")) {
                s.set(i, "Ser");
            }
            else if (s.get(i).equals("AGC")) {
                s.set(i, "Ser");
            }
            // Thr (Thréonine)
            else if (s.get(i).equals("ACU")) {
                s.set(i, "Thr");
            }
            else if (s.get(i).equals("ACC")) {
                s.set(i, "Thr");
            }
            else if (s.get(i).equals("ACA")) {
                s.set(i, "Thr");
            }
            else if (s.get(i).equals("ACG")) {
                s.set(i, "Thr");
            }
            // Trp (Tryptophane)
            else if (s.get(i).equals("UGG")) {
                s.set(i, "Trp");
            }
            // Tyr (Tyrosine)
            else if (s.get(i).equals("UAU")) {
                s.set(i, "Tyr");
            }
            else if (s.get(i).equals("UAC")) {
                s.set(i, "Tyr");
            }
            // Val (Valine)
            else if (s.get(i).equals("GUU")) {
                s.set(i, "Val");
            }
            else if (s.get(i).equals("GUC")) {
                s.set(i, "Val");
            }
            else if (s.get(i).equals("GUA")) {
                s.set(i, "Val");
            }
            else if (s.get(i).equals("GUG")) {
                s.set(i, "Val");
            }
            // Fin (Marqueur)
            else if (s.get(i).equals("UAA")) {
                s.set(i, "Fin");
            }
        }

        return a;

    }

    // Enregistre dans une variable la deuxième chaîne de caractère entrée par l'utilisateur
    // et test sa validité selon les conditions du TP
    public static String lectureARN2() {

        // La variable b représente l'ARN2.
        String b;

        Scanner ARN2 = new Scanner(System.in);

        System.out.println("Veuillez entrer une chaîne de caractères représentant le deuxième ARN ...");
        b = ARN2.nextLine();

        if (b.matches("[ACGU]*") && !b.isEmpty() && b.length() % 3 == 0) {
            System.out.println();
        }
        else if (!b.matches("[ACGU]*")) {

            if (b.length() % 3 != 0) {
                System.err.println("La taille de la chaîne de caractères entrée n'est pas divisible par 3... Fin du programme.");
                System.err.println("La chaîne de caractères entrée n'est pas conforme... Elle ne doit contenir que les lettres 'A', 'C', 'G' et/ou 'U'. Fin du programme.");
                System.exit(-1);
            }
            else {
                System.err.println("La chaîne de caractères entrée n'est pas conforme... Elle ne doit contenir que les lettres 'A', 'C', 'G' et/ou 'U'. Fin du programme.");
                System.exit(-1);
            }
        }
        else if (b.isEmpty()) {
            System.err.println("Vous n'avez pas entrés aucune chaîne de caractères... Fin du programme.");
            System.exit(-1);
        }
        else if (b.length() % 3 != 0) {
            System.err.println("La taille de la chaîne de caractères entrée n'est pas divisible par 3... Fin du programme.");
            System.exit(-1);
        }
        else {
            System.err.println("Fail !");
            System.exit(-1);
        }

        // Variable pour divisé l'ARN en groupe de 3 (en codons)
        int limiteSize3 = 3;

        // Boucle afin de diviser le String input en groupe de 3 et les mettre dans un ArrayList
        for (int i = 0; i < b.length(); i += limiteSize3) {
            t.add(b.substring(i, Math.min(b.length(), i + limiteSize3)));
        }

        //Transformation Codons en acides aminés
        for (int i = 0; i < t.size(); i++) {

            // Ala (Alanine)
            if (t.get(i).equals("GCU")) {
                t.set(i, "Ala");
            }
            else if (t.get(i).equals("GCC")) {
                t.set(i, "Ala");
            }
            else if (t.get(i).equals("GCA")) {
                t.set(i, "Ala");
            }
            else if (t.get(i).equals("GCG")) {
                t.set(i, "Ala");
            }
            // Arg (Arginine)
            else if (t.get(i).equals("CGU")) {
                t.set(i, "Arg");
            }
            else if (t.get(i).equals("CGC")) {
                t.set(i, "Arg");
            }
            else if (t.get(i).equals("CGA")) {
                t.set(i, "Arg");
            }
            else if (t.get(i).equals("CGG")) {
                t.set(i, "Arg");
            }
            else if (t.get(i).equals("AGA")) {
                t.set(i, "Arg");
            }
            else if (t.get(i).equals("AGG")) {
                t.set(i, "Arg");
            }
            // Asn (Asparagine)
            else if (t.get(i).equals("AAU")) {
                t.set(i, "Asn");
            }
            else if (t.get(i).equals("AAC")) {
                t.set(i, "Asn");
            }
            // Asp (Aspartate)
            else if (t.get(i).equals("GAU")) {
                t.set(i, "Asp");
            }
            else if (t.get(i).equals("GAC")) {
                t.set(i, "Asp");
            }
            // Cys (Cystéine)
            else if (t.get(i).equals("UGU")) {
                t.set(i, "Cys");
            }
            else if (t.get(i).equals("UGC")) {
                t.set(i, "Cys");
            }
            // Glu (Glutamate)
            else if (t.get(i).equals("GAA")) {
                t.set(i, "Glu");
            }
            else if (t.get(i).equals("GAG")) {
                t.set(i, "Glu");
            }
            // Gln (Glutamine)
            else if (t.get(i).equals("CAA")) {
                t.set(i, "Gln");
            }
            else if (t.get(i).equals("CAG")) {
                t.set(i, "Gln");
            }
            // Gly (Glycine)
            else if (t.get(i).equals("GGU")) {
                t.set(i, "Gly");
            }
            else if (t.get(i).equals("GGC")) {
                t.set(i, "Gly");
            }
            else if (t.get(i).equals("GGA")) {
                t.set(i, "Gly");
            }
            else if (t.get(i).equals("GGG")) {
                t.set(i, "Gly");
            }
            // His (Histidine)
            else if (t.get(i).equals("CAU")) {
                t.set(i, "His");
            }
            else if (t.get(i).equals("CAC")) {
                t.set(i, "His");
            }
            // Ile (Isoleucine)
            else if (t.get(i).equals("AUU")) {
                t.set(i, "Ile");
            }
            else if (t.get(i).equals("AUC")) {
                t.set(i, "Ile");
            }
            else if (t.get(i).equals("AUA")) {
                t.set(i, "Ile");
            }
            // Leu (Leucine)
            else if (t.get(i).equals("UUA")) {
                t.set(i, "Leu");
            }
            else if (t.get(i).equals("UUG")) {
                t.set(i, "Leu");
            }
            else if (t.get(i).equals("CUU")) {
                t.set(i, "Leu");
            }
            else if (t.get(i).equals("CUC")) {
                t.set(i, "Leu");
            }
            else if (t.get(i).equals("CUA")) {
                t.set(i, "Leu");
            }
            else if (t.get(i).equals("CUG")) {
                t.set(i, "Leu");
            }
            // Lys (Lysine)
            else if (t.get(i).equals("AAA")) {
                t.set(i, "Lys");
            }
            else if (t.get(i).equals("AAG")) {
                t.set(i, "Lys");
            }
            // Met (Méthionine)
            else if (t.get(i).equals("AUG")) {
                t.set(i, "Met");
            }
            // Phe (Phénylalanine)
            else if (t.get(i).equals("UUU")) {
                t.set(i, "Phe");
            }
            else if (t.get(i).equals("UUC")) {
                t.set(i, "Phe");
            }
            // Pro (Proline)
            else if (t.get(i).equals("CCU")) {
                t.set(i, "Pro");
            }
            else if (t.get(i).equals("CCC")) {
                t.set(i, "Pro");
            }
            else if (t.get(i).equals("CCA")) {
                t.set(i, "Pro");
            }
            else if (t.get(i).equals("CCG")) {
                t.set(i, "Pro");
            }
            // Pyl (Pyrrolysine)
            else if (t.get(i).equals("UAG")) {
                t.set(i, "Pyl");
            }
            // Sec (Sélénocystéine)
            else if (t.get(i).equals("UGA")) {
                t.set(i, "Sec");
            }
            // Ser (Sérine)
            else if (t.get(i).equals("UCU")) {
                t.set(i, "Ser");
            }
            else if (t.get(i).equals("UCC")) {
                t.set(i, "Ser");
            }
            else if (t.get(i).equals("UCA")) {
                t.set(i, "Ser");
            }
            else if (t.get(i).equals("UCG")) {
                t.set(i, "Ser");
            }
            else if (t.get(i).equals("AGU")) {
                t.set(i, "Ser");
            }
            else if (t.get(i).equals("AGC")) {
                t.set(i, "Ser");
            }
            // Thr (Thréonine)
            else if (t.get(i).equals("ACU")) {
                t.set(i, "Thr");
            }
            else if (t.get(i).equals("ACC")) {
                t.set(i, "Thr");
            }
            else if (t.get(i).equals("ACA")) {
                t.set(i, "Thr");
            }
            else if (t.get(i).equals("ACG")) {
                t.set(i, "Thr");
            }
            // Trp (Tryptophane)
            else if (t.get(i).equals("UGG")) {
                t.set(i, "Trp");
            }
            // Tyr (Tyrosine)
            else if (t.get(i).equals("UAU")) {
                t.set(i, "Tyr");
            }
            else if (t.get(i).equals("UAC")) {
                t.set(i, "Tyr");
            }
            // Val (Valine)
            else if (t.get(i).equals("GUU")) {
                t.set(i, "Val");
            }
            else if (t.get(i).equals("GUC")) {
                t.set(i, "Val");
            }
            else if (t.get(i).equals("GUA")) {
                t.set(i, "Val");
            }
            else if (t.get(i).equals("GUG")) {
                t.set(i, "Val");
            }
            // Fin (Marqueur)
            else if (t.get(i).equals("UAA")) {
                t.set(i, "Fin");
            }
        }

        return b;

    }

    // Enregistre dans une variable la distance maximale entrée par l'utilisateur
    // et test sa validité selon les conditions du TP
    public static int distanceMaximumMetriqueInput() {

        Scanner distanceMaximum = new Scanner(System.in);

        System.out.println("Veuillez entrer la distance maximum utilisée pour le calcul de la métrique...");

        if (!distanceMaximum.hasNextInt()) {
            System.err.println("La valeur entrée n'est pas un entier...");
            System.exit(-1);
        }

        d = distanceMaximum.nextInt();

        if (d <= 0) {

            System.err.println("La valeur de la distance maximum utilisée dans le calcul de la métrique doit être supérieur à 0...");
            System.exit(-1);

        }

        return d;
    }

    // Compare les 2 ArrayLists d'acides aminés et conserve la déviation minimal
    // de chaque acides aminés des ArrayLists <code> s </code> par rapport à <code> t </code>
    public static void comparaisonAcidesIdentiquesEntreS_T() {

        for (int i = 0; i < s.size(); i++) {
            for (int j = 0; j < t.size(); j++) {
                if (s.get(i).equals(t.get(j))) {
                    int deviation = Math.abs(i-j);
                    if (mapS.containsKey(i)){
                        if (deviation < mapS.get(i)){
                            mapS.put(i, deviation);
                        }
                    }
                    else {
                        mapS.put(i, deviation);
                    }
                    if (d<mapS.get(i)){
                        mapS.put(i, d);
                    }
                }
            }
        }

        ArrayList<String> sClone = (ArrayList<String>)s.clone();
        sClone.removeAll(t);
        deviationInfiniS = (sClone.size()*d);

    }

    // Compare les 2 ArrayLists d'acides aminés et conserve la déviation minimal
    // de chaque acides aminés des ArrayLists <code> t </code> par rapport à <code> s </code>
    public static void comparaisonAcidesIdentiquesEntreT_S() {

        for (int i = 0; i < t.size(); i++) {
            for (int j = 0; j < s.size(); j++) {
                if (t.get(i).equals(s.get(j))) {
                    int deviation = Math.abs(i-j);
                    if (mapT.containsKey(i)){
                        if (deviation < mapT.get(i)){
                            mapT.put(i, deviation);
                        }
                    }
                    else {
                        mapT.put(i, deviation);
                    }
                    if (d<mapT.get(i)){
                        mapT.put(i, d);
                    }
                }
            }
        }

        ArrayList<String> tClone = (ArrayList<String>)t.clone();
        tClone.removeAll(s);
        deviationInfiniT = (tClone.size()*d);

    }

    // Effectue le calcul final de la métrique selon les données
    // entrées par l'utilisateur au début du programme
    public static void calculMetrique() {
        
        int deviationSommeTotal;
        double moyennePondere;
        double resultatPetiteFormule;

        // 7) somme deviation

        int deviationTotalS = 0;
        int deviationTotalT = 0;

        for (int valMapS : mapS.values()){
            deviationTotalS += valMapS;
        }

        for (int valMapT : mapT.values()){
            deviationTotalS += valMapT;
        }

        deviationSommeTotal = (deviationTotalS + deviationTotalT + deviationInfiniS + deviationInfiniT);

        // 8) ponderer

        n = s.size();
        m = t.size();

        double deviationSommeTotalDouble = deviationSommeTotal;
        double nDouble = n;
        double mDouble = m;
        double dDouble = d;

        moyennePondere = deviationSommeTotalDouble / ((m + n) * d);

        // 9) petit calcul

        resultatPetiteFormule = Math.pow(Math.E, (-6.0)*(moyennePondere*moyennePondere));

        // SORTIE FINAL
        System.out.println();
        System.out.println(resultatPetiteFormule);

    }

}









