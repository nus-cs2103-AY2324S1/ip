package command;

import exception.DukeException;

public interface Command {
    public void execute(String input) throws DukeException;
}
