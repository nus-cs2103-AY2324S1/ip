package storage;

/**
 * The Storable interface defines methods for converting objects to storable strings
 * and parsing storable strings to objects.
 *
 * @param <T> The type of object that the storable string can be parsed into.
 */
public interface Storable<T> {
    /**
     * Converts the object to a storable string representation.
     *
     * @return The storable string representation of the object.
     */
    public String toStorableString();

    /**
     * Parses a storable string to create an object of the specified type.
     *
     * @return An object created from the storable string.
     */
    public T parseStorableString();
}
