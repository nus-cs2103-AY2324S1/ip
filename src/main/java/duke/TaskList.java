package duke;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The collection that contains the list of Tasks
 */
public class TaskList implements Serializable {
    private static final String MEGA_INDENT = "    ";
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
        stringBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            Task curr = tasks.get(i);
            stringBuilder.append(num + ". " + curr.toString() + "\n");
        }
        return stringBuilder.toString();
    }
    /**
     * Displays the list of Tasks that description matches the user input
     * @param userInput the String that the user inputs to find similar Tasks
     */
    public String displayMatchingList(String userInput) {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task task : tasks) {
            String taskDescription = task.getDescription();
            if (taskDescription.contains(userInput)) {
                temp.add(task);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (temp.isEmpty()) {
            stringBuilder.append("There are no matching tasks\n");
        } else {
            stringBuilder.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < temp.size(); i++) {
                int num = i + 1;
                Task curr = temp.get(i);
                stringBuilder.append(num + "." + curr.toString() + "\n");
            }
        }
        return stringBuilder.toString();
    }
    /**
     * For an input such as 'todo borrow book', letter is 'T' and string is 'borrow book'
     *
     * @param letter the letter corresponding to the first letter of the duke.Task
     * @param userInput the string corresponding to the chunk of text after the word todo, deadline, or event
     */
    public String addTask(String letter, String userInput) throws DukeException {
        if (letter.equals("T")) {
            tasks.add(ToDo.newToDo(userInput));
        }
        if (letter.equals("D")) {
            tasks.add(Deadline.newDeadline(getDescription(userInput), getBy(userInput)));
        }
        if (letter.equals("E")) {
            tasks.add(Event.newEvent(getDescription(userInput), getFrom(userInput), getTo(userInput)));
        }

        int tasksSize = tasks.size();
        StringBuilder string = new StringBuilder();
        string.append("Got it. I've added this task:\n");
        string.append(MEGA_INDENT + tasks.get(tasksSize - 1).toString() + "\n");
        string.append("Now you have " + tasksSize + " tasks in the list.\n");
        return string.toString();
    }
    /**
     * This method encapsulates the functionality of marking a task as completed or not
     * For example, the input 'mark 1' will mark the duke.Task at position 0 at the TaskArray as 'marked'
     * @param userInput the input string
     * @throws DukeException if input is invalid
     */
    public String markDescription(String userInput) throws DukeException {
        String digits = userInput.replaceAll("\\D+", ""); //remove non-digits
        int pos = Integer.parseInt(digits) - 1;
        if (pos >= tasks.size()) {
            throw new DukeException("You are trying to access a Task that does not exist!");
        }
        Task curr = tasks.get(pos);
        StringBuilder stringBuilder = new StringBuilder();
        if (userInput.contains("unmark")) {
            curr.markAsUnDone();
            stringBuilder.append("OK, I've marked this task as not done yet:\n");
        } else if (userInput.contains("mark")) {
            curr.markAsDone();
            stringBuilder.append("Nice! I've marked this task as done:\n");
        }
        stringBuilder.append(MEGA_INDENT + curr.getStatusIconWithBracket() + " " + curr.getDescription() + "\n");
        return stringBuilder.toString();
    }
    /**
     * For deadline and event Tasks, obtains the description of the duke.Task (before the first slash)
     * For example, the input 'event project meeting /from Mon 2pm /to 4pm' will return 'project meeting'
     *
     * @param userInput of the duke.Task
     * @return the description of the duke.Task
     */
    public static String getDescription(String userInput) {
        int len = userInput.length();
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (userInput.charAt(i) == '/') {
                break;
            }
            count++;
        }
        return userInput.substring(0, count);
    }
    /**
     * A method for the duke.Deadline class to obtain the by part of the duke.Task description
     * For example, the input 'deadline return book /by Sunday' will return 'Sunday'
     *
     * @param userInput the duke.Task description
     * @return the deadline
     * @throws DukeException if the input string is formatted wrongly
     */
    public static String getBy(String userInput) throws DukeException {
        String slash = "/";
        int first = userInput.indexOf(slash);
        if (first == -1 || !userInput.substring(first + 1, first + 3).contains("by")) {
            throw new DukeException("You need to add a by timing!");
        }
        return userInput.substring(first + 4); // returns "Sunday"
    }

    /**
     * A method for the duke.Event class to obtain the from part of the duke.Event description
     * For example, the input 'event project meeting /from Mon 2pm /to 4pm' will return 'Mon 2pm'
     *
     * @param userInput the duke.Task description
     * @return the from part of the event
     * @throws DukeException throws duke.DukeException if invalid input
     */
    public static String getFrom(String userInput) throws DukeException {
        String slash = "/";
        int firstSlash = userInput.indexOf(slash);
        int secondSlash = userInput.indexOf(slash, firstSlash + 1);

        if (firstSlash == -1 || secondSlash == -1
                || !userInput.substring(firstSlash, firstSlash + 5).equals("/from")) {
            throw new DukeException("You need to add a /from and /to for events");
        }

        return userInput.substring(firstSlash + 6, secondSlash - 1);
    }
    /**
     * A method for the duke.Event class to obtain the to part of the duke.Event description
     * For example, the input 'event project meeting /from Mon 2pm /to 4pm' will return '4pm'
     *
     * @param userInput the duke.Task description
     * @return the to part of the event
     * @throws DukeException throws duke.DukeException if invalid input
     */
    public static String getTo(String userInput) throws DukeException {
        String slash = "/";
        int firstSlash = userInput.indexOf(slash);
        int secondSlash = userInput.indexOf(slash, firstSlash + 1);

        if (!userInput.substring(secondSlash, secondSlash + 3).equals("/to")) {
            throw new DukeException("You need to add a /to for events");
        }
        return userInput.substring(secondSlash + 4);
    }
    /**
     * This method encapsulates deleting of a task from TaskArray
     * For example, the input 'delete 3' will delete the duke.Task at position 2 of TaskArray
     *
     * @param userInput the input string
     */
    public String deleteTask(String userInput) throws DukeException {
        String digits = userInput.replaceAll("\\D+", ""); //remove non-digits
        int pos = Integer.parseInt(digits) - 1;
        if (pos >= tasks.size()) {
            throw new DukeException("You are trying to delete a Task that does not exist");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Noted. I've removed this task:\n");
            stringBuilder.append(MEGA_INDENT + tasks.get(pos).toString() + "\n");
            tasks.remove(pos);
            stringBuilder.append("Now you have " + tasks.size() + " tasks in the list.\n");
            return stringBuilder.toString();
        }
    }

}
