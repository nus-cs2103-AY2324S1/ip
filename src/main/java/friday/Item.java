package friday;

/**
 * Represents a generic item in the Friday application.
 */
public abstract class Item {
    protected String itemName;

    /**
     * Constructs a new Item with the specified name.
     *
     * @param itemName The name or description of the item.
     */
    public Item(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Checks if keyword is contained in item description.
     *
     * @param keyWord The keyword to check for.
     * @return whether the keyword is in the item's name.
     */
    public boolean containsKeyWord(String keyWord) {
        String lowerCaseName = this.itemName.toLowerCase();
        return lowerCaseName.contains(keyWord.toLowerCase());
    }
}
