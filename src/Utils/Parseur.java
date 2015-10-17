package Utils;

import java.io.*;
import Modele.Graphe;

/**
 * Created by Clement on 17/10/2015.
 */
public class Parseur {

    public static Graphe lectureFichierGraphe(File f) {
        Graphe g = null;
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(f));
            String line;
            int cpt = -1;
            while ((line = buffer.readLine()) != null) {
                if (cpt == -1) {
                    g = new Graphe(Integer.parseInt(line));
                } else {

                }
                cpt++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return g;
    }
}
