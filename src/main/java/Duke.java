import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that creates a chatbot.
 */
public class Duke {

    /** A list to keep track of the tasks. */
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static File f;

    public static void main(String[] args) {
        try {
            readFile();
        } catch (IOException e) {
            System.out.println("No File :(");
        }
        welcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String x = sc.nextLine();
                String[] stringList = x.split(" ", 2);
                String first = stringList[0];
                String second = null;
                try {
                    second = stringList[1];
                } catch (IndexOutOfBoundsException e) {
                    // do nothing
                }
                switch (first) {
                case "bye":
                    break;
                case "list":
                    printList();
                    break;
                case "mark":
                    markDone(second);
                    break;
                case "unmark":
                    markUndone(second);
                    break;
                case "todo":
                    addTodo(second);
                    break;
                case "deadline":
                    addDeadline(second);
                    break;
                case "event":
                    addEvent(second);
                    break;
                case "delete":
                    delete(second);
                    break;
                default:
                    throw new InvalidInputException("OOPS! I do not know what " + first + " means. Please try again :)");
                }
                if (x.equals("bye")) {
                    ending();
                    break;
                }
                try {
                    updateFile();
                } catch (IOException e) {
                    System.out.println("Couldn't write to file");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Prints out a welcome message.
     */
    private static void welcome() {
        System.out.println("Hello! I'm BoxBox\nWhat can I do for you?");
    }

    /**
     * Prints out a farewell message.
     */
    private static void ending() {
        System.out.println("Bye. Hope to see you again!");
    }

    /**
     * Adds a todo task into the list.
     *
     * @param x Details of the task.
     */
    private static void addTodo(String x) {
        if (x == null) {
            throw new LackDescriptionException("todo");
        }
        Todo t = new Todo(x);
        tasks.add(t);
        addedTask(x);
    }

    /**
     * Adds a deadline task into the list.
     *
     * @param x Details of the task.
     */
    private static void addDeadline(String x) {
        if (x == null || x.startsWith("/by") || x.startsWith(" /by")) {
            throw new LackDescriptionException("deadline");
        }
        String[] s = x.split(" /by ");
        String description = s[0];
        String deadline;
        try {
            deadline = s[1];
        } catch (IndexOutOfBoundsException e) {
            throw new LackInformationException("\"/by\"");
        }
        Deadline d = new Deadline(description, deadline);
        tasks.add(d);
        addedTask(description);
    }

    /**
     * Adds an event task into the list.
     *
     * @param x Details of the task.
     */
    private static void addEvent(String x) {
        if (x == null || x.startsWith("/from") || x.startsWith(" /from")) {
            throw new LackDescriptionException("event");
        }
        String[] s = x.split(" /from ");
        String description = s[0];
        String fromto;
        try {
            fromto = s[1];
        } catch (IndexOutOfBoundsException e) {
            throw new LackInformationException("\"/from\"");
        }
        if (fromto.startsWith("/to") || fromto.startsWith(" /to")) {
            throw new LackInformationException("\"/from\"");
        }
        String[] ft = fromto.split(" /to ");
        String from = ft[0];
        String to;
        try {
            to = ft[1];
        } catch (IndexOutOfBoundsException e) {
            throw new LackInformationException("\"/to\"");
        }
        Event e = new Event(description, from, to);
        tasks.add(e);
        addedTask(description);
    }

    private static void addedTask(String x) {
        System.out.println("Added to list: " + x);
        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the list");
    }

    /**
     * Marks the task as done.
     *
     * @param x Index of the target task.
     */
    private static void markDone(String x) {
        if (x == null) {
            throw new InvalidMarkingException("Missing index");
        }
        int j;
        try {
            j = Integer.parseInt(x);
        } catch (NumberFormatException e) {
            throw new InvalidMarkingException("Please provide a valid index");
        }
        if (j-1 > tasks.size()-1 || j-1<0) {
            throw new InvalidMarkingException("There is no corresponding task in the list");
        }
        Task t = tasks.get(j-1);
        t.markDone();
    }

    /**
     * Marks the task as undone.
     *
     * @param x Index of the target task.
     */
    private static void markUndone(String x) {
        if (x == null) {
            throw new InvalidMarkingException("Missing index");
        }
        int j;
        try {
            j = Integer.parseInt(x);
        } catch (NumberFormatException e) {
            throw new InvalidMarkingException("Please provide a valid index");
        }
        if (j-1 > tasks.size()-1 || j-1<0) {
            throw new InvalidMarkingException("There is no corresponding task in the list");
        }
        Task t = tasks.get(j-1);
        t.markUndone();
    }

    /**
     * Prints out the tasks in the list.
     */
    private static void printList() {
        if (tasks.isEmpty()) {
            System.out.println("list is empty :(");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + " " + tasks.get(i).toString());
            }
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param x Index of the target task.
     */
    private static void delete(String x) {
        if (x == null) {
            throw new InvalidMarkingException("Missing index");
        }
        int j;
        try {
            j = Integer.parseInt(x);
        } catch (NumberFormatException e) {
            throw new InvalidMarkingException("Please provide a valid index");
        }
        if (j-1 > tasks.size()-1) {
            throw new InvalidMarkingException("There is no corresponding task in the list");
        }
        Task t = tasks.get(j-1);
        tasks.remove(j-1);
        System.out.println("I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list");
    }

    /**
     * Reads in a file.
     */
    private static void readFile() throws IOException {
        String path = "src/main/java/";
        String fileName = "duke.txt";

        f = new File(path, fileName);
        if (f.createNewFile()) {
            return ;
        }
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] chars = s.split(" ");
            String type = chars[0];
            boolean isDone = chars[1].equals("1");
            Task t;
            switch (type) {
            case "[T]":
                t = new Todo(chars[2]);
                if (isDone) {
                    t.markDoneFromFile();
                }
                tasks.add(t);
                break;
            case "[D]":
                t = new Deadline(chars[2], chars[3]);
                if (isDone) {
                    t.markDoneFromFile();
                }
                tasks.add(t);
                break;
            case "[E]":
                t = new Event(chars[2], chars[3], chars[4]);
                if (isDone) {
                    t.markDoneFromFile();
                }
                tasks.add(t);
                break;
            }
        }
    }

    /**
     * Updates the file with the current list.
     */
    private static void updateFile() throws IOException {
        FileWriter fw = new FileWriter(f);
        for (Task t : tasks) {
            fw.write(t.toStringInFile() + "\n");
        }
        fw.close();
    }
}
