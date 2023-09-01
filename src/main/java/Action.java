import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketOption;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * A class that encapsulates all actions the Bell Curve God can do.
 */
public class Action {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String LOGO = "\n" +
            "  ____       _ _    _____                        _____           _ \n" +
            " |  _ \\     | | |  / ____|                      / ____|         | |\n" +
            " | |_) | ___| | | | |    _   _ _ ____   _____  | |  __  ___   __| |\n" +
            " |  _ < / _ \\ | | | |   | | | | '__\\ \\ / / _ \\ | | |_ |/ _ \\ / _` |\n" +
            " | |_) |  __/ | | | |___| |_| | |   \\ V /  __/ | |__| | (_) | (_| |\n" +
            " |____/ \\___|_|_|  \\_____\\__,_|_|    \\_/ \\___|  \\_____|\\___/ \\__,_|";

    private static final String DATA_DIRECTORY_PATH = "./data";
    private static final String DATA_FILE_PATH = "./data/tasks.txt";

    /**
     * An ArrayList that stores all tasks entered by the user.
     */
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int numOfTasks = 0;

    /**
     * Greets the user by printing the greeting messages.
     */
    public static void greet() {
        System.out.println(LOGO);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Bell Curve God.");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Says goodbye to the user and exits.
     */
    public static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Respond to commands entered by the user,
     * and exits when the user types the command bye.
     */
    public static void respond() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String[] words = input.split(" ");

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                listTasks();
            } else if (words[0].equals("mark")) {
                mark(tasks.get(Integer.parseInt(words[1]) - 1));
            } else if (words[0].equals("unmark")) {
                unmark(tasks.get(Integer.parseInt(words[1]) - 1));
            } else if (words[0].equals("delete")) {
                delete(tasks.get(Integer.parseInt(words[1]) - 1));
            } else {
                try {
                    addTask(input);
                } catch (InvalidCommandException e) {
                    System.out.println(e.getMessage());
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        exit();
        sc.close();
    }

    /**
     * Adds a task to the storage.
     * @param input description of the task to be added
     */
    public static void addTask(String input) throws InvalidCommandException, EmptyDescriptionException {
        String cmd = input.split(" ")[0];
        Task newTask = null;

        if (!(cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event"))) {
            throw new InvalidCommandException(
                    HORIZONTAL_LINE + "\n" +
                    "You have entered an invalid command word!\n" +
                    "To add a new Task, use \"todo\", \" deadline\", or \"event\".\n" +
                    HORIZONTAL_LINE);
        }

        if (cmd.equals("deadline")) {
            newTask = Deadline.generateDeadlineFromInput(input);
        } else if (cmd.equals("event")) {
            newTask = Event.generateEventFromInput(input);
        } else {
            newTask = Todo.generateTodoFromInput(input);
        }

        tasks.add(newTask);
        numOfTasks++;
        saveTask(newTask);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * List all tasks stored.
     */
    public static void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Mark the given task as done.
     * @param task task to be marked as done.
     */
    public static void mark(Task task) {
        System.out.println(HORIZONTAL_LINE);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Mark the given task as not done.
     * @param task task to be marked as not done.
     */
    public static void unmark(Task task) {
        System.out.println(HORIZONTAL_LINE);
        task.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Deletes the task from the list.
     * @param task task to be deleted.
     */
    public static void delete(Task task) {
        tasks.remove(task);
        numOfTasks--;
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void saveTask(Task task) {
        try {
            appendToFile(DATA_FILE_PATH, task.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }


    public static void loadTasks(String filePath) throws IOException {
        try {
            readFile(filePath);
        } catch (FileNotFoundException e) {
            createMissingFile(filePath);
        }
    }

    public static void readFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            try {
                handleTaskData(sc.nextLine());
            } catch (WrongDataFormatException e) {
                System.out.println(e);
            }
        }
        sc.close();
    }

    public static void createMissingFile(String filePath) throws IOException {
        System.out.println("File not found!\nFile has been created!");
        File dir = new File(DATA_DIRECTORY_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(filePath);
        f.createNewFile();
    }

    public static void handleTaskData(String taskData) throws WrongDataFormatException {
        String[] words = taskData.split(" ");
        char taskType = words[0].charAt(1);
        Character status = words[0].charAt(4);
        boolean isDone = status.equals('X');

        switch (taskType) {
        case 'T':
            ArrayList<String> desWords = new ArrayList<>(Arrays.asList(words).subList(1, words.length));
            String des = String.join(" ", desWords);
            tasks.add(new Todo(des, isDone));
            numOfTasks++;
            break;
        case 'D':
            String[] desAndDdl = taskData.split("[()]");

            String[] frontWords = desAndDdl[0].split(" ");
            ArrayList<String> desWords2 = new ArrayList<>(Arrays.asList(frontWords).subList(1, frontWords.length));
            String des2 = String.join(" ", desWords2);

            String[] backWords = desAndDdl[1].split(" ");
            ArrayList<String> ddlWords = new ArrayList<>(Arrays.asList(backWords).subList(1, backWords.length));
            String ddl = String.join(" ", ddlWords);

            tasks.add(new Deadline(des2, ddl, isDone));
            numOfTasks++;
            break;
        case 'E':
            String[] desAndTime = taskData.split("[()]");

            String[] frontWords2 = desAndTime[0].split(" ");
            ArrayList<String> desWords3 = new ArrayList<>(Arrays.asList(frontWords2).subList(1, frontWords2.length));
            String des3 = String.join(" ", desWords3);

            String times = desAndTime[1];
            String[] fromTo = times.split("to: ");
            String fromTime = fromTo[0].split("from: ")[1];
            String toTime = fromTo[1];

            tasks.add(new Event(des3, fromTime, toTime, isDone));
            numOfTasks++;
            break;
        default:
            throw new WrongDataFormatException("The tasks stored in your local disk have wrong format!");
        }
    }

    public static void updateData() throws IOException {
        String data = "";
        for (Task t: tasks) {
            data += t.toString() + "\n";
        }
        writeToFile(DATA_FILE_PATH, data);
    }
}
