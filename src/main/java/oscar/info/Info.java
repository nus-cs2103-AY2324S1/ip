package oscar.info;

import java.io.Serializable;

/**
 * Abstract Info superclass that note and task classes inherit from.
 */
public abstract class Info implements Serializable {
    final String description;
    final String type;

    /**
     * Constructor to store description and type of info item.
     * @param d String description.
     * @param t String type, "D" for deadline task, "E" for event task, "N" for note, "T" for todo task
     */
    public Info(String d, String t) {
        this.description = d;
        this.type = t;
    }

    /**
     * Obtains description of info item.
     *
     * @return String description of info item.
     */
    public String getDescription() {
        return this.description;
    }
}
