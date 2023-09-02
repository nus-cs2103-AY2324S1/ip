package parser;

import data.exception.DukeException;

public class Parser {
    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        TODO,
        DEADLINE,
        EVENT
    }

    public Command parse(String input) throws DukeException {
        String[] inputSplit = input.split(" ");
        Command command = this.getCommand(input);
        switch(command) {
        case BYE:
            return Command.BYE;
        case LIST:
            return Command.LIST;
        case MARK:
            if (inputSplit.length == 1) {
                throw new DukeException("Input the task number");
            } else if (inputSplit.length > 2) {
                throw new DukeException("Invalid command");
            }  else {
                return Command.MARK;
            }
        case UNMARK:
            if (inputSplit.length == 1) {
                throw new DukeException("Input the task number");
            } else if (inputSplit.length > 2) {
                throw new DukeException("Invalid command");
            }  else {
                return Command.UNMARK;
            }
        case DELETE:
            if (inputSplit.length == 1) {
                throw new DukeException("Input the task number");
            } else if (inputSplit.length > 2) {
                throw new DukeException("Invalid command");
            } else {
                return Command.DELETE;
            }
        case FIND:
            if (input.length() == 4) {
                throw new DukeException("Input something to search for.");
            }
            return Command.FIND;
        case TODO:
            if (input.length() == 4 || inputSplit.length == 1) {
                throw new DukeException("Description of a todo cannot be empty");
            }
            return Command.TODO;
        case DEADLINE:
            if (input.length() == 8 || inputSplit.length == 1) {
                throw new DukeException("Description of a deadline cannot be empty");
            }
            return Command.DEADLINE;
        case EVENT:
            if (input.length() == 5 || inputSplit.length == 1) {
                throw new DukeException("Description of an event cannot be empty");
            }
            return Command.EVENT;
        }
        throw new DukeException("No such command.");
    }

    private Command getCommand(String input) throws DukeException {
        if (input.equals("bye")) {
            return Command.BYE;
        }
        if (input.equals("list")) {
            return Command.LIST;
        }
        if (input.startsWith("mark")) {
            return Command.MARK;
        }
        if (input.startsWith("unmark")) {
            return Command.UNMARK;
        }
        if (input.startsWith("delete")) {
            return Command.DELETE;
        }
        if (input.startsWith("find")) {
            return Command.FIND;
        }
        if (input.startsWith("todo")) {
            return Command.TODO;
        }
        if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        }
        if (input.startsWith("event")) {
            return Command.EVENT;
        }
        throw new DukeException("I don't understand your command.");
    }
}
