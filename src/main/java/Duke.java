import java.time.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

public class Duke {
    static String indent = "   ";
    static String megaIndent = "     ";
    static String horizontalLines = indent  + "__________________________________________";
    //static ArrayList<Task> taskArray = new ArrayList<>();
    static String filePath = "data/duke.txt";
    private Storage storage;
    private static TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException | ClassNotFoundException e) {
            //ui.showLoadingError();
            System.out.println(e.getMessage()); // on top error
            createTxtFile();
            tasks = new TaskList();
        }
    }
    public static void createTxtFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.close();
        } catch (IOException e) {
            System.out.println("shag");
        }
    }

    /**
     * Prints the input string with horizontal lines above and below it
     *
     * @param string the input string
     */
    public static void printWithIndent(String string) {
        System.out.println(horizontalLines);
        System.out.println(indent + string);
        System.out.println(horizontalLines);
    }

    /**
     * Everytime a Task is added to tasks, clear the duke.txt file, then scan the whole
     * taskArray and rewrite the entire txt file
     *
     * Initially, I did a writeToFile method where everytime a Task is added to taskArray, write
     * the new task to duke.txt. However, this may cause some problems when it comes to updating
     * or deleting tasks from the file, so I changed the implementation to rewriting the entire txt
     * file everytime there is a change to the list. This causes a longer run time but since this mod
     * is not about run time, it should be fine.
     *
     * @param filePath hardcoded relative path to duke.txt
     * @throws IOException if the file at the filePath does not exist (I think)
     */
    private static void updateFile(String filePath) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(tasks);

            FileWriter fw = new FileWriter(filePath);
            // Clear the existing content by opening in write mode and immediately closing
            fw = new FileWriter(filePath, true);
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(tasks.getLastTask().toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * For an input such as 'todo borrow book', letter is 'T' and string is 'borrow book'
     * After adding the Task to taskArray, duke.txt is updated to reflect the new list
     *
     * @param letter the letter corresponding to the first letter of the Task
     * @param string the string corresponding to the chunk of text after the word todo, deadline, or event
     */
    public static void addTask(String letter, String string) {
        try {
            if (letter.equals("T")) {
                tasks.addTask(new ToDo(string));
            }
            if (letter.equals("D")) {
                tasks.addTask(new Deadline(getDescription(string), convertToLocalDateTime(getBy(string))));
            }
            if (letter.equals("E")) {
                tasks.addTask(new Event(getDescription(string), getFrom(string), getTo(string)));
            }
            updateFile(filePath);

            int arrayLength = tasks.getSize();
            System.out.println(horizontalLines);
            System.out.println(indent + "Got it. I've added this task:");
            System.out.println(megaIndent + tasks.getLastTask().toString());
            System.out.println(indent + "Now you have " + arrayLength + " tasks in the list.");
            System.out.println(horizontalLines);
        } catch (DukeException | IOException e) {
            printWithIndent(e.getMessage());
        }
    }

    /**
     * For deadline and event Tasks, obtains the description of the Task (before the first slash)
     * For example, the input 'event project meeting /from Mon 2pm /to 4pm' will return 'project meeting'
     *
     * @param string of the Task
     * @return the description of the Task
     */
    public static String getDescription(String string) {
        int len = string.length();
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (string.charAt(i) == '/') {
                break;
            }
            count++;
        }
        return string.substring(0, count);
    }

    /**
     * A method for the Deadline class to obtain the by part of the Task description
     * For example, the input 'deadline return book /by Sunday' will return 'Sunday'
     *
     * @param string the Task description
     * @return the deadline
     * @throws DukeException if the input string is formatted wrongly
     */
    public static String getBy(String string) throws DukeException {
        String slash = "/";
        int first = string.indexOf(slash);
        int second = first + 3;
        if (first == -1 || !string.substring(first, second).equals("/by")) {
            throw new DukeException("You need to add a by timing!");
        }
        return string.substring(first + 4); // returns "Sunday"
    }

    /**
     * A method for the Event class to obtain the from part of the Event description
     * For example, the input 'event project meeting /from Mon 2pm /to 4pm' will return 'Mon 2pm'
     *
     * @param string the Task description
     * @return the from part of the event
     * @throws DukeException throws DukeException if invalid input
     */
    public static String getFrom(String string) throws DukeException {
        String slash = "/";
        int firstSlash = string.indexOf(slash);
        int secondSlash = string.indexOf(slash, firstSlash + 1);

        if (firstSlash == -1 || secondSlash == -1
                || !string.substring(firstSlash, firstSlash + 5).equals("/from")) {
            throw new DukeException("You need to add a /from and /to for events");
        }

        return string.substring(firstSlash + 6, secondSlash - 1);
    }
    /**
     * A method for the Event class to obtain the to part of the Event description
     * For example, the input 'event project meeting /from Mon 2pm /to 4pm' will return '4pm'
     *
     * @param string the Task description
     * @return the to part of the event
     * @throws DukeException throws DukeException if invalid input
     */
    public static String getTo(String string) throws DukeException {
        String slash = "/";
        int firstSlash = string.indexOf(slash);
        int secondSlash = string.indexOf(slash, firstSlash + 1);

        if (!string.substring(secondSlash, secondSlash + 3).equals("/to")) {
            throw new DukeException("You need to add a /to for events");
        }
        return string.substring(secondSlash + 4);
    }

    /**
     * This method encapsulates the functionality of marking a task as completed or not
     * For example, the input 'mark 1' will mark the Task at position 0 at the TaskArray as 'marked'
     * After marking description, duke.txt is updated to reflect the new list
     *
     * @param string the input string
     */
    public static void markDescription(String string) {
        try {
            tasks.markDescription(string);
            updateFile(filePath);
        } catch (IndexOutOfBoundsException | IOException e) {
            printWithIndent("You are trying to access a Task that does not exist!");
        }
    }

    /**
     * This method encapsulates deleting of a task from TaskArray
     * For example, the input 'delete 3' will delete the Task at position 2 of TaskArray
     * After deleting the Task, duke.txt is updated to reflect the new list
     *
     * @param string the input string
     */
    public static void deleteTask(String string) {
        try {
            tasks.deleteTask(string);
            updateFile(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function is called when the main method is run. It will print out all the tasks
     * in the list
     *
     * @param filePath the file where the lists of Tasks are stored. It is hardcoded to be "data/duke.txt"
     * @throws FileNotFoundException if the file at this filePath is not found (though I'm not sure
     * when this will happen)
     */
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * A function that helps convert a string to a LocalDateTime
     *
     * @param input the by part of the Deadline Task, eg. "2/12/2019 1800"
     * @param c whether or not the Deadline is put in a '-' format or '/' format
     * @return a LocalDateTime
     * @throws DukeException if a specific time in 24hr format is not put
     */
    public static LocalDateTime parseDateTime(String input, char c) throws DukeException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new DukeException("put in a time pls");
        }

        String datePart = parts[0];
        String timePart = parts[1];

        String[] dateComponents;
        if (c == '/') {
            dateComponents = datePart.split("/");
        } else {
            // c == '-'
            dateComponents = datePart.split("-");
        }

        if (dateComponents.length != 3) {
            throw new IllegalArgumentException("Invalid date format");
        }

        int date = Integer.parseInt(dateComponents[0]);
        int month = Integer.parseInt(dateComponents[1]);
        int year = Integer.parseInt(dateComponents[2]);
        int hour = Integer.parseInt(timePart.substring(0, 2));
        int minute = Integer.parseInt(timePart.substring(2, 4));

        return LocalDateTime.of(year, month, date, hour, minute);
    }

    /**
     * A function that takes in the by part of a Deadline Task, and converts it to a LocalDateTime
     * For example, the input 'Sunday 1700' will return the corresponding LocalDateTime
     *
     * @param string the by part of the Deadline Task
     * @return the LocalDateTime corresponding to the Deadline
     * @throws DukeException if a specific time in 24hr format is not put
     */
    private static LocalDateTime convertToLocalDateTime(String string) throws DukeException {
        if (string.indexOf('/') != -1) {
            if (string.lastIndexOf('/') + 5 == string.length()) { // "2/12/2019 1800"
                throw new DukeException("put in a time pls");
            }
            LocalDateTime dateTime = parseDateTime(string, '/');
            return dateTime;
        } else if (string.indexOf('-') != -1) { //
            if (string.lastIndexOf('-') + 3 == string.length()) { // "2019-10-15 1800"
                throw new DukeException("put in a time pls");
            }
            LocalDateTime dateTime = parseDateTime(string, '-');
            return dateTime;
        } else { // "Mon 1800"
            // problem 1: date does not overflow to next month
            // problem 2: it goes backwards in day
            String[] parts = string.split(" ");
            if (parts.length == 1) {
                throw new DukeException("put in a time pls");
            }
            String dayPart = parts[0];
            String timePart = parts[1];

            int year = LocalDate.now().getYear();
            int month = LocalDate.now().getMonth().getValue();
            int daysToAdd = -LocalDateTime.now().getDayOfWeek().compareTo(getDayOfWeek(dayPart.toUpperCase()));
            int date = LocalDate.now().getDayOfMonth() + daysToAdd;

            int hour = Integer.parseInt(timePart.substring(0, 2));
            int minute = Integer.parseInt(timePart.substring(2, 4));

            LocalDate temp = LocalDate.of(year, month, 1);
            // temp LocalDate to obtain the maximum no. of days in that month
            int maxDaysOfMonth = temp.lengthOfMonth();

            if (date > maxDaysOfMonth) {
                // Date overflows, adjust LocalDateTime to the next month
                return LocalDateTime.of(year, month + 1, date - maxDaysOfMonth, hour, minute);
            } else {
                return LocalDateTime.of(year, month, date, hour, minute);
            }
        }
    }

    /**
     * A function that takes in a user input that is the day of the week and returns the corresponding DayOfWeek
     *
     * @param string the user input that is a day of the week, eg. "sun", "Tuesday", "Mon"
     * @return the DayOfWeek as an enum
     */
    public static DayOfWeek getDayOfWeek(String string) {
        DayOfWeek result;
        String day = string.substring(0, 3);
        switch(day) {
            case "MON":
                result = DayOfWeek.MONDAY;
                break;
            case "TUE":
                result = DayOfWeek.TUESDAY;
            break;
            case "WED":
                result = DayOfWeek.WEDNESDAY;
            break;
            case "THU":
                result = DayOfWeek.THURSDAY;
            break;
            case "FRI":
                result = DayOfWeek.FRIDAY;
            break;
            case "SAT":
                result = DayOfWeek.SATURDAY;
            break;
            case "SUN":
                result = DayOfWeek.SUNDAY;
            break;
            default:
                result = DayOfWeek.MONDAY;
        }
        return result;
    }
    public void run() {
        String name = "zac";
        Scanner obj = new Scanner(System.in);

        System.out.println(horizontalLines);
        System.out.println(indent + "Hello! I'm " + name);
        System.out.println(indent + "What can I do for you?");
        System.out.println(horizontalLines);

        try {
            printFileContents(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            String userInput = obj.nextLine();
            if (userInput.equals("list")) {
                tasks.displayList();
            } else if (userInput.equals("bye")) {
                printWithIndent("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.contains("unmark")) {
                markDescription(userInput);
            } else if (userInput.contains("mark")) {
                markDescription(userInput);
            } else if (userInput.contains("todo")) {
                try {
                    addTask("T", userInput.substring(5));
                } catch (StringIndexOutOfBoundsException e) {
                    printWithIndent("OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (userInput.contains("deadline")) {
                try {
                    addTask("D", userInput.substring(9));
                } catch (StringIndexOutOfBoundsException e) {
                    printWithIndent("OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (userInput.contains("event")) {
                try {
                    addTask("E", userInput.substring(6));
                } catch (StringIndexOutOfBoundsException e) {
                    printWithIndent("OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (userInput.contains("delete")) {
                deleteTask(userInput);
            } else {
                printWithIndent("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }


    public static void main(String[] args) {
        new Duke(filePath).run();
        tasks.printAllForTestingPurposes();
    }
}
