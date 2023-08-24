import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static String LINE_SEPARATOR = "    ----------------------------------------------------------------------";
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static Boolean programRunning = true;

    private static <T> void respond(T message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(String.format("     %s",  message.toString()));
        System.out.println(LINE_SEPARATOR);
        System.out.print(">>> ");
    }

    private static <T> void respond(List<T> messages) {
        System.out.println(LINE_SEPARATOR);
        for (T message: messages) {
            System.out.println(String.format("     %s",  message.toString()));
        }
        System.out.println(LINE_SEPARATOR);
        System.out.print(">>> ");
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

    private static void respondWithAddedTask(Task newTask) {
        ArrayList<String> messages = new ArrayList<String>();
        messages.add("Got it. I've added this task:");
        messages.add(String.format("  %s", newTask.toString()));
        messages.add(String.format("Now you have %d tasks in the list.", tasks.size()));
        Duke.respond(messages);
    }

    private static void addTodo(String description) {
        if (description.trim().equals("")) {
            Duke.respond("Please specify a description.");
            return;
        }

        Task newTodo = new Todo(description);
        tasks.add(newTodo);
        Duke.respondWithAddedTask(newTodo);
    }

    private static void addDeadline(String args) {
        String[] splitArguments = args.split("/");

        String description = splitArguments[0];
        String dueDateTime = null;

        for (int i = 1; i < splitArguments.length; i++) {
            if (splitArguments[i].startsWith("by ")) {
                dueDateTime = splitArguments[i].substring(3).trim();
            }
        }

        if (description.equals("")) {
            Duke.respond("Please specify a description.");
            return;
        }

        if (dueDateTime == null || dueDateTime.equals("")) {
            Duke.respond("Please specify a due date/time.");
            return;
        }

        Task newDeadline = new Deadline(description, dueDateTime);
        tasks.add(newDeadline);
        Duke.respondWithAddedTask(newDeadline);
    }

    private static void addEvent(String args) {
        String[] splitArguments = args.split("/");

        String description = splitArguments[0].trim();
        String startDatetime = null;
        String endDatetime = null;
        
        for (int i = 1; i < splitArguments.length; i++) {
            if (splitArguments[i].startsWith("from ")) {
                startDatetime = splitArguments[i].substring(5).trim();
            }

            if (splitArguments[i].startsWith("to ")) {
                endDatetime = splitArguments[i].substring(3).trim();
            }
        }

        if (description.equals("")) {
            Duke.respond("Please specify a description.");
            return;
        }
        
        if (startDatetime == null || startDatetime.equals("")) {
            Duke.respond("Please specify a start date/time.");
            return;
        }

        if (endDatetime == null|| endDatetime.equals("")) {
            Duke.respond("Please specify an end date/time.");
            return;
        }

        Task newEvent = new Event(description, startDatetime, endDatetime);
        tasks.add(newEvent);
        Duke.respondWithAddedTask(newEvent);
    }

    public static void main(String[] args) {
        Duke.greet();

        Scanner scanner = new Scanner(System.in);

        while (programRunning) {
            String input = scanner.nextLine();
            System.out.println();

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
            case "todo":
                Duke.addTodo(arguments);
                break;
            case "deadline":
                Duke.addDeadline(arguments);
                break;
            case "event":
                Duke.addEvent(arguments);
                break;
            default:
                Duke.respond(String.format("Command \"%s\" does not exist. Please type another command.", command));
            }
        }

        scanner.close();
        Duke.exit();
    }
}
