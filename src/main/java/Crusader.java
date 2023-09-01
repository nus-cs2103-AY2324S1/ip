import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Crusader {
    /** Logo generated from https://patorjk.com/software/taag */
    private static final String LOGO =
              "   _____                          _\n"
            + "  / ____|                        | |\n"
            + " | |     _ __ _   _ ___  __ _  __| | ___ _ __\n"
            + " | |    | '__| | | / __|/ _` |/ _` |/ _ \\ '__|\n"
            + " | |____| |  | |_| \\__ \\ (_| | (_| |  __/ |\n"
            + "  \\_____|_|   \\__,_|___/\\__,_|\\__,_|\\___|_|";

    /**
     * A list of tasks for the chatbot.
     */
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    /**
     * The filepath used to save data.
     */
    private static final String SAVE_FILE = "./data/crusader.txt";

    /**
     * The date and time format used for all input in this program.
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy kk");

    /**
     * Describes the list of tasks in the chatbot.
     */
    private static void tasksToString() {
        System.out.println("Here are your tasks:");
        for (int x = 0; x < TASKS.size(); x++) {
            System.out.printf("%d. %s\n", x + 1, TASKS.get(x).toString());
        }
        addDivider();
    }

    /**
     * Marks a task as done.
     *
     * @param index index of the task to be marked. 1-indexed.
     */
    private static void mark(int index) {
        Task task = TASKS.get(index - 1);
        System.out.println("I have marked the task as done:");
        task.mark();
        System.out.println(task.toString());
    }

    /**
     * Unmarks a task.
     *
     * @param index index of the task to be unmarked. 1-indexed.
     */
    private static void unmark(int index) {
        Task task = TASKS.get(index - 1);
        System.out.println("I have unmarked the task, it is no longer done:");
        task.unmark();
        System.out.println(task.toString());
    }

    /**
     * Deletes a task.
     *
     * @param index index of the task to be deleted. 1-indexed.
     */
    private static void delete(int index) {
        Task task = TASKS.get(index - 1);
        System.out.println("I have deleted the following task:");
        System.out.println(task.toString());
        TASKS.remove(task);
        System.out.printf("Now there are %d tasks in the list.\n", TASKS.size());
    }

    /**
     * Displays the logo for the chatbot.
     */
    private static void showLogo() {
        System.out.println(LOGO);
        addDivider();
    }

    /**
     * Generates a horizontal line to divide parts of the conversation.
     */
    private static void addDivider() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Makes the chatbot say something.
     * Also adds a divider.
     */
    private static void say(String line) {
        System.out.println(line);
        addDivider();
    }

    /**
     * Greets the user.
     */
    private static void greet() {
        say("Hi, I am CRUSADER\nWhat can I do for you?");
    }

    /**
     * Says goodbye to the user.
     */
    private static void farewell() {
        say("Bye!\nHave a great day!");
    }

    /**
     * Parses dates and times entered.
     */
    private static Date parseDateTime(String dateTimeString) throws ParseException {
        return DATE_FORMAT.parse(dateTimeString);
    }

    /**
     * Parses the prompt to generate a new Todo Task.
     * @param prompt The prompt entered by the user
     */
    private static void addTodo(String prompt) {
        if (prompt.length() < 5) {
            throw new IllegalArgumentException("Hmm, todo should have something inside!");
        }
        String name = prompt.substring(5);
        Todo t = new Todo(name);
        System.out.println("Adding the task:");
        System.out.println(t.toString());
        TASKS.add(t);
        System.out.printf("Now there are %d tasks in the list.\n", TASKS.size());
        addDivider();
    }

    /**
     * Parses the prompt to generate a new Event Task.
     * @param prompt The prompt entered by the user
     */
    private static void addEvent(String prompt) throws ParseException {
        int fromPosition = prompt.indexOf("/from");
        if (fromPosition < 0) {
            throw new IllegalArgumentException("Hmm, an event must have /from!");
        }
        int toPosition = prompt.indexOf("/to");
        if (toPosition < 0) {
            throw new IllegalArgumentException("Hmm, an event must have /to!");
        }
        if (toPosition <= fromPosition ) {
            throw new IllegalArgumentException("Hmm, /to should be in front of /from!");
        }
        if (fromPosition < 7) {
            throw new IllegalArgumentException("There should be an event name!");
        }
        if (fromPosition + 6 > toPosition - 1) {
            throw new IllegalArgumentException("Please specify a /from parameter!");
        }
        if (toPosition + 4 > prompt.trim().length()) {
            throw new IllegalArgumentException("Please specify a /to parameter!");
        }
        String name = prompt.substring(6, fromPosition - 1);
        String from = prompt.substring(fromPosition + 6, toPosition - 1);
        String to = prompt.substring(toPosition + 4);
        Event e = new Event(name, parseDateTime(from), parseDateTime(to));
        System.out.println("Adding the task:");
        System.out.println(e.toString());
        TASKS.add(e);
        System.out.printf("Now there are %d tasks in the list.\n", TASKS.size());
        addDivider();
    }

    /**
     * Parses the prompt to generate a new Deadline Task.
     * @param prompt The prompt entered by the user
     */
    private static void addDeadline(String prompt) throws ParseException {
        int byPosition = prompt.indexOf("/by");
        if (byPosition < 0) {
            throw new IllegalArgumentException("Hmm, a deadline must have /by!");
        }
        if (byPosition + 4 > prompt.trim().length()) {
            throw new IllegalArgumentException("Please specify a /to parameter!");
        }
        String name = prompt.substring(9, byPosition - 1);
        String by = prompt.substring(byPosition + 4);
        Deadline d = new Deadline(name, parseDateTime(by));
        System.out.println("Adding the task:");
        System.out.println(d.toString());
        TASKS.add(d);
        System.out.printf("Now there are %d tasks in the list.\n", TASKS.size());
        addDivider();
    }

    /**
     * Loads into TASKS saved tasks from a file.
     * @param loadPath Path of the saved file.
     * @throws IOException
     */
    private static void loadIntoTasks(String loadPath) throws IOException {
        File save = new File(loadPath);
        try {
            Scanner sc = new Scanner(save);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                // parse the line
                Task task = parseSavedTask(line);
                TASKS.add(task);
            }
            say("Saved data has been loaded!");
        } catch (FileNotFoundException e) {
            say("No saved data was found!");
            // make the file
            save.createNewFile();
        } catch (ParseException e) {
            say("The saved data wasn't saved properly! Ignoring it...");
        }
    }

    /**
     * Parses a single task saved in the text file.
     * @param taskString Line to be parsed.
     * @return A new Task.
     */
    private static Task parseSavedTask(String taskString) throws ParseException {
        String[] components = taskString.split("\\|");
        Task task;
        switch (components[0]) {
        case "E":
            task = new Event(components[1], parseDateTime(components[3]), parseDateTime(components[4]));
            break;
        case "T":
            task = new Todo(components[1]);
            break;
        case "D":
            task = new Deadline(components[1], parseDateTime(components[3]));
            break;
        default:
            throw new IllegalArgumentException();
        }
        if (components[2].equals("X")) {
            task.mark();
        }
        return task;
    }

    /**
     * Saves the Tasks in TASKS to a given file.
     * @param savePath Path to the file
     */
    private static void saveTasks(String savePath) {
        File save = new File(savePath);
        try (PrintWriter fileWriter = new PrintWriter(save)) {
            for (Task task : TASKS) {
                fileWriter.println(task.toFormat());
            }
        } catch (IOException e) {
            say("Critical error in saving the data! It wasn't saved :(\n Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean notEnded = true;
        addDivider();
        showLogo();
        greet();
        try {
            loadIntoTasks(SAVE_FILE);
        } catch (IOException e) {
            say("ERROR: Unable to load or create file!");
        }
        while (notEnded) {
            String currentPrompt = sc.nextLine();
            addDivider();
            // Match the command based on the first word of the string
            switch (currentPrompt.contains(" ")
                    ? currentPrompt.split(" ")[0]
                    : currentPrompt) {
            case "bye":
                notEnded = false;
                break;
            case "list":
                tasksToString();
                break;
            case "mark":
                try {
                    int i = Integer.parseInt(currentPrompt.split(" ")[1]);
                    mark(i);
                } catch (ArrayIndexOutOfBoundsException e) {
                    say("Hmm, there's supposed to be something in front of \"mark\"!");
                } catch (NumberFormatException e) {
                    say("Hmm, there should be a NUMBER in front of \"mark\"!");
                }
                break;
            case "unmark":
                try {
                    int j = Integer.parseInt(currentPrompt.split(" ")[1]);
                    unmark(j);
                } catch (ArrayIndexOutOfBoundsException e) {
                    say("Hmm, there's supposed to be something in front of \"unmark\"!");
                } catch (NumberFormatException e) {
                    say("Hmm, there should be a NUMBER in front of \"unmark\"!");
                }
                break;
            case "todo":
                try {
                    addTodo(currentPrompt);
                } catch (IllegalArgumentException e) {
                    say(e.getMessage());
                }
                break;
            case "event":
                try {
                    addEvent(currentPrompt);
                } catch (IllegalArgumentException e) {
                    say(e.getMessage());
                } catch (ParseException e) {
                    say("Sorry, I don't quite understand the dates you gave!\nI only understand dd/MM/yyyy HH (24-hour)");
                }
                break;
            case "deadline":
                try {
                    addDeadline(currentPrompt);
                } catch (IllegalArgumentException e) {
                    say(e.getMessage());
                } catch (ParseException e) {
                    say("Sorry, I don't quite understand the dates you gave!\nI only understand dd/MM/yyyy HH (24-hour)");
                }
                break;
            case "delete":
                try {
                    int k = Integer.parseInt(currentPrompt.split(" ")[1]);
                    delete(k);
                } catch (ArrayIndexOutOfBoundsException e) {
                    say("Hmm, there's supposed to be something in front of \"delete\"!");
                } catch (NumberFormatException e) {
                    say("Hmm, there should be a NUMBER in front of \"delete\"!");
                }
                break;
            default:
                say("Sorry, but I'm not sure what that is...");
                break;
            }
        }
        saveTasks(SAVE_FILE);
        farewell();
    }
}
