package bob.ui;

import bob.exception.BobInvalidTaskNumberException;
import bob.task.Task;
import bob.task.TaskList;

/**
 * Generates text required for ChatBot GUI.
 */
public class TextGenerator {
    private static final String LOGO =
            ".-. .-')              .-. .-')   \n"
                    + "\\  ( OO )             \\  ( OO )  \n"
                    + " ;-----.\\  .-'),-----. ;-----.\\  \n"
                    + " | .-.  | ( OO'  .-.  '| .-.  |  \n"
                    + " | '-' /_)/   |  | |  || '-' /_) \n"
                    + " | .-. `. \\_) |  |\\|  || .-. `.  \n"
                    + " | |  \\  |  \\ |  | |  || |  \\  | \n"
                    + " | '--'  /   `'  '-'  '| '--'  / \n"
                    + " `------'      `-----' `------'  ";

    public static String getWelcomeMessage() {
        return "Hi, I'm Bob. How can I help you?";
    }

    public static String getFarewellMessage() {
        return "Goodbye! I'll see you next time";
    }

    public static String getNumOfTaskMessage(TaskList taskList) {
        int numOfTask = taskList.size();
        if (numOfTask == 0) {
            return "You currently have no tasks! Good Job!";
        } else if (numOfTask == 1) {
            return String.format("Now you have %d task in your list!", numOfTask);
        } else {
            return String.format("Now you have %d tasks in your list!", numOfTask);
        }
    }

    public static String getErrorMessage(Exception e) {
        return e.getMessage();
    }

    public static String getListMessage(TaskList taskList) throws BobInvalidTaskNumberException {
        return getRawList(taskList) + getNumOfTaskMessage(taskList);
    }

    public static String getMarkMessage(Task task) {
        return "Great Job! I've helped mark this task as done:\n" + task.toString();
    }

    public static String getUnmarkMessage(Task task) {
        return "No worries! I will help you unmark this task:\n" + task.toString();
    }

    public static String getDeleteMessage(Task task) {
        return "Foosh! Let it be gone! I've helped delete the task:\n"
                + task.toString();
    }

    public static String getAddTaskMessage(Task task) {
        return "I gotchu. New task added to the list:\n" + task.toString();
    }

    public static String getFindMessage(TaskList taskList) throws BobInvalidTaskNumberException {
        String message = "These are the matching tasks in your list:\n";
        return message + getRawList(taskList);
    }

    private static String getRawList(TaskList taskList) throws BobInvalidTaskNumberException {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append((i + 1)).append(". ");
            result.append(taskList.getTask(i).toString()).append("\n");
        }
        return result.toString();
    }

    public static String getMarkMultipleMessage(TaskList taskList) throws BobInvalidTaskNumberException {
        String message = "Nice one bud! I have marked all the following tasks as done:\n";
        return message + getRawList(taskList);
    }

    public static String getUnmarkMultipleMessage(TaskList taskList) throws BobInvalidTaskNumberException {
        String message = "No worries bud! I unmarked all the following tasks for you:\n";
        return message + getRawList(taskList);
    }
}
