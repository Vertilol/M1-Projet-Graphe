package Utils;

import Modele.Graphe;
import Modele.Point;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Clement on 29/11/2015.
 */
public class Sortie {
    public static void todot(Graphe g){
        String s = "graph system {\n";
        for(Point p : g.getPoints()){
            s += p.getNom()+" [label=\""+p.getNom()+"\"];\n";
        }

        boolean[][] arreteIn = new boolean[g.getTaille()][g.getTaille()];

        for(Point p : g.getPoints()){
            for(Point p2 : p.getVoisins().keySet()){
                if(!arreteIn[Integer.parseInt(p.getNom())][Integer.parseInt(p2.getNom())]){
                    arreteIn[Integer.parseInt(p.getNom())][Integer.parseInt(p2.getNom())] = true;
                    arreteIn[Integer.parseInt(p2.getNom())][Integer.parseInt(p.getNom())] = true;
                    s += p.getNom()+"--"+p2.getNom()+";\n";
                }
            }
        }

        s+= "}";
        try {
            FileWriter fw = new FileWriter("lol.dot");
            fw.write(s);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
