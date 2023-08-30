package duke.command;
import duke.tasklist.*;
import duke.tasks.*;
import duke.exception.*;

/**
 * Represents the actions needed when the user says Hi.
 */
public class StartCommand extends Command {

    /**
     * Constructor for the start command.
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     */
    public StartCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * Method to be executed when this command is called.
     * Prints out the chatbot name and introduces itself.
     */
    @Override
    public void execute() {
        String logo = "YONG";
        System.out.println("Hello I'm " + logo + "\n" +
                "What can I do for you? \n" +
                "-------------------------------------"
        );
    }
}
