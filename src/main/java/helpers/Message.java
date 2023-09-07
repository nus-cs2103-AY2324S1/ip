package helpers;

import exceptions.EmptyTasksException;
import exceptions.ErrorStorageException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;
import exceptions.InvalidIndexException;
import exceptions.InvalidTimeFormatException;
import tasks.Task;

/**
 * Represents Message class that operations to print out messages for users
 */
public class Message {
    final boolean isDebug = true;
    /**
     * Method to show message that task has been marked
     *
     * @param task Task to mark as done
     * @return String of message
     */
    public String showMarkDoneMessage(Task task) {
        return "I have marked this task as done per your request, macho!\n" + task;
    }

    /**
     * Method to show message that task has been unmarked
     *
     * @param task Task to unmarked as done
     * @return String of message
     */
    public String showUnmarkDoneMessage(Task task) {
        return "I have marked this task as undone yet, per your request, macho!\n" + task;
    }

    /**
     * Method to show message that task has been deleted
     *
     * @param taskList List of tasks
     * @param task     Task to be deleted
     * @return String of message
     */
    public String showDeletedTaskMessage(TaskList taskList, Task task) {
        return "I have deleted this task as done per your request, macho!\n" + task.toString()
                + "\nYou now have " + taskList.getListLength() + " tasks in the list, macho!";
    }

    /**
     * Method to show message that task has been added
     *
     * @param taskList List of tasks
     * @param task     Task to be added
     * @return String of message
     */
    public String showAddTaskMessage(TaskList taskList, Task task) {
        return "Got it macho! I've added this task:\n" + task + "\n"
                + "You now have " + taskList.getListLength() + " tasks in the list, macho!";
    }

    /**
     * Method to show message on the list of tasks
     *
     * @param taskList List of tasks
     * @return String of message
     */
    public String showTaskList(TaskList taskList) {
        return taskList.printTaskList();
    }

    /**
     * Method to show message on the filtered list of tasks
     *
     * @param taskList List of tasks
     * @return String of message
     */
    public String showFilteredTaskList(TaskList taskList, String input) {
        return taskList.filterTaskList(input);
    }

    /**
     * Method to show error message if loading of storage fails
     *
     * @return String of message
     */
    public String showLoadingError() {
        return new ErrorStorageException("").toString();
    }

    /**
     * Method to show error message if there is invalid argument
     *
     * @param substring String with invalid argument
     * @param s         Command for correct argument
     * @return String of message
     */
    public String showArgumentErrorMessage(String substring, String s) {
        return new InvalidArgumentException(substring, s).toString();
    }


    /**
     * Method to show error message if there is invalid datetime format
     *
     * @param part String containing the invalid format
     * @return String of message
     */
    public String showInvalidTimeFormatErrorMessage(String part) {
        return new InvalidTimeFormatException(part).toString();
    }

    /**
     * Method to show error message if there is invalid index
     *
     * @param input String containing the invalid index
     * @return String of message
     */
    public String showInvalidIndexErrorMessage(String input) {
        return new InvalidIndexException(input).toString();
    }


    /**
     * Method to show error message if there is invalid command entered
     *
     * @param message String containing the invalid command
     * @return String of message
     */
    public String showInvalidCommandErrorMessage(String message) {
        return new InvalidCommandException(message).toString();
    }

    /**
     * Method to show error message if there is invalid command entered
     *
     * @param message String containing the invalid command
     * @return String of message
     */
    public String showInvalidArgumentErrorMessage(String command, String message) {
        return new InvalidArgumentException(message, command).toString();
    }

    /**
     * Method to show error message if user is performing actions on empty tasks
     *
     * @return String of message
     */
    public String showEmptyTasksError(String input) {
        return new EmptyTasksException(input).toString();
    }

}
