package corgi.storage;

/**
 * An interface representing a list of storable items.
 *
 * @param <T> The type of storable items that the list contains.
 */
public interface StorableList<T extends Storable<T>> {
    /**
     * Retrieves a storable string representation of the items in the list.
     *
     * @return The storable string representation of the items.
     */
    public String getStorableString();
}
