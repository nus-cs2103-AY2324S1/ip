package friday;

/**
 * Represents a generic item in the Friday application.
 */
public abstract class Item {
    protected String name;

    /**
     * Constructs a new Item with the specified name.
     *
     * @param name The name or description of the item.
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * Checks if keyword is contained in item description.
     *
     * @param keyWord The keyword to check for.
     * @return whether the keyword is in the item's name.
     */
    public boolean containsKeyWord(String keyWord) {
        String lowerCaseName = this.name.toLowerCase();
        return lowerCaseName.contains(keyWord.toLowerCase());
    }
}
