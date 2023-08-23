import java.util.Scanner;

public class Chatbot {
    public static void main(String[] args) {
        String[] messageList = new String[100];
        int messageCount = 0;
        Scanner userInput = new Scanner(System.in);
        System.out.println("Hello! I'm Chatbot!");
        System.out.println("What can I do for you?");
        while (true) {
            String userMessage = userInput.nextLine();
            if (userMessage.equalsIgnoreCase("bye")) break;
            if (userMessage.equalsIgnoreCase("list")) {
                for (int i = 0; i < messageCount; i++) {
                    int listNumber = i + 1;
                    System.out.print(listNumber + ". ");
                    System.out.println(messageList[i]);
                }
                continue;
            }
            System.out.println("Added: " + userMessage);
            messageList[messageCount] = userMessage;
            messageCount++;
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
