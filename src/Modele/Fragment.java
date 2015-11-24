package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import Exception.*;
import Utils.Parcours;

/**
 * Created by Vladimir on 24/11/2015.
 */
public class Fragment {

    //retourne un chemin d'un point bleu a un autre point bleu
    public static List<Point> getCheminFromFragment(List<Point> liste) throws Exception{
        Point source = null, destination = null;
        for(Point p : liste){
            if(p.planariteBleu()){
                if(source == null){
                    source = p;
                }
                else if(destination == null){
                    destination = p;
                }
            }
        }

        if(source == null || destination == null)
            throw new ExceptionNeed2BluePoint();
        return findPathFragment(liste, source, destination);
    }

    public static List<Point> findPathFragment(List<Point> array, Point source, Point destination) throws ExceptionNoPathFound {
        ArrayList<Point> chemin = new ArrayList<Point>();
        Parcours.setVoisinNonAtteint(array);
        Stack<Point> stack = new Stack<Point>();
        stack.push(source);
        while(stack.size() != 0){
            Point current = stack.pop();
            for(Point voisin : current.getVoisins().keySet()){
                if(voisin.estNonAtteint()) {
                    //on veut au moins 1 points qui n'a pas la planarité bleue
                    if(!(destination==voisin && current==source)) {
                        stack.push(voisin);
                        voisin.setParent(current);
                        voisin.setEtatParcoursVisite();
                    }
                }
            }
            current.setEtatParcoursTraite();
        }

        if(destination.getParent() == null)
            throw new ExceptionNoPathFound();
        return destination.remonterParent();
    }
}
