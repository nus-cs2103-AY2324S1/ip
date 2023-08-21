package services;

public class Basics {

    public static void greet() {
        String greetMessage = "Hello! I'm Jarvis\n" +
                "What can I do for you?";
        System.out.println(Format.horizontalLine + greetMessage);
    }

    public static void exit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(Format.horizontalLine + exitMessage);
    }
}
