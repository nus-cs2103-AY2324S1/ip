package bareum;

import java.util.Scanner;

public class Ui {
    private static Scanner sc = new Scanner(System.in);

    public Ui() {
    }

    String getUserInput() {
       return sc.nextLine();
    }

    public static void reply(String reply) {
        String botName = "bareum.Bareum: ";
        String fullReply = botName + reply;
        System.out.println(fullReply);
    }
    public void showWelcomeMessage() {
        reply("Hello! I'm bareum.Bareum! What can I do for you? ^^");
    }

    public void showGoodbyeMessage() {
        reply("Bye! Hope to see you again soon ^^");
    }

    public void showLine() {
        System.out.println("________________________");
    }
}
