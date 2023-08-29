package services;

public class Ui {
    private final static String HORIZONTAL_LINE = "_________________________________________________________________________\n";

    public void exit() {
        String exitMessage = "Goodbye, sir.\n" + "Shutting down...";
        new Ui().print(exitMessage);
    }

    public void print(String message) {
        String messageWithHorizontalLine = HORIZONTAL_LINE + message + "\n" + HORIZONTAL_LINE;
        System.out.print(messageWithHorizontalLine);
    }

    public void greet() {
        String greetMessage = "At your service, sir.";
        print(greetMessage);
    }
}
