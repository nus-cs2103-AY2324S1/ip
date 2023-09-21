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
    public String onEnter() {
        String output = "Hello! I am Duke\n";
        output += "What can I do for you?";
        return output;
    }

    /**
     * Display goodbye text.
     */
    public String onExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Read the command from the user.
     *
     * @return String containing the command to the Parser.
     * @throws DukeEmptyCommandException if the command is empty.
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
     *
     * @param tasks the container of the tasks.
     */
    public String NumOfTasks (TaskList tasks) {
        return "Now you have "
                + tasks.getCount()
                + (tasks.getCount() <= 1 ? " task" : " tasks")
                + " in the list.";
    }
}
