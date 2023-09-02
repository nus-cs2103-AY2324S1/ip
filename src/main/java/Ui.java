import java.util.Scanner;

public class Ui {
    private static Scanner sc = new Scanner(System.in);

    public Ui() {
    }

    String getUserInput() {
       return sc.nextLine();
    }

    static void reply(String reply) {
        String botName = "Bareum: ";
        String fullReply = botName + reply;
        System.out.println(fullReply);
    }
    void showWelcomeMessage() {
        reply("Hello! I'm Bareum! What can I do for you? ^^");
    }

    void showGoodbyeMessage() {
        reply("Bye! Hope to see you again soon ^^");
    }

    void showLine() {
        System.out.println("________________________");
    }
}
