import java.util.Scanner;
public class Richie {
    static private String CHATBOT_NAME = "Richie";
    public static void main(String[] args) {
        System.out.println("Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
        String message = input.next();
        while (!message.equals("bye")) {
            System.out.println(message);
            message = input.next();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
