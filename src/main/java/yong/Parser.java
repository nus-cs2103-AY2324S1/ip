package yong;

import yong.command.Command;
import yong.command.DeadlineCommand;
import yong.command.DeleteCommand;
import yong.command.EventCommand;
import yong.command.ExitCommand;
import yong.command.ListCommand;
import yong.command.MarkCommand;
import yong.command.StartCommand;
import yong.command.ToDoCommand;
import yong.command.FindCommand;
import yong.command.UnmarkCommand;


import yong.exception.DukeException;

import yong.tasklist.TaskList;

/**
 * Parser class to decipher the input of the chatbot and call the correct command.
 */
public class Parser {

    private TaskList taskList;

    /**
     * Constructor for the parser class.
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

        checkNumberInput(numberString);

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
        case "FIND":
            Command findCommand = new FindCommand(taskList, inp);
            return findCommand;
        default:
            throw new DukeException("I do not know what you are saying.");
        }
    }

    private void checkNumberInput(String s) throws DukeException {
        if (s.equals("")) {
            throw new DukeException("This is an invalid input");
        }
        Integer number = Integer.parseInt(s);
        if (number >= taskList.size()) {
            throw new DukeException("This is a bad number");
        }
    }
}
