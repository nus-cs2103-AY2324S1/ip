public class MondayExceptions extends Exception {
    public MondayExceptions(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
