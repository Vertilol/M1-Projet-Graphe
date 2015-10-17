package Main;

import Modele.Graphe;

/**
 * Created by Clement on 17/10/2015.
 */
public class Main {
    public static void main(String[] args){
        Graphe g = Utils.Parseur.lectureFichierGraphe(args[0]);
        System.out.println(g);
    }
}
