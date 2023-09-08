package duke.processors;

import duke.exception.DukeEmptyCommandException;
import java.util.Scanner;

/**
 * A class accounts for user interactions.
 */
public class Ui {
    private final String SEPARATION = "_______________________________________________";
    private final Scanner SC = new Scanner(System.in);

    /**
     * an empty constructor.
     */
    public Ui() {
    }

    /**
     * Display welcome text.
     */
    public void OnEnter() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(SEPARATION);
        System.out.println("Hello! I am YOU");
        System.out.println("What can I do for you?");
        System.out.println(SEPARATION);
    }

    /**
     * Display goodbye text.
     */
    public void OnExit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SEPARATION);
    }

    /**
     * Read the command from the user.
     * @return command
     * @throws DukeEmptyCommandException if the command is empty
     */
    public String readCommand() throws DukeEmptyCommandException {
        String command = SC.nextLine();
        if (command.isEmpty()) {
            throw new DukeEmptyCommandException();
        }
        return command;
    }

    /**
     * print the number of tasks in the current ArrayList.
     * @param tasks the container of the tasks
     */
    public void printNumOfTasks (TaskList tasks) {
        System.out.println("Now you have "
                + tasks.getCount()
                + (tasks.getCount() <= 1 ? " task" : " tasks")
                + " in the list.");
    }

    /**
     * Prints the separation line
     */
    public void showLine() {
        System.out.println(SEPARATION);
    }

    /**
     * Prints the error message
     * @param err the error occurred
     */
    public void showError(String err) {
        System.out.println(err);
    }
}
