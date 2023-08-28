package pogo;

import pogo.storage.Storage;
import pogo.storage.TextStorage;
import pogo.tasks.*;
import pogo.tasks.exceptions.PogoEmptyTaskException;
import pogo.tasks.exceptions.PogoException;
import pogo.tasks.exceptions.PogoInvalidTaskException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pogo {
    private static List<Task> tasks = new ArrayList<>();
    private static final Storage storage = TextStorage.of();
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String QUIT_MESSAGE = "Bye. Hope to see you again soon!";

    private enum Action {
        BYE, LIST, MARK, UNMARK, DELETE, ADD
    }

    private static int parseTaskIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    private static void printTasks() {
        System.out.println("Here are the pogo.tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).getStatusMessage());
        }
    }

    private static Task addTask(String input) throws PogoException {
        TaskType taskType;
        if (input.startsWith("todo")) {
            taskType = TaskType.TODO;
        } else if (input.startsWith("deadline")) {
            taskType = TaskType.DEADLINE;
        } else if (input.startsWith("event")) {
            taskType = TaskType.EVENT;
        } else {
            throw new PogoInvalidTaskException();
        }

        Task task = null;
        int length;
        String[] split;
        String description;

        switch (taskType) {
        case TODO:
            length = "todo".length();
            if (input.length() == length) {
                throw new PogoEmptyTaskException();
            }
            description = input.substring("todo".length() + 1);
            task = new ToDo(description);
            break;
        case DEADLINE:
            length = "deadline".length();
            if (input.length() == length) {
                throw new PogoEmptyTaskException();
            }

            split = input.substring(length + 1).split(" /by ");
            description = split[0];
            String by = split[1];

            task = new Deadline(description, by);
            break;
        case EVENT:
            length = "event".length();
            if (input.length() == length) {
                throw new PogoEmptyTaskException();
            }

            split = input.substring(length + 1).split(" /from ");
            description = split[0];
            String[] temp = split[1].split(" /to ");
            String from = temp[0];
            String to = temp[1];

            task = new Event(description, from, to);
            break;
        }

        tasks.add(task);
        return task;
    }

    private static void printNumberOfTasks() {
        System.out.println("Now you have " + tasks.size() + " pogo.tasks in the list.");
    }

    private static boolean handleInput(String input) {
        Action action;
        if (input.equals("bye")) {
            action = Action.BYE;
        } else if (input.equals("list")) {
            action = Action.LIST;
        } else if (input.startsWith("mark")) {
            action = Action.MARK;
        } else if (input.startsWith("unmark")) {
            action = Action.UNMARK;
        } else if (input.startsWith("delete")) {
            action = Action.DELETE;
        } else {
            action = Action.ADD;
        }

        int index;
        Task task;
        switch (action) {
        case BYE:
            System.out.println(QUIT_MESSAGE);
            return true;
        case LIST:
            Pogo.printTasks();
            break;
        case MARK:
            index = Pogo.parseTaskIndex(input);
            task = tasks.get(index);

            if (task.isDone()) {
                System.out.println("This task is already done!");
            } else {
                tasks.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
            }

            System.out.println(tasks.get(index).getStatusMessage());
            break;
        case UNMARK:
            index = Pogo.parseTaskIndex(input);
            task = tasks.get(index);

            if (task.isDone()) {
                task.markAsUndone();
                System.out.println("Ok, I've marked this task as not done yet:");
            } else {
                System.out.println("This task is already not done!");
            }

            System.out.println(tasks.get(index).getStatusMessage());
            break;
        case DELETE:
            index = Pogo.parseTaskIndex(input);
            task = tasks.remove(index);

            System.out.println("Noted. I've removed this task:");
            System.out.println(task.getStatusMessage());
            Pogo.printNumberOfTasks();
            break;
        case ADD:
            try {
                task = Pogo.addTask(input);
                System.out.println("added: " + task.getStatusMessage());
                Pogo.printNumberOfTasks();
            } catch (PogoInvalidTaskException e) {
                System.out.println("Oops! I don't recognise that task.\n"
                        + "Only the following pogo.tasks are supported:\n"
                        + " - todo <description>\n"
                        + " - deadline <description> /by <date>\n"
                        + " - event <description> /from <date> /to <date>"
                );
            } catch (PogoEmptyTaskException e) {
                System.out.println("Oops! The description of a task cannot be empty.");
            } catch (PogoException e) {
                System.out.println("Oops! An unknown error has occurred.");

            }
            break;
        }
        storage.save(tasks);
        return false;
    }

    public static void main(String[] args) {
        try {
            tasks = storage.load();
            System.out.println("Loaded " + tasks.size() + " pogo.tasks from file.");
        } catch (IOException e) {
            System.out.println("Failed to load pogo.tasks from file.");
            return;
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm pogo.Pogo\nWhat can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            System.out.println(HORIZONTAL_LINE);

            boolean quit = Pogo.handleInput(input);
            if (quit) {
                break;
            } else {
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }
}
