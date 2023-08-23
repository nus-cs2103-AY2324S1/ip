

public class KieraException extends RuntimeException{

    private static final String msg = "sorry, ";
    
    public KieraException(String e) {
        super(msg + e);
    }
    
}
