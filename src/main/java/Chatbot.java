import java.util.Scanner;

public class Chatbot {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Hello! I'm Chatbot");
        System.out.println("What can I do for you?");
        while (true) {
            String userMessage = userInput.nextLine();
            if (userMessage.equalsIgnoreCase("bye")) break;
            System.out.println(userMessage);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
