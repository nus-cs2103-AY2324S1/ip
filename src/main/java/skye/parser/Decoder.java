package skye.parser;

import skye.data.exception.DukeException;

/**
 * Represents a contract that defines common behaviour for decoder objects.
 * @param <T>
 */
public interface Decoder<T> {
    T decode(String line) throws DukeException;
}
