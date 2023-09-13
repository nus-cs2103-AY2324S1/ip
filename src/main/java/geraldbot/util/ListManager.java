package geraldbot.util;

import java.util.ArrayList;

/**
 * Represents a list of items.
 *
 * @param <T> The type of items in the list.
 */
public abstract class ListManager<T> {
    protected final ArrayList<T> itemList;

    /**
     * Constructs a ListManager with the given list of items.
     *
     * @param itemList The initial list of items.
     */
    public ListManager(ArrayList<T> itemList) {
        this.itemList = itemList;
        assert this.itemList != null : "Item list cannot be null.";
    }

    /**
     * Adds an item to the list.
     *
     * @param item The item to be added.
     */
    public void add(T item) {
        assert item != null : "Item to be added cannot be null.";
        itemList.add(item);
    }

    /**
     * Removes and returns the item at the specified index.
     *
     * @param itemIdx The index of the item to be removed.
     * @return The removed item.
     */
    public T remove(int itemIdx) {
        assert itemIdx >= 0 && itemIdx < itemList.size() : "Invalid item index.";
        return itemList.remove(itemIdx);
    }

    /**
     * Returns the item at the specified index.
     *
     * @param itemIdx The index of the item to be retrieved.
     * @return The item at the specified index.
     */
    public T get(int itemIdx) {
        assert itemIdx >= 0 && itemIdx < itemList.size() : "Invalid item index.";
        return itemList.get(itemIdx);
    }

    /**
     * Returns the number of items in the list.
     *
     * @return The number of items in the list.
     */
    public int size() {
        return itemList.size();
    }
}
