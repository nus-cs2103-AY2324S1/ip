package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;

/**
 * Contains utility functions to be used in several contexts.
 */

class Utils {
    /**
     * Takes an array of strings, a start and end index. Returns
     * a string with all tokens between start and end (not inclusive)
     * index separated by a space.
     *
     * @param a Array of strings
     * @param start  start index
     * @param end end index
     * @returns String with all tokens between start and end (not inclusive) index separated by space
     */
    public static String splitStringBySpaces(String[] a, int start, int end) {
        String res = "";
        for (int i = start; i < end; i++) {
            res += a[i];
            if (i != end - 1) {
                res += " ";
            }
        }
        return res;
    }

    /**
     * Returns the number of words in a string, separated by spaces.
     *
     * @param s String whose words are to be counted
     * @returns Number of words in the string
     */

    public static int getWordCount(String s) {
        return s.split("\\s+").length;
    }
}



/**
 * Represents a type of task to be done.
 */

class TaskType {
    private String task;
    private boolean isCompleted;
    private String dateString;

    /**
     * Constructor for the TaskType class.
     *
     * @param task Description of task
     * @param isCompleted Whether of not the task has been completed
     * @param dateString String containing only datetime-related information pf the task, if any.
     */

    public TaskType(String task, boolean isCompleted, String dateString) {
        this.task = task;
        this.isCompleted = isCompleted;
        this.dateString = dateString;
    }

    /**
     * Returns the boolean value of the isCompleted field.
     */

    public boolean getIsCompleted() {
        return isCompleted;
    }

    /**
     * Sets the isCompleted field to false or true.
     *
     * @param x boolean to set the isCompleted field to
     */

    public void setIsCompleted(boolean x) {
        isCompleted = x;
    }

    /**
     * Produces the string representation of a TaskType to be saved in external storage.
     */

    public String saveStringRep() {
        return Utils.getWordCount(this.task) + " " + this.task + " "
                + String.valueOf(isCompleted) + " " + this.toShortString()
                + " " + (dateString == null ? "" : dateString);
    }

    /**
     * Produces a one-letter representation of the TaskType to be shown in lists.
     *
     * @returns one-letter representation of the TaskType
     */

    public String toShortString() {
        return "";
    };

    /**
     * Returns the task description.
     *
     * @returns task description as string
     */

    public String getTaskDesc() {
        return task;
    };

    /**
     * Returns a string of all datetime-related information, suitable to be shown in lists.
     *
     * @returns String of datetime-related information
     */

    public String getFormattedDatetime(DateTimeFormatter formatter) {
        return "";
    }


}



//class Launcher {
//    public static void main(String[] args) {
//        Application.launch(Gui.class, args);
//    }
//}

/**
 * Represents a task of type Todo.
 */
class Todo extends TaskType {

    /**
     * Constructor for the Todo class.
     *
     * @param task Description of task
     * @param isCompleted Whether of not the task has been completed
     */

    public Todo(String task, boolean isCompleted) {
        super(task, isCompleted, "");
    }
    public String toShortString() {
        return "T";
    }
    public String getFormattedDatetime(DateTimeFormatter formatter) {
        return "";
    }
}

/**
 * Represents a task of type Deadline.
 */
class Deadline extends TaskType {

    private LocalDateTime dl;

    /**
     * Constructor for the Deadline class.
     *
     * @param task Description of task
     * @param isCompleted Whether of not the task has been completed
     * @param dateString String of datetime related information
     * @param formatters List of Datetime formatters used to convert the dateString into a LocalDateTime
     * @throws DukeException when the /by description is empty or cannot be interpreted by given formatters.
     */

    public Deadline(String task, boolean isCompleted, String dateString, ArrayList<DateTimeFormatter> formatters)
            throws DukeException {
        super(task, isCompleted, dateString);
        String[] s = dateString.split("\\s+");
        String a = Utils.splitStringBySpaces(s, 1, s.length);
        if (a.isEmpty()) {
            throw new DukeException("/by description cannot be empty");
        }
        for (DateTimeFormatter fr : formatters) {
            try {
                this.dl = LocalDateTime.parse(a, fr);
                break;
            } catch (DateTimeParseException e) {
                continue;
            }

        }
        if (dl == null) {
            throw new DukeException("/by date format count not be recognized");
        }

    }

