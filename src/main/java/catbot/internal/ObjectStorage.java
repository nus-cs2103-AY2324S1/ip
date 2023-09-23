package catbot.internal;

import java.util.function.Supplier;

/**
 * Object that stores and retrieves a specific type of object to disk.
 *
 * @param <T> type of object to read and write.
 */
public interface ObjectStorage<T> {

    /**
     * Writes the given object to disk.
     *
     * @param object object to write.
     */
    void write(T object);

    /**
     * Reads and returns a given object if possible, otherwise calls the default supplier.
     *
     * @return stored object if successful, provided object otherwise.
     * @see java.util.Map#getOrDefault for inspiration.
     */
    T readOrDefault();

    /**
     * Provides a default supplier to supply a default value if an issue occurs while reading.
     *
     * @param supplier to provide default value.
     */
    void setDefault(Supplier<T> supplier);

}
