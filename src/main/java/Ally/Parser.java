package Ally;

import Ally.Commands.*;
import Ally.Exceptions.AllyException;

public class Parser {

    /**
     * Returns the different Commands according to the user input.
     * If there is no input matching the conditions, AllyException will be thrown.
     *
     * @param ipt
     * @return
     * @throws AllyException
     */
    public static Commands parse(String ipt) throws AllyException {
        String[] split = ipt.split(" ", 2);
        if (split[0].equals("bye")) {
            return new ExitCommand();
        } else if (split[0].equals("list")) {
            return new ListCommand();
        } else if (split[0].equals("todo")) {
            return new AddTodoCommand(split[1]);
        } else if (split[0].equals("deadline")) {
            String[] splits1 = split[1].split(" /");
            return new AddDeadlineCommand(splits1[0], splits1[1]);
        } else if (split[0].equals("event")) {
            String[] splits2 = split[1].split(" /");
            return new AddEventCommand(splits2[0], splits2[1], splits2[2]);
        } else if (split[0].equals("mark")) {
            return new MarkCommand(Integer.parseInt(split[1]) - 1);
        } else if (split[0].equals("unmark")) {
            return new UnmarkCommand(Integer.parseInt(split[1]) - 1);
        } else if (split[0].equals("delete")) {
            return new DeleteCommand(Integer.parseInt(split[1]) - 1);
        } else if (split[0].equals(("find"))) {
            return new FindCommand(split[1]);
        } else {
            throw new AllyException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
