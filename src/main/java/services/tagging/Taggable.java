package services.tagging;

/**
 * Represents an object that can be tagged.
 */
public interface Taggable {
    void addTags(String[] tagName);
    void deleteTags(String[] tagName);
    String showAllTags();
}
