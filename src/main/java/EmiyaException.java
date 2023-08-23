public class EmiyaException extends Exception {
    public EmiyaException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "-----------------------------------------\n" +
                super.getMessage() + "\n"
                + "-----------------------------------------\n";
    }
}
