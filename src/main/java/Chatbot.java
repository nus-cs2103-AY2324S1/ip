import java.util.Scanner;

public class Chatbot {
    public static void main(String[] args) {
        String[] messageList = new String[100];
        boolean[] messageDone = new boolean[100];
        int messageCount = 0;
        Scanner userInput = new Scanner(System.in);
        System.out.println("Hello! I'm Chatbot!");
        System.out.println("What can I do for you?");
        while (true) {
            String userMessage = userInput.nextLine();
            if (userMessage.equalsIgnoreCase("bye")) break;
            if (userMessage.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < messageCount; i++) {
                    int listNumber = i + 1;
                    System.out.print(listNumber + ". ");
                    if (messageDone[i]) System.out.print("[X] ");
                    else System.out.print("[ ] ");
                    System.out.println(messageList[i]);
                }
                continue;
            }
            if (userMessage.length() >= 6) {
                if (userMessage.substring(0, 4).equalsIgnoreCase("mark")) {
                    int doneTask = Integer.parseInt(userMessage.substring(5));
                    System.out.println("Well done! This task has been marked as done.");
                    System.out.println("[X] " + messageList[doneTask - 1]);
                    messageDone[doneTask - 1] = true;
                    continue;
                }
            }
            System.out.println("Added: " + userMessage);
            messageList[messageCount] = userMessage;
            messageCount++;
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
