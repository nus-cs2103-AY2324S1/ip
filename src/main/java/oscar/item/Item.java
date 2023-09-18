package oscar.item;

import java.io.Serializable;

/**
 * Abstract Item superclass that note and task classes inherit from.
 */
public abstract class Item implements Serializable {
    final String description;
    final String type;

    /**
     * Constructor to store description and type of item.
     * @param d String description.
     * @param t String type, "D" for deadline task, "E" for event task, "N" for note, "T" for todo task
     */
    public Item(String d, String t) {
        this.description = d;
        this.type = t;
    }

    /**
     * Obtains description of item.
     *
     * @return String description of item.
     */
    public String getDescription() {
        return this.description;
    }
}
