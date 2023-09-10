package duke;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * the collection that contains the list of Tasks
 */
public class TaskList implements Serializable {
    private static String indent = "";
    private static String megaIndent = "     ";
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the number of tasks in ArrayList(Task)
     *
     * @return The number of tasks in the collection
     */
    public int getSize() {
        return tasks.size();
    }
    /**
     * Gets the duke.Task at the specific position of the ArrayList(Task)
     *
     * @param i The position of the duke.Task
     * @return The duke.Task
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }
    /**
     * Gets the ArrayList(Task)
     *
     * @return The collection
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    /**
     * displays the list of Tasks
     */
    public String displayList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(indent + "Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            Task curr = tasks.get(i);
            stringBuilder.append(indent + num + ". " + curr.toString() + "\n");
        }
        return stringBuilder.toString();
    }
    /**
     * Displays the list of Tasks that description matches the user input
     * @param userInput the String that the user inputs to find similar Tasks
     */
    public String displayMatchingList(String userInput) {
        ArrayList<Task> temp = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskDescription = task.getDescription();
            if (taskDescription.contains(userInput)) {
                temp.add(task);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (temp.size() == 0) {
            stringBuilder.append(indent + "There are no matching tasks\n");
        } else {
            stringBuilder.append(indent + "Here are the matching tasks in your list:\n");
            for (int i = 0; i < temp.size(); i++) {
                int num = i + 1;
                Task curr = temp.get(i);
                stringBuilder.append(indent + num + "." + curr.toString() + "\n");
            }
        }
        return stringBuilder.toString();
    }
    /**
     * For an input such as 'todo borrow book', letter is 'T' and string is 'borrow book'
     *
     * @param letter the letter corresponding to the first letter of the duke.Task
     * @param string the string corresponding to the chunk of text after the word todo, deadline, or event
     */
    public String addTask(String letter, String string) throws DukeException {
        if (letter.equals("T")) {
            tasks.add(ToDo.newToDo(string));
        }
        if (letter.equals("D")) {
            tasks.add(Deadline.newDeadline(getDescription(string), getBy(string)));
        }
        if (letter.equals("E")) {
            tasks.add(Event.newEvent(getDescription(string), getFrom(string), getTo(string)));
        }

        int tasksSize = tasks.size();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(indent + "Got it. I've added this task:\n");
        stringBuilder.append(megaIndent + tasks.get(tasksSize - 1).toString() + "\n");
        stringBuilder.append(indent + "Now you have " + tasksSize + " tasks in the list.\n");
        return stringBuilder.toString();
    }
    /**
     * This method encapsulates the functionality of marking a task as completed or not
     * For example, the input 'mark 1' will mark the duke.Task at position 0 at the TaskArray as 'marked'
     * @param string the input string
     * @throws DukeException if input is invalid
     */
    public String markDescription(String string) throws DukeException {
        String clean = string.replaceAll("\\D+", ""); //remove non-digits
        int pos = Integer.parseInt(clean) - 1;
        if (pos >= tasks.size()) {
            throw new DukeException("You are trying to access a Task that does not exist!");
        }
        Task curr = tasks.get(pos);
        StringBuilder stringBuilder = new StringBuilder();
        if (string.contains("unmark")) {
            curr.markAsUnDone();
            stringBuilder.append(indent + "OK, I've marked this task as not done yet:\n");
        } else if (string.contains("mark")) {
            curr.markAsDone();
            stringBuilder.append(indent + "Nice! I've marked this task as done:\n");
        }
        stringBuilder.append(megaIndent + curr.getStatusIconWithBracket() + " " + curr.description + "\n");
        return stringBuilder.toString();
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
        if (first == -1 || !string.substring(first + 1, first + 3).contains("by")) {
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
     * This method encapsulates deleting of a task from TaskArray
     * For example, the input 'delete 3' will delete the duke.Task at position 2 of TaskArray
     *
     * @param string the input string
     */
    public String deleteTask(String string) throws DukeException {
        String clean = string.replaceAll("\\D+", ""); //remove non-digits
        int pos = Integer.parseInt(clean) - 1;
        if (pos >= tasks.size()) {
            throw new DukeException("You are trying to delete a Task that does not exist");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(indent + "Noted. I've removed this task:\n");
            stringBuilder.append(megaIndent + tasks.get(pos).toString() + "\n");
            tasks.remove(pos);
            stringBuilder.append(indent + "Now you have " + tasks.size() + " tasks in the list.\n");
            return stringBuilder.toString();
        }
    }

}
