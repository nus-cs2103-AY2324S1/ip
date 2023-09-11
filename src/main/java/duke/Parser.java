package duke;

/**
 * Represents a parser that parses the user input.
 */
public class Parser {
    private String[] words;

    /**
     * A constructor for a parser.
     *
     * @param input the user input.
     */
    public Parser(String input) {
        // Split string into first word and remaining words
        this.words = input.split(" ", 2);
    }

    /**
     * Returns the command word of the user input.
     *
     * @return the command word.
     */
    public String getCommand() {
        return this.words[0];
    }

    public String[] getTodoTask() throws DukeException {
        if (this.words.length == 1 || this.words[1].isBlank()) {
            throw new DukeException(ExceptionTypes.INCOMPLETECOMMANDTODO);
        }
        return new String[]{this.words[1]};
    }

    /**
     * Returns the description of the deadline task.
     *
     * @return the description of the deadline task.
     * @throws DukeException if the user input is invalid.
     */
    public String[] getDeadlineTask() throws DukeException {
        if (this.words.length == 1 || this.words[1].isBlank()) {
            throw new DukeException(ExceptionTypes.INCOMPLETECOMMANDDEADLINE);
        } else if (this.words[1].contains("/by")) {
            String[] description = this.words[1].split(" /by ");
            if (description.length <= 1) {
                throw new DukeException(ExceptionTypes.INVALIDCOMMANDDEADLINE);
            }
            return description;
        } else {
            assert this.words.length > 1 && !this.words[1].contains("/by") : "Invalid user input";
            throw new DukeException(ExceptionTypes.INVALIDCOMMANDDEADLINE);
        }
    }

    /**
     * Returns the description of the event task.
     *
     * @return the description of the event task.
     * @throws DukeException if the user input is invalid.
     */
    public String[] getEventTask() throws DukeException {
        if (this.words.length == 1 || this.words[1].isBlank()) {
            throw new DukeException(ExceptionTypes.INCOMPLETECOMMANDEVENT);
        } else if (this.words[1].contains("/from") && this.words[1].contains("/to")) {
            String[] description = this.words[1].split(" /from ");
            if (description.length <= 1) {
                throw new DukeException(ExceptionTypes.INCOMPLETECOMMANDEVENT);
            }
            String[] interval = description[1].split(" /to ");
            if (interval.length <= 1) {
                throw new DukeException(ExceptionTypes.INVALIDCOMMANDDEADLINE);
            }
            return new String[]{description[0], interval[0], interval[1]};
        } else {
            assert this.words.length > 1 && !this.words[1].contains("/from") && !this.words[1].contains("/to")
                    : "Invalid user input";
            throw new DukeException(ExceptionTypes.INVALIDCOMMANDEVENT);
        }
    }

    /**
     * Returns the task number of the task to be marked as done or deleted.
     *
     * @return the task number.
     * @throws DukeException if the user input is invalid.
     */
    public int getTaskNumber() throws DukeException {
        if (this.words.length == 1 || this.words[1].isBlank()) {
            throw new DukeException(ExceptionTypes.INCOMPLETETASKNUMBER);
        }
        assert this.words.length > 1 : "Invalid user input";
        if (words[1].contains(" ")) {
            String[] taskNumber = words[1].split(" ");
            return Integer.parseInt(taskNumber[0]);
        }
        return Integer.parseInt(words[1]);
    }

    /**
     * Returns the keyword to search for.
     *
     * @return the keyword.
     * @throws DukeException if the user input is invalid.
     */
    public String getSearchKeyword() throws DukeException {
        if (this.words.length == 1 || this.words[1].isBlank()) {
            throw new DukeException(ExceptionTypes.EMPTYKEYWORD);
        }
        assert this.words.length > 1 : "Invalid user input";
        return this.words[1];
    }
}
