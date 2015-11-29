package Modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vladimir on 17/10/2015.
 */


public class Point {
    private String nom;
    //List<Point> voisins;
    private Map<Point, Boolean> voisins;
    private ETAT etat;
    private PLANARITE planarite;
    private Point parent;

    private enum ETAT {NONATTEINT, VISITE, TRAITE}

    private enum PLANARITE {BLANC, BLEU}

    public Point(String nom) {
        this.nom = nom;
        voisins = new HashMap<Point, Boolean>();
        planarite = PLANARITE.BLANC;
    }

    public Point() {
        voisins = new HashMap<Point, Boolean>();
        etat = ETAT.NONATTEINT;
        planarite = PLANARITE.BLANC;
    }

    public void setVoisinTrue(Point p){
        voisins.put(p, true);
    }


    public void ajouterVoisin(Point p) {
        if (!this.voisins.keySet().contains(p)) {
            voisins.put(p, false);
            p.getVoisins().put(this, false);
        }
    }


    public List<Point> remonterParent() {
        List<Point> liste = new ArrayList<Point>();
        liste.add(this);
        Point p = this.parent;
        while (p != null) {
            liste.add(p);
            p = p.getParent();
        }
        return liste;
    }

    //retourne le premier cycle trouve en remontant les parents
    public List<Point> remonterCycle(Point pointCycle) {
        List<Point> liste = new ArrayList<Point>();
        liste.add(pointCycle);
        liste.add(this);
        liste.add(this.parent);
        Point p = this.parent;
        while (!p.getVoisins().keySet().contains(pointCycle)) {
            p = p.getParent();
            liste.add(p);
        }
        return liste;
    }

    //retourne tout les cycles trouve en remontant les parents
    public List<List<Point>> remonterCycles(Point pointCycle) {
        List<List<Point>> listeCycle = new ArrayList<List<Point>>();
        List<Point> liste = new ArrayList<Point>();
        liste.add(pointCycle);
        liste.add(this);
        //liste.add(this.parent);
        Point p = this;
        while (p.getParent() != null) {
            p = p.getParent();
            liste.add(p);
            if (p.getVoisins().keySet().contains(pointCycle)) {
                listeCycle.add(new ArrayList<Point>(liste));
            }
        }
        return listeCycle;
    }


    public boolean hasVoisinTrue(Point p){
        return this.voisins.get(p);
    }


    @Override
    public String toString() {
        return nom;
    }

    public boolean estVisite() {
        return (this.etat == ETAT.VISITE);
    }

    public boolean estNonAtteint() {
        return (this.etat == ETAT.NONATTEINT);
    }

    public boolean estTraite() {
        return (this.etat == ETAT.TRAITE);
    }


    public Point getParent() {
        return parent;
    }

    public void setParent(Point parent) {
        this.parent = parent;
    }

    public Map<Point, Boolean> getVoisins() {
        return voisins;
    }

    public void setEtatParcoursNonAtteint() {
        this.etat = ETAT.NONATTEINT;
    }

    public void setEtatParcoursVisite() {
        this.etat = ETAT.VISITE;
    }

    public void setEtatParcoursTraite() {
        this.etat = ETAT.TRAITE;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPlanariteBleu() {
        this.planarite = PLANARITE.BLEU;
    }

    public boolean planariteBleu() {
        return this.planarite == PLANARITE.BLEU;
    }

    public boolean planariteBlanc() {
        return this.planarite == PLANARITE.BLANC;
    }

    public PLANARITE getPlanarite() {
        return planarite;
    }


    public ETAT getEtat() {
        return etat;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        Point p = (Point) obj;
        return p.getNom().equals(this.getNom());
    }
}
