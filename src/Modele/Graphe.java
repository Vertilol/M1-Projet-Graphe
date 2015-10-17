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
            points[i] = new Point();
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

}
