package Task3;

/**
 * @Author Jakub Szwedowicz
 * @Create 13.03.2021
 * @Version 1.0
 */
public class BottomOfStackException extends RuntimeException {
    BottomOfStackException(){
        super();
    }
    BottomOfStackException(String s){
        super(s);
    }
}
