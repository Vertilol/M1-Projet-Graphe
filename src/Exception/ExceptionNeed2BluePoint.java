package Exception;

/**
 * Created by Vladimir on 24/11/2015.
 */
public class ExceptionNeed2BluePoint extends Exception {
    public ExceptionNeed2BluePoint(String msg){
        super(msg);
    }
    public ExceptionNeed2BluePoint(){
        super("Il faut 2 point bleu dans la face");
    }
}
