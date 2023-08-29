package duke;

import java.util.Scanner;

public class UI {
    private static String name;
    public UI(String name){
        UI.name = name;
    }
    public static void printline() {
        System.out.println("____________________________________________________________");
    }

    public void welcomeMessage() {
        printline();
        System.out.println(String.format("Hello I'm %s, your personal assistant.", UI.name));
        System.out.println("What can I do for you today, sir?");
        printline();
    }

    public String readCommand() {
        Scanner scanIn = new Scanner(System.in);
        String text = scanIn.nextLine();
        return text;
    }

    public void sendMessage(String message) {
        System.out.println(message);
    }

}
