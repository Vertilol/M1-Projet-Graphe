package Exception;

/**
 * Created by Vladimir on 24/11/2015.
 */
public class ExceptionNoPathFound extends Exception{
    public ExceptionNoPathFound(){
        super("No path found");
    }
    public ExceptionNoPathFound(String msg){
        super(msg);
    }
}
