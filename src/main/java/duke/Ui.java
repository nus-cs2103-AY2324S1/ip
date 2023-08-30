package duke;

import java.util.ArrayList;

/**
 * This class encapsulates the interactions with the user by displaying lines into the output.
 */
public class Ui {

    /**
     * Prints the welcome message when the user starts the program.
     */
    public void welcomeMessage() {
        System.out.println("WEEWOOWEEWOO WELCOME! I'm Siren");
        System.out.println("What can I do for you?");
        horizontalLine();
    }

    /**
     * Prints the string that is given.
     *
     * @param string the string to be printed out.
     */
    public static void print(String string) {
        System.out.println(string);
    }

    /**
     * Prints out a horizontal line.
     */
    public static void horizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the goodbye message when the user exits the program.
     */
    public static void goodbyeMessage() {
        System.out.println("WEEEWOOWEEWOO GOODBYE! Hope to see you again soon!");
    }

    /**
     * Prints the exception's message.
     *
     * @param e the exception that will have its message printed.
     */
    public static void showExceptionError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints the message to indicate a saved file is found.
     */
    public static void savedFileFound() {
        horizontalLine();
        System.out.println("BLINGBLING! You have a saved file! Displaying the contents to you (if any)!");
    }

    /**
     * Prints the message to indicate there is no saved file found.
     */
    public static void savedFileNotFound() {
        horizontalLine();
        System.out.println("OH NO! I couldn't find a \"duke.txt\" file in your data directory, "
                + "I'll be creating one for you!");
        horizontalLine();
    }

    /**
     * Prints the message to indicate the specific directory cannot be found.
     */
    public static void directoryNotFound() {
        horizontalLine();
        System.out.println("OH NO! I couldn't find a \"data\" directory in your project root directory, "
                + "I'll be creating one for you!");
    }

    /**
     * Prints the message when the user deletes a task.
     *
     * @param description the description of task that was deleted.
     * @param arraySize the number of tasks left.
     */
    public static void deleteTaskOutput(String description, int arraySize) {
        System.out.println("ALRIGHTY! I've removed this task:");
        System.out.println(description);
        System.out.println("Now you have " + arraySize + " tasks in the list.");
    }

    /**
     * Prints the message when the user adds a task.
     *
     * @param taskList the TaskList object that contains the array list containing the tasks.
     */
    public static void addedTaskOutput(TaskList taskList) {
        System.out.println("DINGDONG GOT IT! I've added this task:");
        System.out.println(taskList.taskArray.get(taskList.taskArray.size() - 1));
        System.out.println("Now you have " + taskList.taskArray.size() + " tasks in the list.");
    }

    /**
     * Prints the message when the user marks a task.
     *
     * @param marked true if the task was previously marked, false otherwise.
     */
    public static void taskMarked(boolean marked) {
        if (marked) {
            System.out.println("WEEYA! Task was already marked as done!");
        } else {
            System.out.println("GOTCHYA! I've marked this task as done!");
        }
    }

    /**
     * Prints the message when the user unmarks a task.
     *
     * @param notMarked true if the task was previously unmarked, false otherwise.
     */
    public static void taskNotMarked(boolean notMarked) {
        if (notMarked) {
            System.out.println("OOPSIE! Task was already marked as not done!");
        } else {
            System.out.println("HONKHONK! I've marked this task as not done yet!");
        }
    }

    /**
     * Prints the message when the array list containing the tasks.
     *
     * @param taskArray the TaskList object that contains the array list containing the tasks.
     * @param isMatch true if the user command is a "find" command, false otherwise.
     */
    public static void showTaskList(ArrayList<Task> taskArray, boolean isMatch) {
        if (taskArray.isEmpty() && !isMatch) {
            System.out.println("HEYYYYYYYY! There's nothing to show in your list!");
        } else if (taskArray.isEmpty()) {
            System.out.println("HAIYAAAA! There's nothing in your list that matches the keyword!");
        }
        if (!taskArray.isEmpty() && !isMatch) {
            System.out.println("WHEET WHEET WHEET! Here are the tasks in your list:");
        } else if (!taskArray.isEmpty()) {
            System.out.println("HOOOOOYEAAAAA! Here are the matching tasks in your list:");
        }
        for (int i = 0; i < taskArray.size(); i++) {
            System.out.println(i + 1 + "." + taskArray.get(i));
        }
    }
}
