package duke.main;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * The Ui class.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the next command by the user.
     *
     * @return The command string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcomeMsg() {
        System.out.println("Hello! I'm Albatross");
        System.out.println("Please enter a command");
    }

    /**
     * Returns the goodbye message.
     */
    public String showGoodbyeMsg() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Prints a message when a task has been successfully added.
     *
     * @param taskString The string representation of the added task.
     * @param index The number of tasks in the list.
     */
    public String successfulAddTaskMsg(String taskString, int index) {
        String result = "";
        result += "Got it. I've added this task:\n";
        result += taskString + "\n";
        result += "Now you have " + index + " tasks in the list.\n";
        return result;
    }

    /**
     * Prints a message when a task has been successfully marked as done.
     *
     * @param taskString The string representation of the task.
     */
    public String successfulMarkDoneMsg(String taskString) {
        return "Nice! I've marked this task as done:\n" + taskString + "\n";
    }

    /**
     * Prints a message when a task has been successfully marked as not done.
     *
     * @param taskString The string representation of the task.
     */
    public String successfulMarkNotDoneMsg(String taskString) {
        return "OK, I've marked this task as not done yet:\n" + taskString + "\n";
    }

    /**
     * Prints a message when a task has been successfully deleted.
     *
     * @param taskString The string representation of the added task.
     * @param index The number of tasks in the list.
     */
    public String successfulTaskDeletionMsg(String taskString, int index) {
        String result = "";
        result += "Noted. I've removed this task:\n";
        result += taskString + "\n";
        result += "Now you have " + index + " tasks in the list.\n";
        return result;
    }

    /**
     * Prints all tasks in the task list.
     *
     * @param taskList The task list being printed.
     */
    public String produceTaskList(TaskList taskList) {
        return taskList.produceTaskList();
    }

    /**
     * Finds tasks that contain the keyword and prints them in a list.
     *
     * @param taskList The task list to find tasks from.
     * @param keyword The keyword input by the user.
     * @throws DukeException
     */
    public String findTasks(TaskList taskList, String keyword) throws DukeException {
        try {
            String result = "";
            ArrayList<Task> list = taskList.findTasks(keyword);
            result += "Here are the matching tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                result += ((i + 1) + ". " + list.get(i).userDisplayString()) + "\n";
            }
            return result;
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Returns the help list.
     *
     * @return the help list.
     */
    public String showHelpList() {
        String str = "Here are the formats of possible commands:\n"
                + "1. help\n"
                + "2. todo {taskname}\n"
                + "3. deadline {taskname} /by {YYYY-MM-DD}\n"
                + "4. event {taskname} /from {YYYY-MM-DD} /to {YYYY-MM-DD}\n"
                + "5. mark {index}\n"
                + "6. unmark {index}\n"
                + "7. delete {index}\n"
                + "8. find {keywords}\n"
                + "9. bye\n";

        return str;
    }
}
