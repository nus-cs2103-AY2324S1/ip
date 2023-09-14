package catbot.internal;

import java.util.function.Supplier;

/**
 * Object that stores and retrieves a specific type of object to disk.
 *
 * @param <T> type of object to read and write.
 */
public interface ObjectStorage<T> {

    /**
     * Write the given object.
     *
     * @param object object to write.
     */
    void write(T object);

    /**
     * Read and return a given object if possible, otherwise call the default supplier.
     *
     * @return stored object if successful, provided object otherwise.
     * @see java.util.Map#getOrDefault for inspiration.
     */
    T readOrDefault();

    /**
     * Provide a default supplier to provide a default value if an issue occurs while reading.
     *
     * @param supplier to provide default value.
     */
    void setDefault(Supplier<T> supplier);

}
