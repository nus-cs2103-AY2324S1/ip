import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rocket {
    public static void main(String[] args) {
        String LINE = "    ____________________________________________________________";
        // Initialise string variable to store command
        String input;
        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Create list
        List<Task> taskList = new ArrayList<>();
        System.out.println(LINE + "\n    Hello! I'm Rocket\n" +
                "    What can I do for you?\n" + LINE);
        input = scanner.nextLine();

        while (true) {
            // Split string
            int firstWordIndex = input.indexOf(' ');
            String command;
            String arguments = "";
            if (firstWordIndex == -1) {
                command = input;
            } else {
                command = input.substring(0, firstWordIndex);
                arguments = input.substring(firstWordIndex + 1);
            }
            try {
                switch (command) {
                    case "bye": {
                        System.out.println(LINE + "\n    Bye. Hope to see you again soon!\n" + LINE);
                        return;
                    }
                    case "list": {
                        System.out.println(LINE);
                        for (int i = 0; i < taskList.size(); i++) {
                            Task task = taskList.get(i);
                            System.out.println("    " + (i + 1) + "." + task);
                        }
                        System.out.println(LINE);
                        break;
                    }
                    case "mark": {
                        int taskNumber = Integer.parseInt(arguments) - 1;
                        Task task = taskList.get(taskNumber);
                        task.markAsDone();
                        System.out.println(LINE);
                        System.out.println("    Nice! I've marked this task as done:");
                        System.out.println("      " + task);
                        System.out.println(LINE);
                        break;
                    }
                    case "unmark": {
                        int taskNumber = Integer.parseInt(arguments) - 1;
                        Task task = taskList.get(taskNumber);
                        task.markAsUndone();
                        System.out.println(LINE);
                        System.out.println("    OK, I've marked this task as not done yet:");
                        System.out.println("      " + task);
                        System.out.println(LINE);
                        break;
                    }
                    /*case "delete": {
                        int taskNumber = Integer.parseInt(arguments) - 1;
                        taskList.remove(taskNumber);
                        System.out.println(LINE);
                        System.out.println("    OK, I've marked this task as not done yet:");
                        System.out.println("      " + task);
                        System.out.println(LINE);
                        break;
                    }*/
                    case "deadline": {
                        Deadline deadline = getDeadline(arguments);
                        taskList.add(deadline);
                        System.out.println(LINE);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      " + deadline);
                        System.out.println("    Now you have " + taskList.size() + " tasks in the list");
                        System.out.println(LINE);
                        break;
                    }
                    case "todo": {
                        if (arguments.isBlank()) {
                            throw new RocketIllegalArgumentException("The description of a todo cannot be empty.");
                        }
                        Todo todo = new Todo(arguments);
                        taskList.add(todo);
                        System.out.println(LINE);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      " + todo);
                        System.out.println("    Now you have " + taskList.size() + " tasks in the list");
                        System.out.println(LINE);
                        break;
                    }
                    case "event": {
                        Event event = getEvent(arguments);
                        taskList.add(event);
                        System.out.println(LINE);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      " + event);
                        System.out.println("    Now you have " + taskList.size() + " tasks in the list");
                        System.out.println(LINE);
                        break;
                    }
                    default: {
                        throw new RocketInvalidCommandException("I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (RocketException e) {
                System.out.println(LINE);
                System.out.println("     ☹ OOPS!!! " + e.getMessage());
                System.out.println(LINE);
            } finally {
                if (!input.equals("bye")) {
                    input = scanner.nextLine();
                }
            }
        }
    }

    private static Deadline getDeadline(String arguments) throws RocketException {
        int descriptionIndex = arguments.indexOf(" /by");
        String description = arguments.substring(0, descriptionIndex);
        if (description.isEmpty()) {
            throw new RocketIllegalArgumentException("The description of a deadline cannot be empty.");
        }
        String by = arguments.substring(descriptionIndex + 5);
        if (by.isEmpty()) {
            throw new RocketIllegalArgumentException("The deadline of a deadline cannot be empty");
        }
        Deadline deadline = new Deadline(description, by);
        return deadline;
    }

    private static Event getEvent(String arguments) throws RocketException{
        int descriptionIndex = arguments.indexOf(" /from");
        String description = arguments.substring(0, descriptionIndex);
        if (description.isEmpty()) {
            throw new RocketIllegalArgumentException("The description of an event cannot be empty.");
        }
        String duration = arguments.substring(descriptionIndex + 7);
        if (duration.isBlank()) {
            throw new RocketIllegalArgumentException("The duration of an event cannot be empty.");
        }
        int fromIndex = duration.indexOf(" /to");
        String from = duration.substring(0, fromIndex);
        String to = duration.substring(fromIndex + 5);
        Event event = new Event(description, from, to);
        return event;
    }
}
