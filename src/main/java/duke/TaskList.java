package duke;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.Serializable;
public class TaskList implements Serializable {
    static String indent = "   ";
    static String megaIndent = "     ";
    static ArrayList<Task> tasks = new ArrayList<>();
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the number of tasks in ArrayList<duke.Task>
     *
     * @return The number of tasks in the collection
     */
    public int getSize() {
        return tasks.size();
    }
    /**
     * Gets the duke.Task at the specific position of the ArrayList<duke.Task>
     *
     * @param i The position of the duke.Task
     * @return The duke.Task
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }
    /**
     * Gets the ArrayList<duke.Task>
     *
     * @return The collection
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    /**
     * displays the list of Tasks
     */
    public static void displayList() {
        System.out.println(indent + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            Task curr = tasks.get(i);
            System.out.println(indent + num + "." + curr.toString());
        }
    }
    /**
     * This method encapsulates the functionality of marking a task as completed or not
     * For example, the input 'mark 1' will mark the duke.Task at position 0 at the TaskArray as 'marked'
     *
     * @param string the input string
     */
    public void markDescription(String string) throws DukeException {
            String clean = string.replaceAll("\\D+", ""); //remove non-digits
            int pos = Integer.parseInt(clean) - 1;
            if (pos >= tasks.size() ) {
                throw new DukeException("You are trying to access a duke.Task that does not exist!");
            }
            Task curr = tasks.get(pos);

            if (string.contains("unmark")) {
                curr.markAsUnDone();
                System.out.println(indent + "OK, I've marked this task as not done yet:");
            } else if (string.contains("mark")) {
                curr.markAsDone();
                System.out.println(indent + "Nice! I've marked this task as done:");
            }
            System.out.println(megaIndent + curr.getStatusIconWithBracket() + " " + curr.description);
    }
    /**
     * For deadline and event Tasks, obtains the description of the duke.Task (before the first slash)
     * For example, the input 'event project meeting /from Mon 2pm /to 4pm' will return 'project meeting'
     *
     * @param string of the duke.Task
     * @return the description of the duke.Task
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
     * A method for the duke.Deadline class to obtain the by part of the duke.Task description
     * For example, the input 'deadline return book /by Sunday' will return 'Sunday'
     *
     * @param string the duke.Task description
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
     * A method for the duke.Event class to obtain the from part of the duke.Event description
     * For example, the input 'event project meeting /from Mon 2pm /to 4pm' will return 'Mon 2pm'
     *
     * @param string the duke.Task description
     * @return the from part of the event
     * @throws DukeException throws duke.DukeException if invalid input
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
     * A method for the duke.Event class to obtain the to part of the duke.Event description
     * For example, the input 'event project meeting /from Mon 2pm /to 4pm' will return '4pm'
     *
     * @param string the duke.Task description
     * @return the to part of the event
     * @throws DukeException throws duke.DukeException if invalid input
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
     * For an input such as 'todo borrow book', letter is 'T' and string is 'borrow book'
     *
     * @param letter the letter corresponding to the first letter of the duke.Task
     * @param string the string corresponding to the chunk of text after the word todo, deadline, or event
     */
    public static void addTask(String letter, String string) throws DukeException {
        if (letter.equals("T")) {
            tasks.add(new ToDo(string));
        }
        if (letter.equals("D")) {
            tasks.add(new Deadline(getDescription(string), convertToLocalDateTime(getBy(string))));
        }
        if (letter.equals("E")) {
            tasks.add(new Event(getDescription(string), getFrom(string), getTo(string)));
        }

        int tasksSize = tasks.size();
        System.out.println(indent + "Got it. I've added this task:");
        System.out.println(megaIndent + tasks.get(tasksSize - 1).toString());
        System.out.println(indent + "Now you have " + tasksSize + " tasks in the list.");

    }
    /**
     * This method encapsulates deleting of a task from TaskArray
     * For example, the input 'delete 3' will delete the duke.Task at position 2 of TaskArray
     *
     * @param string the input string
     */
    public void deleteTask(String string) throws DukeException {
        String clean = string.replaceAll("\\D+", ""); //remove non-digits
        int pos = Integer.parseInt(clean) - 1;
        if (pos >= tasks.size()) {
            throw new DukeException("You are trying to delete a duke.Task that does not exist");
        } else {
            System.out.println(indent + "Noted. I've removed this task:");
            System.out.println(megaIndent + tasks.get(pos).toString());
            tasks.remove(pos);
            System.out.println(indent + "Now you have " + tasks.size() + " tasks in the list.");
        }
    }
    /**
     * A function that takes in the by part of a duke.Deadline duke.Task, and converts it to a LocalDateTime
     * For example, the input 'Sunday 1700' will return the corresponding LocalDateTime
     *
     * @param string the by part of the duke.Deadline duke.Task
     * @return the LocalDateTime corresponding to the duke.Deadline
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
     * A function that helps convert a string to a LocalDateTime
     *
     * @param input the by part of the duke.Deadline duke.Task, eg. "2/12/2019 1800"
     * @param c whether or not the duke.Deadline is put in a '-' format or '/' format
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
     * A function that takes in a user input that is the day of the week and returns the
     * corresponding DayOfWeek
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
            default:
                // case "SUN"
                result = DayOfWeek.SUNDAY;
        }
        return result;
    }
}
