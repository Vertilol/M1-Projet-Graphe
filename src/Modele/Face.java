package Modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clement on 17/10/2015.
 */
public class Face {
    String nom;
    List<Point> points = new ArrayList<Point>();
    static int cpt = 1;

    //renvoie vrai si on peut inclure le fragment dans la face
    public boolean faceCompatible(List<Point> fragment){
        for(Point p : fragment){
            if(p.planariteBleu() && ! this.points.contains(p)){
                return false;
            }
        }
        return true;
    }

    public Face() {
        this.nom = "F"+cpt;
        cpt++;
    }

    public Face(List<Point> points) {
        this.nom = "F"+cpt;
        this.points = points;
        cpt++;
    }

    public void addPoint(Point p){
        points.add(p);
    }

    public Face[] couperFace(List<Point> coupure){
        Point p1 = coupure.get(0);
        Point p2 = coupure.get(coupure.size()-1);

        List<Point> newCoupure = new ArrayList<Point>();

        for(int i = 1; i < coupure.size() - 1; i++){
            newCoupure.add(coupure.get(i));
        }

        Face[] faces = new Face[2];
        boolean rencontre = false;
        Face f1, f2;
        f1 = new Face();
        f2 = new Face();
        for(Point p : points){
            if(!rencontre){
                f1.addPoint(p);
                if(p.getNom().equals(p1.getNom()) || p.getNom().equals(p2.getNom())){
                    rencontre = true;
                    for(Point pC : newCoupure){
                        f1.addPoint(pC);
                        f2.addPoint(pC);
                    }
                    f2.addPoint(p);
                }
            } else {
                f2.addPoint(p);
                if(p.getNom().equals(p1.getNom()) || p.getNom().equals(p2.getNom())){
                    rencontre = false;
                    f1.addPoint(p);
                }
            }

        }
        faces[0] = f1;
        faces[1] = f2;
        return faces;
    }

    public String toString(){
        String s = "[ "+ nom + " : [ ";
        for(Point p : points){
            s+= p.getNom() + " , ";
        }
        s = s.substring(0,s.length()-2);
        s+= "] ]";
        return s;
    }
}
