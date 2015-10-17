package Modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 17/10/2015.
 */
public class Graphe {
    private Point[] points;

    public Graphe(int taille){
        points = new Point[taille];
        for(int i = 0 ; i < taille ; i++){
            points[i] = new Point();
        }
    }
    public Point getPoint (int num) throws Exception{
        if(num < 0 || num >= points.length)
            throw new Exception("No such point");
        return points[num];
    }

}
