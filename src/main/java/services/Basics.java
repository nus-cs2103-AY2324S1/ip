package services;

public class Basics {

    public static void greet() {
        String greetMessage = "At your service, sir.";
        Format.print(greetMessage);
    }

    public static void exit() {
        String exitMessage = "Shutting down...";
        Format.print(exitMessage);
    }

    public static void echo(String message) {
        Format.print(message);
    }
}
