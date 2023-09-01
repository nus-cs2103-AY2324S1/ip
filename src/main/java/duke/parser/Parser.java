package duke.parser;

import duke.data.exception.DukeException;

public class Parser {
    public String getInstruction(String input) {
        String[] command = input.split(" ");
        return command[0];
    }

    public String getDescription(String input) {
        String[] command = input.split(" ");
        return command[1];
    }

    public String[] splitInputFromFile(String input) {
        return input.split(" \\| ");
    }

    public String getTodoDescription(String input) throws DukeException {
        if (input.split(" ", 2).length <= 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return input.split(" ", 2)[1];
    }

    public String getDeadlineDescription(String input) throws DukeException {
        if (input.split("/by", 2).length <= 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        return input.split(" ", 2)[1].split(" /by ")[0];
    }

    public String getBy(String input) throws DukeException {
        if (input.split("/by", 2).length <= 1) {
            throw new DukeException("☹ OOPS!!! You forgot to include the deadline.");
        }
        return input.split(" ", 2)[1].split(" /by ")[1];
    }

    public String getEventDescription(String input) throws DukeException {
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1 || spaceIndex + 1 >= input.length()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        return input.split(" ", 2)[1].split(" /from")[0];
    }

    public String getFrom(String input) throws DukeException {
        if (input.split("/").length <= 2) {
            throw new DukeException("☹ OOPS!!! Missing event start date/time.");
        }
        return input.split(" ", 2)[1].split(" /from ")[1].split(" /")[0];
    }

    public String getTo(String input) throws DukeException {
        if (input.split("/").length <= 2) {
            throw new DukeException("☹ OOPS!!! Missing event start date/time.");
        }
        return input.split(" ", 2)[1].split(" /from ")[1].split("/to ")[1];
    }

    public int getTaskNumber(String input) {
        String result = input.split(" ")[1];
        return Integer.parseInt(result);
    }

}

