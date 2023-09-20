package duke;

import Exceptions.DukeArgumentException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class encapsulates the current tasks.
 */
public class TaskList {
    protected static ArrayList<Task> taskArray;
    protected static Storage storage;
    protected static int numTask;
    protected static Parser parser;
    private static final String INVALID_TASK_NUMBER_MESSAGE = "I think you keyed in the wrong task number..";
    private static final String EMPTY_DESCRIPTION_MESSAGE = "OOPS!!! The description of todo cannot be empty :(";
    /**
     * Constructor method for the duke.TaskList class.
     * @param loadTask The list of tasks to be used.
     * @param store The place where the tasks are stored.
     */
    public TaskList(ArrayList<Task> loadTask, Storage store) {
        taskArray = loadTask;
        numTask = taskArray.size();
        storage = store;
        parser = new Parser();
    }
    /**
     * Empty constructor method for the duke.TaskList class.
     */
    public TaskList() {
        taskArray = new ArrayList<>();
        parser = new Parser();
    }
    /**
     * Marks a provided task as not done.
     * @param i The information of the task to be unmarked.
     * @return Message regarding the outcome of the task.
     * @throws DukeArgumentException DukeArgumentException is thrown if the provided task number is invalid.
     */
    public String unmark(String i) throws DukeArgumentException {
        // checks for missing index
        if (parser.commandSplit(i).length < 2) {
            throw new DukeArgumentException("Which task do you want to mark as not done? Write your command as:\n"
                    + "unmark <INDEX_OF_TASK>");
        }
        int taskId = Integer.parseInt(i.substring(7)) - 1;
        String message;
        if (taskId < 0 || taskId >= numTask) {
            throw new DukeArgumentException(INVALID_TASK_NUMBER_MESSAGE);
        }
        message = taskArray.get(taskId).markAsUndone();
        // update the duke.txt
        try {
            storage.saveTask(taskArray);
        } catch (IOException e) {
            return "Uhm.. something is not working right..";
        }
        return message;
    }
    /**
     * Marks a provided task as done.
     * @param i The information of the task to be marked done.
     * @return Message regarding the outcome of the task.
     * @throws DukeArgumentException DukeArgumentException is thrown if the provided task number is invalid.
     */
    public String mark(String i) throws DukeArgumentException {
        // checks for missing index
        if (parser.commandSplit(i).length < 2) {
            throw new DukeArgumentException("Which task to you want to mark as done? Write your command as:\n"
                    + "mark <INDEX_OF_TASK>");
        }
        int taskId = Integer.parseInt(i.substring(5)) - 1;
        String message;
        if (taskId < 0 || taskId >= numTask) {
            throw new DukeArgumentException(INVALID_TASK_NUMBER_MESSAGE);
        }
        message = taskArray.get(taskId).markAsDone();
        // update the duke.txt
        try {
            storage.saveTask(taskArray);
        } catch (IOException e) {
            return "Uhm.. something is not working right..";
        }
        return message;
    }
    /**
     * Deletes a task from the task list.
     * @param i The information of the task to be deleted.
     * @return Message regarding the outcome of the task.
     * @throws DukeArgumentException DukeArgumentException is thrown if the provided task number is invalid.
     */
    public static String deleteTask(String i) throws DukeArgumentException {
        if (parser.commandSplit(i).length < 2) {
            throw new DukeArgumentException("Which task do you want to delete? Write your command as:\n"
                    + "delete <INDEX_OF_TASK>");
        }
        int deleteTask = Integer.parseInt(i.substring(7)) - 1;
        if (deleteTask < 0 || deleteTask >= numTask) {
            throw new DukeArgumentException(INVALID_TASK_NUMBER_MESSAGE);
        }
        Task removed = taskArray.get(deleteTask);
        taskArray.remove(deleteTask);
        numTask--;
        assert taskArray.size() == numTask : "Task is not deleted successfully.";
        return "Noted. I've removed this task:\n"
                + removed.printDesc() + "\n"
                + "Now you have " + numTask + " tasks in the list.";
    }
    /**
     * Retrieves the full task list.
     * @return The full task list.
     */
    public static String listTask() {
        if (numTask == 0) {
            return "Good news! You have no tasks in your list!";
        } else {
            String message;
            message = "Here are the tasks in your list:\n";
            for (int a = 0; a < numTask; a++) {
                message += (a + 1) + ". " + taskArray.get(a).printDesc() + "\n";
            }
            return message;
        }
    }
    /**
     * Adds a Todo task to the task list.
     * @param i The information of the Todo task to be added.
     * @return Message regarding the outcome of the task.
     * @throws DukeArgumentException DukeArgumentException is thrown if the provided information is invalid.
     */
    public static String todoTask(String i) throws DukeArgumentException {
        String[] taskDetails = parser.commandSplit(i);
        String message;
        if (taskDetails.length != 2) {
            throw new DukeArgumentException(EMPTY_DESCRIPTION_MESSAGE);
        }
        taskArray.add(new Todo(taskDetails[1]));
        message = taskArray.get(numTask).printMessage(numTask);
        numTask++;
        assert taskArray.size() == numTask : "Task is not added successfully.";
        return message;
    }
    /**
     * Adds a Deadline task to the task list.
     * @param i The information of the Deadline task to be added.
     * @return Message regarding the outcome of the task.
     * @throws DukeArgumentException DukeArgumentException is thrown if the provided information is invalid.
     */
    public static String deadlineTask(String i) throws DukeArgumentException {
        String[] taskDetails = parser.commandSplit(i);
        String message;
        if (taskDetails.length != 2) {
            throw new DukeArgumentException(EMPTY_DESCRIPTION_MESSAGE);
        }
        String[] deadlineDetails = parser.deadlineDetails(taskDetails[1]);
        if (deadlineDetails.length != 2) {
            throw new DukeArgumentException("Something is missing. Write your deadline as:\n"
                    + "description + /by YYYY-MM-ddThh:mm:ss");
        }
        taskArray.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
        message = taskArray.get(numTask).printMessage(numTask);
        numTask++;
        assert taskArray.size() == numTask : "Task is not added successfully.";
        return message;
    }
    /**
     * Adds a Event task to the task list.
     * @param i The information of the Event task to be added.
     * @return Message regarding the outcome of the task.
     * @throws DukeArgumentException DukeArgumentException is thrown if the provided information is invalid.
     */
    public static String eventTask(String i) throws DukeArgumentException {
        String[] taskDetails = parser.commandSplit(i);
        String message;
        if (taskDetails.length != 2) {
            throw new DukeArgumentException(EMPTY_DESCRIPTION_MESSAGE);
        }
        String[] eventDetails = parser.eventDetails(taskDetails[1]);
        if (eventDetails.length != 3) {
            throw new DukeArgumentException("Something is missing. Write your event as:\n"
                    + "description + /from YYYY-MM-ddThh:mm:ss + /to YYYY-MM-ddThh:mm:ss");
        }
        taskArray.add(new Event(eventDetails[0], eventDetails[1].substring(5),
                eventDetails[2].substring(3)));
        message = taskArray.get(numTask).printMessage(numTask);
        numTask++;
        assert taskArray.size() == numTask : "Task is not added successfully.";
        return message;
    }
    protected ArrayList<Task> getTaskArray() {
        return taskArray;
    }
    protected String findTask(String info) throws DukeArgumentException {
        if (parser.commandSplit(info).length < 2) {
            throw new DukeArgumentException("What task are you looking for? Write your command as:\n"
                    + "find <TASK_NAME>");
        }
        String keyword = (parser.commandSplit(info))[1];
        String message;
        ArrayList<Task> keywordTasks = new ArrayList<>();
        for (int i = 0; i < numTask; i++) {
            if (taskArray.get(i).getDesc().contains(keyword)) {
                keywordTasks.add(taskArray.get(i));
            }
        }
        if (keywordTasks.size() == 0) {
            return "I can't find any matching task with that keyword.";
        } else {
            message = "Here are the matching tasks in your list:\n";
            for (int a = 0; a < keywordTasks.size(); a++) {
                message += (a + 1) + ". " + keywordTasks.get(a).printDesc() + "\n";
            }
            return message;
        }
    }
    protected String setReminder(String info) throws DukeArgumentException {
        if (parser.commandSplit(info).length < 2) {
            throw new DukeArgumentException("Which task do you want to set reminder for? Write your command as:\n"
                    + "reminder <INDEX_OF_TASK>");
        }
        int taskId = Integer.parseInt(info.substring(9)) - 1;
        String message;
        if (taskId < 0 || taskId >= numTask) {
            throw new DukeArgumentException(INVALID_TASK_NUMBER_MESSAGE);
        }
        message = taskArray.get(taskId).makeReminder();
        // update the duke.txt
        try {
            storage.saveTask(taskArray);
        } catch (IOException e) {
            return "Uhm.. something is not working right..";
        }
        return message;
    }
    protected static String printReminders() {
        String message;
        int counter = 0;
        message = "Here are some reminders for you:\n";
        for (int a = 0; a < numTask; a++) {
            if (taskArray.get(a).isReminder) {
                message += (counter + 1) + ". " + taskArray.get(a).printDesc() + "\n";
                counter++;
            }
        }
        if (counter == 0) {
            return "You have no reminders for today.";
        } else {
            return message;
        }
    }
    public String getHelp() {
        String message = "Here are some of the commands that I can execute!:\n"
                + "1. List out the all the tasks\n"
                + "   Command format: list()\n"
                + "2. Adding Todo task\n"
                + "   Command format: todo TASK_DESCRIPTION\n"
                + "3. Adding Event task\n"
                + "   Command format: event TASK_DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME\n"
                + "   All DATE_TIME must be in the format of YYYY-MM-ddThh:mm:ss\n"
                + "4. Adding Deadline task\n"
                + "   Command format: deadline TASK_DESCRIPTION /by DATE_TIME\n"
                + "   DATE_TIME must be in the format of YYYY-MM-ddThh:mm:ss\n"
                + "5. Marking a task as done\n"
                + "   Command format: mark TASK_INDEX\n"
                + "6. Marking a task as not done\n"
                + "   Command format: unmark TASK_INDEX\n"
                + "7. Delete a task\n"
                + "   Command format: delete TASK_INDEX\n"
                + "8. Set reminder for a task\n"
                + "   Command format: reminder TASK_INDEX\n"
                + "9. Find a task given a keyword\n"
                + "   Command format: find TASK_KEYWORD\n";
        return message;
    }
}
