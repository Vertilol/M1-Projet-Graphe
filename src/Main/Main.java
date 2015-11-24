package Main;

import Modele.Face;
import Modele.Graphe;
import Modele.Point;
import Utils.Cycle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clement on 17/10/2015.
 */
public class Main {
    public static void main(String[] args) {
        //Graphe g = Utils.Parseur.lectureFichierGraphe(args[0]);
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
            System.out.println(Graphe.getFragments(g, g2));
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
            System.out.println("Face : " + g2.getFaces());

            System.out.println("Graphe old : \n"+g);

        } catch (Exception e) {
            System.out.println("Pas de cycle lel");
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
