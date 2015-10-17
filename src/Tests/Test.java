package Tests;

/**
 * Created by Clement on 17/10/2015.
 */
public class Test {
    public static void main(String[] args){
        String s = "7: [3, 4, 5, 6]";
        String s2 = s.split(":")[1].trim();
        String s3 = s2.substring(1, s2.length()-1);
        System.out.println(s3);
    }
}
