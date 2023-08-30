package data.task.builder;

import exception.DukeException;

/**
 * Interface for all builders.
 * @param <T> The type of object to be built.
 */
public interface Builder<T> {
    public T buildFromString(String input) throws DukeException;
}
