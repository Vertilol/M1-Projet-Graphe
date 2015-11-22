package Tests;
import Modele.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clement on 17/10/2015.
 */
public class Test { // test commit
    public static void main(String[] args){
        Point p1 = new Point("1");
        Point p2 = new Point("2");
        Point p3 = new Point("3");
        Point p4 = new Point("4");
        Point p5 = new Point("5");
        Point p6 = new Point("6");

        List<Point> points = new ArrayList<Point>();

        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);

        Face f = new Face(points);

        System.out.println(f);

        Face[] faces = f.couperFace(p2, p5);

        Face f1 = faces[0];
        Face f2 = faces[1];

        System.out.println(f1);
        System.out.println(f2);
    }
}
