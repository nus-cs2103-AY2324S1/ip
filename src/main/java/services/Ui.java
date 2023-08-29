package services;

public class Ui {
    private final static String horizontalLine = "_________________________________________________________________________\n";

    public static void greet() {
        String greetMessage = "At your service, sir.";
        Ui.print(greetMessage);
    }

    public static void exit() {
        String exitMessage = "Goodbye, sir.\n" + "Shutting down...";
        Ui.print(exitMessage);
    }

    public static void print(String message) {
        String messageWithHorizontalLine = horizontalLine + message + "\n" + horizontalLine;
        System.out.print(messageWithHorizontalLine);
    }

    public static String format(String formattedMessage, Object... args) {
        String messageWithHorizontalLine = horizontalLine + formattedMessage + "\n" + horizontalLine;
        return String.format(messageWithHorizontalLine, args);
    }
}
