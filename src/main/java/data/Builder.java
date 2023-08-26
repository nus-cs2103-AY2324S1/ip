package data;

import exception.DukeException;

public interface Builder<T> {
    public T buildFromString(String input) throws DukeException;   
}
