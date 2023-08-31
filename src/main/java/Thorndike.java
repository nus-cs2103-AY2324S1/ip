import exceptions.*;
import java.util.Scanner;
import java.util.Map;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import utility.DateTimeParser;
import utility.TextFileHandler;

public class Thorndike {
    private Scanner scanner;
    private ArrayList<Task> taskList;
    private int index;
    private Boolean running;
    public static final String TASK_FILE_PATH = "data/tasks.txt";
    public static final String TASK_FILE_SEPARATOR = "-";

    public Thorndike() {
        this.scanner = new Scanner(System.in);
        this.taskList = new ArrayList<>();
        this.index = 0;
        this.running = true;

        try {
            TextFileHandler.createFile(TASK_FILE_PATH);
            readTasksFromFile();
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

            LocalDateTime by = DateTimeParser.parseDateTime(args.get("by"));
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

            LocalDateTime from = DateTimeParser.parseDateTime(args.get("from"));
            LocalDateTime to = DateTimeParser.parseDateTime(args.get("to"));
            if (from == null || to == null) {
                throw new InvalidDateTimeFormat();
            }

            addTask(new Event(description, args.get("from"), args.get("to")));
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
        taskList.get(idx - 1).setDone();
        echo(taskList.get(idx - 1).toString());
        writeTasksToFile();
    }

    /**
     * Marks a task as not done.
     * 
     * @param idx Index of the task.
     * 
     */
    private void markNotDone(int idx) {
        echo("Meow! I've marked this task as not done yet:");
        taskList.get(idx - 1).setNotDone();
        echo(taskList.get(idx - 1).toString());
        writeTasksToFile();
    }

    /**
     * Adds task to list.
     * 
     * @param task The task.
     * 
     */
    private void addTask(Task task) {
        this.taskList.add(task);
        this.index++;
        echo("Got it. I've added this task:");
        echo(task.toString());
        echo(String.format("Now you have %d tasks in the list.", index));
        writeTasksToFile();
    }

    /**
     * Deletes task in list.
     * 
     * @param index Index of task in the list.
     * 
     */
    private void deleteTask(int index) {
        Task deleted = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        this.index--;
        echo("Meow. I've removed this task:");
        echo(deleted.toString());
        echo(String.format("Now you have %d tasks in the list.", this.index));
        writeTasksToFile();
    }

    /**
     * Lists all items in the list.
     * 
     */
    private void list() {
        echo("Here are the tasks in your list:");
        for (int i = 1; i < this.index + 1; i++) {
            Task task = this.taskList.get(i - 1);
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

        if (idx < 1 || idx > this.index) {
            throw new InvalidIndexException();
        }

        return idx;
    }

    public void readTasksFromFile() {
        try {
            String[] lines = TextFileHandler.readLines(TASK_FILE_PATH);
            for (String line : lines) {
                String[] task = line.split(TASK_FILE_SEPARATOR);
                String taskType = task[2];
                String status = task[1];
                String description = task[0];

                if (taskType.equals("T")) {
                    addTaskSilent(new Todo(description));
                } else if (taskType.equals("D")) {
                    String time = task[3];
                    addTaskSilent(new Deadline(description, DateTimeParser.parseDateTime(time)));
                } else if (taskType.equals("E")) {
                    String from = task[3];
                    String to = task[4];
                    addTaskSilent(new Event(description, from, to));
                }

                if (status.equals("1")) {
                    markDoneSilent(this.index);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTasksToFile() {
        String output = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            output += task.getDescription();

            if (task.isDone()) {
                output += TASK_FILE_SEPARATOR + "1";
            } else {
                output += TASK_FILE_SEPARATOR + "0";
            }

            if (task instanceof Todo) {
                output += TASK_FILE_SEPARATOR + "T";
            } else if (task instanceof Deadline) {
                output += TASK_FILE_SEPARATOR + "D";
                Deadline deadline = (Deadline) task;
                output += TASK_FILE_SEPARATOR + deadline.getCompleteBy();
            } else if (task instanceof Event) {
                output += TASK_FILE_SEPARATOR + "E";
                Event event = (Event) task;
                output += TASK_FILE_SEPARATOR + event.getStartTime();
                output += TASK_FILE_SEPARATOR + event.getEndTime();
            }
            output += System.lineSeparator();
        }

        try {
            TextFileHandler.writeText(TASK_FILE_PATH, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTaskSilent(Task task) {
        this.taskList.add(task);
        this.index++;
    }

    private void markDoneSilent(int idx) {
        taskList.get(idx - 1).setDone();
    }

    public static void main(String[] args) {
        Thorndike chatbot = new Thorndike();
        chatbot.start();
    }
}