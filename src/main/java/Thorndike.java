import exceptions.*;
import java.util.Scanner;
import java.util.Map;
import java.io.IOException;
import java.time.LocalDateTime;
import utility.DateTimeParser;
import utility.TextFileHandler;

public class Thorndike {
    private Scanner scanner;
    private TaskList taskList;
    private Boolean running;

    public Thorndike() {
        this.scanner = new Scanner(System.in);
        this.running = true;

        try {
            TextFileHandler.createFile(Storage.TASK_FILE_PATH);
            this.taskList = Storage.readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the chatbot.
     */
    public void start() {
        greet();
        while (running) {
            try {
                listen();
            } catch (ThorndikeException e) {
                echo(e.getMessage());
            }
        }
    }

    /**
     * Listens to command given to user.
     */
    private void listen() throws ThorndikeException {
        System.out.print(">> ");
        String input = scanner.nextLine();
        Map<String, String> args = CommandParser.parse(input);

        String command = args.get("command");
        String description = args.get("description");

        if (command.equals("list")) {
            list();
            return;
        }

        if (command.equals("bye")) {
            exit();
            return;
        }

        if (command.equals("mark")) {
            int idx = getIndexFromUser(description);
            markDone(idx);
            return;
        }
        if (command.equals("unmark")) {
            int idx = getIndexFromUser(description);
            markNotDone(idx);
            return;
        }

        if (command.equals("todo")) {
            if (description.equals("")) {
                throw new MissingDescriptionException("todo");
            }
            addTask(new Todo(description));
            return;
        }

        if (command.equals("deadline")) {
            if (description.equals("")) {
                throw new MissingDescriptionException("deadline");
            }

            LocalDateTime by = DateTimeParser.parse(args.get("by"));
            if (by == null) {
                throw new InvalidDateTimeFormat();
            }

            addTask(new Deadline(description, by));
            return;
        }

        if (command.equals("event")) {
            if (description.equals("")) {
                throw new MissingDescriptionException("event");
            }

            LocalDateTime from = DateTimeParser.parse(args.get("from"));
            LocalDateTime to = DateTimeParser.parse(args.get("to"));
            if (from == null || to == null) {
                throw new InvalidDateTimeFormat();
            }

            addTask(new Event(description, from, to));
            return;
        }

        if (command.equals("delete")) {
            int idx = getIndexFromUser(description);
            deleteTask(idx);
            return;
        }

        throw new InvalidCommandException();
    }

    /**
     * Marks a task as done.
     * 
     * @param idx Index of the task.
     * 
     */
    private void markDone(int idx) {
        echo("Meow! I've marked this task as done:");
        taskList.markDone(idx - 1);
        echo(taskList.getTask(idx - 1).toString());
        Storage.writeToFile(taskList);
    }

    /**
     * Marks a task as not done.
     * 
     * @param idx Index of the task.
     * 
     */
    private void markNotDone(int idx) {
        echo("Meow! I've marked this task as not done yet:");
        taskList.markNotDone(idx - 1);
        echo(taskList.getTask(idx - 1).toString());
        Storage.writeToFile(taskList);
    }

    /**
     * Adds task to list.
     * 
     * @param task The task.
     * 
     */
    private void addTask(Task task) {
        this.taskList.addTask(task);
        echo("Got it. I've added this task:");
        echo(task.toString());
        echo(String.format("Now you have %d tasks in the list.", taskList.size()));
        Storage.writeToFile(taskList);
    }

    /**
     * Deletes task in list.
     * 
     * @param index Index of task in the list.
     * 
     */
    private void deleteTask(int index) {
        Task deleted = taskList.deleteTask(index - 1);
        echo("Meow. I've removed this task:");
        echo(deleted.toString());
        echo(String.format("Now you have %d tasks in the list.", taskList.size()));
        Storage.writeToFile(taskList);
    }

    /**
     * Lists all items in the list.
     * 
     */
    private void list() {
        echo("Here are the tasks in your list:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            Task task = this.taskList.getTask(i - 1);
            echo(String.format("%d. %s", i, task.toString()));
        }
    }

    /**
     * Prints to terminal.
     * 
     * @param str The message to print.
     */
    private void echo(String str) {
        System.out.println("    " + str);
    }

    /**
     * Sends greetings to user.
     */
    private void greet() {
        echo("Meow! I'm Thorndike.");
        echo("What can I do for you?");
    }

    /**
     * Terminates the chatbot.
     */
    private void exit() {
        this.running = false;
        echo("Bye meow! Hope to see you again soon!");
    }

    /**
     * Gets index from a string.
     * 
     * @param idx String to parse index.
     */
    private int getIndexFromUser(String index) throws ThorndikeException {
        int idx = -1;

        try {
            idx = Integer.parseInt(index);
        } catch (Exception e) {
            throw new InvalidIndexException();
        }

        if (!taskList.hasIndex(idx - 1)) {
            throw new InvalidIndexException();
        }

        return idx;
    }

    public static void main(String[] args) {
        Thorndike chatbot = new Thorndike();
        chatbot.start();
    }
}