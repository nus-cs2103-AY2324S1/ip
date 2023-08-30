package duke;

public class Parser {
    private String[] words;
    public Parser(String input) {
        // Split string into first word and remaining words
        this.words = input.split(" ", 2);
    }

    public String getCommand() {
        return this.words[0];
    }

    public String[] getTodoTask() throws DukeException {
        if (this.words.length == 1 || this.words[1].isBlank()) {
            throw new DukeException(ExceptionTypes.INCOMPLETECOMMANDTODO);
        }
        return this.words;
    }

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
            throw new DukeException(ExceptionTypes.INVALIDCOMMANDDEADLINE);
        }
    }

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
            throw new DukeException(ExceptionTypes.INVALIDCOMMANDEVENT);
        }
    }

    public int getTaskNumber() throws DukeException {
        if (this.words.length == 1 || this.words[1].isBlank()) {
            throw new DukeException(ExceptionTypes.INCOMPLETETASKNUMBER);
        }
        return Integer.parseInt(words[1]);
    }

    public String getSearchKeyword() throws DukeException {
        if (this.words.length == 1 || this.words[1].isBlank()) {
            throw new DukeException(ExceptionTypes.EMPTYKEYWORD);
        }
        return this.words[1];
    }
}
