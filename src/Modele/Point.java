package Modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 17/10/2015.
 */
public class Point {
    List<Point> voisins;

    public Point(){
        voisins = new ArrayList<Point>();

    }

    public void ajouterVoisin(Point p){
        voisins.add(p);
        p.getVoisins().add(this);
    }

    public List<Point> getVoisins() {
        return voisins;
    }
}
