import java.util.Scanner;

public class Chatbot {
    public static void main(String[] args) {
        String[] messageList = new String[100];
        boolean[] messageDone = new boolean[100];
        char[] messageType = new char[100];
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
                    System.out.print("[" + messageType[i] + "]");
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
                    System.out.println("[" + messageType[doneTask] + "]" + "[X] " + messageList[doneTask - 1]);
                    messageDone[doneTask - 1] = true;
                    continue;
                } else if (userMessage.substring(0, 4).equalsIgnoreCase("todo")) {
                    userMessage = userMessage.substring(5);
                    messageType[messageCount] = 'T';
                } else if (userMessage.substring(0, 8).equalsIgnoreCase("deadline")) {
                    int index = userMessage.indexOf("/by");
                    userMessage = userMessage.substring(9, index) + "(by: " + userMessage.substring(index + 4) + ")";
                    messageType[messageCount] = 'D';
                } else if (userMessage.substring(0, 5).equalsIgnoreCase("event")) {
                    int index = userMessage.indexOf("/from");
                    int index2 = userMessage.indexOf("/to");
                    userMessage = userMessage.substring(6, index) + "(from: " + userMessage.substring(index + 6, index2)
                        + "to: " + userMessage.substring(index2 + 4) + ")";
                    messageType[messageCount] = 'E';
                }
            }
            System.out.println("Added this task: " + "[" + messageType[messageCount] + "] " + userMessage);
            messageList[messageCount] = userMessage;
            messageCount++;
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
