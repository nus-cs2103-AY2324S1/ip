package data.task.builder;

import exception.DukeException;

public interface Builder<T> {
    public T buildFromString(String input) throws DukeException;
}
