package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.StartCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.tasklist.TaskList;

/**
 * duke.Parser class to decipher the input of the chatbot and call the correct command.
 */
public class Parser {

    TaskList taskList;

    /**
     * Contructor for the parser class.
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Method to parse the string input of the chatbot and call the correct command.
     *
     * @param inp Line of input from the CLI.
     * @return Command object that will be executed.
     */
    public Command parse(String inp) {
        inp = inp.toUpperCase();
        String [] parts = inp.split(" ", 2);
        String check = parts[0];
        String numberString = parts.length > 1 ? parts[1] : "";
        switch (check) {
            case "BYE":
                Command byeCommand = new ExitCommand(taskList);
                return byeCommand;
            case "HI":
                Command startCommand = new StartCommand(taskList);
                return startCommand;
            case "LIST":
                Command listCommand = new ListCommand(taskList);
                return listCommand;
            case "MARK":
                Command markCommand = new MarkCommand(taskList, numberString);
                return markCommand;
            case "UNMARK":
                Command unmarkCommand = new UnmarkCommand(taskList, numberString);
                return unmarkCommand;
            case "DELETE":
                Command deleteCommand = new DeleteCommand(taskList, numberString);
                return deleteCommand;
            case "TODO":
                Command toDoCommand = new ToDoCommand(taskList, inp);
                return toDoCommand;
            case "EVENT":
                Command eventCommand = new EventCommand(taskList, inp);
                return eventCommand;
            case "DEADLINE":
                Command deadLineCommand = new DeadlineCommand(taskList, inp);
                return deadLineCommand;
            default:
                throw new DukeException("I do not know what you are saying.");
        }
    }

}
