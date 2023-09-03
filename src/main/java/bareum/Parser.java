package bareum;

import bareum.commands.Command;
import bareum.commands.AddDeadlineCommand;
import bareum.commands.AddEventCommand;
import bareum.commands.AddTodoCommand;
import bareum.commands.ByeCommand;
import bareum.commands.DeleteCommand;
import bareum.commands.IncorrectCommand;
import bareum.commands.ListCommand;
import bareum.commands.MarkCommand;
import bareum.commands.UnmarkCommand;

public class Parser {
    public Parser() {

    }

    Command parse(String input) throws BareumException {
        String[] commandInputs = input.split(" ", 2);
        Command cmd = null;
        if (commandInputs[0].equals("bye")) {
            return new ByeCommand();
        } else if (commandInputs[0].equals("list")) {
            // exception for no index
            cmd = new ListCommand();
        } else if (commandInputs[0].equals("mark")) {
            int index = Integer.parseInt(commandInputs[1]) - 1;
            cmd = new MarkCommand(index);
        } else if (commandInputs[0].equals("unmark")) {
            int index = Integer.parseInt(commandInputs[1]) - 1;
            cmd = new UnmarkCommand(index);
        } else if (commandInputs[0].equals("delete")) {
            int index = Integer.parseInt(commandInputs[1]) - 1;
            cmd = new DeleteCommand(index);
        } else if (commandInputs[0].equals("todo")) {
            cmd = new AddTodoCommand(commandInputs);
        } else if (commandInputs[0].equals("deadline")){
            cmd = new AddDeadlineCommand(commandInputs);
        } else if (commandInputs[0].equals("event")) {
            cmd = new AddEventCommand(commandInputs);
        } else {
            cmd = new IncorrectCommand();
        }

        return cmd;
    }
}