    public String toShortString() {
        return "D";
    }
    public String getFormattedDatetime(DateTimeFormatter formatter) {
        return "(by: " + dl.format(formatter) + ")";
    }
}

/**
 * Represents a task of type Event.
 */
class Event extends TaskType {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for the Event class.
     *
     * @param task Description of task
     * @param isCompleted Whether of not the task has been completed
     * @param dateString String of datetime related information
     * @param formatters List of Datetime formatters used to convert the dateString into a LocalDateTime
     * @throws DukeException when either the /to or /from description is empty
     */
    public Event(String task, boolean isCompleted, String dateString, ArrayList<DateTimeFormatter> formatters)
                throws DukeException {
        super(task, isCompleted, dateString);
        String[] s = dateString.split("\\s+");
        boolean wasToDetected = false;
        for (int i = 1; i < s.length; i++) {
            if (s[i].equals("/to")) {
                String a = Utils.splitStringBySpaces(s, 1, i);
                String b = Utils.splitStringBySpaces(s, i + 1, s.length);
                if (a.isEmpty()) {
                    throw new DukeException("/from description cannot be empty");
                }
                if (b.isEmpty()) {
                    throw new DukeException("/to description cannot be empty");
                }
                for (DateTimeFormatter fr : formatters) {
                    try {
                        this.from = LocalDateTime.parse(a, fr);
                        break;
                    } catch (DateTimeParseException e) {
                        continue;
                    }
                }
                for (DateTimeFormatter fr : formatters) {
                    try {
                        this.to = LocalDateTime.parse(b, fr);
                        break;
                    } catch (DateTimeParseException e) {
                        continue;
                    }
                }

                wasToDetected = true;
            }
        }
        if (from == null) {
            throw new DukeException("/from date format could not be recognized");
        }
        if (to == null) {
            throw new DukeException("/to date format could not be recognized");
        }
        if (!wasToDetected) {
            throw new DukeException("keyword /to was not detected.");
        }
    }

