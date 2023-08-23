import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskMate {

    static String horizontalLine = "--------------------";
    static String chatbotName = "TaskMate";


    public static void main(String[] args) {

        // Greets user
        String greetMessage = "Hello I'm " + chatbotName + "\nWhat can I do for you?";
        printReply(greetMessage);

        // Echo user input
        Scanner sc = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < Task.getAllTasks().size(); i++) {
                    Task newTask = Task.getAllTasks().get(i);
                    System.out.println(Integer.toString(i+1) + ". " + newTask);
                }
            } else {
                Task newTask = new Task(userInput);
                printReply("added: " + userInput);
            }
        }


        // Farewell user
        String exitMessage = "Bye. Hope to see you again soon!";
        printReply(exitMessage);
    }

    static void printReply(String text) {
        System.out.println(horizontalLine);
        System.out.println(text);
        System.out.println(horizontalLine);
        System.out.println();
    }
}