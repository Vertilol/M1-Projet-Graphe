package Modele;

import Exception.ExceptionNoSuchPoint;

import java.util.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by Vladimir on 17/10/2015.
 */
public class Graphe {
    private List<Point> points;
    private List<Face> faces;

    public Graphe(int taille) {
        points = new ArrayList<Point>();
        for (int i = 0; i < taille; i++) {
            points.add(new Point(String.valueOf(i)));
        }
        faces = new ArrayList<Face>();
    }

    public Graphe() {
        points = new ArrayList<Point>();
        faces = new ArrayList<Face>();
    }

    public void setPoints(Point[] points) {
        for (Point p : points) {
            this.points.add(p);
        }

    }

    public List<Face> getFaces(){
        return faces;
    }


    public Point getPoint(int num) throws ExceptionNoSuchPoint {
        if (!containsPoint(num))
            throw new ExceptionNoSuchPoint();
        Point p = null;
        for (int i = 0; i < points.size(); i++) {
            if (Integer.parseInt(points.get(i).getNom()) == num) {
                p = points.get(i);
            }
        }
        return p;
    }

    public Point getPoint(String num) {
        try {
            int nb = Integer.parseInt(num);
            return getPoint(nb);
        } catch (Exception e) {
            System.out.println("Exception no such point !!");
        }
        return null;
    }


    private boolean containsPoint(int num) {
        boolean test = false;
        for (Point p : points) {
            if (Integer.parseInt(p.getNom()) == num) {
                test = true;
            }
        }
        return test;
    }

    public void addVoisin(int num, Point p) {
        try {
            getPoint(num).ajouterVoisin(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Point> getPoints() {
        return this.points;
    }

    public String toString() {
        String s = "";
        for (Point p : points) {
            s += p.getNom() + " : ";
            for (Point p2 : p.getVoisins().keySet()) {
                s += p2.getNom() + ":"+ p.getVoisins().get(p2) + ", ";
            }
            s = s.substring(0, s.length() - 2);
            s += "\n";
        }
        return s;
    }

    public void inialiserParcours() {

        for (Point p : points) {
            p.setEtatParcoursNonAtteint();
            p.setParent(null);
        }
    }

    public void addFace(Face f) {
        this.faces.add(f);
    }

    public void addPoint(Point p) {
        this.points.add(p);
    }

    //retourne liste des points étant placé dans une face
    public List<Point> getPointsInFace() {
        List<Point> lp = new ArrayList<Point>();
        for (Face f : this.faces) {
            for (Point p : f.points) {
                if (!lp.contains(p)) {
                    lp.add(p);
                }
            }
        }
        return lp;
    }

    //retourne liste de points n'étant pas dans une face et ayant un de leur voisins dans une  face
    public List<Point> getNonDegeneratedPoints() {
        List<Point> lp = new ArrayList<Point>();
        List<Point> pointFace = getPointsInFace();
        for (Point p : this.points) {
            if (!pointFace.contains(p)) {
                boolean hasNeighborInFace = false;
                for (Point pTest : p.getVoisins().keySet()) {
                    if (pointFace.contains(pTest)) {
                        hasNeighborInFace = true;
                        break;
                    }
                }
                if (hasNeighborInFace)
                    lp.add(p);
            }
        }
        return lp;
    }

    public int getTaille() {
        return points.size();
    }


    public static List<ArrayList<Point>> getFragments(Graphe gOld, Graphe gNew) {
        //on recupere les points du grapheOld qui ne sont pas dans gNew
        List<Point> listePoints = new ArrayList<Point>();
        for (Point p : gOld.points) {
            if (!gNew.containsPoint(p)) {
                listePoints.add(gOld.getPoint(p.getNom()));
            }
        }


        //on reforme les fragments
        List<ArrayList<Point>> fragments = new ArrayList<ArrayList<Point>>();
        while (listePoints.size() != 0) {
            List<Point> fragment = new ArrayList<Point>();
            Stack<Point> pile = new Stack<Point>();
            Point p = listePoints.get(0);
            listePoints.remove(0);
            pile.push(p);
            fragment.add(p);
            while (pile.size() != 0) {
                Point current = pile.pop();
                for (Point voisin : current.getVoisins().keySet()) {
                    if (listePoints.contains(voisin)) {
                        fragment.add(voisin);
                        pile.push(voisin);
                        listePoints.remove(voisin);
                    }
                }
            }
            fragments.add((ArrayList<Point>) fragment);

        }

        //on ajoute aux fragment, les points qui les lie avec le cycle
        for (ArrayList<Point> array : fragments) {
            for (Point p : gOld.points) {
                if (p.planariteBleu()) {
                    for (Point p2 : p.getVoisins().keySet()) {
                        if (!p2.planariteBleu()) {
                            if (array.contains(p2)) {
                                if (!array.contains(p)) {
                                    array.add(p);
                                }
                            }
                        }
                    }
                }
            }
        }

        //on recherche entre les point bleus si l'arete a été placé dans H
        for(Point p1 : gOld.getPoints()){
            if(p1.planariteBleu()){
                for(Point p2 : p1.getVoisins().keySet()){
                    if(p2.planariteBleu()){
                        if(! p1.getVoisins().get(p2)){
                            List<Point> entreBleu = new ArrayList<Point>();
                            entreBleu.add(p1);
                            entreBleu.add(p2);
                            fragments.add((ArrayList<Point>)entreBleu);
                        }
                    }
                }
            }
        }

        return fragments;
    }


    public boolean containsPoint(Point p) {
        for (Point p2 : this.points) {
            if (p2.equals(p))
                return true;
        }
        return false;
    }


    //cherche dans quelles faces on peut ajouter un fragment
    public HashMap<List<Point>, List<Face>> placementFragment(List<ArrayList<Point>> fragments) {
        //pour chaque face, on associe une liste de face dans lequel on peut mettre la face
        HashMap<List<Point>, List<Face>> placementsPossible = new HashMap<List<Point>, List<Face>>();
        //List<ArrayList<Point>> listeFragment = getFragments(oldG,newG);

        for (List<Point> fragment : fragments) {
            List<Face> listeFacesCompatible = new ArrayList<Face>();

            for (Face face : faces) {
                if (face.faceCompatible(fragment)) {
                    listeFacesCompatible.add(face);
                }
            }
            placementsPossible.put(fragment, listeFacesCompatible);
        }
        return placementsPossible;
    }

    public boolean containsPointNom(Point p){
        for(Point p2 : this.points){
            if(p2.getNom().equals(p.getNom())){
                return true;
            }
        }
        return false;
    }

    public void plongerChemin(List<Point> listPoint, Face face) throws Exception {
        Point oldPoint = null;
        for (Point p : listPoint) {
            if(!containsPointNom(p)){
                this.addPoint(new Point(p.getNom()));
            }
        }
        int cpt = Integer.parseInt(listPoint.get(0).getNom());
        int first = cpt;
        int last = Integer.parseInt(listPoint.get(listPoint.size() - 1).getNom());
        Point pOld = null;

        for (Point p : listPoint) {
            cpt = Integer.parseInt(p.getNom());
            if (cpt != first) {
                this.addVoisin(cpt, this.getPoint(Integer.parseInt(pOld.getNom())));
            }
            if(pOld != null){
                placementArrete(pOld,p);
            }
            pOld = p;
        }

        this.faces.remove(face);
        Face[] tabFace = face.couperFace(listPoint);
        this.faces.add(tabFace[0]);
        this.faces.add(tabFace[1]);
    }

    public void placementArrete(Point p1, Point p2){
        p1.setVoisinTrue(p2);
        p2.setVoisinTrue(p1);
    }
}
