package Main;

import Modele.Face;
import Modele.Fragment;
import Modele.Graphe;
import Modele.Point;
import Utils.Cycle;
import Utils.Utilitaires;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Clement on 24/11/2015.
 */
public class AlgoPlanarite {
    public static boolean algoPlanarite(String fichier) throws Exception {
        List<ArrayList<Point>> fragments;
        HashMap<List<Point>, List<Face>> possibiliteFragments;
        List<Face> faces;
        List<Point> chemin;
        boolean maj = false;

        Graphe g = Utils.Parseur.lectureFichierGraphe(fichier);
        Graphe gNew = Cycle.construireNouveauGraphe(g);

        do { //tant que g a des fragments
            fragments = Graphe.getFragments(g, gNew);
            maj = false;
            possibiliteFragments = gNew.placementFragment(fragments);
            for (ArrayList<Point> fragment : fragments) {
                faces = possibiliteFragments.get(fragment);
                if (!maj) {
                    if (faces.isEmpty()) { //Fragment aucun face
                        return false;
                    } else if (faces.size() == 1) { //Fragment une face
                        //Construire chemin, plonger et maj face
                        chemin = Fragment.getCheminFromFragment(fragment);
                        gNew.plongerChemin(chemin, faces.get(0));
                        maj = true;
                    }
                }
            }
            if (!maj && !fragments.isEmpty()) {
                ArrayList<Point> fragment = fragments.get(Utilitaires.random(0, fragments.size() - 1));
                faces = possibiliteFragments.get(fragment);
                Face face = faces.get(Utilitaires.random(0, faces.size() - 1));
                chemin = Fragment.getCheminFromFragment(fragment);
                gNew.plongerChemin(chemin, face);
            }
        } while (!fragments.isEmpty());
        return true;
    }
}
