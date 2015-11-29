package Main;

import Modele.Face;
import Modele.Fragment;
import Modele.Graphe;
import Modele.Point;
import Utils.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clement on 17/10/2015.
 */
public class Main {
    public static void main(String[] args) {
        //Graphe g = Utilitaires.Parseur.lectureFichierGraphe(args[0]);
        //testClone();
        Graphe g = Utils.Parseur.lectureFichierGraphe("Exemples/exemple0.graphe");
        System.out.println(g);
        try {
            System.out.println("Cycle " + Cycle.chercherCycle(g));
            Graphe g2 = Cycle.construireNouveauGraphe(g);
            System.out.println("Graphe nouveau :");
            System.out.println(g2);
            //System.out.println(Cycle.chercherCycles(g));
            System.out.println("Voici les fragments");
            List<ArrayList<Point>> fragments = Graphe.getFragments(g, g2);
            System.out.println(fragments);
            //System.out.println(Cycle.chercherCycle(g));
            System.out.println();
            System.out.println("Compatibilité des fragments :");
            System.out.println(g2.placementFragment(Graphe.getFragments(g, g2)));

            Face face1 = g2.placementFragment(Graphe.getFragments(g, g2)).get(Graphe.getFragments(g, g2).get(0)).get(1);
            List<Point> listPoint = Graphe.getFragments(g, g2).get(0);

            System.out.println();
            System.out.println("Graphe g2 avant : \n" + g2);
            System.out.println("Face : " + g2.getFaces());

            List<Point> test = new ArrayList<Point>();
            int cpt = 0;

            //2 = 0, 0 = 2, 3 = 1
            test.add(listPoint.get(2));
            test.add(listPoint.get(0));
            test.add(listPoint.get(3));

            g2.plongerChemin(test, face1);
            System.out.println("Graphe g2 après : \n" + g2);
            List<Face> listefaces = g2.getFaces();
            System.out.println("Face : " + listefaces);

            System.out.println("\n\n Chemins des fragments : ");
            for(ArrayList<Point> fragment : fragments){
                try {
                    System.out.println("Pour le fragment " + fragment + " on trouve le chemin " + Fragment.getCheminFromFragment(fragment));
                }
                catch (Exception e){
                    System.out.println("Exception pour le fragment " + fragment + " " + e.getMessage());
                }
            }

            System.out.println("Graphe old : \n"+g);

        } catch (Exception e) {
            System.out.println("Exception "+e.getMessage());
        }
    }


    public static void testClone() {
        List<Point> test = new ArrayList<Point>();
        Point p = new Point("rrr");
        test.add(p);
        List<Point> test2 = new ArrayList(test);
        p.setNom("ff");
        test2.add(new Point("aaa"));
        System.out.println(test + "  et " + test2);
    }
}
