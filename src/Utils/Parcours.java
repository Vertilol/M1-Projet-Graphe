package Utils;

import Modele.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Exception.*;

/**
 * Created by Vladimir on 24/11/2015.
 */
public class Parcours {

    public static void setVoisinNonAtteint(List<Point> array){
        for(Point p : array){
            p.setEtatParcoursNonAtteint();
            p.setParent(null);
        }
    }




}
