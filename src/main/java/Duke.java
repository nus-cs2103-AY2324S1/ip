import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> items = new ArrayList<>();
    private static int itemsCount = 0;
    private static File taskListFile = null;

    public static void initialiseTaskListFile() {
        try {
            // Load existing file
            System.out.println("Trying to load existing task list in ./data/duke.txt...");
            File f = new File("./data/duke.txt");

            // Load file contents into items array
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                Task task = Task.createTaskFromEntry(s.nextLine());
                if (task != null) {
                    items.add(task);
                    itemsCount++;
                }
            }

            taskListFile = f;
            printTasks();
        } catch (FileNotFoundException e) {
            // No file, create new one
            System.out.println("No existing task list found, creating a new file ./data/duke.txt");

            try {
                File dataDir = new File("./data");
                if (!dataDir.exists()) {
                    dataDir.mkdir();
                }

                File f = new File("./data/duke.txt");
                f.createNewFile();
                System.out.println("File created.");

                taskListFile = f;
            } catch (IOException e1) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    public static void updateTaskListFile() {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            StringBuilder tasksString = new StringBuilder();
            for (int i = 0; i < itemsCount; i++) {
                String task = items.get(i).toTaskListEntry();
                tasksString.append(task + "\n");
            }

            fw.write(tasksString.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while updating file: " + e.getMessage());
        }
    }

    public static void addTask(Task t) {
        items.add(t);
        itemsCount++;
        updateTaskListFile();

        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + itemsCount + " task(s) in the list.");
    }

    public static void removeTask(Task t) {
        items.remove(t);
        itemsCount--;
        updateTaskListFile();

        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + itemsCount + " task(s) in the list.");
    }

    public static void markTask(Task t, boolean isDone) {
        if (isDone) {
            t.markAsDone();
        } else {
            t.markAsUndone();
        }
        updateTaskListFile();
    }

    public static void printTasks() {
        System.out.println("List of items:");
        for (int i = 0; i < itemsCount; i++) {
            int index = i + 1;
            System.out.println(index + "." + items.get(i));
        }
    }

    public static void main(String[] args) {
        // Greeting
        System.out.println("Hello! I'm Eepy 😸");

        // Initialise file
        initialiseTaskListFile();
        if (taskListFile == null) {
            // No file found / created
            return;
        }


        // Get input and store it
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("");
            String s = in.nextLine();
            System.out.println("You entered: " + s);

            try {
                if (s.equals("bye")) {
                    // Exit
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (s.equals("list")) {
                    // List out all items
                    printTasks();
                } else if (s.startsWith("mark")) {
                    // Mark item as done
                    try {
                        int index = Integer.parseInt(s.substring(4).trim()) - 1;
                        if (index <= 0 || index >= itemsCount) {
                            throw new DukeException("Index out of range!");
                        }

                        markTask(items.get(index - 1), true);
                    } catch (NumberFormatException e) {
                        throw new DukeException("Please enter a valid number!");
                    }
                } else if (s.startsWith("unmark")) {
                    // Mark item as done
                    try {
                        int index = Integer.parseInt(s.substring(6).trim()) - 1;
                        if (index <= 0 || index >= itemsCount) {
                            throw new DukeException("Index out of range!");
                        }
                        markTask(items.get(index - 1), false);
                    } catch (NumberFormatException e) {
                        throw new DukeException("Please enter a valid number!");
                    }
                } else if (s.startsWith("todo")) {
                    // New ToDo item
                    String name = s.substring(4).trim();
                    if (name.length() == 0) {
                        throw new DukeException("Description of todo cannot be empty!");
                    }
                    addTask(new ToDo(name));
                } else if (s.startsWith("deadline")) {
                    // New Deadline item
                    // Extract name and by
                    int byIndex = s.indexOf("/by");
                    if (byIndex == -1) {
                        // "/by" not found
                        throw new DukeException("Please include when the deadline is! (`deadline name /by date`)");
                    }

                    String name = s.substring(8, byIndex).trim();
                    String by = s.substring(byIndex + 3).trim();

                    if (name.equals("") || by.equals("")) {
                        // No name or deadline
                        throw new DukeException("Please include name and deadline!"
                                + "(`deadline name /by date (in yyyy-mm-dd)`)");
                    }

                    addTask(new Deadline(name, by));
                } else if (s.startsWith("event")) {
                    // New Event item
                    // Extract name, from and to
                    int fromIndex = s.indexOf("/from");
                    int toIndex = s.indexOf("/to");
                    if (fromIndex == -1 || toIndex == -1) {
                        // "/from" or "/to" not found
                        throw new DukeException("Please include when the event is from and to!"
                                + "(`event name /from date /to date`)");
                    }

                    String name = s.substring(5, fromIndex).trim();
                    String from = s.substring(fromIndex + 5, toIndex).trim();
                    String to = s.substring(toIndex + 3).trim();
                    if (name.equals("") || from.equals("") || to.equals("")) {
                        // No name, from or to
                        throw new DukeException("Please include the name of the event"
                                + "and when the event is from and to! (`event name /from date /to date`)");
                    }

                    addTask(new Event(name, from, to));
                } else if (s.startsWith("delete")) {
                    // Delete item
                    try {
                        int index = Integer.parseInt(s.substring(6).trim()) - 1;
                        if (index <= 0 || index >= itemsCount) {
                            throw new DukeException("Index out of range!");
                        }
                        removeTask(items.get(index - 1));
                    } catch (NumberFormatException e) {
                        throw new DukeException("Please enter a valid number!");
                    }
                } else {
                    throw new DukeException("I do not understand :(((");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
