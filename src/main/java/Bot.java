import exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Main class for the bot.
 */
public class Bot {
    /** Regex pattern for mark commands. */
    private static final Pattern markPattern = Pattern.compile("mark -?\\d+");
    /** Regex pattern for unmark commands. */
    private static final Pattern unmarkPattern = Pattern.compile("unmark -?\\d+");
    /** Regex pattern for delete commands. */
    private static final Pattern deletePattern = Pattern.compile("delete -?\\d+");
    public static void main(String[] args) {
        ArrayList<Task> lst = new ArrayList<>();
        File dataDir = new File("./data");
        File f = new File("./data/bot.txt");
        try {
            if (dataDir.isDirectory() && f.isFile()) {
                Scanner scanner = new Scanner(f);
                populateList(scanner, lst);
            } else {
                System.out.println("No data found, creating...");
                dataDir.mkdir();
                f.createNewFile();
            }
        } catch (FileNotFoundException e) {
            // It shouldn't reach here.
            System.out.println(e.getMessage());
            return;
        } catch (IOException | SecurityException e) {
            System.out.println(e.getMessage());
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm the trash gremlin Caelus!\nWhat can I do for you?");
        while (true) {
            try {
                String str = sc.nextLine();
                if (str.equalsIgnoreCase("bye")) {
                    break;
                } else if (str.equalsIgnoreCase("list")) {
                    displayList(lst);
                } else if (str.startsWith("mark ")) {
                    markTask(str, lst);
                } else if (str.startsWith("unmark ")) {
                    unmarkTask(str, lst);
                } else if (Task.isTaskCommand(str)) {
                    addTask(str, lst);
                } else if (str.startsWith("delete ")) {
                    deleteTask(str, lst);
                } else {
                    throw new InvalidCommandException();
                }
                saveToFile(lst, f);
            } catch (BotException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Bye. I'll be at the nearest trash can!");
    }

    /**
     * Using a scanner with a data string on each line, add Tasks to the list provided.
     *
     * @param scanner Scanner object.
     * @param lst List to add tasks to.
     */
    private static void populateList(Scanner scanner, ArrayList<Task> lst) {
        while (scanner.hasNextLine()) {
            try {
                lst.add(Task.convertFromString(scanner.nextLine()));
            } catch (InvalidTaskException e) {
                System.out.println("Invalid task, skipping...");
            }
        }
    }

    /**
     * Prints the list to console. Throws an EmptyListException if the list is empty.
     *
     * @param lst List to print.
     * @throws exceptions.EmptyListException If the list is empty.
     */
    private static void displayList(ArrayList<Task> lst) throws EmptyListException {
        if (lst.size() == 0) {
            throw new EmptyListException();
        }
        StringBuilder out = new StringBuilder().append("Here are the tasks in your list:\n");
        Iterator<Task> iter = lst.iterator();
        int ctr = 1;
        while (iter.hasNext()) {
            out.append(ctr).append(". ").append(iter.next().toString()).append("\n");
            ctr++;
        }
        out.deleteCharAt(out.length() - 1);
        System.out.println(out);
    }

    /**
     * Prints the length of the list to console.
     *
     * @param lst List to print the length of.
     */
    private static void displayListLength(ArrayList<Task> lst) {
        System.out.println("Now you have " + lst.size() + " task(s) in the list.");
    }

    /**
     * Adds a task to the list and prints corresponding messages to the console.
     *
     * @param str Raw string to convert into a Task object.
     * @param lst List to add the task to.
     * @throws exceptions.InvalidTaskException If the task cannot be created from the string.
     */
    private static void addTask(String str, ArrayList<Task> lst) throws InvalidTaskException {
        Task newTask = Task.makeTask(str);
        System.out.println("Added:\n" + newTask.toString());
        lst.add(newTask);
        displayListLength(lst);
    }

    /**
     * Marks the task in the list and prints corresponding messages to the console.
     *
     * @param str Raw string containing the command to mark the task.
     * @param lst List the task is in.
     * @throws exceptions.InvalidIndexException If a task does not exist at that index.
     */
    private static void markTask(String str, ArrayList<Task> lst) throws InvalidIndexException {
        if (!markPattern.matcher(str).matches()) {
            throw new InvalidIndexException();
        }
        int index = Integer.parseInt(str.substring(5)) - 1;
        if (index < 0 || index >= lst.size()) {
            throw new InvalidIndexException();
        }
        lst.get(index).mark();
        System.out.println("I'll mark this as done:\n" + lst.get(index).toString());
    }

    /**
     * Unmarks a task in a list and prints corresponding messages to the console.
     *
     * @param str Raw string containing the command to unmark the task.
     * @param lst List the task is in.
     * @throws exceptions.InvalidIndexException If a task does not exist at that index.
     */
    private static void unmarkTask(String str, ArrayList<Task> lst) throws InvalidIndexException {
        if (!unmarkPattern.matcher(str).matches()) {
            throw new InvalidIndexException();
        }
        int index = Integer.parseInt(str.substring(7)) - 1;
        if (index < 0 || index >= lst.size()) {
            throw new InvalidIndexException();
        }
        lst.get(index).unmark();
        System.out.println("I'll mark this as not done:\n" + lst.get(index).toString());
    }

    /**
     * Deletes a task from the list and prints corresponding messages to the console.
     *
     * @param str Raw string containing the command to delete a task.
     * @param lst List to delete the task from.
     * @throws exceptions.InvalidIndexException If a task does not exist at that index.
     */
    private static void deleteTask(String str, ArrayList<Task> lst) throws InvalidIndexException {
        if (!deletePattern.matcher(str).matches()) {
            throw new InvalidIndexException();
        }
        int index = Integer.parseInt(str.substring(7)) - 1;
        if (index < 0 || index >= lst.size()) {
            throw new InvalidIndexException();
        }
        System.out.println("I've removed this task:\n" + lst.remove(index).toString());
        displayListLength(lst);
    }

    /**
     * Saves the list to the given file. Overwrites content of the file.
     *
     * @param lst List to save.
     * @param f File to save list to.
     */
    public static void saveToFile(ArrayList<Task> lst, File f) {
        if (lst.size() < 1) {
            return;
        }
        try {
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < lst.size() - 1; i++) {
                fw.write(lst.get(i).convertToDataString());
                fw.write(System.lineSeparator());
            }
            fw.write(lst.get(lst.size() - 1).convertToDataString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Can't write to file.");
        }
    }
}
