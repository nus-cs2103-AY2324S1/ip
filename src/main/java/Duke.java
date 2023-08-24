import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static String LINE_SEPARATOR = "    ------------------------------------------------------------";
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static Boolean programRunning = true;

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

    private static void markTaskAsDone(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            Duke.respond("That task does not exist. Please type another command.");
            return;
        }

        Task targetTask = tasks.get(taskIndex);
        targetTask.markAsDone();

        ArrayList<String> messages = new ArrayList<String>();
        messages.add("Nice! I've marked this task as done:");
        messages.add(String.format("  %s",targetTask.toString()));

        Duke.respond(messages);
    }

    private static void markTaskAsUndone(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            Duke.respond("That task does not exist. Please type another command.");
            return;
        }

        Task targetTask = tasks.get(taskIndex);
        targetTask.markAsUndone();

        ArrayList<String> messages = new ArrayList<String>();
        messages.add("OK, I've marked this task as not done yet:");
        messages.add(String.format("  %s",targetTask.toString()));

        Duke.respond(messages);
    }

    public static void main(String[] args) {
        Duke.greet();

        Scanner scanner = new Scanner(System.in);

        while (programRunning) {
            String input = scanner.nextLine();

            if (input.trim().equals("")) {
                continue;
            }

            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0];
            String arguments = splitInput.length > 1 ? splitInput[1] : "";

            switch(command) {
            case "bye":
                programRunning = false;
                break;
            case "list":
                Duke.listTasks(tasks);
                break;
            case "mark":
                Duke.markTaskAsDone(Integer.parseInt(arguments) - 1);
                break;
            case "unmark":
                Duke.markTaskAsUndone(Integer.parseInt(arguments) - 1);
                break;
            default:
                Duke.respond("Added: " + input);
                tasks.add(new Task(input));
            }
        }

        scanner.close();
        Duke.exit();
    }
}
