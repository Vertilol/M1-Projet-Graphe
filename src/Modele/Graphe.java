package Modele;

import Exception.ExceptionNoSuchPoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by Vladimir on 17/10/2015.
 */
public class Graphe {
    private Point[] points;
    private List<Face> faces;

    public Graphe(int taille) {
        points = new Point[taille];
        for (int i = 0; i < taille; i++) {
            points[i] = new Point(String.valueOf(i));
        }
        faces = new ArrayList<Face>();
    }

    public Graphe(){
        faces = new ArrayList<Face>();
    }

    public void setPoints(Point[] points){
        this.points = points;
    }

    public Point getPoint (int num) throws ExceptionNoSuchPoint{
        if(!containsPoint(num))
            throw new ExceptionNoSuchPoint();
        Point p = null;
        for(int i = 0; i<points.length;i++){
            if(Integer.parseInt(points[i].getNom()) == num){
                p = points[i];
            }
        }
        return p;
    }

    public Point getPoint(String num){
        try{
            int nb = Integer.parseInt(num);
            return getPoint(nb);
        }
        catch(Exception e){
            System.out.println("Exception no such point !!");
        }
        return null;
    }


    private boolean containsPoint(int num){
        boolean test = false;
        for(Point p : points){
            if(Integer.parseInt(p.getNom()) == num){
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


    public String toString(){
        String s = "";
        for(Point p : points){
            s += p.nom + " : ";
            for(Point p2 : p.getVoisins()){
                s+= p2.nom + ", ";
            }
            s = s.substring(0, s.length()-2);
            s += "\n";
        }
        return s;
    }

    public void inialiserParcours(){
        for(Point p : points){
            p.setEtatParcoursNonAtteint();
            p.setParent(null);
        }
    }

    public void addFace(Face f){
        this.faces.add(f);
    }

    //retourne liste des points étant placé dans une face
    public List<Point> getPointsInFace(){
        List<Point> lp = new ArrayList<Point>();
        for(Face f : this.faces){
            for(Point p : f.points){
                if(!lp.contains(p)){
                    lp.add(p);
                }
            }
        }
        return lp;
    }

    //retourne liste de points n'étant pas dans une face et ayant un de leur voisins dans une  face
    public List<Point> getNonDegeneratedPoints(){
        List<Point> lp = new ArrayList<Point>();
        List<Point> pointFace = getPointsInFace();
        for(Point p : this.points){
            if(! pointFace.contains(p)){
                boolean hasNeighborInFace = false;
                for(Point pTest : p.voisins){
                    if(pointFace.contains(pTest)){
                        hasNeighborInFace = true;
                        break;
                    }
                }
                if(hasNeighborInFace)
                    lp.add(p);
            }
        }
        return lp;
    }

    public int getTaille(){return points.length;}



    public static List<ArrayList<Point>> getFragments(Graphe gOld, Graphe gNew){
        //on recupere les points du grapheOld qui ne sont pas dans gNew
        List<Point> listePoints = new ArrayList<Point>();
        for(Point p : gOld.points){
            if(! gNew.containsPoint(p)){
                listePoints.add(gOld.getPoint(p.getNom()));
            }
        }


        //on reforme les fragments
        List<ArrayList<Point>> fragments = new ArrayList<ArrayList<Point>>();
        while(listePoints.size() != 0){
            List<Point> fragment = new ArrayList<Point>();
            Stack<Point> pile = new Stack<Point>();
            Point p = listePoints.get(0);
            listePoints.remove(0);
            pile.push(p);
            fragment.add(p);
            while(pile.size() != 0){
                Point current = pile.pop();
                for(Point voisin : current.voisins){
                    if(listePoints.contains(voisin)){
                        fragment.add(voisin);
                        pile.push(voisin);
                        listePoints.remove(voisin);
                    }
                }
            }
            fragments.add((ArrayList<Point>)fragment);

        }

        //on ajoute aux fragment, les points qui les lie avec le cycle
        for(ArrayList<Point> array : fragments) {
            for (Point p : gOld.points) {
                if (p.planariteBleu()) {
                    for (Point p2 : p.voisins) {
                        if(! p2.planariteBleu()) {
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

        return fragments;
    }



    public boolean containsPoint(Point p){
        for(Point p2 : this.points){
            if(p2.equals(p))
                return true;
        }
        return false;
    }

}
