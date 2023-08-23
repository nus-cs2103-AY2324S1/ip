import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String name = "SoCrates";
    private static String line =
        "\t____________________________________________________________";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<>();
    private boolean isRunning = true;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private void run() {
        printWelcome();

        while (isRunning) {
            try {
                runCommand();
            } catch (DukeException exception) {
                printMessage("☹ OOPS!!! " + exception.getMessage());
            }
        }

        printExit();
    }

    private void runCommand() throws DukeException {
        String input = scanner.nextLine().trim();
        String args = input.contains(" ")
                ? input.split(" ", 2)[1]
                : "";

        if (isCommand(input, "bye")) {
            isRunning = false;
        } else if (isCommand(input, "list")) {
            performListCommand();
        } else if (isCommand(input, "todo")) {
            performTodoCommand(args);
        } else if (isCommand(input, "deadline")) {
            performDeadlineCommand(args);
        } else if (isCommand(input, "event")) {
            performEventCommand(args);
        } else if (isCommand(input, "mark")) {
            performMarkCommand(args);
        } else if (isCommand(input, "unmark")) {
            performUnmarkCommand(args);
        } else if (isCommand(input, "delete")) {
            performDeleteCommand(args);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }



    private static void printMessage(String message) {
        System.out.println(line);
        System.out.println("\t" + message);
        System.out.println("\n" + line + "\n");
    }

    private static void printMessage(String[] message) {
        System.out.println(line);
        for (String messageLine : message) {
            System.out.println("\t" + messageLine);
        }
        System.out.println("\n" + line + "\n");
    }

    private static void printWelcome() {
        String[] message = {
                "Hello! I'm " + name,
                "What can I do for you?"
        };

        printMessage(message);
    }

    private static void printExit() {
        printMessage("Bye. Hope to see you again soon!");
    }

    private void addToTasks(Task task) {
        tasks.add(task);
        String[] message = {
                "Got it. I've added this task:",
                "\t" + task,
                "Now you have " + tasks.size() + " tasks in the list."
        };
        printMessage(message);
    }

    private void performListCommand() {
        System.out.println(line);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, tasks.get(i));
        }

        System.out.println("\n" + line + "\n");
    }

    private void performMarkCommand(String args) throws DukeException {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(args);
        } catch (NumberFormatException exception) {
            throw new DukeException("The task number must be specified.");
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("No such task exists.");
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();

        String[] message = {
                "Nice! I've marked this task as done:",
                "\t " + task
        };

        printMessage(message);
    }

    private void performUnmarkCommand(String args) throws DukeException {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(args);
        } catch (NumberFormatException exception) {
            throw new DukeException("The task number must be specified.");
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("No such task exists.");
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsUndone();

        String[] message = {
                "Ok, I've marked this task as not done yet:",
                "\t " + task
        };

        printMessage(message);
    }

    private void performDeleteCommand(String args) throws DukeException {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(args);
        } catch (NumberFormatException exception) {
            throw new DukeException("The task number must be specified.");
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("No such task exists.");
        }

        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);

        String[] message = {
                "Noted. I've removed this task:",
                "\t " + task
        };

        printMessage(message);
    }

    private void performTodoCommand(String args) throws DukeException {
        if (args.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        ToDo toDo = new ToDo(args);
        addToTasks(toDo);
    }

    private void performDeadlineCommand(String args) throws DukeException {
        if (args.startsWith("/by")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!args.contains(" /by ")) {
            throw new DukeException("The deadline must be specified.");
        }

        String by = args.split(" /by ", 2)[1];
        if (by.isEmpty()) {
            throw new DukeException("The deadline cannot be empty.");
        }

        String description = args.replaceFirst(" /by " + by, "");

        Deadline deadline = new Deadline(description, by);
        addToTasks(deadline);
    }

    private void performEventCommand(String args) throws DukeException {
        if (args.startsWith("/from")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!args.contains(" /from ") || !args.contains(" /to ")) {
            throw new DukeException("The from and to must be specified.");
        }

        String to = args.split(" /to ", 2)[1];
        if (to.isEmpty()) {
            throw new DukeException("The to cannot be empty.");
        }
        args = args.replaceFirst(" /to " + to, "");

        String from = args.split(" /from ", 2)[1];
        if (from.isEmpty()) {
            throw new DukeException("The from cannot be empty.");
        }

        String description = args.replaceFirst(" /from " + from, "");

        if (description.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        Event event = new Event(description, from, to);
        addToTasks(event);
    }



    private static boolean isCommand(String text, String command) {
        return text.equals(command) || text.startsWith(command + " ");
    }


}
