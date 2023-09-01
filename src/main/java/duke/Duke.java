package duke;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

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
    public static String splitStringBySpaces(String[] a, int start, int end){
        String res = "";
        for(int i = start; i < end; i++) {
            res += a[i];
            if (i != end-1) {
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
        return Utils.getWordCount(this.task) + " " + this.task + " " + String.valueOf(isCompleted) + " " + this.toShortString() +
                " " + (dateString == null ? "" : dateString);
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
     * Returns a string of all datetime-related information, suitable to be shown in lists.
     *
     * @returns String of datetime-related information
     */

    public String getFormattedDatetime(DateTimeFormatter formatter) {
        return "";
    }


}

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

    public Deadline(String task, boolean isCompleted, String dateString, ArrayList<DateTimeFormatter> formatters) throws DukeException {
        super(task, isCompleted, dateString);
        String[] s = dateString.split("\\s+");
        String a = Utils.splitStringBySpaces(s, 1, s.length);
        if (a.isEmpty()) {
            throw new DukeException("/by description cannot be empty");
        }
        for(DateTimeFormatter fr : formatters) {
            try {
                this.dl = LocalDateTime.parse(a, fr);
                break;
            } catch (DateTimeParseException e) {
                continue;
            }

        }
        if(dl == null) {
            throw new DukeException("/by date format count not be recognized");
        }

    }

    public String toShortString() {
        return "D";
    }
    public String getFormattedDatetime(DateTimeFormatter formatter) {
        return "(by: " +  dl.format(formatter) + ")";
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
     * @throws DukeException when either the /to or /from description is empty or cannot be interpreted by given formatters
     */
    public Event(String task, boolean isCompleted, String dateString, ArrayList<DateTimeFormatter> formatters) throws DukeException {
        super(task, isCompleted, dateString);
        String[] s = dateString.split("\\s+");
        boolean wasToDetected = false;
        for(int i = 1; i < s.length; i++){
            if(s[i].equals("/to")) {
                String a = Utils.splitStringBySpaces(s, 1, i);
                String b = Utils.splitStringBySpaces(s, i+1, s.length);
                if(a.isEmpty()) {
                    throw new DukeException("/from description cannot be empty");
                }
                if(b.isEmpty()) {
                    throw new DukeException("/to description cannot be empty");
                }
                for(DateTimeFormatter fr : formatters) {
                    try{
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

                wasToDetected  = true;
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
    public String getFormattedDatetime(DateTimeFormatter formatter){
        return "(from: " +  from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}

/**
 * A checked exception class, personalized to fit the Duke application.
 */
class DukeException extends Exception {
    public DukeException(String a){
        super(a);
    }
}


/**
 * Manages displaying of information to the end-user.
 */

class Ui{
    private DTFormat dtf;

    /**
     * Constructor for the Ui class.
     *
     * @param dtf DateTimeFormatter to be used in formatting strings to be displayed.
     */

    public Ui(DTFormat dtf) {
        this.dtf = dtf;
    }

    /**
     * Prints a message to the UI.
     *
     * @param msg string to be printed
     */

    public void print(String msg) {
        System.out.println(msg);
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
        return taskTypeBox + completedBox + " " + task + " "  + formattedDatetime;
    }

    /**
     * Prints all TaskTypes in a list to the UI.
     *
     * @param items items to be printed
     */

    public void printItems(ArrayList<TaskType> items) {
        for(int i = 0; i < items.size(); i++){
            print((i + 1) + "." + formatTaskToPrint(items.get(i), dtf.getOutFormatter()));
        }
    }

    /**
     * Prints the introductory message to the end-user.
     */

    public void printIntro() {
        print("Hello I'm Robot!");
        print("What can I do for you?");
    }

    /**
     * Prints the final message to the end-user when they exit the program.
     */

    public void printExit() {
        print("Bye! Hope to see you again soon!");
    }

    /**
     * Displays a loading error to the end-user when data cannot be loaded for some reason.
     */

    public void showLoadingError() {
        print("An error occurred while loading data into your list, starting with a blank list...");
    }

}

/**
 * Manages DateTimeFormatters to be used in the application.
 */

class DTFormat{
    private ArrayList<DateTimeFormatter> formatters;
    private DateTimeFormatter out_date_format;

    /**
     * Constructor for the DTFormat class, creating severaL default DateTimeFormatters to
     * be used.
     */
    public DTFormat() {
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

        out_date_format = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
    }

    /**
     * Returns an ArrayList of DateTimeFormatters to recognize user input.
     *
     * @returns ArrayList of DateTimeFormatters to recognize user input
     */

    public ArrayList<DateTimeFormatter> getFormatters(){
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
        return out_date_format;
    }
    /**
     * Sets the output DateTimeFormatter to the given datetime format.
     */
    public void setOutFormatter(String k) {
        out_date_format = DateTimeFormatter.ofPattern(k);
    }

}

/**
 * Represents the list of tasks created by user.
 */
class TaskList{
    ArrayList<TaskType> items;

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

    public TaskList(ArrayList<String> ss, DTFormat dtf) throws DukeException {
        items = new ArrayList<>();
        for(String s : ss){
            String[] d = s.split("\\s+");
            int descLen = Integer.valueOf(d[0]);
            String desc = Utils.splitStringBySpaces(d, 1, descLen+1);
            boolean isCompleted = Boolean.valueOf(d[descLen+1]);
            String dateString = d.length > descLen+3 ? Utils.splitStringBySpaces(d, descLen+3, d.length) : null;
            TaskType task;
            if (d[descLen+2].equals("T")) {
                task = new Todo(desc, isCompleted);
            } else if (d[descLen+2].equals("D")) {
                task = new Deadline(desc, isCompleted, dateString, dtf.getFormatters());
            } else if (d[descLen+2].equals("E")) {
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

class Storage{
    ArrayList<String> its;
    private String data_file;

    /**
     * Constructor for Storage class, establishes whether external storage
     * location exists, and if not, creates it.
     *
     * @param data_folder relative pathname to the folder of external storage
     */
    public Storage(String data_folder) {
        File g = new File(data_folder);
        if(!g.exists()) {
            g.mkdir();
        }
        data_file = data_folder + "data.txt";
        this.data_file = data_file;
    }

    /**
     * Loads data from external storage into the application, creating an
     * ArrayList of items as strings to be further processed.
     *
     * @returns ArrayList of strings
     */
    public ArrayList<String> load() {
        File f = new File(data_file);
        its = new ArrayList<>();
        try {
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                its.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException fe) {
            System.out.println("Data file not found, attempting to create one...");
            try{
                f.createNewFile();
                System.out.println("Data file successfully created.");
            } catch (IOException e) {
                System.out.println("Data storage could be created, any items added to the app will be deleted after program exit.");
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

    public void save(ArrayList<TaskType> items){
        try{
            FileWriter fileWriter = new FileWriter(data_file);
            for(int i = 0; i < items.size(); i++) {
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

class Parser{
    private DTFormat dtf;
    private Ui ui;
    private TaskList tl;
    /**
     * Constructor for Parser class.
     *
     * @param dtf DTFormat object used to handle datetime-related strings
     * @param ui Ui object used to handle displaying output to user.
     * @param tl TaskList object to track user-created tasks.
     */
    public Parser(DTFormat dtf, Ui ui, TaskList tl){
        this.dtf = dtf;
        this.ui = ui;
        this.tl = tl;
    }

    /**
     * Parses user input and performs associated actions
     *
     * @param userInput One line of the user input
     */

    public boolean parse(String userInput) throws DukeException{
        String[] splitStr = userInput.split("\\s+");

            if (userInput.equals("bye")) {
                return false;
            }

            if (userInput.equals("list")){
                ui.print("Here are the tasks in your list:");
                ui.printItems(tl.getItems());

            } else if (splitStr[0].equals("mark")) {
                if(splitStr.length != 2) {
                    throw new DukeException("Invalid format detected for 'mark' command. Enter mark [item_no]");
                }
                int x = Integer.parseInt(splitStr[1]) - 1;
                if(x < 0 || x+1 > tl.getSize()) {
                    throw new DukeException("Index is out of list range.");
                }
                tl.getItem(x).setIsCompleted(true);
                ui.print("Nice! I've marked this task as done:");
                ui.print(ui.formatTaskToPrint(tl.getItem(x), dtf.getOutFormatter()));

            } else if (splitStr[0].equals("unmark")) {
                if(splitStr.length != 2) {
                    throw new DukeException("Invalid format detected for 'unmark' command. Enter unmark [item_no]");
                }
                int x = Integer.parseInt(splitStr[1]) - 1;
                if(x < 0 || x+1 > tl.getSize()) {
                    throw new DukeException("Index is out of list range.");
                }
                tl.getItem(x).setIsCompleted(false);
                ui.print("Ok, I've marked this task as not done yet:");
                ui.print(ui.formatTaskToPrint(tl.getItem(x), dtf.getOutFormatter()));

            } else if (splitStr[0].equals("remove")) {
                if(splitStr.length != 2) {
                    throw new DukeException("Invalid format detected for 'remove' command. Enter remove [item_no]");
                }
                int x = Integer.parseInt(splitStr[1]) - 1;
                if(x < 0 || x+1 > tl.getSize()) {
                    throw new DukeException("Index is out of list range.");
                }
                ui.print("Ok, the following item was removed:");
                ui.print(ui.formatTaskToPrint(tl.getItem(x), dtf.getOutFormatter()));
                tl.removeItem(x);

            } else if (splitStr[0].equals("todo")) {
                if(splitStr.length == 1) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                String desc = Utils.splitStringBySpaces(splitStr, 1, splitStr.length);
                tl.addItem(new Todo(desc, false));
                ui.print("Got it, I've added this task:");
                ui.print(ui.formatTaskToPrint(tl.getItem(tl.getSize() - 1), dtf.getOutFormatter()));
                ui.print("Now you have " + tl.getSize() + " tasks in the list.");

            } else if (splitStr[0].equals("deadline")) {
                boolean x = false;
                for(int i = 1; i < splitStr.length; i++) {
                    if(splitStr[i].equals("/by")) {
                        String dateString = Utils.splitStringBySpaces(splitStr, i, splitStr.length);
                        String desc = Utils.splitStringBySpaces(splitStr, 1, i);

                        if(desc.isEmpty()) {
                            throw new DukeException("Description of task cannot be empty.");
                        }
                        if(dateString.isEmpty()) {
                            throw new DukeException("Deadline cannot be empty.");
                        }
                        tl.addItem(new Deadline(desc, false, dateString, dtf.getFormatters()));
                        x = true;
                        break;
                    }
                }
                if(!x) {
                    throw new DukeException("/by keyword is necessary and not detected. Use /by to set a deadline.");
                }
                ui.print("Got it, I've added this task:");

                ui.print(ui.formatTaskToPrint(tl.getItem(tl.getSize() - 1), dtf.getOutFormatter()));

                ui.print("Now you have " + tl.getSize() + " tasks in the list.");

            } else if (splitStr[0].equals("event")) {
                boolean x = false;
                for (int i = 1; i < splitStr.length; i++){
                    if (splitStr[i].equals("/from")) {
                        String dateString = Utils.splitStringBySpaces(splitStr, i, splitStr.length);
                        String desc = Utils.splitStringBySpaces(splitStr, 1, i);
                        if (desc.isEmpty()) {
                            throw new DukeException("Description of task cannot be empty.");
                        }
                        if(dateString.isEmpty()) {
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
                ui.print("Got it, I've added this task:");

                ui.print(ui.formatTaskToPrint(tl.getItem(tl.getSize() - 1), dtf.getOutFormatter()));

                ui.print("Now you have " + tl.getSize() + " tasks in the list.");
            } else {
                throw new DukeException("Sorry, I don't understand that command");
            }

        return true;
    }
}

/**
 * Entry point of the Duke program.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private DTFormat dtf;
    private static Scanner sc = new Scanner(System.in);

    /**
     * Constructor for Duke class, initializing all necessary classes
     * to manage IO and external storage.
     *
     * @param filepath relative pathname to the folder of external storage
     */

    public Duke(String filepath) {
        dtf = new DTFormat();
        ui = new Ui(dtf);
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load(), dtf);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(dtf, ui, tasks);
    }

    /**
     * Runs the Duke program, beginning the loop of receiving user
     * input until the user terminates.
     */

    public void run() {
        ui.printIntro();
        while(true) {
            String userInput = sc.nextLine();
            try {
                if (!parser.parse(userInput)) {
                    break;
                }
            } catch (DukeException e) {
                ui.print(e.toString());
            }

        }
        storage.save(tasks.getItems());
        ui.printExit();
    }


    public static void main(String[] args) {
        new Duke("./data/").run();
    }
}
