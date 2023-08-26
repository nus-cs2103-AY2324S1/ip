import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

        try {
            loadTasks();
        } catch (DukeException exception) {
            printErrorMessage(exception.getMessage());
            printExit();
            return;
        }

        while (isRunning) {
            try {
                runCommand();
            } catch (DukeException exception) {
                printErrorMessage(exception.getMessage());
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
        } else if (isCommand(input, "clear")) {
            performClearCommand();
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

    private static void printErrorMessage(String message) {
        printMessage("☹ OOPS!!! " + message);
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

    private void printTaskAdded(Task task) {
        String[] message = {
                "Got it. I've added this task:",
                "\t" + task,
                "Now you have " + tasks.size() + " tasks in the list."
        };
        printMessage(message);
    }

    private void addToTasks(Task task) {
        tasks.add(task);
    }

    private void saveTasks() throws DukeException {

        try {
            File file = new File("./data/duke.txt");
            file.getParentFile().mkdir();

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks) {
                bufferedWriter.append((task.toSaveString()) + "\n");
            }
            bufferedWriter.close();

        } catch (IOException e) {
            throw new DukeException("There was an IOException while saving the tasks.");
        }
    }

    private void loadTasks() throws DukeException {
        try {
            File file = new File("./data/duke.txt");
            if (!file.exists()) {
                return;
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String saveString;
            while ((saveString = bufferedReader.readLine()) != null) {
                String[] saveStringArgs = saveString.split(" \\| ");
                String type = saveStringArgs[0];
                boolean isMarked = saveStringArgs[1].equals("1");
                String description = saveStringArgs[2];

                Task task;

                switch (type) {
                case "T":
                    task = new ToDo(description);
                    break;
                case "D":
                    String by = saveStringArgs[3];
                    LocalDate localBy = LocalDate.parse(by);
                    task = new Deadline(description, localBy);
                    break;
                case "E":
                    String from = saveStringArgs[3];
                    String to = saveStringArgs[4];
                    LocalDate localFrom = LocalDate.parse(from);
                    LocalDate localTo = LocalDate.parse(to);
                    task = new Event(description, localFrom, localTo);
                    break;
                default:
                    throw new DukeException("Invalid save data.");
                }

                addToTasks(task);
                if (isMarked) {
                    task.markAsDone();
                }
            }

        } catch (IOException e) {
            throw new DukeException("There was an IOException while loading the tasks.");
        }
    }

    private void performListCommand() {
        System.out.println(line);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, tasks.get(i));
        }

        System.out.println("\n" + line + "\n");
    }

    private void performClearCommand() throws DukeException {
        tasks = new ArrayList<>();
        saveTasks();
        printMessage("Got it. I've cleared all tasks.");
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

        saveTasks();
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

        saveTasks();
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

        saveTasks();
        printMessage(message);
    }

    private void performTodoCommand(String args) throws DukeException {
        if (args.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        ToDo toDo = new ToDo(args);
        addToTasks(toDo);
        saveTasks();
        printTaskAdded(toDo);
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

        LocalDate localBy;

        try {
            localBy = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("The dates must be filled in \"yyyy-mm-dd\" format.");
        }

        Deadline deadline = new Deadline(description, localBy);
        addToTasks(deadline);
        saveTasks();
        printTaskAdded(deadline);

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

        LocalDate localFrom, localTo;

        try {
            localFrom = LocalDate.parse(from);
            localTo = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("The dates must be filled in \"yyyy-mm-dd\" format.");
        }

        Event event = new Event(description, localFrom, localTo);
        addToTasks(event);
        saveTasks();
        printTaskAdded(event);
    }

    private static boolean isCommand(String text, String command) {
        return text.equals(command) || text.startsWith(command + " ");
    }

}
