package Duke;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Duke {
    Scanner reader = new Scanner(System.in);
    ArrayList<Task> list = new ArrayList<>();
    int counter = -1;
    boolean isShuttingDown = false;
    File savedTasks = new File("./data/tasks.txt");
    String logo = " _           _        \n"
            + "| |    _   _| | _____ \n"
            + "| |   | | | | |/ / _ \\\n"
            + "| |___| |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public void startDuke() {
        System.out.println("Hi, I'm \n" + logo);
        loadTasklist();
    }
    public void loadTasklist() {
        try {
            Scanner loader = new Scanner(savedTasks);
            while (loader.hasNextLine()) {
                processInput(loader.nextLine());
            }
        } catch (FileNotFoundException e) {
            try {
                System.out.println("No file, creating one now.");
                savedTasks.createNewFile();
            } catch (IOException f) {
                System.out.println("Could not create file; shutting down");
                shutdownDuke();
            }
        } catch (InvalidVarException | InvalidCommandException e){
            throw new RuntimeException("Need to put a handler for corrupted inputs when loading");
        }

    }
    public void shutdownDuke() {
        System.out.println("Ok byeee\n");
        isShuttingDown = true;
    }
    public void processInput(String input) throws InvalidCommandException, InvalidVarException {
        if (input.equals("bye")) {
            shutdownDuke();
        } else if (input.equals("help")) {
            helpList();
        } else if (input.equals("list")) {
            listTasks();
        } else if (input.startsWith("delete ") || input.equals("delete")) {
            deleteTask(input);
        } else if (input.startsWith("mark ") || input.equals("mark")) {
            markTask(input);
        } else if (input.startsWith("unmark ") || input.equals("unmark")) {
            unmarkTask(input);
        } else if (input.startsWith("todo ") || input.equals("todo")) {
            processToDo(input);
        } else if (input.startsWith("event ") || input.equals("event")) {
            processEvent(input);
        } else if (input.startsWith("deadline ") || input.equals("deadline")) {
            processDeadline(input);
        } else {
            throw new InvalidCommandException("Unrecognized command");
        }
    }
    public void helpList() {
        System.out.println("Here's the guidelines for all functions.\n" +
                "list: lists all tasks\n" +
                "bye: ends bot\n" +
                "mark: mark (int x); marks indicated task\n" +
                "unmark: unmark (int x); unmarks indicated task\n" +
                "event: event (String name) /from (String start) /to (String end); creates event\n" +
                "todo: todo (String name); creates todo\n" +
                "deadline: deadline (String name) /by (String deadline); creates deadline\n"
        );
    }

    public void listTasks() {
        if (counter == -1) {
            System.out.println("No list, silly!");
        } else {
            System.out.println("Here's the list so far.");
            for (int i = 0; i < counter + 1; i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }
    }

    public void deleteTask(String input) throws InvalidVarException, InvalidCommandException {
        if (input.equals("delete")) {
            throw new InvalidCommandException("No parameter");
        }
        int number;
        try {
            number = Integer.parseInt(input.substring(7));
        } catch (Exception e) {
            throw new InvalidVarException("Task number could not be read");
        }
        try {
            Task deletedTask = list.get(number - 1);
            list.remove(number - 1);
            counter -= 1;
            System.out.println("task " + number + ": " + deletedTask + " deleted.");
        } catch (Exception e) {
            throw new InvalidVarException("Task not found");
        }

    }

    public void markTask(String input) throws InvalidVarException, InvalidCommandException {
        if (input.equals("mark")) {
            throw new InvalidCommandException("No parameter");
        }
        int number;
        try {
            number = Integer.parseInt(input.substring(5));
        } catch (Exception e) {
            throw new InvalidVarException("Task number could not be read");
        }
        try {
            list.get(number - 1).markDone();
        } catch (Exception e) {
            throw new InvalidVarException("Task not found");
        }
        System.out.println("task " + number + ": " + list.get(number - 1) + " marked as done.");
    }

    public void unmarkTask(String input) throws InvalidVarException,InvalidCommandException {
        if (input.equals("unmark")) {
            throw new InvalidCommandException("No parameter");
        }
        int number;
        try {
            number = Integer.parseInt(input.substring(7));
        } catch (Exception e) {
            throw new InvalidVarException("Task number could not be read");
        }
        try {
            list.get(number - 1).markUndone();
        } catch (Exception e) {
            throw new InvalidVarException("Task not found");
        }
        System.out.println("task " + number + ": " + list.get(number - 1) + " marked as not done.");
    }

    public void processToDo(String input) throws InvalidCommandException, InvalidVarException {
        if (input.equals("todo")) {
            throw new InvalidCommandException("No parameter");
        }

        if (input.length() < 6) {
            throw new InvalidVarException("No name!");
        }
        String name = input.substring(5);

        if (name.isBlank()) {
            throw new InvalidVarException("Blank name!");
        }
        ToDo task = new ToDo(name);
        addTask(task, input);
    }

    public void processEvent(String input) throws InvalidCommandException, InvalidVarException {
        if (input.equals("event")) {
            throw new InvalidCommandException("No parameter");
        }
        int split1 = input.indexOf("/from");
        int split2 = input.indexOf("/to");
        if (split1 == -1 || split2 == -1) {
            throw new InvalidCommandException("Some parameters missing");
        }
        if (split1 < 7 || split2 < split1 + 5 || input.length() < split2 + 4) {
            throw new InvalidVarException("Blank parameters!");
        }
        String name = input.substring(6, split1 - 1);
        LocalDate start = LocalDate.parse(input.substring(split1 + 6, split2 - 1));
        LocalDate end = LocalDate.parse(input.substring(split2 + 4));
        Event task = new Event(name, start, end);
        if (name.isBlank()) {
            throw new InvalidVarException("Blank parameters!");
        }
        addTask(task, input);
    }

    public void processDeadline(String input) throws InvalidVarException, InvalidCommandException {
        if (input.equals("deadline")) {
            throw new InvalidCommandException("No parameter");
        }
        int split = input.indexOf("/by");
        if (split == -1) {
            throw new InvalidCommandException("Deadline missing");
        }
        if (split < 10 || input.length() < split + 4) {
            throw new InvalidVarException("Blank parameters!");
        }
        String name = input.substring(9, split - 1);
        LocalDate deadline = LocalDate.parse(input.substring(split + 4));

        if (name.isBlank()) {
            throw new InvalidVarException("Blank parameters!");
        }
        Deadline task = new Deadline(name, deadline);
        addTask(task, input);
    }

    public void addTask(Task task, String input) {

        list.add(task);
        counter += 1;
        System.out.println("added: " + task.toString());
        System.out.println("current task count: " + (counter + 1));
        try {
            FileWriter taskSaver = new FileWriter("./data/tasks.txt");
            taskSaver.write(input);
            taskSaver.close();
        } catch (IOException e) {
            System.out.println("Error in saving to disk");
        }
    }

    public static void main(String[] args) {
        Duke luke = new Duke();
        luke.startDuke();
        while (!luke.isShuttingDown) {
            try {
                luke.processInput(luke.reader.nextLine());
            }
            catch (InvalidCommandException e) {
                System.out.println("Unknown command given; " + e.getMessage());
            }
            catch (InvalidVarException e) {
                System.out.println("Invalid input; " + e.getMessage());
            }
        }
    }
}
