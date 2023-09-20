package services.tagging;

/**
 * Represents an object that can be tagged.
 */
public interface Taggable {
    /**
     * Adds tags to the object.
     *
     * @param tagName The list of names of the tags to be added.
     */
    void addTags(String[] tagName);

    /**
     * Deletes tags from the object.
     *
     * @param tagName The list of names of the tags to be deleted.
     */
    void deleteTags(String[] tagName);

    /**
     * Shows all tags of the object.
     *
     * @return A string representation of all tags of the object.
     */
    String showAllTags();
}
