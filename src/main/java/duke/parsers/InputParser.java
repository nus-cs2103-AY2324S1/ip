package duke.parsers;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.tasks.*;

/**
 * Parses the input and executes respective command
 */
public class InputParser {
    private TaskList taskList;

    /**
     * Create new InputParser Object
     *
     * @param taskList updates taskList after every command to be saved at the end
     */
    public InputParser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * parses user input
     *
     * @param input
     * @param toStore
     * @return String "exit app" if user types in "bye", else returns respective response to input
     */
    public String parse(String input, boolean toStore) {
        String[] splitStr = input.trim().split("\\s+");
        String reply = "";
        Command command;

        try {
            if (input.equals("bye")) {
                command = new ByeCommand(taskList, toStore);
            } else if (input.equals("list")) {
                command = new ListCommand(taskList);
            } else if (splitStr[0].equals("todo")) {
                command = new TodoCommand(input, taskList);
            } else if (splitStr[0].equals("deadline")) {
                command = new DeadlineCommand(input, taskList);
            } else if (splitStr[0].equals("event")) {
                command = new EventCommand(input, taskList);
            } else if (splitStr[0].equals("find")) {
                command = new FindCommand(input, taskList);
            } else if (splitStr[0].equals("mark") || splitStr[0].equals("unmark")) {
                command = new MarkCommand(input, taskList);
            } else if (splitStr[0].equals("delete")) {
                command = new DeleteCommand(input, taskList);
            } else if (splitStr[0].equals("tag")) {
                command = new TagCommand(input, taskList);
            } else {
                command = new UnknownCommand(taskList);
            }
            reply = command.execute();
        } catch (DukeException e) {
            reply += e.getMessage();
        }

        return reply;
    }
}
