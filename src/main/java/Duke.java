import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static String LINE_SEPARATOR = "    ------------------------------------------------------------";

    private static <T> void respond(T message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(String.format("     %s",  message.toString()));
        System.out.println(LINE_SEPARATOR);
        System.out.print(">> ");
    }

    private static <T> void respond(List<T> messages) {
        System.out.println(LINE_SEPARATOR);
        for (T message: messages) {
            System.out.println(String.format("     %s",  message.toString()));
        }
        System.out.println(LINE_SEPARATOR);
        System.out.print(">> ");
    }
    
    private static void greet() {
        ArrayList<String> messages = new ArrayList<String>();
        messages.add("Hello! I'm A-CAT (Automated Chatbot Assistant for Tasks)");
        messages.add("What do you want to do today?");
        Duke.respond(messages);
    }

    private static void exit() {
        Duke.respond("Bye. Hope to see you again soon!");
    }

    private static void listTasks(ArrayList<Task> tasks) {
        ArrayList<String> output = new ArrayList<String>();
        for (int i = 0; i < tasks.size(); i++) {
            String taskOutput = String.format("%d. %s", i + 1, tasks.get(i));
            output.add(taskOutput);
        }
        Duke.respond(output);
    }

    public static void main(String[] args) {
        Duke.greet();

        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<Task>();

        Boolean programRunning = true;

        while (programRunning) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                programRunning = false;
            } else if (input.equals("list")) {
                Duke.listTasks(tasks);
            } else {
                Duke.respond("Added: " + input);
                tasks.add(new Task(input));
            }
        }

        scanner.close();
        Duke.exit();
    }
}
