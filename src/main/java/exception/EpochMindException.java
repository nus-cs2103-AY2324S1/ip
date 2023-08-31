package exception;

public class EpochMindException {

    protected String error;

    public EpochMindException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
