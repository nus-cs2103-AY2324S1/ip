package duke.parser;

import duke.data.exception.DukeException;

public class Parser {

    /**
     * Obtains instruction from the user input.
     *
     * @param input the user input.
     * @return The instruction.
     */
    public String getInstruction(String input) {
        String[] command = input.split(" ");
        return command[0];
    }

    public String[] splitInputFromFile(String input) {
        return input.split(" \\| ");
    }

    /**
     * Obtains description of a ToDo initilization string.
     *
     * @param input the user input.
     * @return The description.
     * @throws DukeException If description is missing.
     */
    public String getTodoDescription(String input) throws DukeException {
        if (input.split(" ", 2).length <= 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return input.split(" ", 2)[1];
    }

    /**
     * Obtains description of a Deadline initilization string.
     *
     * @param input the user input.
     * @return The description.
     * @throws DukeException If description is missing.
     */
    public String getDeadlineDescription(String input) throws DukeException {
        if (input.split("/by", 2).length <= 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        return input.split(" ", 2)[1].split(" /by ")[0];
    }

    /**
     * Obtains deadline date of a Deadline initilization string.
     *
     * @param input the user input.
     * @return The deadline date.
     * @throws DukeException If date format is wrong.
     */
    public String getBy(String input) throws DukeException {
        if (input.split("/by", 2).length <= 1) {
            throw new DukeException("☹ OOPS!!! You forgot to include the deadline.");
        }
        return input.split(" ", 2)[1].split(" /by ")[1];
    }

    /**
     * Obtains description of an Event initilization string.
     *
     * @param input the user input.
     * @return The description.
     * @throws DukeException If description is missing.
     */
    public String getEventDescription(String input) throws DukeException {
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1 || spaceIndex + 1 >= input.length()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        return input.split(" ", 2)[1].split(" /from")[0];
    }

    /**
     * Obtains start date of an Event initilization string.
     *
     * @param input the user input.
     * @return The start date.
     * @throws DukeException If date format is wrong.
     */
    public String getFrom(String input) throws DukeException {
        if (input.split("/").length <= 2) {
            throw new DukeException("☹ OOPS!!! Missing event start date/time.");
        }
        return input.split(" ", 2)[1].split(" /from ")[1].split(" /")[0];
    }

    /**
     * Obtains end date of an Event initilization string.
     *
     * @param input the user input.
     * @return The end date.
     * @throws DukeException If date format is wrong.
     */
    public String getTo(String input) throws DukeException {
        if (input.split("/").length <= 2) {
            throw new DukeException("☹ OOPS!!! Missing event start date/time.");
        }
        return input.split(" ", 2)[1].split(" /from ")[1].split("/to ")[1];
    }

    /**
     * Obtains task number from user input.
     *
     * @param input the user input.
     * @return The task number.
     */
    public int getTaskNumber(String input) {
        String result = input.split(" ")[1];
        return Integer.parseInt(result);
    }

}

