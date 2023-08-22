import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final String chatbot = "War Room";
    private static Task[] userData = new Task[100];
    private static int index = 0;

    public static void main(String[] args) {
        System.out.println("Hello! I'm " + chatbot);
        System.out.println("What can I do for you?");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String user_input = scanner.nextLine();
            String[] words = user_input.split(" ", 2);
            if (words.length > 0 && Objects.equals(words[0], "mark")) {
                int referenceIndex = Integer.parseInt(words[1]);
                Task currentTask = userData[referenceIndex - 1];
                currentTask.isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + currentTask.getStatusIcon() + "]" + " " + currentTask.getDescription());
            } else if (words.length > 0 && Objects.equals(words[0], "unmark")) {
                int referenceIndex = Integer.parseInt(words[1]);
                Task currentTask = userData[referenceIndex - 1];
                currentTask.isDone = false;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + currentTask.getStatusIcon() + "]" + " " + currentTask.getDescription());
            } else if (Objects.equals(user_input, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else if (Objects.equals(user_input, "list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    Task currentTask = userData[i];
                    System.out.println((i+1) + "." + "[" + currentTask.getStatusIcon() + "]" + " " + currentTask.getDescription());
                }
            } else {
                Task newTask = new Task(user_input);
                userData[index] = newTask;
                index++;
                System.out.println("added: " + user_input);
            }
        }
    }
}
