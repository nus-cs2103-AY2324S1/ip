package chatty.utils;

import java.util.Scanner;

import chatty.exception.InvalidTaskNumberException;
import chatty.task.Task;
import chatty.task.TaskList;


/**
 * Responsible for interactions with the user.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Constructor for the Ui class.
     * Initializes the chatbot.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Gets input from the user.
     *
     * @return The input from the user.
     */
    public String getInput() {
        String input = scanner.nextLine();
        assert !input.isEmpty() : "User input should not be empty!";
        return input;
    }

    /**
     * Prints the greeting message when the user starts the chatbot.
     *
     * @return The greeting message.
     */
    public String showGreet() {
        return "Hi there! I'm Chatty! \n How can I help you today?";
    }

    /**
     * Prints the exit message when the user wants to exit the chatbot.
     *
     * @return The goodbye message.
     */
    public String showExit() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Prints the exit message when the user wants to exit the chatbot.
     *
     * @return The goodbye message.
     */
    public String showList(TaskList taskList) {
        assert taskList.listSize() >= 0 : "Task list size should not be negative.";
        if (taskList.listSize() == 0) {
            return "There is currently no task in your list.";
        } else {
            StringBuilder result = new StringBuilder("Here are the task(s) in your list: \n");
            for (int i = 0; i < taskList.listSize(); i++) {
                result.append(i + 1).append(". ").append(taskList.getTask(i)).append("\n");
            }
            return result.toString();
        }
    }

    /**
     * Prints the message to show the user that the task has been mark done.
     *
     * @param i The index of the task the user wants to mark as done
     * @param taskList the task list consisting of all the available tasks
     * @return The specified message
     */
    public String showDone(int i, TaskList taskList) {
        return "Nice! I've marked this task as done: \n" + "         " + taskList.showTask(i);
    }

    /**
     * Prints the message to show the user that the task has been mark as undone.
     *
     * @param i The index of the task the user wants to mark as undone
     * @param taskList the task list consisting of all the available tasks
     * @return The specified message
     */
    public String showUndone(int i, TaskList taskList) {
        return "Ok, I've marked this task as not done: \n" + "           " + taskList.showTask(i);
    }


    /**
     * Prints the message to show the user that the task has been deleted
     *
     * @param i The index of the task the user wants to delete
     * @param taskList the task list consisting of all the available tasks
     * @return The specified message
     * @exception InvalidTaskNumberException when the index of the task is not a valid index
     */
    public String showDelete(int i, TaskList taskList) throws InvalidTaskNumberException {
        assert i >= 0 && i < taskList.listSize() : "Invalid task index: " + i;
        return "Alright, I've removed this task from the list: \n"
                + "           " + taskList.deleteTask(i) + "\n"
                + "Now you have " + taskList.listSize() + " task(s) in your list.";
    }

    /**
     * Prints the message to show the user that the task has been added to the list
     *
     * @param task The task to be added into the list
     * @param taskList the task list consisting of all the available tasks
     * @return The specified message
     */
    public String showAdded(Task task, TaskList taskList) {
        return "Got it. I've added this task into the list: \n"
                + "         " + task + "\n"
                + "You now have " + taskList.listSize() + " task(s) in the list.";
    }

    /**
     * Prints the message to tell the user that the command is not valid
     *
     * @return The specified message
     */
    public String showInvalid() {
        return "Sorry, I don't understand this command";
    }

    /**
     * Prints the list of task that matched the specified keyword
     *
     * @param matchedTask the list of task that matched the keyword
     * @return The specified message + the list of matching task
     */
    public String showMatched(String matchedTask) {
        if (matchedTask.isEmpty()) {
            return "There is no tasks matching your keyword";
        } else {
            return "Here are the list of task(s) matching your keyword: \n" + matchedTask;
        }
    }
    public String showSet(String alias, String command) {
        return String.format("I have set [ %s ] as the alias for [ %s ] command", alias, command);
    }
    public String showReplace(String alias, String command) {
        return String.format("I have replaced the alias for [ %s ] command to [ %s ]", command, alias);
    }

    public String showInUse(String alias) {
        return String.format("Error! The alias entered, [ %s ], is currently being used for another command", alias);
    }
}
