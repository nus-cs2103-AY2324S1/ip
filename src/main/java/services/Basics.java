package services;

public class Basics {

    public static void greet() {
        String greetMessage = "Hello! I'm Jarvis\n" +
                "What can I do for you?";
        Format.print(greetMessage);
    }

    public static void exit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        Format.print(exitMessage);
    }

    public static void echo(String message) {
        Format.print(message);
    }
}
