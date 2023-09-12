package duke.tasks;

import duke.exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

/**
 * Class to handle individual task and task list manipulation.
 */
public class TaskList {
    private ArrayList<Task> taskArr;
    public TaskList(ArrayList<Task> taskArr) {
        this.taskArr = taskArr;
    }
    public TaskList() {
        this.taskArr = new ArrayList<>();
    }

    /**
     * Creates a Todo task in taskArr.
     * @param desc Description of the Todo task
     */
    public String addTodo(String desc) {
        Todo curr = new Todo(desc);
        taskArr.add(curr);

        return curr.toString();
    }
    /**
     * Creates a Deadline task in taskArr.
     * @param desc Description of the Deadline task
     * @param date Date to complete the Deadline task by
     */
    public String addDeadline(String desc, String date) {
        Deadline curr = new Deadline(desc, date);
        taskArr.add(curr);

        return curr.toString();
    }
    /**
     * Creates an Event task in taskArr.
     * @param desc Description of the Event task
     * @param timeline Start and End date of the Event task
     */
    public String addEvent(String desc, String timeline) {
        Event curr = new Event(desc, timeline);
        taskArr.add(curr);

        return curr.toString();
    }
    /**
     * Mark a specific task in the Task ArrayList as done.
     * @param input User input for mark action
     * @throws WrongInput Error when user enters illegal input
     */
    public String mark(String[] input) throws WrongInput {
        int size = taskArr.size();

        try {
            int index = Integer.parseInt(input[1]);
            if (index > size || index < 0) {
                throw new WrongInput();
            } else {
                taskArr.get(index - 1).mark();
                return "Got it, I've marked this task:\n"
                + taskArr.get(index - 1).toString();
            }
        } catch (NumberFormatException e) {
            throw new WrongInput();
        }
    }
    /**
     * Unmarks a specific task in the Task ArrayList as not done.
     * @param input User input for unmark action
     * @throws WrongInput Error when user enters illegal input
     */
    public String unmark(String[] input) throws WrongInput {
        int size = taskArr.size();

        try {
            int index = Integer.parseInt(input[1]);
            if (index > size || index < 0) {
                throw new WrongInput();
            } else {
                taskArr.get(index - 1).unmark();
                return "Got it, I've unmarked this task:\n"
                        + taskArr.get(index - 1).toString();
            }
        } catch (NumberFormatException e) {
            throw new WrongInput();
        }
    }
    /**
     * Deletes a specified task from the Task ArrayList.
     * @param input User input for delete action
     * @throws WrongInput Error when user enters illegal input
     */
    public String delete(String[] input) throws WrongInput {
        int size = taskArr.size();

        try {
            int num = Integer.parseInt(input[1]);
            if (num > size || num < 0) {
                throw new WrongInput();
            } else {
                int index = num - 1;
                Task toRemove = taskArr.get(index);
                taskArr.remove(index);
                return "Noted, I've removed this task:\n"
                        + toRemove.toString();
            }
        } catch (NumberFormatException e) {
            throw new WrongInput();
        }
    }
    /**
     * Lists out all the tasks in taskArr.
     */
    public String listOut() {
        int size = taskArr.size();
        String response = "Here are the tasks in your list:\n";

        for (int i = 0; i < size; i++) {
            response += i + 1 + ". " + taskArr.get(i).toString() + "\n";
        }

        return response + countTotalTasks();
    }
    /**
     * String to show the user the current state of the Task ArrayList.
     * @return String containing all information of the current Task ArrayList
     */
    public String countTotalTasks() {
        int size = taskArr.size();

        return "\nNow you have " + size + " tasks in the list.";
    }
    /**
     * String representing what the user input into the scanner
     * @param arr User input converted to String array
     * @return Description of the task specified by user
     */
    public String getDescription(String[] arr) {
        String result = null;

        // can safely ignore the first element as we have already
        // checked for the task type in main logic
        for (int i = 1; i < arr.length; i++) {

            if (arr[i].equals("/by")) {
                break;
            } else if (arr[i].equals("/from")) {
                break;
            }

            if (result == null) {
                result = arr[i];
            } else {
                result += " " + arr[i];
            }
        }

        return result;
    }
    /**
     * Retrieves deadline of a Deadline task
     * @param arr User input converted to String array
     * @return Task deadline set by user in Month Day Year format
     */
    public String getDeadline(String[] arr) {
        String input = null;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("/by")) {
                input = arr[i + 1];
            }
        }

        String result = LocalDate.parse(input)
                .format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return result;
    }
    /**
     * Retrieves timeline of an Event task
     * @param arr User input converted to String array
     * @return Event timeline set by user in Month Day Year format
     */
    public String getEventTimeline(String[] arr) {
        String fromInput = null;
        String toInput = null;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("/from")) {
                fromInput = arr[i + 1];
                toInput = arr[i + 3];
            }
        }

        String from = LocalDate.parse(fromInput)
                .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String to = LocalDate.parse(toInput)
                .format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return from + " - " + to;
    }
    public String find(String[] arr) throws WrongInput {
        if (arr.length == 1) {
            throw new WrongInput();
        } else {
            String response = "Here are the matching tasks in your list:\n";

            int[] result = new int[taskArr.size()];
            String term = arr[1];

            for (int i = 0; i < taskArr.size(); i++) {
                String currDescription = taskArr.get(i).desc();
                if (currDescription.toLowerCase().contains(term.toLowerCase())) {
                    result[i] = 1;
                } else {
                    result[i] = 0;
                }
            }

            for (int i = 0; i < taskArr.size(); i++) {
                if (result[i] == 1) {
                    response += taskArr.get(i).toString();
                }
            }
            return response;
        }
    }
    public ArrayList<Task> retrieveArray() {
        return this.taskArr;
    }
}
