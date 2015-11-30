package Tests;
import Modele.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clement on 17/10/2015.
 */
public class Test {
    public static void main(String[] args){
        System.out.println(random(0, 1));
    }

    public static int random(int min, int max){
        max += 1;
        return (min + (int)(Math.random() * (max - min)));
    }
}
