package Modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 17/10/2015.
 */
public class Point {
    String nom;
    List<Point> voisins;
    ETAT etat;
    PLANARITE planarite;

    public Point(String nom){
        this.nom = nom;
        voisins = new ArrayList<Point>();
    enum ETAT {NONATTEINT, VISITE, TRAITE}
    enum PLANARITE{ BLANC, BLEU}


    public Point(){
        voisins = new ArrayList<>();
        etat = ETAT.NONATTEINT;
        planarite = PLANARITE.BLANC;
    }

    public void ajouterVoisin(Point p){
        voisins.add(p);
        p.getVoisins().add(this);
    }

    public List<Point> getVoisins() {
        return voisins;
    }

    public void setEtatParcoursNonAtteint(){
        this.etat = ETAT.NONATTEINT;
    }
}
