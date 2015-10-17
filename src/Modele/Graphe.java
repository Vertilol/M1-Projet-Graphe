package Modele;

 import Exception.ExceptionNoSuchPoint;

/**
 * Created by Vladimir on 17/10/2015.
 */
public class Graphe {
    private Point[] points;

    public Graphe(int taille){
        points = new Point[taille];
        for(int i = 0 ; i < taille ; i++){
            points[i] = new Point(String.valueOf(i));
        }
    }
    public Point getPoint (int num) throws ExceptionNoSuchPoint{
        if(num < 0 || num >= points.length)
            throw new ExceptionNoSuchPoint();
        return points[num];
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
        }
    }

}
