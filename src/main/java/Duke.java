import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.Scanner;

/**
 * @author Donovan Chan Jia Jun
 */
public class Duke {
    /**
     * Temporary data storage to store user text.
     */
    static ArrayList<Task> storage;
    static final String dir = "/data";
    public static final String outputPath = System.getProperty("user.dir") + dir + "/ipOutput.txt";
    static FileWriter writer;
    static String line = "---------------------------------------------------------------------------------------------";

    /**
     * Starting point of the bot.
     * Says hello - Carries out data storage for user text - Says goodbye
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        // Initialise the data storage
        Duke.storage = new ArrayList<>();
        String chatbotName = "notDuke";
        String introMessage = "Hello! I'm " + chatbotName + "\n"
                        + "What can I do for you?";
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.printf("%s\n%s\n%s\n", Duke.line, introMessage, Duke.line);
        try {
            LoadOutputFile(outputPath);
            Duke.echo();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
        System.out.printf("%s\n%s\n", exitMessage, Duke.line);
    }

    /**
     * Creates the tasks based on String input.
     *
     * @param input String input by user
     * @return Task Can be Events, Deadlines, Todos
     * @throws Exception
     */
    public static Task createTask(String input) throws Exception {
        // Splits based on white spaces, identifies based on the relevant /...
        String[] arrStrings = input.split("\\s+");
        String command = arrStrings[0];
        String name = "";
        if (command.equals("deadline")) {
            String deadline = "";
            boolean completedName = false;
            for (int i = 1; i < arrStrings.length; i ++) {
                if (arrStrings[i].equals("/by")) {
                    completedName = true;
                    continue;
                }
                if (completedName) {
                    deadline += arrStrings[i] + " ";
                } else {
                    name += arrStrings[i] + " ";
                }
            }
            return new Deadlines(name.substring(0, name.length() - 1), deadline.substring(0, deadline.length() - 1));
        } else if (command.equals("todo")) {
            if (arrStrings.length == 1) {
                throw new Exception("OOPS!!! The description of a todo cannot be empty.");
            }
            for (int i = 1; i < arrStrings.length; i ++) {
                name += arrStrings[i] + " ";
            }
            return new Todos(name.substring(0, name.length() - 1));
        } else if (command.equals("event")) {
            String from = "";
            String to = "";
            boolean completedName = false;
            boolean completedFrom = false;
            for (int i = 1; i < arrStrings.length; i ++) {
                if (arrStrings[i].equals("/from")) {
                    completedName = true;
                } else if (arrStrings[i].equals("/to")) {
                    completedFrom = true;
                } else if (!completedFrom && completedName) {
                    from += arrStrings[i] + " ";
                } else if (completedFrom && completedName) {
                    to += arrStrings[i] + " ";
                } else {
                    name += arrStrings[i] + " ";
                }
            }
            return new Events(name.substring(0, name.length() - 1), from.substring(0, from.length() - 1), to.substring(0, to.length() - 1));
        } else {
            throw new Exception("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Handles and stores user inputs.
     * When arraylist changes, the entire output file is overwritten and all contents is transferred over
     */
    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (!Duke.outputPath.equals("")) {
                try {
                    Duke.writer = new FileWriter(outputPath, false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("File path could not be resolved");
            }
            // Splits the input based on whitespaces.
            String command = userInput.split("\\s+")[0];
            int choice = -1;
            switch (command) {
            case "list":
                int counter = 0;
                System.out.println("Here are the tasks in your list:");
                for (Task task : Duke.storage) {
                    counter ++;
                    System.out.printf("%d.%s\n", counter, task.toString());
                }
                break;
            case "mark":
                choice = Integer.parseInt(userInput.split("\\s+")[1]);
                Duke.storage.get(choice - 1).markDone();
                System.out.printf("Nice! I've marked this task as done:\n" +
                        "  %s\n", Duke.storage.get(choice - 1).toString());
                break;
            case "unmark":
                choice = Integer.parseInt(userInput.split("\\s+")[1]);
                Duke.storage.get(choice - 1).markUndone();
                System.out.printf("OK, I've marked this task as not done yet:\n" +
                        "  %s\n", Duke.storage.get(choice - 1).toString());
                break;
            case "delete":
                choice = Integer.parseInt(userInput.split("\\s+")[1]);
                Task removedTask = Duke.storage.remove(choice - 1);
                System.out.printf("Noted. I've removed this task:\n" +
                                    "  %s\n" +
                                    "Now you have %d tasks in the list.\n"
                                    , removedTask.toString(), Duke.storage.size());
                break;
            default:
                Task task = null;
                try {
                    task = createTask(userInput);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (task != null) {
                    Duke.storage.add(task);
                    System.out.printf("Got it. I've added this task:\n" +
                            "  %s\n" +
                            "Now you have %d tasks in the list.\n", task.toString(), Duke.storage.size());
                }
            }
            Duke.updateTasks();
            System.out.println(Duke.line);
            userInput = scanner.nextLine();
        }
    }

    /**
     * Creates the output file if does not exists. Also creates directories that are missing.
     *
     * @param outputPath String of the path
     * @return File filePointer to to output file
     */
    public static File createOutputFile(String outputPath) {
        File filePointer = new File(outputPath);
        if (!filePointer.exists()) {
            File directory = new File(System.getProperty("user.dir") + Duke.dir);
            // create directory if it doesn't exist
            if (!directory.exists()) {
                boolean result = directory.mkdirs();
            }
            try {
                // create file in that directory
                if (!filePointer.createNewFile()) {
                    throw new FileNotFoundException();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePointer;
    }

    /**
     * Loads the data stored in the hard disk.
     *
     * @param outputPath String of the path
     * @throws FileNotFoundException
     */
    public static void LoadOutputFile(String outputPath) throws FileNotFoundException{
        File filePointer = Duke.createOutputFile(outputPath);
        Scanner storageScanner = new Scanner(filePointer);
        while (storageScanner.hasNext()) {
            String item = storageScanner.nextLine();
            if (item != "") {
                // process the item
                // T|1|read book
                String[] itemParts = item.split("\\|");
                boolean itemComplete = itemParts[1].equals("0");
                String name = itemParts[2];
                switch (itemParts[0]) {
                case "T":
                    Duke.storage.add(new Todos(name, itemComplete));
                    break;
                case "D":
                    String deadline = itemParts[3];
                    Duke.storage.add(new Deadlines(name, deadline, itemComplete));
                    break;
                case "E":
                    System.out.println(item);
                    String from = itemParts[3];
                    String to = itemParts[4];
                    Duke.storage.add(new Events(name, from, to, itemComplete));
                    break;
                default:
                    System.out.println("Error when reading file");
                }
            }
        }
        storageScanner.close();
    }

    /**
     * Overwrites data in the arrayList to the output file in the hard disk.
     */
    public static void updateTasks() {
        Consumer<Task> storeTask = task -> task.writeToFile(Duke.writer);
        Duke.storage.forEach(storeTask);
        try {
            Duke.writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
