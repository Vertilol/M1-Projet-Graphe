package Modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clement on 17/10/2015.
 */
public class Face {
    String nom;
    List<Point> points = new ArrayList<Point>();

    public Face(String nom){
        this.nom = nom;
    }
}
