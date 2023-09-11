package duke.command;

import duke.exception.DukeException;

public interface Command {
    public String execute(String input) throws DukeException;
}
