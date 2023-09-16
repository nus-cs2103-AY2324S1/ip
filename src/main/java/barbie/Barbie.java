package barbie;

import java.util.ArrayList;

import barbie.commands.Command;
import barbie.commands.ExitCommand;
import barbie.types.Task;


/**
 * Implements the main Barbie chatbot logic.
 */
public class Barbie {
    Parser parser;
    MainWindow mainWindow;
    ArrayList<Task> taskList;
    boolean doExit;

    /**
     * Constructs an instance of Barbie.
     */
    public Barbie() {
        this.parser = new Parser();
        this.taskList = Storage.getLastList();
        this.doExit = false;

    }

    /**
     * Gets the doExit variable -- to see if the application should exit or not.
     * @return whether the application should exit
     */
    public boolean getDoExit() {
        return this.doExit;
    }

    /**
     * Gets barbie's response to the user input.
     * @param input user input
     * @return barbie's response
     */
    protected String getResponse(String input) {
        String toPrint;
        try {
            Command command = parser.parse(input);
            toPrint = command.run(taskList);
            if (command instanceof ExitCommand) {
                this.doExit = true;
            }

        } catch (Exception e) {
            return e.getMessage();
        }

        return toPrint;

    }

    protected String greet() {
        return Ui.intro(taskList);
    }

}
