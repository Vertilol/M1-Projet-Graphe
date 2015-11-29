package Utils;

/**
 * Created by Clement on 29/11/2015.
 */
public class Utilitaires {
    public static int random(int min, int max){
        max += 1;
        return (min + (int)(Math.random() * (max - min)));
    }
}