    public String toShortString() {
        return "E";
    }
    public String getFormattedDatetime(DateTimeFormatter formatter) {
        return "(from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}

/**
 * A checked exception class, personalized to fit the Duke application.
 */
class DukeException extends Exception {
    public DukeException(String a) {
        super(a);
    }
}


/**
 * Manages displaying of information to the end-user.
 */

class Ui {
    private DtFormat dtf;

    /**
     * Constructor for the Ui class.
     *
     * @param dtf DateTimeFormatter to be used in formatting strings to be displayed.
     */

    public Ui(DtFormat dtf) {
        this.dtf = dtf;
    }

    /**
     * Prints a message to the UI.
     *
     * @param msg string to be printed
     */

    public String print(String msg) {
        return msg + "\n";
    }

    /**
     * Formats the information of a TaskType into a string to be shown in the UI.
     *
     * @param task TaskType to be formatted.
     * @param fmt DateTimeFormatter to be used in producing the output
     * @returns String representation of the TaskType to be shown in the UI
     */

    public String formatTaskToPrint(TaskType task, DateTimeFormatter fmt) {
        String completedBox = "[" + (task.getIsCompleted() ? "X" : " ") + "] ";
        String taskTypeBox = "[" + task.toShortString() + "]";
        String formattedDatetime = task.getFormattedDatetime(fmt);
        return taskTypeBox + completedBox + " " + task.getTaskDesc() + " " + formattedDatetime;
    }

    /**
     * Prints all TaskTypes in a list to the UI.
     *
     * @param items items to be printed
     */

    public String printItems(ArrayList<TaskType> items) {
        String out = "";
        for (int i = 0; i < items.size(); i++) {
            out += (i + 1) + ". " + formatTaskToPrint(items.get(i), dtf.getOutFormatter());
            out += "\n";
        }
        return out;
    }

    /**
     * Prints the introductory message to the end-user.
     */

    public String printIntro() {
        String out = "";
        out += "Hello I'm Robot!\n";
        out += "What can I do for you?\n";
        return out;
    }

    /**
     * Prints the final message to the end-user when they exit the program.
     */

    public String printExit() {
        return "Bye! Hope to see you again soon!\n";
    }

    /**
     * Displays a loading error to the end-user when data cannot be loaded for some reason.
     */

    public String showLoadingError() {
        return "An error occurred while loading data into your list, starting with a blank list...";
    }

}
/**
 * Manages DateTimeFormatters to be used in the application.
 */

class DtFormat {
    private ArrayList<DateTimeFormatter> formatters;
    private DateTimeFormatter outDateFormat;

    /**
     * Constructor for the DtFormat class, creating severaL default DateTimeFormatters to
     * be used.
     */
    public DtFormat() {
        formatters = new ArrayList<>();
        DateTimeFormatter f1 = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-M-d[ H:m]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();

        DateTimeFormatter f2 = new DateTimeFormatterBuilder()
                .appendPattern("d/M/yyyy[ Hmm]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();


        formatters.add(f1);
        formatters.add(f2);
        formatters.add(DateTimeFormatter.ofPattern("yyyy-M-d"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy Hmm"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy H"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy"));
        outDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
    }

    /**
     * Returns an ArrayList of DateTimeFormatters to recognize user input.
     *
     * @returns ArrayList of DateTimeFormatters to recognize user input
     */

    public ArrayList<DateTimeFormatter> getFormatters() {
        return formatters;
    }
    /**
     * Adds a datetime format to the list of recognized formats.
     */
    public void addReadFormat(String k) {
        formatters.add(DateTimeFormatter.ofPattern(k));
    }
    /**
     * Removes a datetime format by index from the list of recognized formats.
     */
    public void removeReadFormat(int x) {
        formatters.remove(x);
    }
    /**
     * Returns a DateTimeFormatter to format output.
     *
     * @returns DateTimeFormatter to format output
     */
    public DateTimeFormatter getOutFormatter() {
        return outDateFormat;
    }
    /**
     * Sets the output DateTimeFormatter to the given datetime format.
     */
    public void setOutFormatter(String k) {
        outDateFormat = DateTimeFormatter.ofPattern(k);
    }
}

/**
 * Represents the list of tasks created by user.
 */
class TaskList {
    private ArrayList<TaskType> items;

    /**
     * Default constructor for TaskList class, creates an empty list of TaskTypes.
     */
    public TaskList() {
        items = new ArrayList<>();
    }

    /**
     * Secondary constructor for TaskList class, generates a list of TaskTypes
     * from saved data.
     *
     * @param ss ArrayList of strings loaded from external data storage
     * @param dtf Datetime formatter used to recognize dates in saved data
     * @throws DukeException when the string format cannot be recognized and converted to TaskType
     */

    public TaskList(ArrayList<String> ss, DtFormat dtf) throws DukeException {
        items = new ArrayList<>();
        for (String s : ss) {
            String[] d = s.split("\\s+");
            int descLen = Integer.valueOf(d[0]);
            String desc = Utils.splitStringBySpaces(d, 1, descLen + 1);
            boolean isCompleted = Boolean.valueOf(d[descLen + 1]);
            String dateString = d.length > descLen + 3 ? Utils.splitStringBySpaces(d, descLen + 3, d.length) : null;
            TaskType task;
            if (d[descLen + 2].equals("T")) {
                task = new Todo(desc, isCompleted);
            } else if (d[descLen + 2].equals("D")) {
                task = new Deadline(desc, isCompleted, dateString, dtf.getFormatters());
            } else if (d[descLen + 2].equals("E")) {
                task = new Event(desc, isCompleted, dateString, dtf.getFormatters());
            } else {
                throw new DukeException("Task type not recognized");
            }
            items.add(task);
        }
    }

    /**
     * Returns the size of the TaskList.
     *
     * @returns size of TaskList.
     */

    public int getSize() {
        return items.size();
    }

    /**
     * Returns an item in the TaskList by index
     *
     * @param x index of item
     * @returns item in the TaskList by index
     */

    public TaskType getItem(int x) {
        return items.get(x);
    }

    /**
     * Appends an item to the end of the TaskList
     *
     * @param y TaskType to be appended
     */

    public void addItem(TaskType y) {
        items.add(y);
    }

    /**
     * Removes an item from the TaskList by index
     *
     * @param x index of item to be removed
     */
    public void removeItem(int x) {
        items.remove(x);
    }

    /**
     * Returns all items in the TaskList as an ArrayList of TaskTypes
     *
     * @returns ArrayList of TaskTypes
     */

    public ArrayList<TaskType> getItems() {
        return items;
    }
}


/**
 * Manages all loading and saving of data to external storage.
 */

class Storage {
    private ArrayList<String> its;
    private String dataFile;

    /**
     * Constructor for Storage class, establishes whether external storage
     * location exists, and if not, creates it.
     *
     * @param dataFolder relative pathname to the folder of external storage
     */
    public Storage(String dataFolder) {
        File g = new File(dataFolder);
        if (!g.exists()) {
            g.mkdir();
        }
        this.dataFile = dataFolder + "data.txt";
    }

    /**
     * Loads data from external storage into the application, creating an
     * ArrayList of items as strings to be further processed.
     *
     * @returns ArrayList of strings
     */
    public ArrayList<String> load() {
        File f = new File(dataFile);
        its = new ArrayList<>();
        try {
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                its.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException fe) {
            System.out.println("Data file not found, attempting to create one...");
            try {
                f.createNewFile();
                System.out.println("Data file successfully created.");
            } catch (IOException e) {
                System.out.println("Data storage failed, items added to the app will be deleted after program exit.");
                e.printStackTrace();
            }
        }
        return its;
    }

    /**
     * Saves data from application into external storage.
     *
     * @param items list of TaskTypes to be saved to storage
     */

    public void save(ArrayList<TaskType> items) {
        try {
            FileWriter fileWriter = new FileWriter(dataFile);
            for (int i = 0; i < items.size(); i++) {
                fileWriter.write(items.get(i).saveStringRep() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while saving your list items.");
        }

    }

}

/**
 * Handles parsing of user input and performing associated actions.
 */

class Parser {
    private DtFormat dtf;
    private Ui ui;
    private TaskList tl;
    /**
     * Constructor for Parser class.
     *
     * @param dtf DtFormat object used to handle datetime-related strings
     * @param ui Ui object used to handle displaying output to user.
     * @param tl TaskList object to track user-created tasks.
     */
    public Parser(DtFormat dtf, Ui ui, TaskList tl) {
        this.dtf = dtf;
        this.ui = ui;
        this.tl = tl;
    }

    /**
     * Parses user input and performs associated actions
     *
     * @param userInput One line of the user input
     */

    public String parse(String userInput) throws DukeException {
        String[] splitStr = userInput.split("\\s+");
        String out = "";

        if (userInput.equals("bye")) {
            out = "bye";
        }

        if (userInput.equals("list")) {
            out += ui.print("Here are the tasks in your list:");
            out += ui.printItems(tl.getItems());

        } else if (splitStr[0].equals("find")) {
            if (splitStr.length < 2) {
                throw new DukeException("Invalid format detected for 'find' command. Enter find [search_term]");
            }
            String searchTerm = Utils.splitStringBySpaces(splitStr, 1, splitStr.length);


            ArrayList<TaskType> suitable = new ArrayList<>();
            for (int i = 0; i < tl.getSize(); i++) {
                if (tl.getItem(i).getTaskDesc().contains(searchTerm)) {
                    suitable.add(tl.getItem(i));
                }
            }
            out += ui.print("Here are the matching tasks in your list:");
            out += ui.printItems(suitable);

        } else if (splitStr[0].equals("mark")) {
            if (splitStr.length != 2) {
                throw new DukeException("Invalid format detected for 'mark' command. Enter mark [item_no]");
            }
            int x = Integer.parseInt(splitStr[1]) - 1;
            if (x < 0 || x + 1 > tl.getSize()) {
                throw new DukeException("Index is out of list range.");
            }
            tl.getItem(x).setIsCompleted(true);
            out += ui.print("Nice! I've marked this task as done:");
            out += ui.print(ui.formatTaskToPrint(tl.getItem(x), dtf.getOutFormatter()));

        } else if (splitStr[0].equals("unmark")) {
            if (splitStr.length != 2) {
                throw new DukeException("Invalid format detected for 'unmark' command. Enter unmark [item_no]");
            }
            int x = Integer.parseInt(splitStr[1]) - 1;
            if (x < 0 || x + 1 > tl.getSize()) {
                throw new DukeException("Index is out of list range.");
            }
            tl.getItem(x).setIsCompleted(false);
            out += ui.print("Ok, I've marked this task as not done yet:");
            out += ui.print(ui.formatTaskToPrint(tl.getItem(x), dtf.getOutFormatter()));

        } else if (splitStr[0].equals("remove")) {
            if (splitStr.length != 2) {
                throw new DukeException("Invalid format detected for 'remove' command. Enter remove [item_no]");
            }
            int x = Integer.parseInt(splitStr[1]) - 1;
            if (x < 0 || x + 1 > tl.getSize()) {
                throw new DukeException("Index is out of list range.");
            }
            out += ui.print("Ok, the following item was removed:");
            out += ui.print(ui.formatTaskToPrint(tl.getItem(x), dtf.getOutFormatter()));
            tl.removeItem(x);

        } else if (splitStr[0].equals("todo")) {
            if (splitStr.length == 1) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            String desc = Utils.splitStringBySpaces(splitStr, 1, splitStr.length);
            tl.addItem(new Todo(desc, false));
            out += "Got it, I've added this task:";
            out += ui.print(ui.formatTaskToPrint(tl.getItem(tl.getSize() - 1), dtf.getOutFormatter()));
            out += ui.print("Now you have " + tl.getSize() + " tasks in the list.");

        } else if (splitStr[0].equals("deadline")) {
            boolean x = false;
            for (int i = 1; i < splitStr.length; i++) {
                if (splitStr[i].equals("/by")) {
                    String dateString = Utils.splitStringBySpaces(splitStr, i, splitStr.length);
                    String desc = Utils.splitStringBySpaces(splitStr, 1, i);

                    if (desc.isEmpty()) {
                        throw new DukeException("Description of task cannot be empty.");
                    }
                    if (dateString.isEmpty()) {
                        throw new DukeException("Deadline cannot be empty.");
                    }
                    tl.addItem(new Deadline(desc, false, dateString, dtf.getFormatters()));
                    x = true;
                    break;
                }
            }
            if (!x) {
                throw new DukeException("/by keyword is necessary and not detected. Use /by to set a deadline.");
            }
            out += ui.print("Got it, I've added this task:");

            out += ui.print(ui.formatTaskToPrint(tl.getItem(tl.getSize() - 1), dtf.getOutFormatter()));

            out += ui.print("Now you have " + tl.getSize() + " tasks in the list.");

        } else if (splitStr[0].equals("event")) {
            boolean x = false;
            for (int i = 1; i < splitStr.length; i++) {
                if (splitStr[i].equals("/from")) {
                    String dateString = Utils.splitStringBySpaces(splitStr, i, splitStr.length);
                    String desc = Utils.splitStringBySpaces(splitStr, 1, i);
                    if (desc.isEmpty()) {
                        throw new DukeException("Description of task cannot be empty.");
                    }
                    if (dateString.isEmpty()) {
                        throw new DukeException("Event dates cannot be empty.");
                    }
                    tl.addItem(new Event(desc, false, dateString, dtf.getFormatters()));
                    x = true;
                    break;
                }
            }
            if (!x) {
                throw new DukeException("/by keyword is necessary and not detected. Use /by to set a deadline.");
            }
            out += ui.print("Got it, I've added this task:");

            out += ui.print(ui.formatTaskToPrint(tl.getItem(tl.getSize() - 1), dtf.getOutFormatter()));

            out += ui.print("Now you have " + tl.getSize() + " tasks in the list.");
        } else {
            throw new DukeException("Sorry, I don't understand that command");
        }

        return out;
    }
}

/**
 * Entry point of the Duke program.
 */

public class Duke {

    /**
     * Runs the Duke program, beginning the loop of receiving user
     * input until the user terminates.
     */

    public static void main(String[] args) {
        // new Duke("./data/").run();
        Application.launch(Main.class, args);
    }
}


