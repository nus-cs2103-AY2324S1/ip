import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final int INDENT_SIZE = 4;
    private static final String NAME = "Jimmy";
    private static final Path TASKS_CACHE_PATH = Path.of(".duke-cache");
    private static List<Task> tasks;

    private static String indent(String s) {
        return String.format("%s%s", " ".repeat(INDENT_SIZE), s);
    }

    private static void whisper(String... strings) {
        for (String s : strings) {
            System.out.println(indent(s));
        }
    }

    private static void say(String... strings) {
        whisper(strings);
        System.out.println();
    }

    private static void greet() {
        say(String.format("Hello! I'm %s", NAME), "What can I do for you?");
    }

    private static void farewell() {
        whisper("Bye. Hope to see you again soon!");
    }

    private static Command toCommand(String input) throws DukeException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means...");
        }
    }

    private static void list() {
        if (tasks.isEmpty()) {
            say("You have no tasks in your list!");
            return;
        }

        whisper("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String taskEntry = String.format("%d. %s", i + 1, tasks.get(i));
            whisper(indent(taskEntry));
        }
        say(String.format("You have %d %s in your list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks"));
    }

    private static void add(Task task) {
        tasks.add(task);
        say(
                "Got it. I've added this task:",
                indent(task.toString()),
                String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks")
        );
    }

    private static void addTodo(String details) throws DukeException {
        if (details.isEmpty()) {
            throw new DukeException("Todo description cannot be empty!");
        }

        Todo todo = new Todo(details);
        add(todo);
    }

    private static void addDeadline(String details) throws DukeException {
        String[] tokens = details.split(" /by ", 2);
        String description = tokens[0];

        if (description.isEmpty()) {
            throw new DukeException("Deadline description cannot be empty!");
        }
        if (tokens.length != 2) {
            throw new DukeException("Deadline due date cannot be empty!");
        }

        String by = tokens[1];
        Deadline deadline = new Deadline(description, by);
        add(deadline);
    }

    private static void addEvent(String details) throws DukeException {
        String[] tokens = details.split(" /from ", 2);
        String description = tokens[0];

        if (description.isEmpty()) {
            throw new DukeException("Event description cannot be empty!");
        }
        if (tokens.length != 2) {
            throw new DukeException("Event start and end dates cannot be empty!");
        }

        tokens = tokens[1].split(" /to ");
        String from = tokens[0];

        if (from.isEmpty()) {
            throw new DukeException("Event start date cannot be empty!");
        }
        if (tokens.length != 2) {
            throw new DukeException("Event end date cannot be empty!");
        }

        String to = tokens[1];
        Event event = new Event(description, from, to);
        add(event);
    }

    private static void mark(String index) throws DukeException {
        if (index.isEmpty()) {
            throw new DukeException("Missing task index!");
        }

        try {
            int idx = Integer.parseInt(index);

            if (idx < 1 || idx > tasks.size()) {
                throw new DukeException("Invalid task index!");
            }

            Task task = tasks.get(idx - 1);

            if (task.isDone) {
                throw new DukeException("Task has already been done!");
            }

            task.markAsDone();
            say("Nice! I've marked this task as done:", indent(task.toString()));
        } catch (NumberFormatException e) {
            throw new DukeException("Task index is not a number!");
        }
    }

    private static void unmark(String index) throws DukeException {
        if (index.isEmpty()) {
            throw new DukeException("Missing task index!");
        }

        try {
            int idx = Integer.parseInt(index);

            if (idx < 1 || idx > tasks.size()) {
                throw new DukeException("Invalid task index!");
            }

            Task task = tasks.get(idx - 1);

            if (!task.isDone) {
                throw new DukeException("Task has not been done yet!");
            }

            task.markAsUndone();
            say("OK, I've marked this task as not done yet:", indent(task.toString()));
        } catch (NumberFormatException e) {
            throw new DukeException("Task index is not a number!");
        }
    }

    private static void delete(String index) throws DukeException {
        if (index.isEmpty()) {
            throw new DukeException("Missing task index!");
        }

        try {
            int idx = Integer.parseInt(index);

            if (idx < 1 || idx > tasks.size()) {
                throw new DukeException("Invalid task index!");
            }

            Task task = tasks.get(idx - 1);
            tasks.remove(idx - 1);

            say(
                    "Noted. I've removed this task:",
                    indent(task.toString()),
                    "Now you have 3 tasks in the list."
            );
        } catch (NumberFormatException e) {
            throw new DukeException("Task index is not a number!");
        }
    }

    private static void load() {
        if (Files.notExists(TASKS_CACHE_PATH)) {
            say("No existing tasks found, initializing an empty task list...");
            tasks = new ArrayList<>();
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(TASKS_CACHE_PATH.toString());
                ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);

                tasks = (List<Task>) objInputStream.readObject();

                objInputStream.close();
                fileInputStream.close();
                say(String.format("Loaded existing tasks from %s", TASKS_CACHE_PATH));
            } catch (IOException | ClassNotFoundException e) {
                say(String.format("Something went wrong loading existing tasks from %s, initializing an empty task list...", TASKS_CACHE_PATH));
                tasks = new ArrayList<>();
                try {
                    Files.delete(TASKS_CACHE_PATH);
                } catch (IOException ignored) {
                }
            }
        }
    }

    private static void save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(TASKS_CACHE_PATH.toString());
            ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);

            objOutputStream.writeObject(tasks);

            objOutputStream.close();
            fileOutputStream.close();
        } catch (IOException ignored) {
        }
    }

    public static void main(String[] args) {
        load();
        greet();

        boolean shouldTerminate = false;
        Scanner sc = new Scanner(System.in);

        while (!shouldTerminate && sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            String[] tokens = input.split(" ", 2);

            try {
                Command command = toCommand(tokens[0]);
                String commandArgs = tokens.length > 1 ? tokens[1] : "";

                switch (command) {
                    case BYE:
                        farewell();
                        shouldTerminate = true;
                        break;
                    case LIST:
                        list();
                        break;
                    case TODO:
                        addTodo(commandArgs);
                        save();
                        break;
                    case DEADLINE:
                        addDeadline(commandArgs);
                        save();
                        break;
                    case EVENT:
                        addEvent(commandArgs);
                        save();
                        break;
                    case MARK:
                        mark(commandArgs);
                        save();
                        break;
                    case UNMARK:
                        unmark(commandArgs);
                        save();
                        break;
                    case DELETE:
                        delete(commandArgs);
                        save();
                        break;
                }
            } catch (DukeException e) {
                say(e.getMessage());
            }
        }
    }

    enum Command {
        BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE
    }
}
