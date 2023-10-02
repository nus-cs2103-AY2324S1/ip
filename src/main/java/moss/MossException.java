package moss;

public class MossException extends Exception {
    public MossException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
